import PatriciaTree.PatriciaTree;
import PatriciaTree.Exemplo;

public class Main {
    public static void main(String[] args) {

        PatriciaTree tree = new PatriciaTree();

        Exemplo.InserirPalavrasNaArvore(tree);
        Exemplo.ExibirTodasAsPalavras(tree);
        Exemplo.BuscarPalavrasNaArvore(tree);
        Exemplo.ExibirNivelANivel(tree);
    }
}