

package Model;

import java.util.ArrayList;

public class StatementOfInvoices {
    private int number;
    private String date;
    private String client;
    private ArrayList<LineItemInv> lines;
    
    public StatementOfInvoices() {
    }

    public StatementOfInvoices(int number, String date, String client) {
        this.number = number;
        this.date = date;
        this.client = client;
    }

    public double getInvoiceTotal() {
        double total = 0.0;
        for (LineItemInv line : getLines()) {
            total += line.getLineTotal();
        }
        return total;
    }
    
    public ArrayList<LineItemInv> getLines() {
        if (lines == null) {
            lines = new ArrayList<>();
        }
        return lines;
    }

    public String getCustomer() {
        return client;
    }

    public void setCustomer(String customer) {
        this.client = customer;
    }

    public int getNum() {
        return number;
    }

    public void setNum(int num) {
        this.number = num;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setLines(ArrayList<LineItemInv> lines) {
        this.lines = lines;
    }
    

    @Override
    public String toString() {
        return "Invoice{" + "num=" + number + ", date=" + date + ", customer=" + client + '}';
    }
    
    public String getAsCSV() {
        return number + "," + date + "," + client;
    }
    
}
