package gr.aueb.cf.schoolapp;

import gr.aueb.cf.schoolapp.viewcontroller.MainMenuFrame;
import gr.aueb.cf.schoolapp.viewcontroller.TeachersInsertFrame;
import gr.aueb.cf.schoolapp.viewcontroller.TeachersMenuFrame;
import gr.aueb.cf.schoolapp.viewcontroller.TeachersUpdateDeleteFrame;

import java.awt.EventQueue;

public class Main {

	private final static MainMenuFrame mainMenuFrame = new MainMenuFrame();
	private final static TeachersMenuFrame teachersMenuFrame = new TeachersMenuFrame();
	private final static TeachersInsertFrame teachersInsertFrame = new TeachersInsertFrame();
	private final static TeachersUpdateDeleteFrame teachersUpdateDeleteFrame = new TeachersUpdateDeleteFrame();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					mainMenuFrame.setLocationRelativeTo(null);
					mainMenuFrame.setVisible(true);
					
					teachersMenuFrame.setLocationRelativeTo(null);
					teachersMenuFrame.setVisible(false);
					
					teachersInsertFrame.setLocationRelativeTo(null);
					teachersInsertFrame.setVisible(false);
					
					teachersUpdateDeleteFrame.setLocationRelativeTo(null);
					teachersUpdateDeleteFrame.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static MainMenuFrame getMainMenuFrame() {
		return mainMenuFrame;
	}

	public static TeachersMenuFrame getTeachersMenuFrame() {
		return teachersMenuFrame;
	}

	public static TeachersInsertFrame getTeachersInsertFrame() {
		return teachersInsertFrame;
	}

	public static TeachersUpdateDeleteFrame getTeachersUpdateDeleteFrame() {
		return teachersUpdateDeleteFrame;
	}

}
