//Ultan Kearns
//imports
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class GUI extends TextEditor{
	JTextArea text = new JTextArea(800,600);
	//Constructor
	public GUI() 
	{
		JPanel pane = new JPanel();
		setTitle("Text Editor"); 
		setSize(800,600);
		JMenuBar menu = new JMenuBar();
		//menus for menuitems
		JMenu file = new JMenu("File"); 
		JMenu edit = new JMenu("Edit");
		//set scrollbars for textarea
		JScrollPane scroll = new JScrollPane(text,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		//Allow user to edit text
		text.setEditable(true); 
		//Set so text moves to new line when reaches end of window
		text.setLineWrap(true); 
		//menuitems
		JMenuItem clear = new JMenuItem("Clear Text"); 
		JMenuItem save = new JMenuItem("Save");
		JMenuItem readjust = new JMenuItem("Resize");
		JMenuItem readF = new JMenuItem("Open File");
		//declare event handles
		readFile read = new readFile();
		saveFile saveF = new saveFile();
		clear clearTextArea = new clear();
		resize changeSize = new resize();
		file.add(readF);
		file.add(clear);
		file.add(save);
		edit.add(readjust);
		//add actionlisteners to JMenuItems
		save.addActionListener(saveF);
		clear.addActionListener(clearTextArea);
		readjust.addActionListener(changeSize);
		readF.addActionListener(read);
		//add JMenus to JMenuBar obj
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
			//allow user to enter file name
			String s = JOptionPane.showInputDialog("Input name of file:");
			String w = "";
			try
			{
				//Allow user to enter directory
				w = JOptionPane.showInputDialog("Input Directory where you want the file:\n"
						+ "eg C:\\ for C drive");
			}
			catch(Exception writeFile)
			{
				JOptionPane.showMessageDialog(null, "Task could not be completed\nPlease try again"
						,"Cannot write file error", JOptionPane.ERROR_MESSAGE, null);
			}
			//try catch block
			try
			{
				//check if fields are null or empty
				if(!(s.isEmpty() && w.isEmpty()) && (w != null && s != null))
				{
					//Create new file and append with .txt
					File userFile =  new File(w + s + ".txt"); 
					PrintWriter file = new PrintWriter(userFile); 
					//set files content = text
					file.println(text.getText());
					JOptionPane.showMessageDialog(null,"File created and saved in " + w);
					file.close();
				}
				else
				{
					//print out error message if either field is empty
					JOptionPane.showMessageDialog(null, "File name or directory cannot be null"
							,"Null Error", JOptionPane.ERROR_MESSAGE, null);
				}
				
			}
			//if failure occurs
			catch(Exception file)
			{
				JOptionPane.showMessageDialog(null,"File not created\n"
						+ "Please check to see if file name entered already exists","File Error", 
						JOptionPane.ERROR_MESSAGE, null);
				file.printStackTrace();
			}
		}
	}
	public class readFile implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			//allow user to enter file name 
			String s = JOptionPane.showInputDialog("Input name of file:");
			String w = "";
			
			//Allow user to enter directory
			w = JOptionPane.showInputDialog("Input Directory where file is located:\n"
			+ "eg C:\\ for C drive");
			//check if fields are null or empty
			if(!(s.isEmpty() && w.isEmpty()) && (w != null && s != null))
			{
				//Create new file and append with .txt
				File file = new File(w + s + ".txt"); 
				Scanner input;
				try {
					input = new Scanner(file);
					while(input.hasNext())
					{
						text.setText(input.nextLine());
					}
					input.close();
				} catch (FileNotFoundException e1) {
					JOptionPane.showMessageDialog(null, "Failure to read file");
				} 
				//set files content = text
			}
			else
			{
				//print out error message if either field is empty
				JOptionPane.showMessageDialog(null, "File name or directory cannot be null"
						,"Null Error", JOptionPane.ERROR_MESSAGE, null);
			}
		}
	}
	public class clear implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			//try catch block
			try
			{
				text.setText("");
			}
			//if failure occurs
			catch(Exception clearFail)
			{
				JOptionPane.showMessageDialog(null, "Something went wrong please try again!" + 
			" Error: " + clearFail.getStackTrace());
			}
		}
	}
	public class resize implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			try 
			{
				String height = JOptionPane.showInputDialog("Enter height: ");
				String width = JOptionPane.showInputDialog("Enter width: ");
				int h = Integer.parseInt(height);
				int w = Integer.parseInt(width);
				setSize(h,w);
			}
			catch(Exception reSizeFail)
			{
				JOptionPane.showMessageDialog(null, "Invalid type entered must be integer","Error", JOptionPane.ERROR_MESSAGE, null);
			}
		}
	}
}

