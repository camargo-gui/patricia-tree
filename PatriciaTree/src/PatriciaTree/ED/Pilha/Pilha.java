package PatriciaTree.ED.Pilha;

import PatriciaTree.ED.Elemento;
import PatriciaTree.No.No;

public class Pilha {
    private Elemento topo;

    public Pilha(){
        this.topo = null;
    }

    public void push(No no){
        Elemento elemento = new Elemento(no);
        elemento.setProximo(topo);
        topo = elemento;
    }

    public No pop(){
        Elemento elemento = topo;
        topo = topo.getProximo();
        return elemento.getNo();
    }

    public boolean isEmpty(){
        return topo == null;
    }
}
