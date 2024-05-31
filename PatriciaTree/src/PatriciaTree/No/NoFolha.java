package PatriciaTree.No;

import PatriciaTree.No.No;

public class NoFolha extends No {
    public NoFolha(String palavra) {
        super(palavra);
    }

    @Override
    public boolean isFolha() {
        return true;
    }
}
