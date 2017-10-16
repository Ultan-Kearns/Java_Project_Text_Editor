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
		JMenuBar menu = new JMenuBar();
		JMenu file = new JMenu("File"); //menus for menuitems
		JMenu edit = new JMenu("Edit");
		JScrollPane scroll = new JScrollPane(text,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);//set scrollbars for textarea
		text.setEditable(true); //Allow user to edit text
		text.setLineWrap(true); //Set so text moves to new line when reaches end
		JMenuItem delete = new JMenuItem("Delete"); //menuitems
		JMenuItem save = new JMenuItem("Save");	
		saveFile saveF = new saveFile(); //declare event handles
		delete deleteText = new delete();
		file.add(delete);
		file.add(save);
		save.addActionListener(saveF);
		delete.addActionListener(deleteText);
		menu.add(file);
		menu.add(edit);
		pane.add(scroll,text);
		setJMenuBar(menu);
		add(scroll);
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
				if(s != null)
				{
					File userFile =  new File("C:/" + s + ".txt"); 
					PrintWriter file = new PrintWriter(userFile); 
					file.println(text.getText());
					JOptionPane.showMessageDialog(null,"File created and saved in C:\\ drive");
					file.close();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "File name cannot be null");
				}
				
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
	public class delete implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			//try catch block
			try
			{
				text.setText("");
			}
			//if failure occurs
			catch(Exception deleteFail)
			{
				JOptionPane.showMessageDialog(null, "Something went wrong please try again!" + 
			" Error: " + deleteFail.getStackTrace());
			}
		}
	}
}

