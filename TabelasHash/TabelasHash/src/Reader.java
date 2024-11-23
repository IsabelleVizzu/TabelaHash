import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Reader {

    private String file;
    private String separator;


    public Reader (String file) {
        this.file = file;
        this.separator = ",";
    }

    public long addInTableHash (TabelaHash table) {
        long startTime = System.currentTimeMillis();
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(this.file))) {
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] names = line.split(this.separator);
                for (String name : names) {
                    table.add(name.trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
}
