

package Model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class TableStatementOfInvoicesModel extends AbstractTableModel {
    
    private String[] columns = {"No.", "Date", "Customer", "Total"};
    
    // ArrayList
    private ArrayList<StatementOfInvoices> invoices;

    
    public TableStatementOfInvoicesModel(ArrayList<StatementOfInvoices> invoices) {
        this.invoices = invoices;
    }
    
    //Getter
    @Override
    public int getRowCount() {
        return invoices.size();
    }
    
    //Getter
    @Override
    public int getColumnCount() {
        return columns.length;
    }
    
    //Getter
    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
    
    
    //Getter
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        StatementOfInvoices invoice = invoices.get(rowIndex);
        
        //Switch Case 
        switch (columnIndex) {
            case 0: return invoice.getNum();
            case 1: return invoice.getDate();
            case 2: return invoice.getCustomer();
            case 3: return invoice.getInvoiceTotal();
            default : return "";
        }
    }

    
    }
    

