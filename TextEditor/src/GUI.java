//Ultan Kearns
//imports
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
		//declare event handles
		saveFile saveF = new saveFile();
		clear clearTextArea = new clear();
		resize changeSize = new resize();
		file.add(clear);
		file.add(save);
		edit.add(readjust);
		//add actionlisteners to JMenuItems
		save.addActionListener(saveF);
		clear.addActionListener(clearTextArea);
		readjust.addActionListener(changeSize);
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
			//allow user to enter file name and append with .txt
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
				if(!(s.isEmpty() && w.isEmpty()) && (w != null && s != null))
				{
					//Create new file
					File userFile =  new File(w + s + ".txt"); 
					PrintWriter file = new PrintWriter(userFile); 
					//set files content = text
					file.println(text.getText());
					JOptionPane.showMessageDialog(null,"File created and saved in " + w);
					file.close();
				}
				else
				{
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

