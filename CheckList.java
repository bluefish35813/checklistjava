import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

public class CheckList extends JPanel implements ActionListener, MouseListener{
	String mytasks,compTask,editTask,username,password,enterPass,enterUsernamepass,enterColor;
	String enterUsername = "";
	String newenterUsername= " You";
	int rval;
	int gval;
	int bval;
	int newcompTask;
	int neweditTask;
	int numtasks = 0;
	int cnums = 0;
	String[] allmytasks = {"","","","","","","",""};
	int[] delnums = {0,0,0,0,0,0,0,0};
	int[] enums = {0,0,0,0,0,0,0,0};
	int numCompleted = 0;
	String stnumCompleted;
	private Color myColor = Color.white;
    private static final int RECT_X = 140;
    private static final int RECT_X1 = 283;
    private static final int RECT_X2 = 500;
    private static final int RECT_X3 = 640;
    private static final int RECT_X4 = 780;
    private static final int RECT_X5 = 865;
    private static final int RECT_X6 = 815;
    private static final int RECT_Y = 140;
    private static final int RECT_Y1 = 50;
	private static final int RECT_WIDTH = 120;
	private static final int RECT_WIDTH1 = 195;
	private static final int RECT_WIDTH2 = 70;
	private static final int RECT_HEIGHT = 35;
	static File file = new File("logins.txt");
	public void inp() {
		mytasks = JOptionPane.showInputDialog("What task do you want to put in?");
		allmytasks[numtasks] = mytasks;
		numtasks++;
	}
	
	public void del() {
		compTask = JOptionPane.showInputDialog("What task would you like to mark as completed?"
				+ "(Enter the # of that task)");
		newcompTask = Integer.parseInt(compTask);
		delnums[cnums] = newcompTask;
		cnums++;
		numCompleted++;
		stnumCompleted = String.valueOf(numCompleted);
	}
	
	public void edit() {
		editTask = JOptionPane.showInputDialog("What task would you like to edit?"
				+ "(Enter the # of that task)");
		neweditTask = Integer.parseInt(editTask);
		mytasks = JOptionPane.showInputDialog("Enter what you like for the edited task to be.");
		allmytasks[neweditTask-1] = mytasks;
	}
	
	public void createLogin() throws IOException {
		stnumCompleted = String.valueOf(numCompleted);
		username = JOptionPane.showInputDialog("Enter your new username.");
		password = JOptionPane.showInputDialog("Enter your new password.");
		BufferedWriter out = new BufferedWriter(new FileWriter(file,true));
		out.write(username + password);
		out.newLine();
		out.write(stnumCompleted);
		out.newLine();
		for(int i = 0; i < numtasks; i++) {
			if(allmytasks[i].equals("")) {
				continue;
			}
			else {
				out.write(allmytasks[i]);
				out.newLine();
			}
		}
		out.write("stop");
		out.newLine();
		out.close();
		BufferedReader br = new BufferedReader(new FileReader("usscores.txt")); 
		String st;
		while ( (st = br.readLine() ) != null) {
			continue;
		}
		br.close();
	}
	
	public void login() throws IOException {
		enterUsername = JOptionPane.showInputDialog("Enter your username.");
		if(enterUsername.equals("")) {
			newenterUsername = " You";
		}
		else {
			newenterUsername = " " + enterUsername;
		
			enterPass = JOptionPane.showInputDialog("Enter your password.");
			enterUsernamepass = enterUsername + enterPass;
			BufferedReader br = new BufferedReader(new FileReader(file)); 
			String str;
			String st;
			String nu = "stop";
			int n = 0;
			int p = 0;
			int fnum = 0;
			int numb = 0;
			while ( (str = br.readLine() ) != null) {
				if(enterUsernamepass.equals(str)) {
					fnum = p;
					p++;
				}
				else {
					p++;
				}
			}
			br.close();
			BufferedReader bre = new BufferedReader(new FileReader(file));
			while ( (st = bre.readLine() ) != null) {
			  if(n == 0 && numb != fnum) {
				  numb++;
			  }
			  else if(numb == fnum) {
				  numb++;
				  n++;
			  }
			  else if(st.equals(nu) && n != 0) {
				  break;
			  }
			  else if(n == 1) {
				  numCompleted = Integer.parseInt(st);
				  n++;
			  }
			  else if(n >= 2 && numtasks != allmytasks.length - 1){
				  mytasks = st;
				  allmytasks[numtasks] = mytasks;
				  numtasks++;
				  n++;
			  }
			}
			bre.close();
		}
	}
	
	public void save() throws IOException {
		BufferedWriter out = new BufferedWriter(new FileWriter(file,true));
		out.write(enterUsernamepass);
		out.newLine();
		stnumCompleted = String.valueOf(numCompleted);
		out.write(stnumCompleted);
		out.newLine();
		for(int i = 0; i < numtasks; i++) {
			if(allmytasks[i].equals("")) {
				continue;
			}
			else {
				out.write(allmytasks[i]);
				out.newLine();
			}
		}
		out.write("stop");
		out.newLine();
		out.close();
	}
	
