package PatriciaTree;

import PatriciaTree.ED.Elemento;
import PatriciaTree.ED.Fila.Fila;
import PatriciaTree.ED.Pilha.Pilha;
import PatriciaTree.No.No;
import PatriciaTree.No.NoFolha;
import PatriciaTree.No.NoLig;

public class PatriciaTree {

    private NoLig raiz;

    public void insere(String palavra){
        No novoNo = new NoFolha(palavra);
        No ant, atual;

        if(raiz == null){
            inicializarRaiz(novoNo);
        }
        else {
            int posPrimeiraLetra = raiz.buscarLetra(palavra.charAt(0));
            if(posPrimeiraLetra == -1){
                raiz.inserirNovaLetra(palavra.charAt(0), novoNo);
            }

            else{
                NoLig intermediario = new NoLig();
                boolean inseriu = false;
                int i = 1, posLetra = posPrimeiraLetra;
                ant = raiz;
                atual = ((NoLig) ant).getLig(posPrimeiraLetra);
                while (!atual.isFolha() && ((NoLig) atual).getIndex() <= palavra.length() && !inseriu){
                    if(((NoLig) atual).getIndex() == i){
                        posLetra =  ((NoLig) atual).buscarLetra(palavra.charAt(i-1));
                        if(posLetra == -1){
                            ((NoLig) atual).inserirNovaLetra(palavra.charAt(i-1), novoNo);
                            inseriu = true;
                        }
                        else{
                            ant = atual;
                            atual =  ((NoLig) atual).getLig(posLetra);
                            i++;
                        }
                    }
                    else{
                        String filhoParaComparar = acharFilhoParaComparar(atual, palavra,
                                ((NoLig) atual).getIndex()-1);
                        int diferenca = StringAux.charDiferenteIndex(filhoParaComparar, palavra);
                        if(diferenca <  ((NoLig) atual).getIndex()-1){
                            intermediario.setIndex(diferenca+1);
                            intermediario.inserirNovaLetra(filhoParaComparar.charAt(diferenca), atual);
                            intermediario.inserirNovaLetra(palavra.charAt(diferenca), novoNo);
                            ((NoLig) ant).setLig(posLetra, intermediario);
                            inseriu = true;
                        }
                        else if(diferenca >= ((NoLig) atual).getIndex()-1){
                            posLetra = ((NoLig) atual).buscarLetra(palavra.charAt(((NoLig) atual).getIndex()-1));
                            if(posLetra == -1){
                                ((NoLig) atual).inserirNovaLetra(palavra.charAt(((NoLig) atual).getIndex()-1), novoNo);
                                inseriu = true;
                            }
                            else{
                                if(((NoLig) atual).getLig(0) == null){
                                    repartePalavra(((NoLig) atual), novoNo, posLetra);
                                    inseriu = true;
                                }
                                else{
                                    ant = atual;
                                    atual = ((NoLig) atual).getLig(posLetra);
                                }
                            }
                        }
                    }
                }
                if(atual.isFolha() && !inseriu){
                    repartePalavra(((NoLig) ant), novoNo,
                            ((NoLig) ant).buscarLetra(palavra.charAt(((NoLig) ant).getIndex()-1)));
                }
                else if(((NoLig) atual).getIndex() > palavra.length() && !inseriu){
                    intermediario.inserirNovaLetra(palavra.charAt(palavra.length() - 1), atual);
                    intermediario.setIndex(palavra.length());

                    atual.setPalavra(palavra);

                    int posInicio =  ((NoLig) ant).buscarLetra(palavra.charAt(((NoLig) ant).getIndex() - 1));
                    ((NoLig) ant).setLig(posInicio, intermediario);
                    intermediario.setLig(  intermediario.buscarLetra(palavra.charAt(palavra.length() - 1)), atual);
                }

            }
        }
    }

    private String acharFilhoParaComparar(No raiz, String palavra, int index){
        if(index < palavra.length() && !raiz.isFolha()){
            char base = palavra.charAt(index);
            while (!raiz.isFolha() && ((NoLig) raiz).buscarLetra(base) >= 0 && index < palavra.length()){
                raiz = ((NoLig) raiz).getLig(((NoLig) raiz).buscarLetra(base));
                base = palavra.charAt(index);
                index++;
            }
        }
        while(!raiz.isFolha()){
            raiz = ((NoLig) raiz).getLig(0);
        }
        return raiz.getPalavra();
    }

