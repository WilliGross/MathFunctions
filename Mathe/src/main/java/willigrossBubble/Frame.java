package willigrossBubble;

//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.FocusEvent;
//import java.awt.event.FocusListener;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JScrollPane;
//import javax.swing.JTextField;
//import javax.swing.ScrollPaneConstants;
//import javax.swing.SwingConstants;
//import javax.swing.WindowConstants;
//import willigrossBubble.Frame;

public class Frame {
	
//	static JFrame frame1;
//	static JLabel label1, label2, label3, label4, label5, label6, label7, label8, label9, label10, label11, label12, label13, label14, label15, label16, label17;
//	static JButton button1, button2, button3, button4, button5, button6, button7, button8, button9, button10, button11, button12, button13, button14, button15, button16, button17, button18, button19,
//	button20, button21, button22, button23, button24;
//	static JButton back1, back2, back3;
//	static JButton MainButton;
//	static JTextField tfield1, tfield2, tfield3, tfield4, tfield5, tfield6, tfield7;
//	static JScrollPane sp, sp2;
//	
//	public Frame() {
//		frame1 = new JFrame();
//		frame1.setSize(600, 400);
//		frame1.setLocationRelativeTo(null);
//		frame1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//		frame1.setResizable(false);
//		frame1.setLayout(null);
//		
//		initialisierung();
//	}
//	
//	public static void initialisierung() {
//		label1 = new JLabel("Error with file!", SwingConstants.CENTER);
//		label2 = new JLabel("Error while loading file!", SwingConstants.CENTER);
//		label3 = new JLabel("What would you like to do?", SwingConstants.CENTER);
//		label4 = new JLabel("How would you like to create your function?", SwingConstants.CENTER);
//		label5 = new JLabel("f(x) = ", SwingConstants.CENTER);
//		label6 = new JLabel("What do you want to do?", SwingConstants.CENTER);
//		label7 = new JLabel("How do you want to mirror your function?", SwingConstants.CENTER);
//		label8 = new JLabel("", SwingConstants.CENTER);
//		label9 = new JLabel("Enter START and END value for x and STEP", SwingConstants.CENTER);
//		label10 = new JLabel("", SwingConstants.CENTER);
//		label11 = new JLabel("Check your point", SwingConstants.CENTER);
//		label12 = new JLabel("", SwingConstants.CENTER);
//		label13 = new JLabel("", SwingConstants.CENTER);
//		label14 = new JLabel("", SwingConstants.CENTER);
//		label15 = new JLabel("Do you want to save or remove your function?", SwingConstants.CENTER);
//		label16 = new JLabel("Which function do you want to reload?", SwingConstants.CENTER);
//		label17 = new JLabel("", SwingConstants.CENTER);
//		
//		button1 = new JButton("Create a function");
//		button2 = new JButton("Load a function");
//		button3 = new JButton("Intersection of two functions");
//		button4 = new JButton("Close");
//		button5 = new JButton("Type your function");
//		button6 = new JButton("Linear through 2 points");
//		button7 = new JButton("Exponential through 2 points");
//		button9 = new JButton("Go");
//		button10 = new JButton("Value Table");
//		button11 = new JButton("Check a particular point");
//		button12 = new JButton("Create mirrored version");
//		button13 = new JButton("Save or remove this function");
//		button14 = new JButton("X");
//		button15 = new JButton("Y");
//		button16 = new JButton("Origin");
//		button17 = new JButton("Back");
//		button18 = new JButton("Go");
//		button19 = new JButton("Back");
//		button20 = new JButton("Go");
//		button21 = new JButton("Back");
//		button22 = new JButton("Save");
//		button23 = new JButton("Remove");
//		button24 = new JButton("Ok");
//		
//		back1 = new JButton("Back");
//		back2 = new JButton("Back");
//		back3 = new JButton("Back");
//		
//		tfield1 = new JTextField();
//		tfield2 = new JTextField("Start value for x");
//		tfield3 = new JTextField("End value for x");
//		tfield4 = new JTextField("Step");
//		tfield5 = new JTextField("X-Coordinate of Point p");
//		tfield6 = new JTextField("Y-Coordinate of Point p");
//		tfield7 = new JTextField("Which function would you like to select?");
//		
//		sp = new JScrollPane(label10, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
//	            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//		
//		sp2 = new JScrollPane(label17, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
//	            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//		
//		MainButton = new JButton("Back to Main_old Menu");
//		
//		framemain();
//	}
//	
//	public static void framemain() {
//		setVisible();
//		MainButton.setVisible(false);
//		
//		label1.setBounds(250, 185, 100, 30);
//		label1.setFont(new Font("Calibri", Font.PLAIN, 15));
//		label1.setForeground(Color.RED);
//		label1.setVisible(false);
//		frame1.add(label1);
//		
//		label2.setBounds(250,185, 100, 30);
//		label2.setFont(new Font("Calibri", Font.PLAIN, 15));
//		label2.setForeground(Color.RED);
//		label2.setVisible(false);
//		frame1.add(label2);
//		
//		label3.setBounds((frame1.getWidth()-250)/2, 40, 250, 30);
//		label3.setFont(new Font("Calibri", Font.PLAIN, 17));
//		label3.setVisible(true);
//		frame1.add(label3);
//	
//		button1.setBounds(50, 150, 200, 30);
//		button1.setBackground(Color.lightGray);
//		button1.setForeground(Color.BLUE);
//		button1.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		button1.setVisible(true);
//		button1.addActionListener(new AListener());
//		frame1.add(button1);
//		
//		button2.setBounds(350, 150, 200, 30);
//		button2.setBackground(Color.lightGray);
//		button2.setForeground(Color.BLUE);
//		button2.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		button2.setVisible(true);
//		button2.addActionListener(new AListener());
//		frame1.add(button2);
//		
//		button3.setBounds(50, 200, 200, 30);
//		button3.setBackground(Color.lightGray);
//		button3.setForeground(Color.BLUE);
//		button3.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		button3.setVisible(true);
//		button3.addActionListener(new AListener());
//		frame1.add(button3);
//		
//		button4.setBounds(350, 200, 200, 30);
//		button4.setBackground(Color.RED);
//		button4.setForeground(Color.BLACK);
//		button4.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		button4.setVisible(true);
//		button4.addActionListener(new AListener());
//		frame1.add(button4);
//		
//		
//		
//		frame1.setVisible(true);
//		
//		
//	}
//	public static void frameFunctionsMenu() {
//		setVisible();
//		
//		frame1.add(label4);
//		label4.setBounds((frame1.getWidth()-300)/2, 40, 300, 30);
//		label4.setFont(new Font("Calibri", Font.PLAIN, 15));
//		label4.setVisible(true);
//		
//		frame1.add(button5);
//		button5.setBounds(100, 100, 400, 30);
//		button5.setBackground(Color.lightGray);
//		button5.setForeground(Color.BLUE);
//		button5.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		button5.setVisible(true);
//		button5.addActionListener(new AListener());
//		
//		frame1.add(button6);
//		button6.setBounds(100, 135, 400, 30);
//		button6.setBackground(Color.lightGray);
//		button6.setForeground(Color.BLUE);
//		button6.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		button6.setVisible(true);
//		button6.addActionListener(new AListener());
//		
//		frame1.add(button7);
//		button7.setBounds(100, 170, 400, 30);
//		button7.setBackground(Color.lightGray);
//		button7.setForeground(Color.BLUE);
//		button7.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		button7.setVisible(true);
//		button7.addActionListener(new AListener());
//		
//		frame1.add(MainButton);
//		MainButton.setBounds(5, 335, 145, 30);
//		MainButton.setBackground(new Color(0, 111, 174));
//		MainButton.setForeground(Color.WHITE);
//		MainButton.addActionListener(new AListener());
//		MainButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		MainButton.setVisible(true);
//		
//		frame1.add(back1);
//		back1.setBounds(445, 335, 145, 30);
//		back1.setBackground(new Color(0, 111, 174));
//		back1.setForeground(Color.WHITE);
//		back1.addActionListener(new AListener());
//		back1.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		back1.setVisible(true);
//		
//	}
//	
//	public static void showAndSelectPrevious(String s) {
//		setVisible();
//		
//		String t = "";
//		t = s + "<html/>";
//		
//		frame1.add(label16);
//		label16.setBounds(100, 15, 400, 30);
//		label16.setVisible(true);
//		
//
//		label17.setText(t);
//		label17.setVisible(true);
//		label17.setFont(new Font("Calibri", Font.PLAIN, 15));
//		
//		frame1.add(sp2);
//		sp2.setBounds(10, 60, 575, 80);
//		sp2.setVisible(true);
//
//		
//		frame1.add(tfield7);
//		tfield7.setBounds(100, 150, 400, 30);
//		tfield7.setForeground(Color.GRAY);
//		tfield7.addKeyListener(new KListener());
//		tfield7.addFocusListener(new FListener());
//		tfield7.requestFocus();
//		tfield7.setVisible(true);
//		
//		frame1.add(button24);
//		button24.setBounds(225, 290, 150, 30);
//		button24.setBackground(Color.lightGray);
//		button24.setForeground(Color.BLUE);
//		button24.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		button24.addActionListener(new AListener());
//		button24.setVisible(true);
//		
//		frame1.add(MainButton);
//		MainButton.setBounds(5, 335, 145, 30);
//		MainButton.setBackground(new Color(0, 111, 174));
//		MainButton.setForeground(Color.WHITE);
//		MainButton.addActionListener(new AListener());
//		MainButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		MainButton.setVisible(true);
//		
//		frame1.add(back1);
//		back1.setBounds(445, 335, 145, 30);
//		back1.setBackground(new Color(0, 111, 174));
//		back1.setForeground(Color.WHITE);
//		back1.addActionListener(new AListener());
//		back1.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		back1.setVisible(true);
//		
//		
//	}
//	
//	
//	
//	
//	
//	public static void frameActionsMenu() {
//		setVisible();
//		
//		frame1.add(label6);
//		label6.setBounds((frame1.getWidth()-300)/2, 15, 300, 30);
//		label6.setVisible(true);
//		label6.setFont(new Font("Calibri", Font.PLAIN, 15));
//		
//		frame1.add(button10);
//		button10.setBounds(100, 60, 400, 30);
//		button10.setBackground(Color.lightGray);
//		button10.setForeground(Color.BLUE);
//		button10.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		button10.setVisible(true);
//		button10.addActionListener(new AListener());
//		
//		frame1.add(button11);
//		button11.setBounds(100, 95, 400, 30);
//		button11.setBackground(Color.lightGray);
//		button11.setForeground(Color.BLUE);
//		button11.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		button11.setVisible(true);
//		button11.addActionListener(new AListener());
//		
//		
//		frame1.add(button12);
//		button12.setBounds(100, 130, 400, 30);
//		button12.setBackground(Color.lightGray);
//		button12.setForeground(Color.BLUE);
//		button12.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		button12.setVisible(true);
//		button12.addActionListener(new AListener());
//		
//		
//		frame1.add(button13);
//		button13.setBounds(100, 165, 400, 30);
//		button13.setBackground(Color.lightGray);
//		button13.setForeground(Color.BLUE);
//		button13.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		button13.setVisible(true);
//		button13.addActionListener(new AListener());
//		
//		frame1.add(back3);
//		back3.setBounds(445, 335, 145, 30);
//		back3.setBackground(new Color(0, 111, 174));
//		back3.setForeground(Color.WHITE);
//		back3.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		back3.addActionListener(new AListener());
//		back3.setVisible(true);
//		
//		frame1.add(label14);
//		label14.setBounds(100, 230, 400, 30);
//		label14.setFont(new Font("Calibri", Font.PLAIN, 16));
//		label14.setVisible(false);
//	}
//	
//	public static void framefunktioneingabe() {		
//		frame1.add(label5);	
//		label5.setBounds(25, 250, 50, 30);
//		label5.setVisible(true);
//		label5.setFont(new Font("Calibri", Font.PLAIN, 15));
//			
//		frame1.add(tfield1);
//		tfield1.setForeground(Color.BLACK);
//		tfield1.setBounds(75, 250, 475, 30);
//		tfield1.setVisible(true);
//		tfield1.addKeyListener(new KListener());
//		tfield1.requestFocus();
//		
//		frame1.add(button9);
//		button9.setBounds(250, 290, 100, 30);
//		button9.setBackground(Color.lightGray);
//		button9.setForeground(Color.BLUE);
//		button9.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		button9.setVisible(true);
//		button9.addActionListener(new AListener());
//		
//	}
//	
//	static public void frameMirror() {
//		setVisible();
//		
//		frame1.add(label7);
//		label7.setBounds((frame1.getWidth()-300)/2, 20, 300, 30);
//		label7.setVisible(true);
//		label7.setFont(new Font("Calibri", Font.PLAIN, 15));
//		label7.setText("How do you want to mirror your function?");
//		
//		frame1.add(button14);
//		button14.setBounds(100, 75, 400, 30);
//		button14.setBackground(Color.lightGray);
//		button14.setForeground(Color.BLUE);
//		button14.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		button14.setVisible(true);
//		button14.addActionListener(new AListener());
//		
//		frame1.add(button15);
//		button15.setBounds(100, 110, 400, 30);
//		button15.setBackground(Color.lightGray);
//		button15.setForeground(Color.BLUE);
//		button15.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		button15.setVisible(true);
//		button15.addActionListener(new AListener());
//		
//		frame1.add(button16);
//		button16.setBounds(100, 145, 400, 30);
//		button16.setBackground(Color.lightGray);
//		button16.setForeground(Color.BLUE);
//		button16.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		button16.setVisible(true);
//		button16.addActionListener(new AListener());
//		
//		frame1.add(label8);
//		label8.setBounds((frame1.getWidth()-400)/2, 210, 400, 30);
//		if (tfield1.getText().equals("")) {
//			label8.setText("f(x) = " + Main_old.t);
//		}else {
//			label8.setText("f(x) = " + tfield1.getText());
//		}
//		label8.setFont(new Font("Calibri", Font.PLAIN, 15));
//		label8.setVisible(true);
//		
//		frame1.add(button17);
//		button17.setBounds(250, 320, 100, 30);
//		button17.setBackground(Color.lightGray);
//		button17.setForeground(Color.BLUE);
//		button17.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		button17.setVisible(true);
//		button17.addActionListener(new AListener());
//		
//		frame1.add(back2);
//		back2.setBounds(445, 335, 145, 30);
//		back2.setBackground(new Color(0, 111, 174));
//		back2.setForeground(Color.WHITE);
//		back2.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		back2.addActionListener(new AListener());
//		back2.setVisible(true);
//		
//	}
//	
//	static public void framausgabemirror(String s) {
//		
//		label8.setText("f(x) = " + s);		
//		
//	}
//	
//	static public void framevaluetable() {
//		setVisible();
//		
//		frame1.add(label9);
//		label9.setBounds(100, 20, 400, 30);
//		label9.setFont(new Font("Calibri", Font.PLAIN, 15));
//		label9.setVisible(true);
//		
//		frame1.add(tfield2);
//		tfield2.setForeground(Color.GRAY);
//		tfield2.addKeyListener(new KListener());
//		tfield2.setBounds(100, 80, 400, 30);
//		tfield2.setVisible(true);
//		tfield2.addFocusListener(new FListener());
//		
//		frame1.add(tfield3);
//		tfield3.setForeground(Color.GRAY);
//		tfield3.addKeyListener(new KListener());
//		tfield3.setBounds(100, 115, 400, 30);
//		tfield3.setVisible(true);
//		tfield3.addFocusListener(new FListener());
//		
//		frame1.add(tfield4);
//		tfield4.setForeground(Color.GRAY);
//		tfield4.addKeyListener(new KListener());
//		tfield4.setBounds(100, 150, 400, 30);
//		tfield4.setVisible(true);
//		tfield4.addFocusListener(new FListener());
//		
//		frame1.add(button18);
//		button18.setBounds(225, 320, 150, 30);
//		button18.setBackground(Color.lightGray);
//		button18.setForeground(Color.BLUE);
//		button18.addActionListener(new AListener());
//		button18.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		button18.setVisible(true);
//		
//		frame1.add(back2);
//		back2.setBounds(445, 335, 145, 30);
//		back2.setBackground(new Color(0, 111, 174));
//		back2.setForeground(Color.WHITE);
//		back2.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		back2.addActionListener(new AListener());
//		back2.setVisible(true);
//		
//	}
//	
//	static public void framevalueausgabe() {
//		setVisible();
//		
//		label10.setText(Function.s);
//		label10.setFont(new Font("Calibri", Font.PLAIN, 15));
//		label10.setVisible(true);
//		
//		frame1.add(sp);
//		sp.setBounds(5, 5, 585, 290);
//		sp.setVisible(true);
//		
//		frame1.add(button19);
//		button19.setBounds(225, 320, 150, 30);
//		button19.setBackground(Color.lightGray);
//		button19.setForeground(Color.BLUE);
//		button19.addActionListener(new AListener());
//		button19.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		button19.setVisible(true);
//		
//	}
//	
//	public static void framecheckpoint() {
//		setVisible();
//		
//		frame1.add(label11);
//		label11.setBounds(100, 20, 400, 30);
//		label1.setFont(new Font("Calibri", Font.PLAIN, 15));
//		label11.setVisible(true);
//		
//		frame1.add(tfield5);
//		tfield5.setForeground(Color.GRAY);
//		tfield5.setBounds(100, 100, 400, 30);
//		tfield5.addKeyListener(new KListener());
//		tfield5.setVisible(true);
//		tfield5.addFocusListener(new FListener());
//		
//		frame1.add(tfield6);
//		tfield6.setForeground(Color.GRAY);
//		tfield6.setBounds(100, 135, 400, 30);
//		tfield6.addKeyListener(new KListener());
//		tfield6.addFocusListener(new FListener());
//		tfield6.setVisible(true);
//		
//		frame1.add(button20);
//		button20.setBounds(225, 300, 150, 30);
//		button20.setBackground(Color.lightGray);
//		button20.setForeground(Color.BLUE);
//		button20.addActionListener(new AListener());
//		button20.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		button20.setVisible(true);
//		
//		frame1.add(label13);
//		label13.setBounds(100, 200, 400, 40);
//		label13.setFont(new Font("Calibri", Font.ITALIC, 17));
//		label13.setForeground(Color.MAGENTA);
//		
//		frame1.add(back2);
//		back2.setBounds(445, 335, 145, 30);
//		back2.setBackground(new Color(0, 111, 174));
//		back2.setForeground(Color.WHITE);
//		back2.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		back2.addActionListener(new AListener());
//		back2.setVisible(true);
//		
//	}
//	
//	public static void frameanzeigepoint(int i) {
//		label13.setVisible(false);
//		frame1.add(label12);
//		label12.setBounds(100, 200, 400, 40);
//		label12.setFont(new Font("Calibri", Font.ITALIC, 17));
//		if (i == 1) {
//			label12.setText("The point DOES lie on your Graph");
//			label12.setForeground(Color.GREEN);
//			label12.setVisible(true);
//		}else if (i == 2) {
//			label12.setText("The point DOES NOT lie on your Graph");
//			label12.setForeground(Color.RED);
//			label12.setVisible(true);
//		}
//	}
//	
//	
//	public static void RemoveAndSave() {
//		setVisible();
//		
//		frame1.add(label15);
//		label15.setBounds((frame1.getWidth()-300)/2, 20, 300, 30);
//		label15.setVisible(true);
//		
//		frame1.add(button22);
//		button22.setBounds(100, 120, 400, 30);
//		button22.setBackground(Color.lightGray);
//		button22.setForeground(Color.BLUE);
//		button22.addActionListener(new AListener());
//		button22.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		button22.setVisible(true);
//		
//		frame1.add(button23);
//		button23.setBounds(100, 155, 400, 30);
//		button23.setBackground(Color.lightGray);
//		button23.setForeground(Color.BLUE);
//		button23.addActionListener(new AListener());
//		button23.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		button23.setVisible(true);
//		
//		frame1.add(label14);
//		label14.setBounds(100, 200, 400, 30);
//		label14.setFont(new Font("Calibri", Font.PLAIN, 16));
//		label14.setVisible(false);
//		
//		frame1.add(back2);
//		back2.setBounds(445, 335, 145, 30);
//		back2.setBackground(new Color(0, 111, 174));
//		back2.setForeground(Color.WHITE);
//		back2.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		back2.addActionListener(new AListener());
//		back2.setVisible(true);
//		
//		
//	}
//	
//	
//	
//	
//	
//	static class AListener implements ActionListener {
//
//		@Override
//		public void actionPerformed(ActionEvent ae) {
//			if (ae.getSource() == Frame.button1) {			
//				frameFunctionsMenu();
//				
//			}else if (ae.getSource() == Frame.button2) {
//				Main_old.showPreviousFunctions();
//			}else if (ae.getSource() == Frame.button3) {
//				Main_old.calcIntersection();
//			}else if (ae.getSource() == Frame.button4) {
//				System.exit(0);
//				
//			}else if (ae.getSource() == Frame.button5) {
//				
//				framefunktioneingabe();
//				
//				
//			}else if (ae.getSource() == Frame.button6) {
//				
//			}else if (ae.getSource() == Frame.button7) {
//				
//			}else if (ae.getSource() == Frame.button9) {
//				setVisible();
//				
//				frameActionsMenu();
//				
//			}else if (ae.getSource() == Frame.button10) {
//				Frame.framevaluetable();				
//			}else if (ae.getSource() == Frame.button11) {
//				framecheckpoint();
//			}else if (ae.getSource() == Frame.button12) {
//				frameMirror();
//			}else if (ae.getSource() == Frame.button13) {
//				RemoveAndSave();
//			}else if (ae.getSource() == Frame.button14) {
//				Main_old.typeFunction(3);
//			}else if (ae.getSource() == Frame.button15) {
//				Main_old.typeFunction(4);
//			}else if (ae.getSource() == Frame.button16) {
//				Main_old.typeFunction(5);
//			}else if (ae.getSource() == Frame.button17) {
//				frameFunctionsMenu();
//			}else if (ae.getSource() == Frame.button18) {
//				framevalueausgabe();	
//				Main_old.typeFunction(1);					
//			}else if (ae.getSource() == Frame.button19) {
//				frameActionsMenu();
//			}else if (ae.getSource() == Frame.button20) {
//				label12.setVisible(false);
//				Main_old.typeFunction(2);
//			}else if (ae.getSource() == Frame.button21) {
//				frameActionsMenu();
//			}else if (ae.getSource() == Frame.MainButton) {
//				framemain();
//			}else if (ae.getSource() == Frame.back1) {
//				framemain();
//			}else if (ae.getSource() == Frame.back2) {
//				frameActionsMenu();
//			}else if (ae.getSource() == Frame.back3) {
//				frameFunctionsMenu();
//			}else if (ae.getSource() == Frame.button22) {
//				Main_old.typeFunction(7);
//			}else if (ae.getSource() == Frame.button23) {
//				Main_old.typeFunction(8);
//			}else if (ae.getSource() == Frame.button24) {
//				Main_old.selectPreviousFunctions();
//			}
//		}
//		
//	}
//	
//	static class KListener implements KeyListener{
//
//		@Override
//		public void keyPressed(KeyEvent e) {
//			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//				if (e.getSource() == Frame.tfield1) {
//					setVisible();
//
//					frameActionsMenu();
//				}else if (e.getSource() == Frame.tfield2) {
//					if (tfield2.getText() != null && tfield3.getText() != null && tfield4.getText() != null) {
//						Main_old.typeFunction(1);
//						framevalueausgabe();
//					}
//				}else if (e.getSource() == Frame.tfield3) {
//					if (tfield2.getText() != null && tfield3.getText() != null && tfield4.getText() != null) {
//						Main_old.typeFunction(1);
//						framevalueausgabe();
//					}
//				}else if (e.getSource() == Frame.tfield4) {
//					if (tfield2.getText() != null && tfield3.getText() != null && tfield4.getText() != null) {
//						Main_old.typeFunction(1);
//						framevalueausgabe();
//					}
//				}else if (e.getSource() == Frame.tfield5) {
//						label12.setVisible(false);
//						Main_old.typeFunction(2);
//						
//				}else if (e.getSource() == Frame.tfield6) {
//						label12.setVisible(false);
//						Main_old.typeFunction(2);
//				}
//			}
//			
//		}
//
//		@Override
//		public void keyReleased(KeyEvent arg0) {
//			// TODO Auto-generated method stub
//			
//		}
//
//		@Override
//		public void keyTyped(KeyEvent arg0) {
//			// TODO Auto-generated method stub
//			
//		}
//		
//	}
//	
//	static class FListener implements FocusListener {
//
//		@Override
//		public void focusGained(FocusEvent fe) {
//			if (fe.getSource() == Frame.tfield1){
//				tfield1.selectAll();
//				tfield1.setForeground(Color.BLACK);
//			}else if (fe.getSource() == Frame.tfield2) {
//				tfield2.selectAll();
//				tfield2.setForeground(Color.BLACK);
//			}else if (fe.getSource() == Frame.tfield3) {
//				tfield3.selectAll();
//				tfield3.setForeground(Color.BLACK);
//			}else if (fe.getSource() == Frame.tfield4) {
//				tfield4.selectAll();
//				tfield4.setForeground(Color.BLACK);
//			}else if (fe.getSource() == Frame.tfield5) {
//				tfield5.selectAll();
//				tfield5.setForeground(Color.BLACK);
//			}else if (fe.getSource() == Frame.tfield6) {
//				tfield6.selectAll();
//				tfield6.setForeground(Color.BLACK);
//			}else if (fe.getSource() == Frame.tfield7) {
//				tfield7.selectAll();
//				tfield7.setForeground(Color.BLACK);
//			}
//			
//		}
//
//		@Override
//		public void focusLost(FocusEvent arg0) {
//			// TODO Auto-generated method stub
//			
//		}
//		
//	}
//	
//	public static void setVisible() {
//		button1.setVisible(false);
//		button2.setVisible(false);
//		button3.setVisible(false);
//		button4.setVisible(false);
//		button5.setVisible(false);
//		button6.setVisible(false);
//		button7.setVisible(false);
//		button9.setVisible(false);
//		button10.setVisible(false);
//		button11.setVisible(false);
//		button12.setVisible(false);
//		button13.setVisible(false);
//		button14.setVisible(false);
//		button15.setVisible(false);
//		button16.setVisible(false);
//		button17.setVisible(false);
//		button18.setVisible(false);
//		button19.setVisible(false);
//		button20.setVisible(false);
//		button21.setVisible(false);
//		button22.setVisible(false);
//		button23.setVisible(false);
//		button24.setVisible(false);
//		
//		tfield1.setVisible(false);
//		tfield2.setVisible(false);
//		tfield3.setVisible(false);
//		tfield4.setVisible(false);
//		tfield5.setVisible(false);
//		tfield6.setVisible(false);
//		tfield7.setVisible(false);
//		
//		label1.setVisible(false);
//		label2.setVisible(false);
//		label3.setVisible(false);
//		label4.setVisible(false);
//		label5.setVisible(false);
//		label6.setVisible(false);
//		label7.setVisible(false);
//		label8.setVisible(false);
//		label9.setVisible(false);
//		label10.setVisible(false);
//		label11.setVisible(false);
//		label12.setVisible(false);
//		label13.setVisible(false);
//		label14.setVisible(false);
//		label15.setVisible(false);
//		label16.setVisible(false);
//		label17.setVisible(false);
//		
//		sp.setVisible(false);
//		sp2.setVisible(false);
//		
//		back1.setVisible(false);
//		back2.setVisible(false);
//		back3.setVisible(false);
//	}
}