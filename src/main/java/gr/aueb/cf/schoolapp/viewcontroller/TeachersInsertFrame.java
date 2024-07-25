package gr.aueb.cf.schoolapp.viewcontroller;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gr.aueb.cf.schoolapp.Main;
import gr.aueb.cf.schoolapp.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.dao.TeacherDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapp.dto.TeacherReadOnlyDTO;
import gr.aueb.cf.schoolapp.dto.TeacherUpdateDTO;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.ITeacherService;
import gr.aueb.cf.schoolapp.service.TeacherServiceImpl;
import gr.aueb.cf.schoolapp.service.util.DBUtil;
import gr.aueb.cf.schoolapp.validator.TeacherValidator;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Toolkit;
import java.util.Map;

public class TeachersInsertFrame extends JFrame {

	// Wiring
	private final ITeacherDAO teacherDAO = new TeacherDAOImpl();
	private final ITeacherService teacherService = new TeacherServiceImpl(teacherDAO);

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField firstnameText;
	private JTextField lastnameText;

	private JLabel errorFirstname;
	private JLabel errorLastname;


	public TeachersInsertFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Thread.currentThread().getContextClassLoader().getResource("eduv2.png")));
		setTitle("Εισαγωγή Εκπαιδευτών");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				firstnameText.setText("");
				lastnameText.setText("");
			}
		});
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 442, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel firstnameLabel = new JLabel("Όνομα");
		firstnameLabel.setForeground(new Color(0, 0, 255));
		firstnameLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		firstnameLabel.setBounds(42, 60, 41, 25);
		contentPane.add(firstnameLabel);
		
		firstnameText = new JTextField();
		firstnameText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String inputFirstname;	
				inputFirstname = firstnameText.getText().trim();	
				
				if (inputFirstname.equals("")) {
					errorFirstname.setText("Το όνομα είναι υποχρεωτικό");
				}
				
				if (!inputFirstname.equals("")) {
					errorFirstname.setText("");
				}
			}
		});
		firstnameText.setFont(new Font("Tahoma", Font.PLAIN, 12));
		firstnameText.setBounds(85, 60, 258, 25);
		contentPane.add(firstnameText);
		firstnameText.setColumns(10);
		
		JLabel lastnameLabel = new JLabel("Επώνυμο");
		lastnameLabel.setForeground(new Color(0, 0, 255));
		lastnameLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lastnameLabel.setBounds(32, 123, 55, 14);
		contentPane.add(lastnameLabel);
		
		lastnameText = new JTextField();
		lastnameText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String inputLastname;
				inputLastname = lastnameText.getText().trim();
				
				if (inputLastname.equals("")) {
					errorLastname.setText("Το επώνυμο είναι υποχρεωτικό");
				}
				
				if (!inputLastname.equals("")) {
					errorLastname.setText("");
				}
				
			}
		});
		lastnameText.setBounds(85, 118, 258, 25);
		contentPane.add(lastnameText);
		lastnameText.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(21, 23, 354, 179);
		contentPane.add(panel);
		panel.setLayout(null);
		
		errorFirstname = new JLabel("");
		errorFirstname.setForeground(new Color(255, 0, 0));
		errorFirstname.setBounds(64, 66, 258, 25);
		panel.add(errorFirstname);
		
		errorLastname = new JLabel("");
		errorLastname.setForeground(Color.RED);
		errorLastname.setBounds(64, 129, 258, 25);
		panel.add(errorLastname);
		
		JButton insertBtn = new JButton("Εισαγωγή");
		insertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Map<String, String> errors;
				TeacherInsertDTO insertDTO = new TeacherInsertDTO();
				String firstnameMessage;
				String lastnameMessage;
				Teacher teacher;

				try {
					// Data binding
					insertDTO.setFirstname(firstnameText.getText().trim());
					insertDTO.setLastname(lastnameText.getText().trim());

					// Validation
					errors = TeacherValidator.validate(insertDTO);

					if (!errors.isEmpty()) {
						firstnameMessage = errors.getOrDefault("firstname", "");
						lastnameMessage = errors.containsKey("lastname") ? errors.get("lastname") : "";

//						if (!firstnameMessage.isEmpty()) {
//							errorFirstname.setText(firstnameMessage);
//						}

						errorFirstname.setText(firstnameMessage);

						if (!lastnameMessage.isEmpty()) {
							errorLastname.setText(lastnameMessage);
						}

//						if (firstnameMessage.isEmpty()) {
//							errorFirstname.setText("");
//						}

						if (lastnameMessage.isEmpty()) {
							errorLastname.setText("");
						}

						return;
					}

					teacher = teacherService.insertTeacher(insertDTO);
					TeacherReadOnlyDTO readOnlyDTO = mapToReadOnlyDTO(teacher);

					JOptionPane.showMessageDialog(null, "Teacher with lastname: " + readOnlyDTO.getLastname(),
							"Insert Teacher", JOptionPane.INFORMATION_MESSAGE);

				} catch (TeacherDAOException ex) {
					//ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Insertion Error", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		insertBtn.setForeground(new Color(0, 0, 255));
		insertBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		insertBtn.setBounds(156, 213, 109, 39);
		contentPane.add(insertBtn);
		
		JButton closeBtn = new JButton("Κλείσιμο");
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getTeachersMenuFrame().setEnabled(true);
				Main.getTeachersInsertFrame().setVisible(false);
			}
		});
		closeBtn.setIcon(null);
		closeBtn.setForeground(Color.BLUE);
		closeBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		closeBtn.setBounds(266, 213, 109, 39);
		contentPane.add(closeBtn);
	}

	private TeacherReadOnlyDTO mapToReadOnlyDTO(Teacher teacher) {
		return new TeacherReadOnlyDTO(teacher.getId(), teacher.getFirstname(), teacher.getLastname());
	}
}
