/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package investment.calculator;

/**
 *
 * @author arnol
 */


// main class
public class InvestmentCalculator {
    public static void main(String[] args) {
        // investment and display frame
        InvestFrame investframe = new InvestFrame();
        investframe.showFrame();
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package investment.calculator;

import java.awt.FlowLayout;
import java.util.Vector;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author arnol
 */

// textfields for customer name and amount
// type of investment (moderate/aggressive)
// radio button for investment years
// radio button group
// vector types for adding investment types
class InvestPanel extends JPanel {
    private final JTextField customerName;
    private final JTextField amount;
    private final JComboBox type;
    private final JRadioButton term_5years;
    private final JRadioButton term_10years;
    private final JRadioButton term_15years;
    private final ButtonGroup group;
    private final Vector<Object> types = new Vector<>();

    // constructor
    public InvestPanel() {
        customerName = new JTextField(20);
        amount = new JTextField(20);
        types.add("Moderate                         ");
        types.add("Aggressive                         ");
        type = new JComboBox(types);
        term_5years = new JRadioButton("5 Years");
        term_10years = new JRadioButton("10 Years");
        term_15years = new JRadioButton("15 Years");
        group = new ButtonGroup();

    }

    public InvestPanel createInvestmentPanel() {
        term_5years.setSelected(true);
        group.add(term_5years);
        group.add(term_10years);
        group.add(term_15years);

        setLayout(new FlowLayout(10));
        add(new JLabel(" Customer Name: "));
        add(customerName);

        add(new JLabel(" Enter Amount:    "));
        add(amount);

        add(new JLabel(" Select Type:        "));
        add(type);

        add(new JLabel(" Select Term:      "));
        add(term_5years);
        add(term_10years);
        add(term_15years);
        return this;
    }

    // getter methods
    public String getCustomerName() {
        return customerName.getText();
    }

    public String getAmount() {
        return amount.getText();
    }

    public String getType() {
        return type.getSelectedItem().toString().trim();
    }

    public int getTerm() {
        if (term_5years.isSelected())
            return 5;
        else if (term_10years.isSelected())
            return 10;
        else
            return 15;
    }

    // setter methods
    public void setCustomerName(String s) {
        customerName.setText(s);
    }

    public void setAmount(String s) {
        amount.setText(s);
    }

    public void setType(int i) {
        type.setSelectedIndex(i);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package investment.calculator;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author arnol
 */
// InvestmentPanel object
// menu items: File has Exit, Tools has Calculate and Clear
// menu bar has File and Tools
class InvestFrame extends JFrame {
    private final JMenuBar menuBar;
    private final JMenu fileMenu;
    private final JMenu toolsMenu;
    private final JMenuItem file_exitMenuItem;
    private final JMenuItem tools_calculateMenuItem;
    private final JMenuItem tools_clearMenuItem;
    private InvestPanel investPanel;

    // constructor
    public InvestFrame() {
        // initialize
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        toolsMenu = new JMenu("Tools");
        file_exitMenuItem = new JMenuItem("Exit");
        tools_calculateMenuItem = new JMenuItem("Calculate");
        tools_clearMenuItem = new JMenuItem("Clear");


        fileMenu.add(file_exitMenuItem);

        toolsMenu.add(tools_calculateMenuItem);
        toolsMenu.add(tools_clearMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);

    }

    public void showFrame() {
        investPanel = new InvestPanel();
        MenuButtons act = new MenuButtons(investPanel);
        file_exitMenuItem.addActionListener(act);
        tools_calculateMenuItem.addActionListener(act);
        tools_clearMenuItem.addActionListener(act);

       
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(menuBar);
        getContentPane().add(investPanel.createInvestmentPanel());
        setTitle("Investment Calculator");
        setSize(420, 200);
        setLocation(300, 200);
        setVisible(true);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package investment.calculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author arnol
 */
// Menu Button Functionality
class MenuButtons implements ActionListener {
    private InvestPanel investPanel;

    public MenuButtons(InvestPanel panel) {
        investPanel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() instanceof JMenuItem) {
            String item = evt.getActionCommand().trim();
            switch (item) {  
                case "Exit":
                    System.exit(0);
                    break;
                case "Calculate":
                    calculate();
                    break;
                case "Clear":
                    clear();
                    break;
            }
        }
    }

    // calculate investment amount
    private void calculate() {
        // get amount from customer name text field
        double amount = Double.parseDouble(investPanel.getAmount());
        // get intereset rate from type
        double interest = 10.0;
        String type = investPanel.getType();
        if (type.equals("Moderate"))
            interest = 10.0;
        else if (type.equals("Aggressive"))
            interest = 15.0;

        // investment in years input
        int years = investPanel.getTerm();
        // Math.pow is used for exponents
        double investment = amount * (Math.pow((1.00 + (interest / 100.00)), years));
        double compound_interest = investment - amount;

        String str = "INVESTMENT REPORT\n"
                + "\nCUSTOMER NAME: " + investPanel.getCustomerName()
                + "\nORIGINAL AMOUNT: R " + investPanel.getAmount()
                + "\nYEARS Invested: " + years
                + "\nFINAL AMOUNT: R " + Math.round(investment * 100.0) / 100.0;

        // Message box details
        JOptionPane.showMessageDialog(null, str);
    }

    // This should clear all the text fields
    private void clear() {
        investPanel.setCustomerName("");
        investPanel.setAmount("");
        investPanel.setType(0);
    }
}