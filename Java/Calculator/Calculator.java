//Name - Matthew Ho	
//Date - 2/25
//Period- 2nd 

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.regex.*;
import java.awt.Color;


//text field method setEditable = false
// do not ask if 1 do this, if 2 do this
//check to see operator/number USE REGEX?


//instance variables: Arraylist, text field (output), jPanel of buttons, 
// String that is getting text field, String operator, Left Hand Side (23 +)

/**
 * Represents a GUI Calculator
 */
class Calculator extends JFrame implements ActionListener
{
	private ArrayList<JButton> button;
	private JTextField calcOutput;
	private String input;
	private JPanel calcPad;
	private String output;
	private String operator;
	private double lhs;
	private double rhs;
	private double result;

	
	
	/**
	 * Sets up the initial calculator window
	 */
	public Calculator()
	{
		setSize(600,600); // modify as needed
		setTitle("CALCULATOR");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		button = new ArrayList<JButton>();
		calcOutput = new JTextField(30);
		calcPad = new JPanel();
		output = "";
		operator = "";
		lhs = 0.0;
		rhs = 0.0;
		input = "";
		result = 0.0;
		
		
		for(int i = 7; i <= 9; i++)
		{
			button.add(new JButton("" + i));
		}
		button.add(new JButton("/"));
		button.add(new JButton("Sqrt"));
		for(int i = 4; i <= 6; i++)
		{
			button.add(new JButton("" + i));
		}
		button.add(new JButton("*"));
		button.add(new JButton("CE"));
		for(int i = 1; i <=3; i++)
		{
			button.add(new JButton("" + i));
		}
		button.add(new JButton("-"));
		button.add(new JButton("CLR"));
		button.add(new JButton("0"));
		button.add(new JButton("."));
		button.add(new JButton("%"));
		button.add(new JButton("+"));
		button.add(new JButton("="));
		calcPad.setLayout(new GridLayout(4,5));
		for(JButton b : button)
		{
			calcPad.add(b);
			b.addActionListener(this);
		}
		setLayout(new BorderLayout());
		calcOutput.setEditable(false);
		add(calcOutput,BorderLayout.NORTH);
		add(calcPad,BorderLayout.CENTER);
		
		setVisible(true);
		
	}
		

	/**
	 * Processes the clicks of the calculator buttons
	 * @param e the trigger - the source is the clicked button
	 */
	public void actionPerformed(ActionEvent e)
	{
		input = ((JButton)e.getSource()).getText();
		if(input.matches("[0-9]")) 
		{		
				output += input;
				calcOutput.setText(output);
				rhs = Double.parseDouble(output);
		}
		else if(input.equals("CLR"))
		{
			lhs = 0;
			output = "";
			operator = "";
			calcOutput.setText(output);
		}
		else if(input.matches("[-+*/]"))
		{	
			if(!operator.equals(null))
			{
				if(operator.equals("+"))
				{
					lhs += rhs;
					calcOutput.setText(String.valueOf(lhs));
				}
				else if(operator.equals("-"))
				{
					
					if(calcOutput.getText().equals(""))
					{
						output += "-";
						calcOutput.setText(output);
					}
					lhs -= rhs;
					calcOutput.setText(String.valueOf(lhs));
				}
				else if(operator.equals("*"))
				{
					lhs *= rhs;
					calcOutput.setText(String.valueOf(lhs));
				}
				else if(operator.equals("/"))
				{
					lhs /= rhs;
					calcOutput.setText(String.valueOf(lhs));
				}
			}
				lhs = Double.parseDouble(calcOutput.getText());
				operator = input;
				output = "";
		}
		else if(input.equals("="))
		{
			if(operator.equals("+"))
			{
				lhs += rhs;
				calcOutput.setText(String.valueOf(lhs));
			}
			else if(operator.equals("-"))
			{
				lhs -= rhs;
				calcOutput.setText(String.valueOf(lhs));
			}
			else if(operator.equals("*"))
			{
				lhs *= rhs;
				calcOutput.setText(String.valueOf(lhs));
			}
			else if(operator.equals("/"))
			{
				lhs /= rhs;
				calcOutput.setText(String.valueOf(lhs));
			}
		}
		else if(input.equals("."))
		{
			if(calcOutput.getText().contains("."))
			{
				output += "";
			}
			else
			{
				output += input;
				calcOutput.setText(output);
			}
		}
		else if(input.equals("CE"))
		{
				rhs = 0;
				output = "";
				calcOutput.setText(output);
			
		}
		else if(input.equals("Sqrt"))
		{
				lhs = Double.parseDouble(calcOutput.getText());
				lhs = Math.sqrt(lhs);
				calcOutput.setText(String.valueOf(lhs));
		}
		else if(input.equals("%"))
		{
		
				rhs =  lhs * (Double.parseDouble(calcOutput.getText())/100);
				calcOutput.setText(String.valueOf(rhs));
		}
		
	
		
		
		
		
	
	}
	
}