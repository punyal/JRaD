/**
 * JRaD 2015
 * @author Pablo Puñal Pereira <pablo@punyal.com>
 * @version 0.1
 */

package com.punyal.jrad.test;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class ClientGUI {
    // RADIUS Propierties
    JLabel tagCode = new JLabel("Code: ", JLabel.RIGHT);
    JTextField Code = new JTextField("0");
    
    // Table
    private JTable fTable;
    private RowSorter<TableModel> fSorter;
    
    /** Custom GUI for RADIUS
     * @param args */
    public static void main(String[] args) {
        ClientGUI app = new ClientGUI();
    }
    
    ClientGUI() { //Build and display
        JFrame frame = new JFrame("JRAD Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridLayout layout = new GridLayout(2, 1, 10, 10);
        frame.setLayout(layout);
        
        addRADIUSMessage(frame);
        addHistorianTableTo(frame);
        frame.pack();
        frame.setVisible(true);
    }
    
    private void addRADIUSMessage(JFrame aFrame) {
        JPanel panel = new JPanel();
        GridLayout layoutRAD = new GridLayout(2, 3, 10, 10);
        panel.setLayout(layoutRAD);
        panel.add(tagCode);
        panel.add(Code);
        panel.add(tagCode);
        panel.add(Code);
        panel.add(tagCode);
        panel.add(Code);
        aFrame.getContentPane().add(panel);
    }
    
    private void addHistorianTableTo(JFrame aFrame) {
        JPanel panel = new JPanel();
        Object[][] data = { {"23/10/14 18:00:32","192.168.0.110:3343","192.168.0.120:1802", 112, "ACCESS_REQUEST"},
                            {"23/10/14 18:00:33","192.168.0.120:1802","192.168.0.110:3343", 143, "ACCESS_ACCEPT"},
                            {"23/10/14 18:00:45","192.168.0.110:3343","192.168.0.120:1802", 168, "ACCOUNT_REQUEST"},
                            {"23/10/14 18:00:46","192.168.0.120:1802","192.168.0.110:3343", 210, "ACCOUNT_REJECT"}};
        String[] cols = {"Time", "Origin", "Destination", "Length", "Message Type"};
        fTable = new JTable(data, cols);
        fTable.setPreferredSize(new Dimension(800, 400));
        fTable.getColumnModel().getColumn(0).setMinWidth(84);
        fTable.getColumnModel().getColumn(0).setPreferredWidth(84);
        fTable.getColumnModel().getColumn(1).setMinWidth(120);
        fTable.getColumnModel().getColumn(1).setPreferredWidth(120);
        fTable.getColumnModel().getColumn(2).setMinWidth(120);
        fTable.getColumnModel().getColumn(2).setPreferredWidth(120);
        fTable.getColumnModel().getColumn(3).setMinWidth(5);
        fTable.getColumnModel().getColumn(3).setPreferredWidth(10);
        fTable.getColumnModel().getColumn(4).setMinWidth(120);
        fTable.getColumnModel().getColumn(4).setPreferredWidth(120);
        fTable.setPreferredScrollableViewportSize(fTable.getPreferredSize());
        fSorter = new TableRowSorter<>(fTable.getModel());
        fTable.setRowSorter(fSorter);
        fTable.getTableHeader().addMouseListener(new CustomSorter());

        panel.add(new JScrollPane(fTable));
        aFrame.getContentPane().add(panel);
    }
    
    
    /** Default sort behaviour, plus every third click removes the sort. */
  private final class CustomSorter extends MouseAdapter {
    @Override public void mouseClicked(MouseEvent aEvent) {
      int columnIdx = fTable.getColumnModel().getColumnIndexAtX(aEvent.getX());
      //build a list of sort keys for this column, and pass it to the sorter
      //you can build the list to fit your needs here 
      //for example, you can sort on multiple columns, not just one
      List<RowSorter.SortKey> sortKeys = new ArrayList<>();
      //cycle through all orders; sort is removed every 3rd click
      SortOrder order =  SortOrder.values()[fCountClicks % 3];
      sortKeys.add(new RowSorter.SortKey(columnIdx, order));
      fSorter.setSortKeys(sortKeys);
      ++fCountClicks;
    }
    private int fCountClicks;
  }
    
}