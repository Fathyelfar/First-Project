
package Model;

public class LineItemInv {
    private int num,count;
    private String item;
    private double price;
    private StatementOfInvoices invoice;
    
    
// Empty Constructor
    public LineItemInv() {
    }

    public LineItemInv(String item, double price, int count, StatementOfInvoices invoice) {
        this.item = item;
        this.price = price;
        this.count = count;
        this.invoice = invoice;
    }
    // Getter 
    public double getLineTotal() {
        return price * count;
    }
    
    //Getter
    public int getCount() {
        return count;
    }
    
   //Setter
    public void setCount(int count) {
        this.count = count;
    }
    
   //Getter
    public String getItem() {
        return item;
    }
    
    //Setter
    public void setItem(String item) {
        this.item = item;
    }

    //Getter
    public double getPrice() {
        return price;
    }
    
   //Setter
    public void setPrice(double price) {
        this.price = price;
    }
    
 //ToString
    @Override
    public String toString() {
        return "Line{" + "num=" + invoice.getNum() + ", item=" + item + ", price=" + price + ", count=" + count + '}';
    }
    
  //Getter
    public int getNum() {
        return num;
    }
    
   //Getter
    public StatementOfInvoices getInvoice() {
        return invoice;
    }

    public String getAsCSV() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
   

   
    }
    

