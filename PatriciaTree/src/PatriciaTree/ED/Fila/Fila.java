package PatriciaTree.ED.Fila;

import PatriciaTree.No.No;
import PatriciaTree.ED.Elemento;

public class Fila {
    private Elemento inicio;
    private Elemento fim;

    public Fila(){
        this.inicio = null;
        this.fim = null;
    }

    public void enqueue(No no, int nivel){
        Elemento elemento = new Elemento(no, nivel);
        if (inicio == null){
            inicio = elemento;
            fim = elemento;
        } else {
            fim.setProximo(elemento);
            fim = elemento;
        }
    }

    public Elemento dequeue(){
        Elemento elemento = inicio;
        inicio = inicio.getProximo();
        return elemento;
    }

    public Elemento getInicio(){
        return inicio;
    }

    public boolean isEmpty(){
        return inicio == null;
    }
}
