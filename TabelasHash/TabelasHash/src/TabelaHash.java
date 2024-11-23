import java.util.Arrays;
import java.util.LinkedList;

public abstract class TabelaHash {

    private int size;
    private LinkedList[] hashTable;
    private int numberOfCollisions;
    private int[] distribution;


    public TabelaHash (int size) {
        this.size = size;
        this.hashTable = new LinkedList[size];
        this.distribution = new int[size];
        this.numberOfCollisions = 0;
    }


    public int countEmptySlots() {
        int count = 0;
        for (LinkedList value : hashTable) {
            if (value == null) {
                count++;
            }
        }
        return count;
    }

    public int[][] getBiggest() {
        int[] topPositions = new int[10];
        int[] topValues = new int[10];

        Arrays.fill(topValues, Integer.MIN_VALUE);

        for (int i = 0; i < distribution.length; i++) {
            int currentValue = distribution[i];

            for (int j = 0; j < 10; j++) {
                if (currentValue > topValues[j]) {
                    for (int k = 9; k > j; k--) {
                        topValues[k] = topValues[k - 1];
                        topPositions[k] = topPositions[k - 1];
                    }

                    topValues[j] = currentValue;
                    topPositions[j] = i;
                    break;
                }
            }
        }
        return new int[][]{topPositions, topValues};
    }


    public int getSize() {
        return size;
    }


    public int getNumberOfCollisions() {
        return numberOfCollisions;
    }


    public abstract int calculeHash (String name);


    public void add(String name) {
        int value = calculeHash(name);
        this.distribution[value]++;
        if (hashTable[value] == null) {
            LinkedList<String> linkedList = new LinkedList<>();
            linkedList.add(name);
            hashTable[value] = linkedList;
            return;
        }
        LinkedList<String> linkedList = hashTable[value];
        linkedList.add(name);
        numberOfCollisions++;
    }


    public String[] search(String name) {
        long startTime = System.nanoTime();
        int value = calculeHash(name);
        if (this.hashTable[value] == null) {
            System.out.println("Nome não encontrado");
            long endTime = System.nanoTime();
            return new String[]{"false", Long.toString(endTime - startTime)};
        }
        for (Object n : this.hashTable[value]) {
            if (n.equals(name)) {
                System.out.println("Nome encontrado");
                long endTime = System.nanoTime();
                return new String[]{"true", Long.toString(endTime - startTime)};
            }
        }
        long endTime = System.nanoTime();
        return new String[]{"false", Long.toString(endTime - startTime)};
    }
    
}
