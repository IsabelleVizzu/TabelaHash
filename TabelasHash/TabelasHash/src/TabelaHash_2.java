public class TabelaHash_2 extends TabelaHash {

    public TabelaHash_2(int size) {
        super(size);
    }

    public int calculeHash(String name) {
        int hash = 0;

        for (int i = 0; i < name.length(); i++) {
            hash += (name.charAt(i) * (i + 1));
        }

        return Math.abs(hash % super.getSize());
    }
}