package PatriciaTree;

public class No {
    private String palavra;
    private char [] vLetra;
    private No [] vLig;
    private int index;
    private int TL;

    public No(){
        TL = 0;
        vLig = new No[26];
        vLetra = new char[26];
        palavra = null;
    }

    public No(String palavra){
        this();
        this.palavra = palavra;
        index = 0;
    }

    public int buscarLetra(char letra){
        int i = 0;
        char aux = vLetra[i++];
        while(i < TL && aux < letra)
            aux = vLetra[i++];

        if(aux == letra)
            return i - 1;
        else
            return -1;
    }

    public void inserirNovaLetra(char letra, No lig) {
        int i = TL - 1;
        while (i >= 0 && vLetra[i] > letra) {
            vLetra[i + 1] = vLetra[i];
            vLig[i + 1] = vLig[i];
            i--;
        }

        vLetra[i + 1] = letra;
        vLig[i + 1] = lig;

        TL++;
    }


    public String getPalavra() {
        return palavra;
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }

    public char getLetra(int i) {
        return vLetra[i];
    }

    public void setLetra(int i, char letra) {
        vLetra[i] = letra;
    }

    public No getLig(int i) {
        return vLig[i];
    }

    public void setLig(int i, No lig) {
        vLig[i] = lig;
    }
    public int getIndex(){
        return index;
    }

    public void setIndex(int i){
        index = i;
    }

    public int getTL() {
        return TL;
    }

    public void setTL(int TL) {
        this.TL = TL;
    }
}
