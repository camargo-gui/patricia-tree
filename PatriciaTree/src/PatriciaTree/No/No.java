package PatriciaTree.No;

public abstract class No {
    private String palavra;

    public No(){
        this.palavra = null;
    }

    public No(String palavra){
        this.palavra = palavra;
    }

    public String getPalavra() {
        return palavra;
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }

    public abstract boolean isFolha();
}
