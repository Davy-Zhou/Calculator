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

	// 计算器上数字键，与运算符键。
	private final String[] KEYS = { "7", "8", "9", "/", "sqrt", "4", "5", "6", "*", "%", "1", "2", "3", "-", "1/x", "0",
			"+/-", ".", "+", "=" };
	// 功能键
	 private final String [] COMMAND= {"Backspace","CE","C"};
	// 左边M的名字
	private final String [] M= {" ","MC","MR","MS","M+"};
	private JButton keys[]=new JButton [KEYS.length]; //数字，运算符键
	private JButton commands[]=new JButton[COMMAND.length];//计算器上功能键
	private JButton m[]=new JButton[M.length];//计算器左边的按键
	private JTextField resultText=new JTextField("0");//计算结果文本框
	//标志用户按的是计算表达式的第一个数字还是运算符后的第一个数字
	private boolean firstDigit=true;
	//计算的中间结果
	private double resultNum=0.0;
	//当前的运算符
	private String operator="=";
	//操作是否合法
	private boolean oprateValidFlag=true;
	/**
	 * 构造函数
	 */
	public CalculatorDy() {
		super();//?
		//初始化计算器
		init();
		//设置计算器的背景颜色
		this.setBackground(Color.white);
		this.setTitle("计算器");
		//在屏幕（400，400）处显示计算器
		this.setLocation(400, 400);
		//不改变计算器的大小
		this.setResizable(false);
		//使计算器中各组件的大小合适
		this.pack();//?
	}
	/**
	 * 初始化计算器
	 */
	private void init() {
		//文本框中的内容采用左对齐
		resultText.setHorizontalAlignment(JTextField.LEFT);
		//不允许修改结果文本框
		resultText.setEditable(false);
		//设置文本框背景颜色为白色。
		resultText.setBackground(Color.white);
		//初始化计算器上键的按钮，将键放在一个画板内
		JPanel calckeysPanel=new JPanel();
		//用网格布局器，4行5列的网格，网格间的水平方向间隔为3个像素，垂直方向间隔3个像素。?
		calckeysPanel.setLayout(new GridLayout(4,5,3,3));
		for(int i=0;i<KEYS.length;i++) {
			keys[i]=new JButton(KEYS[i]);
			calckeysPanel.add(keys[i]);
			keys[i].setForeground(Color.BLUE);			
		}
		//运算符用红色标示，其他用蓝色标识。？
		keys[3].setForeground(Color.red);
		keys[8].setForeground(Color.red);
		keys[13].setForeground(Color.red);
		keys[18].setForeground(Color.red);
		keys[19].setForeground(Color.red);
		//初始化功能键，都用红色表示。将功能放到一个画板内。
		JPanel commandsPanel=new JPanel();
		//用网络布局，1行3列的网络，网络间的水平方向间隔为3个像素，垂直方向间隔3 个像素。
		commandsPanel.setLayout(new GridLayout(1,3,3,3));
		for(int i=0;i<COMMAND.length;i++) {
			commands[i]=new JButton(COMMAND[i]);
			commandsPanel.add(commands[i]);
			commands[i].setForeground(Color.red);
			
		}
		//初始化M键，用红色标示，将M键放在一个画板内
		JPanel calmsPanel=new JPanel();
		// 用网格布局管理器，5行，1列的网格，网格之间的水平方向间隔为3个像素，垂直方向间隔为3个像素
		calmsPanel.setLayout(new GridLayout(5,1,3,3));
		for(int i=0;i<M.length;i++) {
			m[i]=new JButton(M[i]);
			calmsPanel.add(m[i]);
			m[i].setForeground(Color.red);
		}
		//下面进行计算器的整体布局，将calckeys和command画板放在计算器的中部，
		// 将文本框放在北部，将calms画板放在计算器的西部。
		// 新建一个大的画板，将上面建立的command和calckeys画板放在该画板内
		JPanel panel1=new JPanel();
		//画板采用边界布局管理器，画板里组件之间的水平和垂直方向上间隔都为3像素
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
					resultText.setText("除数不能为0");
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
					resultText.setText("零没有倒数");
					
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
		
		
		
		
		
		
		
		
		
		
		

			

		
		
		
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	

	
