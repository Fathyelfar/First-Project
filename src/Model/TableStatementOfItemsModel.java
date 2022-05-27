

package Model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class TableStatementOfItemsModel extends AbstractTableModel {

    private String[] columns = {"No.", "Item Name", "Item Price", "Count", "Item Total"};
    // ArrayList
    private ArrayList<LineItemInv> itemLines;


    public TableStatementOfItemsModel(ArrayList<LineItemInv> lines) {
        this.itemLines = lines;
    }

    public ArrayList<LineItemInv> getItemLines() {
        return itemLines;
    }
    
    //Override
    @Override
    public int getRowCount() {
        return itemLines.size();
    }
    
    //Override
    @Override
    public int getColumnCount() {
        return columns.length;
    }
    
    //Override
    @Override
    public String getColumnName(int i) {
        return columns[i];
    }
    
   //Override
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        LineItemInv line = itemLines.get(rowIndex);
        
        switch(columnIndex) {
            case 0: return line.getInvoice().getNum();
            case 1: return line.getItem();
            case 2: return line.getPrice();
            case 3: return line.getCount();
            case 4: return line.getLineTotal();
            default : return "";
        }
    }

    }
    
