//Ultan Kearns
package Gui;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;

public class Gui extends JFrame  //text editor gui
{
	
	public Gui() //constructor
	{
		setTitle("Text Editor");
		setSize(800,600);
		setResizable(true);
		setLayout(new FlowLayout());
		JTextArea text = new JTextArea(200,70);
		JMenuBar menubar = new JMenuBar();
		JScrollBar horizontal = new JScrollBar();
		text.setEditable(true);
		add(text);
		add(menubar);
		add(horizontal);
		setVisible(true); 
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
