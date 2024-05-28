package PatriciaTree;

public class PatriciaTree {

    private No raiz;

    public void insere(String palavra){
        inserePalavra(raiz, palavra);
    }

    private void inserePalavra(No raiz, String p){
        String palavra = p.toLowerCase();
        No novoNo = new No(palavra);

        if (raiz == null){
            inicializarRaiz(novoNo);
        }
        else{
            No intermediario = new No();
//            String compararFilho = acharFilhoParaComparar(raiz);
//            int diferenca = StringAux.charDiferenteIndex(palavra, compararFilho);
//            if(diferenca > raiz.getIndex()-1){
//                intermediario.inserirNovaLetra(palavra.charAt(diferenca), novoNo);
//                intermediario.inserirNovaLetra(compararFilho.charAt(diferenca), );
//
//            }
//            else if (diferenca < raiz.getIndex()-1){
//
//            }
//            else {
            int posInicio = raiz.buscarLetra(palavra.charAt(raiz.getIndex() - 1));

            //Caso ainda nao exista a letra na raiz
            if (posInicio == -1){
                raiz.inserirNovaLetra(palavra.charAt(raiz.getIndex() - 1), novoNo);
            }

            //Caso exista a letra na raiz
            else {
                No atual = raiz.getLig(posInicio);

                if (atual.getPalavra() != null && atual.getTL() == 0) {

                    repartePalavra(raiz, novoNo, posInicio);
                }
                else {
                    if (StringAux.charDiferenteIndex(palavra, atual.getLig(0).getPalavra()) < raiz.getIndex() - 1) {
                        intermediario.setPalavra(palavra);
                        intermediario.setIndex(StringAux.charDiferenteIndex(palavra, atual.getLig(0).getPalavra()));
                        intermediario.inserirNovaLetra(
                                palavra.charAt(StringAux.charDiferenteIndex(palavra,
                                        atual.getLig(0).getPalavra())), atual);
                    }

                    if (palavra.length() < atual.getIndex()) {

                        intermediario.inserirNovaLetra(palavra.charAt(palavra.length() - 1), atual);
                        intermediario.setIndex(palavra.length());

                        atual.setPalavra(palavra);

                        raiz.setLig(posInicio, intermediario);
                        intermediario.setLig(intermediario.buscarLetra(palavra.charAt(palavra.length() - 1)), atual);
                    } else {
                        inserePalavra(atual, palavra);
                    }
              }
            }
        }

    }

    private String acharFilhoParaComparar(No raiz){
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
