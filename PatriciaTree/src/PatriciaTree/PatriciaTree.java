package PatriciaTree;

public class PatriciaTree {

    private No raiz;

//    public void insere(String palavra){
//        inserePalavra(raiz, palavra);
//    }
//
//    private void inserePalavra(No raiz, String p){
//        String palavra = p.toLowerCase();
//        No novoNo = new No(palavra);
//
//        if (raiz == null){
//            inicializarRaiz(novoNo);
//        }
//        else{
//            No intermediario = new No();
//            int posInicio = raiz.buscarLetra(palavra.charAt(raiz.getIndex() - 1));
//
//            //Caso ainda nao exista a letra na raiz
//            if (posInicio == -1){
//                raiz.inserirNovaLetra(palavra.charAt(raiz.getIndex() - 1), novoNo);
//            }
//
//            //Caso exista a letra na raiz
//            else {
//                No atual = raiz.getLig(posInicio);
//
//                if (atual.getPalavra() != null && atual.getTL() == 0) {
//
//                    repartePalavra(raiz, novoNo, posInicio);
//                }
//                else {
//                    if (StringAux.charDiferenteIndex(palavra, atual.getLig(0).getPalavra()) < raiz.getIndex() - 1) {
//                        intermediario.setPalavra(palavra);
//                        intermediario.setIndex(StringAux.charDiferenteIndex(palavra, atual.getLig(0).getPalavra()));
//                        intermediario.inserirNovaLetra(
//                                palavra.charAt(StringAux.charDiferenteIndex(palavra,
//                                        atual.getLig(0).getPalavra())), atual);
//                    }
//
//                    if (palavra.length() < atual.getIndex()) {
//
//                        intermediario.inserirNovaLetra(palavra.charAt(palavra.length() - 1), atual);
//                        intermediario.setIndex(palavra.length());
//
//                        atual.setPalavra(palavra);
//
//                        raiz.setLig(posInicio, intermediario);
//                        intermediario.setLig(intermediario.buscarLetra(palavra.charAt(palavra.length() - 1)), atual);
//                    } else {
//                        inserePalavra(atual, palavra);
//                    }
//              }
//            }
//        }
//
//    }

    public void insere(String palavra){
        No novoNo = new No(palavra);
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
                No intermediario = new No();
                boolean inseriu = false;
                int i = 1, posLetra = posPrimeiraLetra;
                ant = raiz;
                atual = ant.getLig(posPrimeiraLetra);
                while (atual.getLig(0) != null && atual.getIndex() <= palavra.length() && !inseriu){
                    if(atual.getIndex() == i){
                        posLetra = atual.buscarLetra(palavra.charAt(i-1));
                        if(posLetra == -1){
                            atual.inserirNovaLetra(palavra.charAt(i-1), novoNo);
                            inseriu = true;
                        }
                        else{
                            ant = atual;
                            atual = atual.getLig(posLetra);
                            i++;
                        }
                    }
                    else{
                        String filhoParaComparar = acharFilhoParaComparar(atual, palavra, atual.getIndex()-1);
                        int diferenca = StringAux.charDiferenteIndex(filhoParaComparar, palavra);
                        if(diferenca < atual.getIndex()-1){
                            intermediario.setIndex(diferenca+1);
                            intermediario.inserirNovaLetra(filhoParaComparar.charAt(diferenca), atual);
                            intermediario.inserirNovaLetra(palavra.charAt(diferenca), novoNo);
                            ant.setLig(posLetra, intermediario);
                            inseriu = true;
                        }
                        else if(diferenca >= atual.getIndex()-1){
                            posLetra = atual.buscarLetra(palavra.charAt(atual.getIndex()-1));
                            if(posLetra == -1){
                                atual.inserirNovaLetra(palavra.charAt(atual.getIndex()-1), novoNo);
                                inseriu = true;
                            }
                            else{
                                if(atual.getLig(0) == null){
                                    repartePalavra(atual, novoNo, posLetra);
                                    inseriu = true;
                                }
                                else{
                                    ant = atual;
                                    atual = atual.getLig(posLetra);
                                }
                            }
                        }
                    }
                }
                if(atual.getLig(0) == null && !inseriu){
                    repartePalavra(ant, novoNo, ant.buscarLetra(palavra.charAt(ant.getIndex()-1)));
                }
                else if(atual.getIndex() > palavra.length() && !inseriu){
                    intermediario.inserirNovaLetra(palavra.charAt(palavra.length() - 1), atual);
                    intermediario.setIndex(palavra.length());

                    atual.setPalavra(palavra);

                    int posInicio = ant.buscarLetra(palavra.charAt(ant.getIndex() - 1));
                    ant.setLig(posInicio, intermediario);
                    intermediario.setLig(intermediario.buscarLetra(palavra.charAt(palavra.length() - 1)), atual);
                }

            }
        }
    }

    private String acharFilhoParaComparar(No raiz, String palavra, int index){
        if(index < palavra.length()){
            char base = palavra.charAt(index);
            while (raiz.getLig(0) != null && raiz.buscarLetra(base) >= 0 && index < palavra.length()){
                raiz = raiz.getLig(raiz.buscarLetra(base));
                base = palavra.charAt(index);
                index++;
            }
        }
        while(raiz.getLig(0) != null){
            raiz = raiz.getLig(0);
        }
        return raiz.getPalavra();
    }

    private void repartePalavra(No raiz, No novo, int pos){
        No atual = raiz.getLig(pos);
        int posDiferente = StringAux.charDiferenteIndex(novo.getPalavra(), atual.getPalavra());
        No intermediario = new No();

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
        raiz = new No();
        raiz.setIndex(1);
        raiz.setLetra(0, novoNo.getPalavra().charAt(0));
        raiz.setLig(0, novoNo);
        raiz.setTL(1);
    }

}
