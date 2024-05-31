package PatriciaTree;

public class Exemplo {
    public static void InserirPalavrasNaArvore(PatriciaTree tree){
        tree.insere("galo");
        tree.insere("gala");
        tree.insere("galocha");
        tree.insere("galeria");
        tree.insere("gel");
        tree.insere("gelado");
        tree.insere("geladeira");
        tree.insere("gelatina");
        tree.insere("sola");
        tree.insere("solo");
        tree.insere("sol");
        tree.insere("solado");
        tree.insere("soldado");
        tree.insere("somente");
        tree.insere("sombra");
        tree.insere("bolo");
        tree.insere("bola");
        tree.insere("bolero");
        tree.insere("botao");
        tree.insere("botija");
    }

    public static void ExibirTodasAsPalavras(PatriciaTree tree){
        System.out.println("\n<-- Exibição de todas as palavras -->\n");
        tree.exibirIterativo();
    }

    public static void BuscarPalavrasNaArvore(PatriciaTree tree){
        System.out.println("\n<-- Busca de determinadas palavras: -->\n");
        System.out.println("Palavra bola " + StringAchou(tree.buscarPalavra("bola")));
        System.out.println("Palavra bol " + StringAchou(tree.buscarPalavra("bol")));
        System.out.println("Palavra bolero " + StringAchou(tree.buscarPalavra("bolero")));
        System.out.println("Palavra b " + StringAchou(tree.buscarPalavra("b")));
        System.out.println("Palavra galo " + StringAchou(tree.buscarPalavra("galo")));
        System.out.println("Palavra gala " + StringAchou(tree.buscarPalavra("gala")));
        System.out.println("Palavra galocha " + StringAchou(tree.buscarPalavra("galocha")));
        System.out.println("Palavra som " + StringAchou(tree.buscarPalavra("som")));
        System.out.println("Palavra sombra " + StringAchou(tree.buscarPalavra("sombra")));
        System.out.println("Palavra somente " + StringAchou(tree.buscarPalavra("somente")));
        System.out.println("Palavra some " + StringAchou(tree.buscarPalavra("some")));
        System.out.println("Palavra sol " + StringAchou(tree.buscarPalavra("sol")));
        System.out.println("Palavra solda " + StringAchou(tree.buscarPalavra("solda")));
        System.out.println("Palavra soldado " + StringAchou(tree.buscarPalavra("soldado")));
    }

    public static void ExibirNivelANivel(PatriciaTree tree){
        System.out.print("\n<-- Exibição nível a nível: -->");
        tree.exibirNivelANivel();
    }

    private static String StringAchou(boolean achou){
        return achou ? "encontrada" : "não encontrada";
    }
}
