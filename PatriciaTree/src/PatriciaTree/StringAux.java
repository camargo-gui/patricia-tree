package PatriciaTree;

public class StringAux {
    public static int charDiferenteIndex(String s1, String s2){
        int i = 0;
        while (i < s1.length() && i < s2.length() && s1.charAt(i) == s2.charAt(i))
            i++;
        return i;
    }
}
