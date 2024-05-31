package PatriciaTree.ED;

import PatriciaTree.No.No;

public class Elemento {
    private No no;
    private Elemento proximo;
    private int nivel;

    public Elemento(No no){
        this.no = no;
        this.proximo = null;
    }

    public Elemento(No no, int nivel){
        this(no);
        this.nivel = nivel;
    }

    public No getNo() {
        return no;
    }

    public void setNo(No no) {
        this.no = no;
    }

    public Elemento getProximo() {
        return proximo;
    }

    public void setProximo(Elemento proximo) {
        this.proximo = proximo;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
}
