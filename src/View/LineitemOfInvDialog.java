
package View;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class LineitemOfInvDialog extends JDialog{
    private JTextField itemLineNameField;
    private JTextField itemLineCountField;
    private JTextField itemLinePriceField;
    private JLabel itemLineNameLbl;
    private JLabel itemLineCountLbl;
    private JLabel itemLinePriceLbl;
    private JButton okBttn;
    private JButton cancelBttn;
    
    public LineitemOfInvDialog(MainFrame frame) {
        itemLineNameField = new JTextField(20);
        itemLineNameLbl = new JLabel("Item Name");
        
        
        
        
        itemLineCountField = new JTextField(20);
        itemLineCountLbl = new JLabel("Item Count");
        
        
        
        
        itemLinePriceField = new JTextField(20);
        itemLinePriceLbl = new JLabel("Item Price");
        
        
        
        
        okBttn = new JButton("OK");
        cancelBttn = new JButton("Cancel");
        
        
        
        
        okBttn.setActionCommand("createLineOK");
        cancelBttn.setActionCommand("createLineCancel");
        
        
        
        
        okBttn.addActionListener(frame.getHandler());
        
        cancelBttn.addActionListener(frame.getHandler());
        
        setLayout(new GridLayout(4, 2));
        
        
        add(itemLineNameLbl);
        
        add(itemLineNameField);
        
        add(itemLineCountLbl);
        
        add(itemLineCountField);
        
        add(itemLinePriceLbl);
        
        add(itemLinePriceField);
        
        add(okBttn);
        
        add(cancelBttn);
        
        pack();
    }
    
     public JTextField getItemLineCountField() {
        return itemLineCountField;
    }


    public JTextField getItemLinePriceField() {
        return itemLinePriceField;
    }
    
     public JTextField getItemLineNameField() {
        return itemLineNameField;
    }
}
