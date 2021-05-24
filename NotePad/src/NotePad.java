import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;

public class NotePad {

	private JFrame frame;
	static JDialog d;
	private JTextField currentPath;
	private JTextField fileName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NotePad window = new NotePad();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NotePad() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame("Note Pad");
		frame.getContentPane().setBackground(new Color(105, 105, 105));
		frame.setBounds(100, 100, 600, 458);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);
		
		JButton SaveTo = new JButton("Save to");
		SaveTo.setBackground(new Color(240, 248, 255));
		SaveTo.setFont(new Font("Simplified Arabic", Font.BOLD, 13));
		SaveTo.setBounds(495, 9, 79, 28);
		frame.getContentPane().add(SaveTo);
		
		currentPath = new JTextField();
		currentPath.setFont(new Font("Simplified Arabic", Font.PLAIN, 13));
		currentPath.setBounds(82, 12, 207, 23);
		frame.getContentPane().add(currentPath);
		currentPath.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Arial", Font.PLAIN, 13));
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(10, 50, 564, 359);
		frame.getContentPane().add(scrollPane);
		
		fileName = new JTextField();
		fileName.setFont(new Font("Simplified Arabic", Font.PLAIN, 13));
		fileName.setText("file.txt");
		fileName.setBounds(365, 11, 120, 24);
		frame.getContentPane().add(fileName);
		fileName.setColumns(10);
		
		JLabel lblFileName = new JLabel("File name");
		lblFileName.setForeground(new Color(255, 255, 255));
		lblFileName.setFont(new Font("Simplified Arabic", Font.BOLD, 13));
		lblFileName.setHorizontalAlignment(SwingConstants.CENTER);
		lblFileName.setBounds(299, 9, 55, 28);
		frame.getContentPane().add(lblFileName);
		
		JLabel lblPath = new JLabel("Path");
		lblPath.setForeground(new Color(255, 255, 255));
		lblPath.setFont(new Font("Simplified Arabic", Font.BOLD, 14));
		lblPath.setHorizontalAlignment(SwingConstants.CENTER);
		lblPath.setBounds(10, 9, 62, 28);
		frame.getContentPane().add(lblPath);
		
		SaveTo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(!currentPath.getText().equals("")) {
					
					File parent = new File(currentPath.getText());
					System.out.println("parent file created!!!");
					
					if(!parent.exists()) {
						parent.mkdirs();
						System.out.println("parent file created!!!");
					}
					
					String[] files = parent.list();
					boolean status = false;
					
					if(!fileName.getText().equals("")) {
						for(String s : files) {
							if(fileName.getText().equals(s)) {
								status = true;
								break;
							}else {
								status = false;
							}
						}
						System.out.println(status);
						System.out.println(currentPath.getText());
						System.out.println(fileName.getText());
						if(status == false) {
							File file = new File(parent,"//"+ fileName.getText());
							FileOutputStream out;
							try {
								file.createNewFile();
								System.out.println("file created!");
								out = new FileOutputStream(file);
								out.write(textArea.getText().getBytes());
								System.out.println("File writed!");
							} catch (IOException e) {
								e.printStackTrace();
							}
							
							d = new JDialog(frame, "Save to path");
							d.getContentPane().setLayout( new FlowLayout() ); 
							JLabel l = new JLabel("File saved at : "+ currentPath.getText() + " succesfully");
							l.setBounds(100, 30, 290, 90);
							d.getContentPane().add(l);
							d.setSize(300,100);    
					        d.setVisible(true);
						} else {
							d = new JDialog(frame, "File name error");
							d.getContentPane().setLayout( new FlowLayout() ); 
							JLabel l = new JLabel("File name exists in path : " + currentPath.getText() + " " + fileName.getText());
							l.setBounds(100, 30, 290, 90);
							d.getContentPane().add(l);
							d.setSize(300,100);    
					        d.setVisible(true);
						}
						
					}
					
				}
				
			}
		});
		
	}
}


