    private void repartePalavra(NoLig raiz, No novo, int pos){
        No atual = raiz.getLig(pos);
        int posDiferente = StringAux.charDiferenteIndex(novo.getPalavra(), atual.getPalavra());
        NoLig intermediario = new NoLig();

        if(posDiferente == novo.getPalavra().length()){
            intermediario.setPalavra(novo.getPalavra());
            intermediario.inserirNovaLetra(atual.getPalavra().charAt(posDiferente), atual);
        }
        else if (posDiferente == atual.getPalavra().length()){
            intermediario.setPalavra(atual.getPalavra());
            intermediario.inserirNovaLetra(novo.getPalavra().charAt(posDiferente), novo);
        }
        else{
            intermediario.inserirNovaLetra(novo.getPalavra().charAt(posDiferente), novo);
            intermediario.inserirNovaLetra(atual.getPalavra().charAt(posDiferente), atual);
        }
        intermediario.setIndex(posDiferente + 1);
        raiz.setLig(pos, intermediario);
    }

    private void inicializarRaiz(No novoNo){
        raiz = new NoLig();
        raiz.setIndex(1);
        raiz.setLetra(0, novoNo.getPalavra().charAt(0));
        raiz.setLig(0, novoNo);
        raiz.setTL(1);
    }

    public boolean buscarPalavra(String palavra){
        No aux = raiz;
        boolean achou = false;
        boolean naoAchou = false;
        while(!aux.isFolha() && !achou && !naoAchou){
            if(aux.getPalavra() != null && aux.getPalavra().compareTo(palavra) == 0){
                achou = true;
            }
            else{
                if(((NoLig)aux).getIndex() > palavra.length()){
                    naoAchou = true;
                }
                else{
                    int pos = ((NoLig)aux).buscarLetra(palavra.charAt((((NoLig)aux).getIndex()-1)));
                    if(pos == -1){
                        naoAchou = true;
                    }
                    else{
                        aux = ((NoLig) aux).getLig(pos);
                    }
                }
            }
        }
        if(aux.isFolha()){
            return aux.getPalavra().compareTo(palavra) == 0;
        }
        else{
            return achou;
        }
    }

    public void exibirIterativo(){
        No aux;
        Pilha pilha = new Pilha();
        pilha.push(raiz);
        while(!pilha.isEmpty()){
            aux = pilha.pop();
            if(aux.getPalavra() != null){
                System.out.println(aux.getPalavra());
            }
            if(!aux.isFolha()){
                //De trás para frente para ficar em ordem alfabética
                for(int i = ((NoLig) aux).getTL() - 1; i >= 0; i--){
                    pilha.push(((NoLig) aux).getLig(i));
                }
            }
        }
    }

    public void exibirNivelANivel(){
        Elemento aux;
        int nivelAtual = 0;
        Fila fila = new Fila();
        fila.enqueue(raiz, 1);

        while (!fila.isEmpty()){
            aux = fila.dequeue();
            if(aux.getNivel() != nivelAtual){
                nivelAtual = aux.getNivel();
                System.out.print("\n\nNivel " + nivelAtual + ": \n");
            }
            System.out.print("<-- ");
            if(aux.getNo().isFolha()){
                System.out.print("Folha: " + aux.getNo().getPalavra() + " ");
            }
            else{
                NoLig auxlig = (NoLig) aux.getNo();
                System.out.print("Indice: "+auxlig.getIndex());
                for (int i = 0; i < auxlig.getTL(); i++){
                    System.out.print(" | Letra: " + auxlig.getLetra(i));
                    fila.enqueue(auxlig.getLig(i), nivelAtual + 1);
                }
                if(auxlig.getPalavra() != null){
                    System.out.print(" | Palavra: " + auxlig.getPalavra());
                }
            }
            System.out.print(" -->\t");
        }
        System.out.println();
    }


    public void exibirR(){
        exibirRecursivo(raiz);
    }

    private void exibirRecursivo(No raiz){
        if(raiz.isFolha()){
            System.out.println(raiz.getPalavra());
        }
        else{
            NoLig aux = (NoLig) raiz;
            if(aux.getPalavra() != null){
                System.out.println(aux.getPalavra());
            }
            for(int i = aux.getTL(); i >= 0; i--){
                exibirRecursivo(((NoLig) raiz).getLig(i));
            }
        }
    }

}
