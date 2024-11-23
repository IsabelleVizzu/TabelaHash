import java.awt.*;
import java.awt.print.*;
import javax.swing.*;

public class GerarPDF implements Printable {
    private String text;
    private String[] textSeparate;

    public GerarPDF(String report){
        this.text = report;
        SeparateText();
    }

    public void SeparateText(){
        String[] divided = text.split("\n");
        textSeparate = divided;
    }

    @Override
    public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
        int linesPerPage = 40; 
        int totalPages = (int) Math.ceil((double) textSeparate.length / linesPerPage);

        if (page >= totalPages) {
            return NO_SUCH_PAGE; 
        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());

        int y = 50;
        int start = page * linesPerPage;
        int end = Math.min(start + linesPerPage, textSeparate.length);

        for (int i = start; i < end; i++) {
            g.drawString(textSeparate[i], 50, y);
            y += 20; 
        }

        return PAGE_EXISTS;
    }

    
}
