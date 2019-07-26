import java.awt.*;
import java.awt.event.*;
//--created by Paul Zänker

public class CalcPanel extends Panel
{
	String s1, s2, operator;
	double result;
	boolean point;
	TextField tf = new TextField();
	Button bs = new Button("Backspace");
	Button[] button = new Button[20]; 
	static final String[] symbol = {"M+", "7", "8", "9", "/", "M-", "4", "5", "6", "*", "MR", "1", "2", "3", "-", "CE", ".", "0", "=", "+",};

	//Anonymous Classes (Listeners)
	ActionListener nl = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{	
			tf.setText(tf.getText() + e.getActionCommand());
		}
	};

	//Berechnungen
	ActionListener ol = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			if (!(e.getActionCommand().equals("="))) 
			{
				s1 = tf.getText();
				tf.setText("");
				operator = e.getActionCommand();
			}

			if(e.getActionCommand().equals("="))
			{
				switch(operator)
				{
					case "+" :result=Double.parseDouble(s1)+Double.parseDouble(tf.getText()); break;
					case "-" :result=Double.parseDouble(s1)-Double.parseDouble(tf.getText()); break;
					case "*" :result=Double.parseDouble(s1)*Double.parseDouble(tf.getText()); break;
					case "/" :result=Double.parseDouble(s1)/Double.parseDouble(tf.getText()); break;
				}

				tf.setText(String.valueOf(result));
			}
		}
	};

	//Löschen
	ActionListener dl = new ActionListener() 
	{
		public void actionPerformed(ActionEvent e)
		{
			tf.setText("");
			point = false;
		}
	};

	//Punkt
	ActionListener pl = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			if(!point) 
			tf.setText(tf.getText() + e.getActionCommand());
			point = true;
		}
	};

	//M Funktionen
	ActionListener ml = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getActionCommand().equals("M+"))
			{
				s2 = tf.getText();
				tf.setText("");
				s2 += tf.getText();
				tf.setText(s2);
			}
			
			if(e.getActionCommand().equals("M-"))
			{
				s2 = "";
			}

			if(e.getActionCommand().equals("MR"))
			{
				tf.setText(s2);
			}
		}
	};

	//Letzen Wert löschen
	ActionListener bl = new ActionListener() 
	{
		public void actionPerformed(ActionEvent e)
		{
			if(tf.getText().length() > 0)
			tf.setText(tf.getText().substring(0, tf.getText().length() - 1));
			point = false;
	    }
	};
	
	ActionListener[] al = {ml, nl, nl, nl, ol, ml, nl, nl, nl, ol, ml, nl, nl, nl, ol, dl, pl, nl, ol, ol}; 

	public CalcPanel()
	{
		bs.setFont(new Font("sanserif",Font.PLAIN,15));
		bs.addActionListener(bl);
		tf.setFont(new Font("monospaced",Font.BOLD,17));

		//GridLayout
		Panel buttonPanel = new Panel();
		buttonPanel.setLayout(new GridLayout(4,5,3,3));
		for(int i=0; i<symbol.length; i++)
		{
			button[i] = new Button(symbol[i]);
			button[i].addActionListener(al[i]); 
			buttonPanel.add(button[i]);

			if(i > 0 && i < 4 || i > 5 && i < 9 || i > 10 && i < 14 || i == 17)
			{button[i].setFont(new Font("monospaced",Font.BOLD,18));}
			else
			if(i == 0 || i > 3 && i < 6 || i > 8 && i < 11 || i > 13 && i < 17 || i > 17)
			{button[i].setFont(new Font("sanserif",Font.ITALIC,17));}
		}

		//BorderLayout
		setLayout(new BorderLayout());
		add(tf,BorderLayout.NORTH);
		add(buttonPanel,BorderLayout.CENTER);
		add(bs,BorderLayout.SOUTH);
	}

	@Override
	public Dimension getPreferredSize()
	{return new Dimension(300, 200);}

	public static void main(String args[])
	{
      Frame F=new Frame("Rechner");
      F.addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent we){System.exit(0);}});
      CalcPanel C=new CalcPanel();
      F.add(C);
      F.pack();
      F.setVisible(true);
  }
}