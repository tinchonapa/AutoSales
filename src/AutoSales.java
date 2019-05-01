import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
//784

public class AutoSales extends JFrame {
	//Class declaration
	JPanel leftPanel, customerPanel, rightPanel, balancePanel, totalPanel, southPanel;
	JPanel customerInfo, customerImg, modelInfo, optionsInfo, packageType, tradeInPanel, paymentType;
	JLabel logoImg, accountL, firstL, addressL, phoneL, customerImgL; //Label Customer Info
	JLabel modelL, priceL, s40P, s60P, s70P, s80P; //Label Model Info
	JLabel s40I, s60I, s70I, s80I, serv, tradeInL, paymentTypeL; //Label Model Image + Options Packages
	JLabel blankLabel; //FOR Right PANEL
	JLabel modelTotalL, serviceTotalL, paintTotalL, tradeInTotalL, paymentTypeTotalL, titleL, salesTaxL, finalBalanceL; //Labels Right Panel
	JLabel modelTotal, serviceTotal, paintTotal, tradeInTotal, paymentTypeTotal, titleTotal, salesTaxTotal, finalBalanceTotal; //Labels for totals
	JTextField accountT, firstT, lastT, addressT, phoneT, tradeInT;
	ImageIcon logoP, customerPhoto, s40Img, s60Img, s70Img, s80Img;
	ButtonGroup modelG, serviceG, financingG;
	JRadioButton s40R, s60R, s70R, s80R, pAR, pBR, finR, cashR;
	JComboBox servicesCombo; // If S70 or S80 available Package B
	JCheckBox paintCH, tradeCH;
	JButton reset,calculate, submit;
	Random randomNumber = new Random();
	int accountNumber, phone;
	String accountNumberFinal, model;
	String[] services = {"None", "'A' $2,200 - 2 Oil Change p/yr. Available on all models", "'B' $3,200 - 5 Oil Change p/yr. Only S70 & S80 models"};
	String  name = "", address = "";
	double vehicle = 0.00, service = 0.00, paint = 0.00, trade = 0.00, payment = 0.00, tt = 325.00, total = 0.00;
	double subtotal = (vehicle + service + paint + payment - trade + tt), tax = (subtotal) * 0.06;
	
