public class TabelaHash_1 extends TabelaHash{

    public TabelaHash_1 (int size) {
        super(size);
    }


    public int calculeHash(String name) {
        int hash = 7;
        int primeNumber = 31;

        for (int i = 0; i < name.length(); i++) {
            hash = hash * primeNumber + name.charAt(i);
        }

        return Math.abs(hash % super.getSize());
    }

}

