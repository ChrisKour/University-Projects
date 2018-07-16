import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class GraphicalUserInterface extends JFrame{
	
	private ArrayList<DEHInvoice> invoices;

	JPanel panel = new JPanel();
	JTextField field1 = new JTextField(5);
	JTextField field2 = new JTextField(5);
	JTextField field3 = new JTextField(5);
	JButton button1 = new JButton("Create Normal House Invoice");
	JButton button2 = new JButton("Create House Night Invoice");
	JButton button3 = new JButton("Create Industrial Invoice");
	JButton button4 = new JButton("Print Total");
	JLabel label1 = new JLabel("Kodikos Paroxis:");
	JLabel label2 = new JLabel("kWh:");
	JLabel label3 = new JLabel("Square Meters:");
	
	public GraphicalUserInterface() {
		invoices = new ArrayList<>();
		
		panel.add(label1);
		panel.add(field1);
		panel.add(label2);
		panel.add(field2);
		panel.add(label3);
		panel.add(field3);
		panel.add(button1);
		panel.add(button2);
		panel.add(button3);
		panel.add(button4);
		
		ButtonListener1 listener1 = new ButtonListener1();
		ButtonListener2 listener2 = new ButtonListener2();
		ButtonListener3 listener3 = new ButtonListener3();
		ButtonListener4 listener4 = new ButtonListener4();
		
		button1.addActionListener(listener1);
		button2.addActionListener(listener2);
		button3.addActionListener(listener3);
		button4.addActionListener(listener4);
		
		this.setContentPane(panel);
		this.setTitle("DEH APPlication");
		this.setSize(550,130);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void addInvoice(DEHInvoice invoice){
		invoices.add(invoice);
	}
	
	public class ButtonListener1 implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if(!field1.getText().isEmpty() && !field2.getText().isEmpty() && !field3.getText().isEmpty()){
				String kodikostext = field1.getText();
				int kodikos = (int) Double.parseDouble(kodikostext);
				String kwhtext = field2.getText();
				long kwh = (long) Double.parseDouble(kwhtext);
				String meterstext = field3.getText();
				int meters = (int) Double.parseDouble(meterstext);
				addInvoice(new HouseInvoice(kodikos, kwh, meters));
			}
			else
				JOptionPane.showMessageDialog(null, "Please fill in all the fields");
			field1.setText("");
			field2.setText("");
			field3.setText("");
					
		}
	}
	
	public class ButtonListener2 implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if(!field1.getText().isEmpty() && !field2.getText().isEmpty() && !field3.getText().isEmpty()){
				String kodikostext = field1.getText();
				int kodikos = (int) Double.parseDouble(kodikostext);
				String kwhtext = field2.getText();
				long kwh = (long) Double.parseDouble(kwhtext);
				String meterstext = field3.getText();
				int meters = (int) Double.parseDouble(meterstext);
				String nightText = JOptionPane.showInputDialog("Please type night kilowatt hours");
				int night = (int) Double.parseDouble(nightText);	
				addInvoice(new HouseNightInvoice(kodikos, kwh, meters, night));
			}
			else
				JOptionPane.showMessageDialog(null, "Please fill in all the fields");
			field1.setText("");
			field2.setText("");
			field3.setText("");
		}
	}
	
	public class ButtonListener3 implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if(!field1.getText().isEmpty() && !field2.getText().isEmpty()){
				String kodikostext = field1.getText();
				int kodikos = (int) Double.parseDouble(kodikostext);
				String kwhtext = field2.getText();
				long kwh = (long) Double.parseDouble(kwhtext);
				addInvoice(new IndustrialInvoice(kodikos, kwh));
			}
			else
				JOptionPane.showMessageDialog(null, "Please fill the required fields");
			field1.setText("");
			field2.setText("");
		}
	}
	
	public class ButtonListener4 implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			double total = 0;
			for(DEHInvoice i : invoices){
				total += i.computeCost();
				if(i.getClass().getName() == "HouseInvoice")
					System.out.println("Invoice of the Normal House customer with supply number " + i.getCode() +" Total cost = "+i.computeCost());
				else if(i.getClass().getName() == "HouseNightInvoice")
					System.out.println("Invoice of the House Night customer with supply number "+ i.getCode() +" Total cost = "+i.computeCost());
				else
					System.out.println("Invoice of the industrial customer with supply number "+ i.getCode() +" Total cost = "+i.computeCost());
			}
			JOptionPane.showMessageDialog(null, "Total Amount = "+total+" euros");	
			field1.setText("");
			field2.setText("");
			field3.setText("");
		}
	}
}