	//Constructor
	public AutoSales() {
		
		//Left Panel declaration
		leftPanel = new JPanel();
		customerPanel = new JPanel();
		customerInfo = new JPanel();
		customerImg = new JPanel();
		modelInfo = new JPanel();
		optionsInfo = new JPanel();
		packageType = new JPanel();
		tradeInPanel = new JPanel();
		paymentType = new JPanel();
		//Right Panel declaration
		rightPanel = new JPanel();
		balancePanel = new JPanel();
		totalPanel = new JPanel();
		southPanel = new JPanel();
		
		//Left Panel Layouts
		this.setLayout(new BorderLayout());
		leftPanel.setLayout(new GridLayout(3,1));
		customerPanel.setLayout(new GridLayout(1,2));
		customerInfo.setBorder(BorderFactory.createTitledBorder("Customer Information"));
		customerInfo.setLayout(new GridLayout(4,2));
		modelInfo.setLayout(new GridLayout(4,3));
		modelInfo.setBorder(BorderFactory.createTitledBorder("Model Information"));
		optionsInfo.setLayout(new GridLayout(5,2));
		optionsInfo.setBorder(BorderFactory.createTitledBorder("Optional Services"));
		packageType.setLayout(new FlowLayout(FlowLayout.LEFT));
		tradeInPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		paymentType.setLayout(new FlowLayout(FlowLayout.LEFT));
		//Right Panel Layouts
		rightPanel.setLayout(new GridLayout(3,1));
		balancePanel.setLayout(new GridLayout(7,2));
		rightPanel.setBorder(BorderFactory.createTitledBorder("Balance Information"));
		totalPanel.setLayout(new GridLayout(1,2));	
		//South Panel Layout
		southPanel.setLayout(new GridLayout(1,3));
		
		logoP = new ImageIcon("logo494w216h.jpg");
		logoImg = new JLabel(logoP);
		

		buildCustomerInfo();
		buildCustomerImg();
		buildModelInfo();
		buildOptionsInfo();
		buildBalanceInfo();
		buildTotal();
		
		//BUTTONS
		blankLabel = new JLabel(""); // To balance GUI
		reset = new JButton("RESET FORM");
		reset.addActionListener(new resetListener());
		reset.setBackground(Color.red);
		reset.setForeground(Color.black);
		calculate = new JButton("CALCULATE ESTIMATE");
		calculate.addActionListener(new calculateListener());
		submit = new JButton("SUBMIT");
		submit.addActionListener(new submitListener());

		//Adding to Left Panel
		leftPanel.add(customerInfo);
		leftPanel.add(modelInfo);
		leftPanel.add(optionsInfo);
		
		rightPanel.add(customerImg);
		rightPanel.add(balancePanel);
		rightPanel.add(totalPanel);
		
		southPanel.add(reset);
		southPanel.add(calculate);
		southPanel.add(submit);
		
		add(logoImg, BorderLayout.NORTH);
		add(leftPanel, BorderLayout.CENTER);
		add(rightPanel, BorderLayout.EAST);
		add(southPanel, BorderLayout.SOUTH);
		
		setTitle("Welcome to Penny Motors Auto Sale");
		setSize(700,800);
		//pack(); //Set pack and making visible
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void buildCustomerInfo() {
		//Adding content to Customer Info Panel
		accountL = new JLabel("Account Number: ");
		firstL = new JLabel("Name: ");
		addressL = new JLabel("Address: ");
		phoneL = new JLabel("Phone Number: ");
		
		accountT = new JTextField(6);
		accountNumber = randomNumber.nextInt(9999999);
		accountNumberFinal = Integer.toString(accountNumber);
		accountT.setText(accountNumberFinal);
		accountT.setEditable(false);
		firstT = new JTextField(12); 
		addressT = new JTextField(30);
		addressT.setText("Address, City, State Zip");
		phoneT = new JTextField(10);
		phoneT.setText("(___)___-____");
		
		customerInfo.add(accountL);
		customerInfo.add(accountT);
		customerInfo.add(firstL);
		customerInfo.add(firstT);
		customerInfo.add(addressL);
		customerInfo.add(addressT);
		customerInfo.add(phoneL);
		customerInfo.add(phoneT);
	}
	private void buildCustomerImg() {
		//Adding content to Customer Img Panel
		customerPhoto = new ImageIcon("profile.png");
		customerImgL = new JLabel(customerPhoto);
		customerImg.add(customerImgL);
		//pack();
	}
	private void buildModelInfo() {
		//Adding content to ModelInfo Panel
		s40Img = new ImageIcon("s40.png");
		s60Img = new ImageIcon("s60.png");
		s70Img = new ImageIcon("s70.png");
		s80Img = new ImageIcon("s80.png");
		
		s40I = new JLabel(s40Img);
		s60I = new JLabel(s60Img);
		s70I = new JLabel(s70Img);
		s80I = new JLabel(s80Img);
		
		
		//Radio Buttons
		s40R = new JRadioButton("S40");
		s60R = new JRadioButton("S60");
		s70R = new JRadioButton("S70");
		s80R = new JRadioButton("S80");
		//s40R.addActionListener(new calculateListener()); //TO CREATE ACTION DIRECTLY WITHOUT BUTTON
		
		modelG = new ButtonGroup();
		modelG.add(s40R);
		modelG.add(s60R);
		modelG.add(s70R);
		modelG.add(s80R);
		
		s40P = new JLabel("$27,700");
		s60P = new JLabel("$32,500");
		s70P = new JLabel("$36,000");
		s80P = new JLabel("$44,000");
		
		modelInfo.add(s40I);
		modelInfo.add(s40R);
		modelInfo.add(s40P);
		modelInfo.add(s60I);
		modelInfo.add(s60R);
		modelInfo.add(s60P);
		modelInfo.add(s70I);
		modelInfo.add(s70R);
		modelInfo.add(s70P);
		modelInfo.add(s80I);
		modelInfo.add(s80R);
		modelInfo.add(s80P);
	}
	private void buildOptionsInfo() {
		//Adding content to Options Package Label
		//Services Plan Combo Box
		serv = new JLabel("Service Package: ");
		servicesCombo = new JComboBox(services);
		servicesCombo.addActionListener(new comboBoxListener());
		packageType.add(serv);
		packageType.add(servicesCombo);
		
		//Paint Checkbox
		paintCH = new JCheckBox("Add Metallic Paint for $650.00?");
		paintCH.addItemListener(new chBoxListener());
		//Trade Checkbox
		tradeCH = new JCheckBox("Doing a Trade-in?");
		tradeCH.addItemListener(new chBoxListener());
		tradeInL = new JLabel("Price: ");
		tradeInT = new JTextField(6);
		tradeInT.setText("0.00");
		//tradeInT.setEditable(false);
		tradeInPanel.add(tradeCH);
		tradeInPanel.add(tradeInL);
		tradeInPanel.add(tradeInT);
		
		//Financing or Cash
		paymentTypeL = new JLabel("Payment Type: ");
		cashR = new JRadioButton("Cash ($750 Back)");
		finR = new JRadioButton("Financing (7% Interest)");

		financingG = new ButtonGroup();
		financingG.add(cashR);
		financingG.add(finR);
		paymentType.add(paymentTypeL);
		paymentType.add(cashR);
		paymentType.add(finR);
		
		optionsInfo.add(packageType);
		optionsInfo.add(paintCH);
		optionsInfo.add(tradeInPanel);
		optionsInfo.add(paymentType);
	}
	private void buildBalanceInfo() {
		modelTotalL = new JLabel("Vehicle: ");
		serviceTotalL = new JLabel("Service: ");
		paintTotalL = new JLabel("Metallic Paint: ");
		tradeInTotalL = new JLabel("Trade-in: ");
		paymentTypeTotalL = new JLabel("Payment Type: ");
		titleL = new JLabel("Title & Tags fee: ");
		salesTaxL = new JLabel("Sales Tax(6%): ");
		
		modelTotal = new JLabel("$0.00");
		serviceTotal = new JLabel("$0.00");
		paintTotal = new JLabel("$0.00");
		tradeInTotal = new JLabel("$0.00");
		paymentTypeTotal = new JLabel("$0.00");
		titleTotal = new JLabel("$0.00");
		salesTaxTotal = new JLabel("$0.00");
		
		balancePanel.add(modelTotalL);
		balancePanel.add(modelTotal);
		balancePanel.add(serviceTotalL);
		balancePanel.add(serviceTotal);
		balancePanel.add(paintTotalL);
		balancePanel.add(paintTotal);
		balancePanel.add(tradeInTotalL);
		balancePanel.add(tradeInTotal);
		balancePanel.add(paymentTypeTotalL);
		balancePanel.add(paymentTypeTotal);
		balancePanel.add(titleL);
		balancePanel.add(titleTotal);
		balancePanel.add(salesTaxL);
		balancePanel.add(salesTaxTotal);
		
	}
	private void buildTotal() {
		finalBalanceL = new JLabel("TOTAL: ");
		finalBalanceTotal = new JLabel("0.00");
		totalPanel.add(finalBalanceL);
		totalPanel.add(finalBalanceTotal);
	}
	/*
	 * Action Listeners
	 */
	private class comboBoxListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String selection = (String) servicesCombo.getSelectedItem();
			if(selection.equals("None")) { //"None", "'A' $2,200 - 2 Oil Change p/yr. Available on all models", "'B' $3,200 - 5 Oil Change p/yr. Only S70 & S80 models"};
				service = 0;
				serviceTotal.setText(String.format("$%,.2f", service));
			} else if(selection.equals("'A' $2,200 - 2 Oil Change p/yr. Available on all models")) {
				service = 2200;
				serviceTotal.setText(String.format("$%,.2f", service));
			} else if(selection.equals("'B' $3,200 - 5 Oil Change p/yr. Only S70 & S80 models")) {
				if(s70R.isSelected() || s80R.isSelected()) {
					service = 3200;
					serviceTotal.setText(String.format("$%,.2f", service));
				} else {
					JOptionPane.showMessageDialog(null,"Package B only available for models S70 & S80.");
				}
			}
			
		}
	}
	private class resetListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			//Name Reset
			name = "";
			firstT.setText("");
			//Address Reset
			addressT.setText("Address, City, State Zip");
			//Phone Reset
			phoneT.setText("(___)___-____");
			phone = 0;
			//Model Reset
			if(s40R.isSelected()) {
				vehicle = 0;
				s40R.setSelected(false);
			} else if(s60R.isSelected()) {
				vehicle = 0;
				s60R.setSelected(false);
			} else if(s70R.isSelected()) {
				vehicle = 0;
				s70R.setSelected(false);
			} else if(s80R.isSelected()) {
				vehicle = 0;
				s80R.setSelected(false);
			}
			//Paint Reset
			if(paintCH.isSelected()){
				paintCH.setSelected(false);
			}
			//Trade Reset
			if(tradeCH.isSelected()) {
				trade = 0;
				tradeCH.setSelected(false);
				tradeInT.setText("0.00");
			}
			//Type of payment Reset
			if(cashR.isSelected()) {
				payment = 0;
				cashR.setSelected(false);
			} else if(finR.isSelected()) {
				payment = 0;
				finR.setSelected(false);
			}
			
			
			subtotal = 0;
			tax = 0;
			total = 0;
			modelTotal.setText(String.format("$%,.2f", vehicle));
			paymentTypeTotal.setText(String.format("$%,.2f", payment));
			titleTotal.setText(String.format("$%,.2f", tt));
			salesTaxTotal.setText(String.format("$%,.2f", tax));
			finalBalanceTotal.setText(String.format("$%,.2f", total));
		}
	}
	private class calculateListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			//Model Calculation
			if(s40R.isSelected()) {
				vehicle = 27700.00;
			} else if(s60R.isSelected()) {
				vehicle = 32500.00;
			} else if(s70R.isSelected()) {
				vehicle = 36000.00;
			} else if(s80R.isSelected()) {
				vehicle = 44000.00;
			} else {
				JOptionPane.showMessageDialog(null,"No vehicle has been added!");
			}
			//Type of payment calculation
			if(cashR.isSelected()) {
				payment = -750;
			} else if(finR.isSelected()) {
				payment = vehicle * 0.07;
			}
			//Name Validation
			name = firstT.getText();
			String[] nameTest = name.split(" "); //Separates string into array in case there are first & last names  
			for(String word : nameTest) { //For each nameTest value
				for (int i = 0; i < word.length(); i++){
					char c = word.charAt(i);
					if (Character.isLetter(c)){
						name = name + c;
					} else {
						JOptionPane.showMessageDialog(null,"Invalid Input in Name Field. Only characters are allowed!");
						name = "";
						firstT.setText("");
						break;
					}
				}	
			}
			//Phone Validation
	 		try {
				phone = Integer.parseInt(phoneT.getText());
			}
			catch(NumberFormatException ex) {
				phoneT.setText("");
				JOptionPane.showMessageDialog(null, "Invalid Input in Phone Field. Only 10 numbers are allowed");
			}
			
			subtotal = (vehicle + service + paint + payment - trade + tt);
			tax = subtotal * 0.06;
			total = (subtotal + tax);
			modelTotal.setText(String.format("$%,.2f", vehicle));
			paymentTypeTotal.setText(String.format("$%,.2f", payment));
			titleTotal.setText(String.format("$%,.2f", tt));
			salesTaxTotal.setText(String.format("$%,.2f", tax));
			finalBalanceTotal.setText(String.format("$%,.2f", total));
		}
	}
	private class submitListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			//Model Calculation
			if(s40R.isSelected()) {
				vehicle = 27700.00;
				model = "s40";
			} else if(s60R.isSelected()) {
				vehicle = 32500.00;
				model = "s60";
			} else if(s70R.isSelected()) {
				vehicle = 36000.00;
				model = "s70";
			} else if(s80R.isSelected()) {
				vehicle = 44000.00;
				model = "s80";
			} else {
				JOptionPane.showMessageDialog(null,"No vehicle has been added!");
			}
			//Type of payment calculation
			if(cashR.isSelected()) {
				payment = -750;
			} else if(finR.isSelected()) {
				payment = vehicle * 0.07;
			}
			//Name Validation
			name = firstT.getText();
			String[] nameTest = name.split(" "); //Separates string into array in case there are first & last names  
			for(String word : nameTest) { //For each nameTest value
				for (int i = 0; i < word.length(); i++){
					char c = word.charAt(i);
					if (Character.isLetter(c)){
						
					} else {
						JOptionPane.showMessageDialog(null,"Invalid Input in Name Field. Only characters are allowed!");
						name = "";
						firstT.setText("");
						return;
					}
				}	
			}
			//Phone Validation
	 		try {
				phone = Integer.parseInt(phoneT.getText());
			}
			catch(NumberFormatException ex) {
				phoneT.setText("");
				JOptionPane.showMessageDialog(null, "Invalid Input in Phone Field. Only 10 numbers are allowed");
				return;
			}
			
			subtotal = (vehicle + service + paint + payment - trade + tt);
			tax = subtotal * 0.06;
			total = (subtotal + tax);
			modelTotal.setText(String.format("$%,.2f", vehicle));
			paymentTypeTotal.setText(String.format("$%,.2f", payment));
			titleTotal.setText(String.format("$%,.2f", tt));
			salesTaxTotal.setText(String.format("$%,.2f", tax));
			finalBalanceTotal.setText(String.format("$%,.2f", total));
			JOptionPane.showMessageDialog(null, "Congratulations "+name+" you just bought a BRAND NEW "+model+" for "+String.format("$%,.2f", total));
		}
	}
	private class chBoxListener implements ItemListener{
		public void itemStateChanged(ItemEvent e) {
			if(e.getSource() == paintCH) {
				if(paintCH.isSelected()) {
					paint += 650;
					subtotal = (vehicle + service + paint + payment - trade + tt);
					tax = subtotal * 0.06;
					total = (subtotal + tax);
				} else {
					paint -= 650;
					subtotal = (vehicle + service + paint + payment - trade + tt);
					tax = subtotal * 0.06;
					total = (subtotal + tax);
				}
			}
			if(e.getSource() == tradeCH) {
				if(tradeCH.isSelected()) {
					try {
	 					trade = Double.parseDouble(tradeInT.getText());
						subtotal = (vehicle + service + paint + payment - trade + tt);
						tax = subtotal * 0.06;
						total = (subtotal + tax);
					}
					catch(NumberFormatException ex) {
						tradeInT.setText("0.00");
						trade = 0;
						JOptionPane.showMessageDialog(null, "Invalid Input. Select checkmark and try again");
					}
				} else {
					trade = 0;
				}
			}
			paintTotal.setText(String.format("$%,.2f", paint));
			tradeInTotal.setText(String.format("$-%,.2f", trade));
			titleTotal.setText(String.format("$%,.2f", tt));
			salesTaxTotal.setText(String.format("$%,.2f", tax));
			finalBalanceTotal.setText(String.format("$%,.2f", total));
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new AutoSales();
	}

}
