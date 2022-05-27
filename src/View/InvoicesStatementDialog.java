

package View;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;





public class InvoicesStatementDialog extends JDialog {
    private JTextField clienttNameField;
    private JTextField invoiceDateField;
    private JLabel clientNameLbl;
    private JLabel invoiceDateLbl;
    private JButton okButton;
    private JButton cancelButton;
    
    
    

    public InvoicesStatementDialog(MainFrame frame) {
        
        clientNameLbl = new JLabel("Customer Name:");
        
        clienttNameField = new JTextField(20);
        
        invoiceDateLbl = new JLabel("Invoice Date:");
        
        invoiceDateField = new JTextField(20);
        
        okButton = new JButton("OK");
        
        cancelButton = new JButton("Cancel");
        
        
        okButton.setActionCommand("createInvoiceOK");
        
        cancelButton.setActionCommand("createInvoiceCancel");
        
        okButton.addActionListener(frame.getHandler());
        
        cancelButton.addActionListener(frame.getHandler());
        
        setLayout(new GridLayout(3, 2));
        
        
        add(invoiceDateLbl);
        add(invoiceDateField);
        add(clientNameLbl);
        add(clienttNameField);
        add(okButton);
        add(cancelButton);
        
        pack();
        
    }


    public JTextField getInvoiceDateField() {
        return invoiceDateField;
    }
    
    public JTextField getClienttNameField() {
        return clienttNameField;
    }
    
}
