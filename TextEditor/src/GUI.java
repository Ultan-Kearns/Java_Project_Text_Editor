import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.PrintWriter;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class GUI extends TextEditor{
	JTextArea text = new JTextArea(800,600);
	 
	public GUI() //constructor
	{
		JPanel pane = new JPanel();
		setTitle("Text Editor");
		setSize(800,600);
		pane.setLayout(new GridLayout());
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
		JMenuItem delete = new JMenuItem("Delete");
		JMenuItem save = new JMenuItem("Save");
		file.add(delete);
		file.add(save);
		edit.add("Replace");
		saveFile saveF = new saveFile();
		save.addActionListener(saveF);
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
			//allow user to enter file name and append with .txt
			String s = JOptionPane.showInputDialog("Input name of file:");
			//try catch block
			try
			{
				//Create new file
				File userFile =  new File("C:/" + s + ".txt"); 
				PrintWriter file = new PrintWriter(userFile); 
				file.println(text.getText());
				JOptionPane.showMessageDialog(null,"File created and saved in C:\\ drive");
				file.close();
			}
			//if failure occurs
			catch(Exception file)
			{
				JOptionPane.showMessageDialog(null,"File not created\n"
						+ "Please check to see if file name entered already exists");
				file.printStackTrace();
			}
		}
	}
}

