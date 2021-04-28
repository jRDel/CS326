import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.io.*;

import java.awt.event.*;

import java.util.ArrayList;


public class ColorApp extends JFrame
{
	protected Drawing colorBlock;
	protected JButton buttonSave;
	protected JButton buttonReset;
	protected JButton plusR;
	protected JButton minusR;
	protected JButton plusG;
	protected JButton minusG;
	protected JButton plusB;
	protected JButton minusB;
	protected JTextField Red;
	protected JLabel labelRed;
	protected JTextField Blue;
	protected JLabel labelBlue;
	protected JTextField Green;
	protected JLabel labelGreen;
	protected JMenuBar mb;
	protected JMenu m;
	protected JMenuItem mi[];
	protected JList listColors;
	protected static ArrayList <String> lines;

	//protected FileIODemo file;
 
  	public static void main(String argv []) throws IOException
	{

		new ColorApp("Color Sampler");
			FileInputStream stream = new FileInputStream("colors.txt");
			InputStreamReader reader = new InputStreamReader(stream);
			StreamTokenizer tokens = new StreamTokenizer(reader);

			String s;
			int n1, n2, n3;
			
			lines = new ArrayList<String>();

			while(tokens.nextToken()!=tokens.TT_EOF)
			{
				s = (String) tokens.sval;
				tokens.nextToken();
				n1= (int) tokens.nval;
				tokens.nextToken();
				n2 = (int) tokens.nval;
				tokens.nextToken();
				n3 = (int) tokens.nval;

				String line = (s+ " " + n1 + " " + n2 + " " + n3);
				lines.add(line);
			}
			stream.close();
	}
	

	
	public ColorApp(String title)
	{
		
		super(title);
		addWindowListener(new WindowDestroyer());
		
		listColors = new JList();
		listColors.addListSelectionListener(new ListHandler());
		
		getContentPane().add(new JScrollPane(listColors));

		buttonSave = new JButton("Save");
		buttonReset = new JButton("Reset");

		plusR = new JButton("+");
		minusR = new JButton("-");
		plusG = new JButton("+");
		minusG = new JButton("-");
		plusB = new JButton("+");
		minusB = new JButton("-");
		
		plusR.addActionListener(new ActionHandler());                 
		minusR.addActionListener(new ActionHandler());
		plusG.addActionListener(new ActionHandler());                 
		minusG.addActionListener(new ActionHandler());        
		plusB.addActionListener(new ActionHandler());                 
		minusB.addActionListener(new ActionHandler());   

		buttonSave.addActionListener(new ActionHandler());                 
		buttonReset.addActionListener(new ActionHandler());                 

		colorBlock = new Drawing();
		Red = new JTextField("");
		labelRed = new JLabel("Red:");
		labelGreen = new JLabel("Green:");
		labelBlue = new JLabel("Blue:");
		Green = new JTextField("");
		Blue = new JTextField("");

		mb = new JMenuBar();
		m = new JMenu("Actions");
		mi = new JMenuItem[2];
		mi[0] = new JMenuItem("Clear");
		mi[0].addActionListener(new ActionHandler());
		mi[1] = new JMenuItem("Exit");
		mi[1].addActionListener(new ActionHandler());
		m.add(mi[0]);
		m.add(new JSeparator());
		m.add(mi[1]);
		mb.add(m);
		setJMenuBar(mb);

		//Adding color block and color buttons
		getContentPane().add(colorBlock);
		getContentPane().setLayout(null);
		getContentPane().add(labelRed);
		getContentPane().add(Red);
		getContentPane().add(labelGreen);
		getContentPane().add(Green);
		getContentPane().add(labelBlue);
		getContentPane().add(Blue);

		String colors [] = {"Red", "Green", "Blue", "Yellow", "Cyan", "Magenta", "Orange", "Pink", "Grey", "Black", "White"};

		listColors.setListData(colors);
		
		//Save, reset, and list
		getContentPane().add(buttonSave);
		getContentPane().add(buttonReset);
		getContentPane().add(plusR);
		getContentPane().add(minusR);
		getContentPane().add(plusG);
		getContentPane().add(minusG);
		getContentPane().add(plusB);
		getContentPane().add(minusB);
		getContentPane().add(listColors);

		//Button bounding
		listColors.setBounds(300, 10, 100, 300);
		colorBlock.setBounds(10, 10, 270, 200);

		labelRed.setBounds(40, 220, 50, 30);
		Red.setBounds(80, 220, 50, 30);
		minusR.setBounds(140, 220, 50, 30);
		plusR.setBounds(180, 220, 50, 30);

		labelGreen.setBounds(40, 250, 50, 30);
		Green.setBounds(80, 250, 50, 30);
		minusG.setBounds(140, 250, 50, 30);
		plusG.setBounds(180, 250, 50, 30);

		labelBlue.setBounds(40, 280, 50, 30);
		Blue.setBounds(80, 280, 50, 30);
		minusB.setBounds(140, 280, 50, 30);
		plusB.setBounds(180, 280, 50, 30);

		buttonSave.setBounds(60, 320, 100, 50);
		buttonReset.setBounds(170, 320, 100, 50);

		//Default window size
		setSize(new Dimension(500, 500));
		setVisible(true);
		
		Red.setText("255");
		Green.setText("0");
		Blue.setText("0");

	}

