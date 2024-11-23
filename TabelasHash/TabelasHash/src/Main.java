import java.awt.print.*;
import java.util.Scanner;

public class Main {

    public static String searchTime(TabelaHash table) {
        int found = 0;
        String[] result = table.search("Zea");
        long time1 = Long.parseLong(result[1]);
        String cond = result[0];
        found += cond.equals("true") ? 1 : 0;
        String[] result2 = table.search("Zara");
        long time2 = Long.parseLong(result2[1]);
        String cond2 = result2[0];
        String[] result3 = table.search("Zarah");
        found += cond2.equals("true") ? 1 : 0;
        long time3 = Long.parseLong(result3[1]);
        String cond3 = result3[0];
        found += cond3.equals("true") ? 1 : 0;
        return "O tempo para buscar 3 nomes, cujo " +  found + " de 3 nomes foram encontrados \n"
                + "foi de " + (time1+time2+time3)+ " nanossegundos.";
    }

    public static void main(String[] args) {

        Reader reader = new Reader("names.csv");
        TabelaHash table_1 = new TabelaHash_1(5000);
        TabelaHash table_2 = new TabelaHash_2(5000);

        long time = reader.addInTableHash(table_1);
        long timeHash2 = reader.addInTableHash(table_2);

        String report = "Relatório da Tabela Hash 1\n" +
                "------------------------------------------------\n" +
                "Tempo de inserção:\n" +
                "O tempo total para adicionar todas as chaves à Tabela Hash 1 foi de " + time + " milissegundos.\n\n" +
                "Tempo de busca:\n" +
                searchTime(table_1) + "\n\n" +
                "Análise de colisões:\n" +
                "O número total de colisões detectadas na Tabela Hash 1 foi de " + table_1.getNumberOfCollisions() + " colisões.\n\n" +
                "Dados importantes:\n" +
                "------------------------------------------------\n";

        String report2 = "------------------------------------------------\n" +
                "Relatório da Tabela Hash 2\n" +
                "------------------------------------------------\n" +
                "Tempo de inserção:\n" +
                "O tempo total para adicionar todas as chaves à Tabela Hash 2 foi de " + timeHash2 + " milissegundos.\n\n" +
                "Tempo de busca:\n" +
                searchTime(table_2) + "\n\n" +
                "Análise de colisões:\n" +
                "O número total de colisões detectadas na Tabela Hash 2 foi de " + table_2.getNumberOfCollisions() + " colisões.\n\n" +
                "Dados importantes:\n" +
                "------------------------------------------------\n";

        int[][] biggest = table_1.getBiggest();
        String report_biggest = "Os 10 valores com os maiores números de colisões na Tabela Hash 1 são:\n";

        int[] topPositions = biggest[0];
        int[] topValues = biggest[1];

        for (int i = 0; i < 10; i++) {
            report_biggest += (i + 1) + " - Valor " + topPositions[i] + " com " + (topValues[i] - 1) + " colisões\n";
        }

        int[][] biggest2 = table_2.getBiggest();
        String report_biggest2 = "Os 10 valores com os maiores números de colisões na Tabela Hash 2 são:\n";

        int[] topPositions2 = biggest2[0];
        int[] topValues2 = biggest2[1];

        for (int i = 0; i < 10; i++) {
            report_biggest2 += (i + 1) + " - Valor " + topPositions2[i] + " com " + (topValues2[i] - 1) + " colisões\n";
        }

        String report_null = "A Tabela Hash 1 tem " + table_1.countEmptySlots() + " valores com 0 chaves.\n\n";
        String report2_null = "A Tabela Hash 2 tem " + table_2.countEmptySlots() + " valores com 0 chaves.\n\n";


        String finalReport = report + report_null + report_biggest + report2 +  report2_null + report_biggest2;

        System.out.println(finalReport);

        Scanner scan = new Scanner(System.in);
        System.out.println("Você deseja salvar o relatório?");
        System.out.println("1 - Sim");
        System.out.println("2 - Não");
        int choice = scan.nextInt();

        switch (choice) {
            case 1:
                PrinterJob job = PrinterJob.getPrinterJob();
                job.setPrintable(new GerarPDF(finalReport));

                boolean doPrint = job.printDialog();
                if (doPrint) {
                    try {
                        job.print();
                    } catch (PrinterException e) {
                        e.printStackTrace();
                    }
                }
                break;

            case 2:
                System.out.println("Programa encerrado.");
                break;

            default:
                System.out.println("Opção inválida.");
                break;
        }
    }
}
