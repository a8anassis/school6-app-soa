package gr.aueb.cf.schoolapp.viewcontroller;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.sql.Connection;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import  gr.aueb.cf.schoolapp.Main;

public class MainMenuFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JPanel footer = new JPanel();
	private static Connection connection;

	public MainMenuFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Thread.currentThread().getContextClassLoader().getResource("eduv2.png")));

		setBackground(new Color(255, 255, 255));
		setTitle("Ποιότητα στην Εκπαίδευση");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 363);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSeparator headerSeparator = new JSeparator();
		headerSeparator.setBounds(9, 71, 426, 1);
		contentPane.add(headerSeparator);
		
		JPanel header = new JPanel();
		header.setBackground(new Color(0, 0, 164));
		header.setBounds(-1, 0, 437, 60);
		contentPane.add(header);
		header.setLayout(null);
		
		JLabel codingFactoryLabel = new JLabel("Coding Factory");
		codingFactoryLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		codingFactoryLabel.setForeground(new Color(255, 255, 255));
		codingFactoryLabel.setBounds(10, 11, 131, 25);
		header.add(codingFactoryLabel);
		
		JButton teachersBtn = new JButton("");
		teachersBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getTeachersMenuFrame().setVisible(true);
				Main.getMainMenuFrame().setEnabled(false);
			}
		});
		teachersBtn.setBounds(10, 108, 40, 40);
		contentPane.add(teachersBtn);
		
		JLabel teachersLabel = new JLabel("Εκπαιδευτές");
		teachersLabel.setForeground(new Color(0, 0, 255));
		teachersLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		teachersLabel.setBounds(54, 108, 97, 40);
		contentPane.add(teachersLabel);
		
		JButton studentsBtn = new JButton("");
		studentsBtn.setBounds(10, 171, 40, 40);
		contentPane.add(studentsBtn);
		
		JLabel studentsLabel = new JLabel("Εκπαιδευόμενοι");
		studentsLabel.setForeground(Color.BLUE);
		studentsLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		studentsLabel.setBounds(54, 171, 111, 40);
		contentPane.add(studentsLabel);
		footer.setBackground(new Color(217, 217, 217));
		footer.setBounds(-1, 261, 438, 65);
		contentPane.add(footer);
		footer.setLayout(null);
		
		JLabel manual = new JLabel("Εγχειρίδιο Χρήσης");
		manual.setForeground(new Color(0, 0, 255));
		manual.setFont(new Font("Tahoma", Font.PLAIN, 12));
		manual.setBounds(24, 27, 102, 27);
		footer.add(manual);
	}
}
