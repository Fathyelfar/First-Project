
package Controller;

import Model.StatementOfInvoices;
import Model.TableStatementOfInvoicesModel;
import Model.LineItemInv;
import Model.TableStatementOfItemsModel;
import View.InvoicesStatementDialog;
import View.MainFrame;
import View.LineitemOfInvDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class ProjectListener implements ActionListener, ListSelectionListener {

    private MainFrame invframe;
    private InvoicesStatementDialog invoiceDialog;
    private LineitemOfInvDialog linesDialog;

    public ProjectListener(MainFrame frame) {
        this.invframe = frame;
    }
     
    @Override
    public void actionPerformed(ActionEvent x) {
        String actionCommand = x.getActionCommand();
        System.out.println("Button Clicked :  " + actionCommand);
        
        //Switch Case
        switch (actionCommand) {
                 case "Load File":
                loadingFile();
                       break;
                 case "Save File":
                savingFile();
                       break;
                 case "Create New Invoice":
                createNewInv();
                       break;
                 case "Delete Invoice":
                deletInv();
                       break;
                 case "Create New Item":
                createNewLine();
                       break;
                 case "Delete Item":
                deleteItem();
                       break;
                 case "createInvoiceCancel":
                createInvoiceCancel();
                       break;
                 case "createInvoiceOK":
                createInvoiceOK();
                       break;
                 case "createLineOK":
                createItemLineOK();
                       break;
                 case "createLineCancel":
                createItemLineCancel();
                
               //Break
                       break;
        }
    }
     
    @Override
    public void valueChanged(ListSelectionEvent e) {
        
        int selectedIndex = invframe.getInvoiceTable().getSelectedRow();
        
        if (selectedIndex != -1) {
            
            StatementOfInvoices currentInvoice = invframe.getInvoices().get(selectedIndex);
            
            invframe.getInvDateLbl().setText(currentInvoice.getDate());
            
            invframe.getInvNoLbl().setText("" + currentInvoice.getNum());
           
            invframe.getClientNameLbl().setText(currentInvoice.getCustomer());
            
            invframe.getInvTotalLbl().setText("" + currentInvoice.getInvoiceTotal());
            
            TableStatementOfItemsModel linesTableModel = new TableStatementOfItemsModel(currentInvoice.getLines());
            invframe.getLineTable().setModel(linesTableModel);
            linesTableModel.fireTableDataChanged();
           
        }
    }

    private void loadingFile() 
    
    {
        //JFileChooser
        JFileChooser fc = new JFileChooser();
        //Try---Catch
        try {
            int result = fc.showOpenDialog(invframe);
            if (result == JFileChooser.APPROVE_OPTION) {
                File headerFile = fc.getSelectedFile();
                Path headerPath = Paths.get(headerFile.getAbsolutePath());
                List<String> headerLines = Files.readAllLines(headerPath);
               // ArrayLists
                ArrayList<StatementOfInvoices> arrayInv = new ArrayList<>();
                for (String headerLine : headerLines) {
                    //Try---Catch
                    try {
                        String[] headerElements = headerLine.split(",");
                        int invoiceNum = Integer.parseInt(headerElements[0]);
                        String invoiceDate = headerElements[1];
                        String customerName = headerElements[2];

                        StatementOfInvoices invoice = new StatementOfInvoices(invoiceNum, invoiceDate, customerName);
                        arrayInv.add(invoice);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(invframe, "Error in line format", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                result = fc.showOpenDialog(invframe);
                //JFILE CHOOSER
                if (result == JFileChooser.APPROVE_OPTION) {
                    File itemLineFile = fc.getSelectedFile();
                    Path linePath = Paths.get(itemLineFile.getAbsolutePath());
                    List<String> itemLineLines = Files.readAllLines(linePath);
                    
                    //for loop 
                    for (String itemLineLine : itemLineLines) {
                        try {
                            String[] lineElements = itemLineLine.split(",");
                            int invoiceNum = Integer.parseInt(lineElements[0]);
                            String itemName = lineElements[1];
                            double itemPrice = Double.parseDouble(lineElements[2]);
                            int count = Integer.parseInt(lineElements[3]);
                            StatementOfInvoices inv = null;
                            //for loop & If 
                            for (StatementOfInvoices invoice : arrayInv) {
                                if (invoice.getNum() == invoiceNum) {
                                    inv = invoice;
                                    //Break
                                    break;
                                }
                            }

                            LineItemInv line = new LineItemInv(itemName, itemPrice, count, inv);
                            inv.getLines().add(line);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(invframe, "Error in line format", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    
                    
                    //SOUT
                    System.out.println("Review ");
                    
                    
                }
                invframe.setInvoices(arrayInv);
                TableStatementOfInvoicesModel invoicesTableModel = new TableStatementOfInvoicesModel(arrayInv);
                invframe.setInvoicesTableModel(invoicesTableModel);
                invframe.getInvoiceTable().setModel(invoicesTableModel);
                invframe.getInvoicesTableModel().fireTableDataChanged();
            }
            
            
            
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(invframe, "Cannot read file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void savingFile() {
        ArrayList<StatementOfInvoices> invoices = invframe.getInvoices();
        String headers = "";
        String lines = "";
        
        //Fixed the null pointer exception error 
        if(invoices!=null)
       
        // For Loop 
            for(StatementOfInvoices invoice :invoices){
            String invCSV = invoice.getAsCSV();
            headers += invCSV;
            headers += "\n";

            for (LineItemInv line : invoice.getLines()) {
                String lineCSV = line.getAsCSV();
                lines += lineCSV;
                lines += "\n";
                
                
                
            }
        }
        
        
        //SOUT
        System.out.println("Review 2");
        
        
        
        //Try Catch
        try {
            //JFileChooser
            JFileChooser fc = new JFileChooser();
            int result = fc.showSaveDialog(invframe);
            if (result == JFileChooser.APPROVE_OPTION) {
                File headerFile = fc.getSelectedFile();
                FileWriter hfw = new FileWriter(headerFile);
                hfw.write(headers);
                hfw.flush();
                hfw.close();
                result = fc.showSaveDialog(invframe);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File itemLineFile = fc.getSelectedFile();
                    FileWriter lfw = new FileWriter(itemLineFile);
                    lfw.write(lines);
                    lfw.flush();
                    lfw.close();
                }
            }
        } catch (Exception ex) {

        }
    }
    
    

    private void createNewInv() {
        invoiceDialog = new InvoicesStatementDialog(invframe);
        invoiceDialog.setVisible(true);
    }
    
    
    

    private void deletInv() {
        int selectedRow = invframe.getInvoiceTable().getSelectedRow();
        if (selectedRow != -1) {
            invframe.getInvoices().remove(selectedRow);
            invframe.getInvoicesTableModel().fireTableDataChanged();
        }
    }
    
    
    

    private void createNewLine() {
        linesDialog = new LineitemOfInvDialog(invframe);
        linesDialog.setVisible(true);
    }

    private void deleteItem() {
        int selectedRow = invframe.getLineTable().getSelectedRow();

        if (selectedRow != -1) {
            TableStatementOfItemsModel linesTableModel = (TableStatementOfItemsModel) invframe.getLineTable().getModel();
            linesTableModel.getItemLines().remove(selectedRow);
            linesTableModel.fireTableDataChanged();
            invframe.getInvoicesTableModel().fireTableDataChanged();
        }
    }

    private void createInvoiceCancel() {
        invoiceDialog.setVisible(false);
        invoiceDialog.dispose();
        invoiceDialog = null;
    }
    

    private void createInvoiceOK() {
        
        String date = invoiceDialog.getInvoiceDateField().getText();
        String customer = invoiceDialog.getClienttNameField().getText();
        int num = invframe.getNextInvoiceNum();
        //Try Catch
        try {
            String[] dateParts = date.split("-");  
            //IF Else
            if (dateParts.length < 3) {
                JOptionPane.showMessageDialog(invframe, "Wrong date format", "Error", JOptionPane.ERROR_MESSAGE);
                
            } else {
                int day = Integer.parseInt(dateParts[0]);
                int month = Integer.parseInt(dateParts[1]);
                int year = Integer.parseInt(dateParts[2]);
                if (day > 31 || month > 12) {
                    JOptionPane.showMessageDialog(invframe, "Wrong date format", "Error", JOptionPane.ERROR_MESSAGE);
                    
                } else {
                    
                    StatementOfInvoices invoice = new StatementOfInvoices(num, date, customer);
                    
                    invframe.getInvoices().add(invoice);
                    invframe.getInvoicesTableModel().fireTableDataChanged();
                    invoiceDialog.setVisible(false);
                    invoiceDialog.dispose();
                    invoiceDialog = null;
                }
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(invframe, "Wrong date format", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
    
    private void createItemLineCancel() {
        linesDialog.setVisible(false);
        linesDialog.dispose();
        linesDialog = null;
    }
    

    private void createItemLineOK() {
        String item = linesDialog.getItemLineNameField().getText();
        String countStr = linesDialog.getItemLineCountField().getText();
        String priceStr = linesDialog.getItemLinePriceField().getText();
        int count = Integer.parseInt(countStr);
        double price = Double.parseDouble(priceStr);
        int selectedInvoice = invframe.getInvoiceTable().getSelectedRow();
        if (selectedInvoice != -1) {
            StatementOfInvoices invoice = invframe.getInvoices().get(selectedInvoice);
            LineItemInv line = new LineItemInv(item, price, count, invoice);
            invoice.getLines().add(line);
            TableStatementOfItemsModel linesTableModel = (TableStatementOfItemsModel) invframe.getLineTable().getModel();
            linesTableModel.fireTableDataChanged();
            //Updating the total 
            invframe.getInvoicesTableModel().fireTableDataChanged();
        }
        linesDialog.setVisible(false);
        linesDialog.dispose();
        linesDialog = null;
    }

              

}
