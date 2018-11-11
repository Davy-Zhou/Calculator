package my.code.calculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.color.*;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalculatorDy extends JFrame implements ActionListener {
	private static final long serialVersionUID = 6585746704577693092L;

	// �����������ּ��������������
	private final String[] KEYS = { "7", "8", "9", "/", "sqrt", "4", "5", "6", "*", "%", "1", "2", "3", "-", "1/x", "0",
			"+/-", ".", "+", "=" };
	// ���ܼ�
	 private final String [] COMMAND= {"Backspace","CE","C"};
	// ���M������
	private final String [] M= {" ","MC","MR","MS","M+"};
	private JButton keys[]=new JButton [KEYS.length]; //���֣��������
	private JButton commands[]=new JButton[COMMAND.length];//�������Ϲ��ܼ�
	private JButton m[]=new JButton[M.length];//��������ߵİ���
	private JTextField resultText=new JTextField("0");//�������ı���
	//��־�û������Ǽ�����ʽ�ĵ�һ�����ֻ����������ĵ�һ������
	private boolean firstDigit=true;
	//������м���
	private double resultNum=0.0;
	//��ǰ�������
	private String operator="=";
	//�����Ƿ�Ϸ�
	private boolean oprateValidFlag=true;
	/**
	 * ���캯��
	 */
	public CalculatorDy() {
		super();//?
		//��ʼ��������
		init();
		//���ü������ı�����ɫ
		this.setBackground(Color.white);
		this.setTitle("������");
		//����Ļ��400��400������ʾ������
		this.setLocation(400, 400);
		//���ı�������Ĵ�С
		this.setResizable(false);
		//ʹ�������и�����Ĵ�С����
		this.pack();//?
	}
	/**
	 * ��ʼ��������
	 */
	private void init() {
		//�ı����е����ݲ��������
		resultText.setHorizontalAlignment(JTextField.LEFT);
		//�������޸Ľ���ı���
		resultText.setEditable(false);
		//�����ı��򱳾���ɫΪ��ɫ��
		resultText.setBackground(Color.white);
		//��ʼ���������ϼ��İ�ť����������һ��������
		JPanel calckeysPanel=new JPanel();
		//�����񲼾�����4��5�е�����������ˮƽ������Ϊ3�����أ���ֱ������3�����ء�?
		calckeysPanel.setLayout(new GridLayout(4,5,3,3));
		for(int i=0;i<KEYS.length;i++) {
			keys[i]=new JButton(KEYS[i]);
			calckeysPanel.add(keys[i]);
			keys[i].setForeground(Color.BLUE);			
		}
		//������ú�ɫ��ʾ����������ɫ��ʶ����
		keys[3].setForeground(Color.red);
		keys[8].setForeground(Color.red);
		keys[13].setForeground(Color.red);
		keys[18].setForeground(Color.red);
		keys[19].setForeground(Color.red);
		//��ʼ�����ܼ������ú�ɫ��ʾ�������ܷŵ�һ�������ڡ�
		JPanel commandsPanel=new JPanel();
		//�����粼�֣�1��3�е����磬������ˮƽ������Ϊ3�����أ���ֱ������3 �����ء�
		commandsPanel.setLayout(new GridLayout(1,3,3,3));
		for(int i=0;i<COMMAND.length;i++) {
			commands[i]=new JButton(COMMAND[i]);
			commandsPanel.add(commands[i]);
			commands[i].setForeground(Color.red);
			
		}
		//��ʼ��M�����ú�ɫ��ʾ����M������һ��������
		JPanel calmsPanel=new JPanel();
		// �����񲼾ֹ�������5�У�1�е���������֮���ˮƽ������Ϊ3�����أ���ֱ������Ϊ3������
		calmsPanel.setLayout(new GridLayout(5,1,3,3));
		for(int i=0;i<M.length;i++) {
			m[i]=new JButton(M[i]);
			calmsPanel.add(m[i]);
			m[i].setForeground(Color.red);
		}
		//������м����������岼�֣���calckeys��command������ڼ��������в���
		// ���ı�����ڱ�������calms������ڼ�������������
		// �½�һ����Ļ��壬�����潨����command��calckeys������ڸû�����
		JPanel panel1=new JPanel();
		//������ñ߽粼�ֹ����������������֮���ˮƽ�ʹ�ֱ�����ϼ����Ϊ3����
		panel1.setLayout(new BorderLayout(3,3));
		panel1.add("North",commandsPanel);
		panel1.add("West",calckeysPanel);
		//
		JPanel top=new JPanel();
		top.setLayout(new BorderLayout());
		top.add("Center",resultText);
		//
		getContentPane().setLayout(new BorderLayout(3,5));
		getContentPane().add("North",top);
		getContentPane().add("Center",panel1);
		getContentPane().add("West",calmsPanel);
		//
		//
		for(int i=0;i<KEYS.length;i++) {
			keys[i].addActionListener(this);	
		}
		for(int i=0;i<COMMAND.length;i++) {
			commands[i].addActionListener(this);	
		}
		for(int i=0;i<M.length;i++) {
			m[i].addActionListener(this);	
		}
	}
	/**
	 * 
	 * 
	 */
	public void actionPerformed(ActionEvet a) {
		//
		String label=e.getActionCommand();
		if(label.equals(COMMAND[0])) {
			//
			handleBackspace();
		}
		else if(label.equals(COMMAND[1])) {
			//
			resultText.setText("0");
		}
		else if(label.equals(COMMAND[2])) {
			//
			handleC();
		}
		else if("0123456789".indexOf(label)>=0) {
			//
			handleNumber(label);
		}
		else {
			//
			handleOperater(lable);
		}
	}
	/**
	 * 
	 * 
	 */
	private void handleBackspace() {
		String text=resultText.getText();
		int i=text.length();
		if(i>0) {
			//
			text=text.substring(0,i-1);
			if(text.length()==0) {
				//
				resultText.setText("0");
				firstDigit=true;
				operator="=";
			}
			else {
				//
				resultText.setText(text);
				
			}
		}
	}
	/**
	 * 
	 * 
	 */
	private void handleNumber(String key) {
		if(firstDigit) {
			//
			resultText.setText(key);
			
		}
		else if((key.equals("."))&&(resultText.getText().indexOf(".")<0)) {
			//
			resultText.setText(resultText.getText()+".");
			
		}
		else if(!key.equals(".")) {
			//
			resultText.setText(resultText.getText()+key);
		}
			//
		firstDigit=false;
	}
	/**
	 * 
	 * 
	 */
		private void handleC() {
			resultText.setText("0");
			firstDigit=true;
			operator="=";
		}
		/**
		 * 
		 * 
		 */
		private void handleOprator(String key) {
			if(operator.equals("/")) {
				//
				if(getNumberFromText()==0.0) {
					oprateValidFlag=false;
					resultText.setText("��������Ϊ0");
				}
				else {
					resultNum/=getNumberFromText();
					
				}
			}
			else if(operator.equals("1/x")) {
				//
				if(resultNum==0.0) {
					//
					oprateValidFlag=flase;
					resultText.setText("��û�е���");
					
				}
				else {
					resultNum=1/resultNum;
					
				}
			}
			else if(operator.equals("+")) {
				//
				resultNum+=getNumberFromText();
			}
			else if(operator.equals("-")) {
				//
				resultNum-=getNumberFromText();
			}
			else if(operator.equals("*")) {
				//
				resultNum*=getNumberFromText();
			}
			else if(operator.equals("sqrt")) {
				//
				resultNum=Math.sqrt(resultNum);
			}
			else if(operator.equals("%")) {
				//
				resultNum=resultNum/100;
			}
			else if(operator.equals("+/-")) {
				//
				resultNum=resultNum*(-1);
			}
			else if(operator.equals("=")) {
				//
				resultNum=getNumberFromText();
			}
			if(oprateValidFlag) {
				//
				long t1;
				double t2;
				t1=(long)resultNum;
				t2=resultNum-t1;
				if(t2==0) {
					resultText.setText(String.valueOf(t1));
				}
				else {
					resultText.setText(String.valueOf(resultNum));
				}
					
			}
			//
			operator=key;
			firstDigit=true;
			oprateValidFlag=true;
		}
		/**
		 * 
		 * 
		 */
		private double getNumberFromText() {
			double result=0;
			try {
				result=Double.valueOf(resultText.getText()).doubleValue();
				
			}
			catch(NumberFormatException e) {
				
			}
			return result;
		}
		public static void main(String args[]) {
			CalculatorDy calculator1 = new CalculatorDy();
			calculator1.setVisible(true);
			calculator1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	
}	
		
		
		
		
		
		
		
		
		
		
		

			

		
		
		
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	

	