	public void changeColor() throws IOException {
		enterColor = JOptionPane.showInputDialog("What color do you want?");
		Color colors[] = {Color.white, Color.blue, Color.red, Color.green, Color.gray, Color.cyan, Color.yellow, Color.pink, Color.magenta, Color.orange};
		String scolors[] = {"white","blue","red","green","gray","cyan","yellow","pink","magenta","orange"};
		int i;
		for(i = 0; i <= 9; i++) {
			if(enterColor.equals(scolors[i])) {
				myColor = colors[i];
				break;
			}
		}
		
	}
	
	public void paintComponent(Graphics graph) {

		setSize(1050,600);
		super.paintComponent(graph);
		Color mybackground = new Color(0,0,0);
		
		Font myFont = new Font("Helvetica", Font.BOLD, 35);
		graph.setFont(myFont);
		graph.setColor(myColor);
		this.setBackground(mybackground);
		graph.drawString("Checklist For" + newenterUsername, 150, 80);
		
		Font myFont1 = new Font("Helvetica", Font.BOLD, 28);
		graph.setFont(myFont1);
		graph.drawString("Tasks", 150, 130);
		graph.drawString("Completed: "+numCompleted, 315, 130);
		
		Font myFont2 = new Font("Helvetica", Font.PLAIN, 20);
		graph.setFont(myFont2);
		if(mytasks != null && compTask == null) {
			for(int i = 0; i < numtasks; i++) {
				graph.drawString("-" + (i+1) + ". " + allmytasks[i], 150, 200+(40*i));
			}
		}
		
		else if(compTask != null) {
			for(int i = 0; i < numtasks; i++) {
				if(i != (newcompTask-1)) {
					graph.drawString("-" + (i+1) + ". " + allmytasks[i], 150, 200+(40*i));
				}
				else {
					graph.drawString("-" + (i+1) + ". ", 150, 200+(40*i));
					allmytasks[i] = "";
				}
			}
		}
		
		graph.drawRect(RECT_X, RECT_Y, RECT_WIDTH, RECT_HEIGHT);
		Font myFont3 = new Font("Helvetica", Font.PLAIN, 22);
		graph.setFont(myFont3);
		graph.drawString("Add a task", 150, 165);
		
		graph.drawRect(RECT_X1, RECT_Y, RECT_WIDTH1, RECT_HEIGHT);
		graph.drawString("Mark As Complete", 290, 165);
		
		graph.drawRect(RECT_X2, RECT_Y, RECT_WIDTH, RECT_HEIGHT);
		graph.drawString("Edit Task", 515, 165);
		
		graph.drawRect(RECT_X3, RECT_Y, RECT_WIDTH, RECT_HEIGHT);
		graph.drawString("Create login", 643, 165);
		
		graph.drawRect(RECT_X4, RECT_Y, RECT_WIDTH2, RECT_HEIGHT);
		graph.drawString("Login", 790, 165);
		
		graph.drawString("Login to save your tasks.", 150, 520);
		
		graph.drawRect(RECT_X5, RECT_Y, RECT_WIDTH2, RECT_HEIGHT);
		graph.drawString("Save", 875, 165);
		
		graph.drawRect(RECT_X6, RECT_Y1, RECT_WIDTH, RECT_HEIGHT);
		graph.drawString("New Color", 825, 75);
		
	}
	
	public static void main(String[] args) {
		JFrame w = new JFrame("Planner");
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		CheckList test = new CheckList();
		
		w.setSize(1000,600);    
		w.add(test);
	    
	    w.setVisible(true);
	    test.addMouseListener(new MouseAdapter() { 
	          public void mousePressed(MouseEvent me) { 
	        		if(me.getX() > RECT_X && me.getX() < RECT_X + 100 && 
	        				me.getY() > RECT_Y && me.getY() < RECT_Y + 35) {
	        			test.inp();
	        			w.setVisible(true);
	        		} 
	        		else if(me.getX() > RECT_X1 && me.getX() < RECT_X1 + 195 && 
	        				me.getY() > RECT_Y && me.getY() < RECT_Y + 35) {
	        			test.del();
	        			w.setVisible(true);
	        		} 
	        		else if(me.getX() > RECT_X2 && me.getX() < RECT_X2 + 100 && 
	        				me.getY() > RECT_Y && me.getY() < RECT_Y + 35) {
	        			test.edit();
	        			w.setVisible(true);
	        		} 
	        		else if(me.getX() > RECT_X3 && me.getX() < RECT_X3 + 100 && 
	        				me.getY() > RECT_Y && me.getY() < RECT_Y + 35) {
	        			try {
							test.createLogin();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	        			w.setVisible(true);
	        		} 
	        		else if(me.getX() > RECT_X4 && me.getX() < RECT_X4 + 70 && 
	        				me.getY() > RECT_Y && me.getY() < RECT_Y + 35) {
	        			try {
							test.login();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	        			w.setVisible(true);
	        		} 
	        		else if(me.getX() > RECT_X5 && me.getX() < RECT_X5 + 70 && 
	        				me.getY() > RECT_Y && me.getY() < RECT_Y + 35) {
	        			try {
							test.save();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	        		}
	        		else if(me.getX() > RECT_X6 && me.getX() < RECT_X6 + 100 && 
	        				me.getY() > RECT_Y1 && me.getY() < RECT_Y1 + 35) {
	        			try {
							test.changeColor();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	        			w.setVisible(true);
	        		} 
	          } 
	        }); 
	    w.setVisible(true);
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
	
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
}