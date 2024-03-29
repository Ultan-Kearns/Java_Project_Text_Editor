
//imports
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GUI extends TextEditor {
	/**
	 * Code by Ultan Kearns ID: G00343745 E-Mail G00343745@gmit.ie Start Date:
	 * 02/10/17 End Date: Java Project for Software Year 2
	 */

	private static final long serialVersionUID = 1L;
	JTextArea text = new JTextArea(800, 600);

	// Constructor for GUI
	public GUI() {
		JPanel pane = new JPanel();
		setTitle("Text Editor");
		setSize(800, 600);
		JMenuBar menu = new JMenuBar();
		// menus for menuitems
		JMenu file = new JMenu("File");
		JMenu edit = new JMenu("Edit");
		JMenu font = new JMenu("Font");
		JMenu style = new JMenu("Font-style");
		// set scrollbars for textarea
		JScrollPane scroll = new JScrollPane(text, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		// Allow user to edit text
		text.setEditable(true);
		// Set so text moves to new line when reaches end of window
		text.setLineWrap(true);
		// set default font and size
		text.setFont(new Font("Arial", Font.PLAIN, 12));
		// menuitems
		JMenuItem clear = new JMenuItem("Clear Text");
		JMenuItem save = new JMenuItem("Save");
		JMenuItem readjust = new JMenuItem("Resize");
		JMenuItem readF = new JMenuItem("Open File");
		// declare event handles
		readFile read = new readFile();
		saveFile saveF = new saveFile();
		clear clearTextArea = new clear();
		resize changeSize = new resize();
		file.add(readF);
		file.add(clear);
		file.add(save);
		edit.add(readjust);
		edit.add("Font Size").addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int size = Integer
							.parseInt(JOptionPane.showInputDialog(null, "Enter size you would like font to be:"));
					Font f = (new Font(text.getFont().toString(), text.getFont().getStyle(), size));
					if (size >= 12 && size <= 100) {
						text.setFont(f);
					} else {
						throw new Exception();
					}
				} catch (Exception fontResize) {
					JOptionPane.showMessageDialog(null,
							"Font must be between 12 and 100\nPlease make sure input is valid", "Font Resize Error",
							JOptionPane.ERROR_MESSAGE, null);
				}
			}
		});
		// add action listeners to fonts and styles using lambda functions
		style.add("Bold").addActionListener(e -> {
			Font f = (new Font(text.getFont().toString(), Font.BOLD, text.getFont().getSize()));
			text.setFont(f);
		});
		style.add("Italic").addActionListener(e -> {
			Font f = (new Font(text.getFont().toString(), Font.ITALIC, text.getFont().getSize()));
			text.setFont(f);
		});
		style.add("Plain").addActionListener(e -> {
			Font f = (new Font(text.getFont().toString(), Font.PLAIN, text.getFont().getSize()));
			text.setFont(f);
		});
		font.add("Arial").addActionListener(e -> {
			text.setFont(new Font("Arial", Font.PLAIN, text.getFont().getSize()));
		});
		font.add("Georgia").addActionListener(e -> {
			text.setFont(new Font("Georgia", Font.PLAIN, text.getFont().getSize()));
		});
		font.add("Times New Roman").addActionListener(e -> {
			text.setFont(new Font("Times New Roman", Font.PLAIN, text.getFont().getSize()));
		});
		font.add("Monospace").addActionListener(e -> {
			text.setFont(new Font("Monospace", Font.PLAIN, text.getFont().getSize()));
		});
		// add actionlisteners to JMenuItems
		save.addActionListener(saveF);
		clear.addActionListener(clearTextArea);
		readjust.addActionListener(changeSize);
		readF.addActionListener(read);
		// add JMenus to JMenuBar obj
		menu.add(file);
		menu.add(edit);
		menu.add(font);
		menu.add(style);
		pane.add(scroll, text);
		setJMenuBar(menu);
		add(scroll);
		setResizable(true);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	final public class saveFile implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// allow user to enter file name
			String s = JOptionPane.showInputDialog("Input name of file:");
			String w = "";
			// Allow user to enter directory
			w = JOptionPane.showInputDialog("Input Directory where you want the file:\n" + "eg C:\\ for C drive");
			// try catch block
			try {
				// check if fields are null or empty
				if (!(s.isEmpty() && w.isEmpty()) && (w != null && s != null)) {
					try {
						// Create new file and append with .rtf
						File userFile = new File(w + s + ".rtf");
						PrintWriter file = new PrintWriter(userFile);
						// set files content = text
						file.print(text.getText());
						file.close();
						File dir = new File(w);
						if (dir.exists()) {
							JOptionPane.showMessageDialog(null, "File " + s + " created " + "Saved in " + w, null,
									JOptionPane.PLAIN_MESSAGE);
						} else {
							throw new Exception();
						}

					} catch (Exception fileNotCreatedException) {
						JOptionPane.showMessageDialog(null, "File not created\n"
								+ "Please check to see if app is running in administator mode\n and directory exists.",
								"File Creation Error", JOptionPane.ERROR_MESSAGE, null);
						fileNotCreatedException.printStackTrace();
					}

				}
			} catch (Exception fileDirectory) {
				// print out error message if either field is empty
				JOptionPane.showMessageDialog(null, "File name or directory cannot be null", "Null Name/Dir Error",
						JOptionPane.ERROR_MESSAGE, null);
			}
			// if failure occurs

		}
	}

	public class readFile implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// allow user to enter file name
			String s = JOptionPane.showInputDialog("Input name of file:");
			String w = "";

			// Allow user to enter directory
			w = JOptionPane.showInputDialog("Input Directory where file is located:\n" + "eg C:\\ for C drive");
			// check if fields are null or empty
			if (!(s.isEmpty() && w.isEmpty()) && (w != null && s != null)) {
				// Create new file and append with .rtf
				File file = new File(w + s + ".rtf");
				Scanner input;
				try {
					input = new Scanner(file);
					String inFile = "";
					while (input.hasNextLine()) {
						inFile += input.nextLine();
						inFile += "\n";
					}
					text.setText(inFile);
					input.close();
				} catch (FileNotFoundException e1) {
					JOptionPane.showMessageDialog(null, "Failure to read file", "Read File fail",
							JOptionPane.ERROR_MESSAGE, null);
				}
				// set files content = text
			} else {
				// print out error message if either field is empty
				JOptionPane.showMessageDialog(null, "File name or directory cannot be null",
						"File Directory or Name Null Error", JOptionPane.ERROR_MESSAGE, null);
			}
		}
	}

	public class clear implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// try catch block
			try {
				text.setText("");
			}
			// if failure occurs
			catch (Exception clearFail) {
				JOptionPane.showMessageDialog(null,
						"Something went wrong please try again!" + " Error: " + clearFail.getStackTrace(),
						"Clear Error", JOptionPane.WARNING_MESSAGE, null);
			}
		}
	}

	public class resize implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				String height = JOptionPane.showInputDialog("Enter height: ");
				String width = JOptionPane.showInputDialog("Enter width: ");
				int h = Integer.parseInt(height);
				int w = Integer.parseInt(width);
				if (h >= 800 && w >= 600 && h <= 2560 && w <= 1440) {
					setSize(h, w);
				} else {
					throw new Exception();
				}
			} catch (Exception WindowResizeFail) {
				JOptionPane.showMessageDialog(null,
						"Invalid types entered\nTypes must be integer\n"
								+ "also window size should be minimum 800 * 600 and maximum 2560 * 1440",
						"Error Window Resize Error!", JOptionPane.ERROR_MESSAGE, null);
			}
		}
	}
}
