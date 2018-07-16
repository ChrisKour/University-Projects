import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class ApplicationFrame extends JFrame{
	
	private boolean flag;
	
	JPanel panel1 = new JPanel(new GridLayout (4,4));
	JPanel panel2 = new JPanel(new GridLayout (0,1));
		
	JTextField field = new JTextField(15);
	JButton b1 = new JButton("1");
	JButton b2 = new JButton("2");
	JButton b3 = new JButton("3");
	JButton b4 = new JButton("4");
	JButton b5 = new JButton("5");
	JButton b6 = new JButton("6");
	JButton b7 = new JButton("7");
	JButton b8 = new JButton("8");
	JButton b9 = new JButton("9");
	JButton b10 = new JButton("0");
	JButton b11 = new JButton(".");
	JButton b12 = new JButton("C");
	JButton b13 = new JButton("/");
	JButton b14 = new JButton("*");
	JButton b15 = new JButton("-");
	JButton b16 = new JButton("+");
	JButton b17 = new JButton("=");
	
	public ApplicationFrame(){
		
		flag = true;
		
		panel1.add(b7);
		panel1.add(b8);
		panel1.add(b9);
		panel1.add(b13);
		panel1.add(b4);
		panel1.add(b5);
		panel1.add(b6);
		panel1.add(b14);
		panel1.add(b1);
		panel1.add(b2);
		panel1.add(b3);
		panel1.add(b15);
		panel1.add(b10);
		panel1.add(b11);
		panel1.add(b12);
		panel1.add(b16);
		panel2.add(b17);
		
		NumbersListener NL = new NumbersListener();
		CListener CL = new CListener();
		TeleiaListener TL = new TeleiaListener();
		IsonListener IL = new IsonListener();
		
		b1.addActionListener(NL);
		b2.addActionListener(NL);
		b3.addActionListener(NL);
		b4.addActionListener(NL);
		b5.addActionListener(NL);
		b6.addActionListener(NL);
		b7.addActionListener(NL);
		b8.addActionListener(NL);
		b9.addActionListener(NL);
		b10.addActionListener(NL);
		b11.addActionListener(TL);
		b13.addActionListener(NL);
		b14.addActionListener(NL);
		b15.addActionListener(NL);
		b16.addActionListener(NL);
		b12.addActionListener(CL);
		b17.addActionListener(IL);
		
		add(field, BorderLayout.NORTH);
		add(panel1, BorderLayout.CENTER);
		add(panel2, BorderLayout.SOUTH);
		this.setSize(200,200);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	class NumbersListener implements ActionListener{
	      public void actionPerformed(ActionEvent e){
	    		  String input = e.getActionCommand();
	    		  if (flag)
	    	         {
	    	            field.setText("");
	    	            flag = false;
	    	         }
	    		  field.setText(field.getText() + input);
	      }
	   }
	
	class TeleiaListener implements ActionListener{
	      public void actionPerformed(ActionEvent e){
	    	  if(!field.getText().contains(".")){
	    		  String input = e.getActionCommand();
	    		  field.setText(field.getText() + input);
	    	  }
	    	  else if(field.getText().contains("+")){ 
	    		  if(!field.getText().substring(field.getText().indexOf("+")).contains(".")){
	    			  String input = e.getActionCommand();
	    			  field.setText(field.getText() + input);
	    		  }
	    	  }
	    	  else if(field.getText().contains("-")){ 
	    		  if(!field.getText().substring(field.getText().indexOf("-")).contains(".")){
	    			  String input = e.getActionCommand();
	    			  field.setText(field.getText() + input);
	    		  }
	    	  }
	    	  else if(field.getText().contains("/")){ 
	    		  if(!field.getText().substring(field.getText().indexOf("/")).contains(".")){
	    			  String input = e.getActionCommand();
	    			  field.setText(field.getText() + input);
	    		  }
	    	  }
	    	  else if(field.getText().contains("*")){ 
	    		  if(!field.getText().substring(field.getText().indexOf("*")).contains(".")){
	    			  String input = e.getActionCommand();
	    			  field.setText(field.getText() + input);
	    		  }
	    	  }
	      }
	   }
	
	class IsonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String input = field.getText();
			if(input.contains("+")){
				int indexNumber = input.indexOf("+");
				String number1Text = input.substring(0, indexNumber);
				double number1 = Double.parseDouble(number1Text);
				String number2Text = input.substring(indexNumber+1, input.length());
				double number2 = Double.parseDouble(number2Text);
				double result = number1 + number2;
				field.setText("" + result);
			}
			else if(input.contains("-")){
				int indexNumber = input.indexOf("-");
				String number1Text = input.substring(0, indexNumber);
				double number1 = Double.parseDouble(number1Text);
				String number2Text = input.substring(indexNumber+1, input.length());
				double number2 = Double.parseDouble(number2Text);
				double result = number1 - number2;
				field.setText("" + result);
			}
			else if(input.contains("/")){
				int indexNumber = input.indexOf("/");
				String number1Text = input.substring(0, indexNumber);
				double number1 = Double.parseDouble(number1Text);
				String number2Text = input.substring(indexNumber+1, input.length());
				double number2 = Double.parseDouble(number2Text);
				double result = number1 / number2;
				field.setText("" + result);
			}
			else if(input.contains("*")){
				int indexNumber = input.indexOf("*");
				String number1Text = input.substring(0, indexNumber);
				double number1 = Double.parseDouble(number1Text);
				String number2Text = input.substring(indexNumber+1, input.length());
				double number2 = Double.parseDouble(number2Text);
				double result = number1 * number2;
				field.setText("" + result);
			}
			flag = true;
		}
	}
	
	class CListener implements ActionListener{
	      public void actionPerformed(ActionEvent e){
	    	  	field.setText("");
	      }
	}
}