	// Define window adapter                                       
	private class WindowDestroyer extends WindowAdapter 
	{      
		public void windowClosing(WindowEvent e) 
		{    System.exit(0);  }                                                             
	}
	// Define action listener                                       
	private class ActionHandler implements ActionListener 
	{      
		public void actionPerformed(ActionEvent e)
		{
		   if ( e.getSource() == buttonSave )
		   {
		      String s1 = Red.getText();
		      String s2 = Blue.getText();
			  String s3 = Green.getText();
		      System.out.println("R: " + s1 + " B: " + s2 + " G: " + s3);
			  colorBlock.repaint();
		   }
		   else if ( e.getSource() == buttonReset )
		   {
		      System.out.println("You pressed the Reset button.");
			  Red.setText("255");
		      Blue.setText("0");
			  Green.setText("0");
			  colorBlock.repaint();
		   }
		   else	if ( e.getSource() == mi[0] )		// clear
		   {
		      Red.setText("");
		      Blue.setText("");
			  Green.setText("");
		   }
		   else	if ( e.getSource() == mi[1] )		// exit
		      System.exit(0);
		   else if (e.getSource() == plusR || e.getSource() == plusG | e.getSource() ==  plusB)
		   {
				if(e.getSource() == plusR)
				{
					int num = Integer.parseInt(Red.getText());
					num = num + 5;
					Red.setText(String.valueOf(num));
				}
				if(e.getSource() == plusG)
				{
					int num = Integer.parseInt(Green.getText());
					num = num + 5;
					Green.setText(String.valueOf(num));
				}
				if(e.getSource() == plusB)
				{
					int num = Integer.parseInt(Blue.getText());
					num = num + 5;
					Blue.setText(String.valueOf(num));
				}
		   }
		   else if (e.getSource() == minusR || e.getSource() == minusG | e.getSource() ==  minusB)
		   {
				if(e.getSource() == minusR)
				{
					int num = Integer.parseInt(Red.getText());
					num = num - 5;
					Red.setText(String.valueOf(num));
				}
				if(e.getSource() == minusG)
				{
					int num = Integer.parseInt(Green.getText());
					num = num - 5;
					Green.setText(String.valueOf(num));
				}
				if(e.getSource() == minusB)
				{
					int num = Integer.parseInt(Blue.getText());
					num = num - 5;
					Blue.setText(String.valueOf(num));
				}
		   }
		}
	}                                                              

	//Define colorBlock
	public class Drawing extends JComponent
	{
		public void paint(Graphics g)
		{
			Dimension d = getSize();

			int r = Integer.parseInt(Red.getText());
			int gr = Integer.parseInt(Green.getText());
			int b = Integer.parseInt(Blue.getText());


			g.setColor(new Color(r, gr, b));
			g.fillRect(1, 1, d.width-2, d.height-2);

			//g.setColor(Color.black);
			//g.drawRect(1, 1, d.width-2, d.height-2);
			//g.drawLine(1, d.height-1, d.width-1, 1);
			//g.drawOval(d.width/2 -30, d.height/2 -30, 60, 60);
		}
	}


	//Define list listener
	private class ListHandler implements ListSelectionListener
	{
		public void valueChanged(ListSelectionEvent e)
		{
			if(e.getSource() == listColors)
			{
				if(!e.getValueIsAdjusting())
				{
					//int i = listColors.getSelectedIndex();
					String s = (String) listColors.getSelectedValue();
					System.out.println("Selected:" + s);
					

					String line = lines.get(1);
					String [] splitStr = line.split("\\s+");

					Red.setText(splitStr[1]);
					Green.setText(splitStr[2]);
					Blue.setText(splitStr[3]);
					//System.out.println("Position " + i + " selected: " +s);
					colorBlock.repaint();
					

				}
			}
		}
	}

}
