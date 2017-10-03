//Ultan Kearns
package Gui;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Gui extends JFrame  //text editor gui
{
	private static final long serialVersionUID = 1L;

	public Gui() //constructor
	{
		setTitle("Text Editor");
		setSize(800,600);
		setResizable(true);
		setLayout(new FlowLayout());
		JTextArea text = new JTextArea(20,20);
		JMenuBar menu = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenu edit = new JMenu("Edit");
		JScrollPane scroll = new JScrollPane(text);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		text.setEditable(true);
		file.add("New File");
		file.add("Rename");
		file.add("Delete");
		edit.add("Replace");
		menu.add(file);
		menu.add(edit);
		add(text);
		add(scroll);
		setJMenuBar(menu);
		setResizable(false);
		setVisible(true); 
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
