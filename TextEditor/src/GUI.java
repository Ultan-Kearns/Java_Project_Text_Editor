import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GUI extends TextEditor {
	public GUI() //constructor
	{
		JPanel pane = new JPanel();
		setTitle("Text Editor");
		setSize(800,600);
		pane.setLayout(new GridLayout());
		JTextArea text = new JTextArea(20,20);
		JMenuBar menu = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenu edit = new JMenu("Edit");
		JScrollPane scroll = new JScrollPane(text);
		scroll.createHorizontalScrollBar();
		scroll.createVerticalScrollBar();
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		text.setEditable(true); //Allow user to edit text
		text.setLineWrap(true); //Set so text moves to new line when reaches end
		text.setWrapStyleWord(true);
		JMenuItem newFile = new JMenuItem("New File");
		JMenuItem rename = new JMenuItem("Rename"); //Turn these to JMenuItems to implement action
		file.add("Delete");
		file.add("Save");
		edit.add("Replace");
		menu.add(file);
		menu.add(edit);
		pane.add(scroll,BorderLayout.CENTER);
		setJMenuBar(menu);
		pane.add(scroll);
		add(text);
		pack();
		setResizable(true);
		setVisible(true); 
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public class saveFile implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			String s = JOptionPane.showInputDialog("Input name of file:");
			try
			{
				PrintWriter file = new PrintWriter(s + ".txt");
				file.close();
			}
			catch(Exception file)
			{
				System.out.println("File not created\n"
						+ "Please check to see if file name entered already exists");
			}
		}
	}
}

