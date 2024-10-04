// Vanshika Turkar
// 4-19-2021
// HealthyChoices5.java
// Description: This game teaching people about eating healthy food and making 
// healthier choices.
// Working On: GUI, File I/O, and images (Everything, final game project)

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.Image;
import java.awt.Dimension;

import javax.swing.JFrame;	
import javax.swing.JPanel;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;

import javax.imageio.ImageIO;	
import javax.swing.Timer;

public class HealthyChoices5
{
	public static void main(String[] args)
	{
		HealthyChoices5 hc = new HealthyChoices5();
		hc.runIt();
	}
	
	//contains basic set-up for GUI
	public void runIt()
	{
		JFrame frame = new JFrame("START MAKING HEALTHY CHOICES");
		frame.setSize(800, 800);
		frame.setLocation(200, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GamePanel gPanel = new GamePanel();
		frame.getContentPane().add(gPanel);
		frame.setVisible(true);
	}
}

// This method adds cards to card layout and creates JPanels/classes
// contains getter and setter methods to recieves and sends values of the 
// time variable and fileName variable to different classes
class GamePanel extends JPanel
{
	private CardLayout allCards; // card layout
	private String fileName;  // name of meal and file that will be choosen
	private PlayPage pp; // instance of PlayPage
	private HighScorePage hsp; // instance of High Score Page
	private int time; // amount of time the word will show for --> getter, setter
	private String name; // name of player
	private int points; // points the user has
	private int errors; // errors the user has
	
	// creates CardLayout and adds cards to it
	public GamePanel()
	{
		String txtFileName = new String("");
		fileName = new String("");
		time = 0;
		allCards = new CardLayout();
		setLayout(allCards);
	
		StartPage p1 = new StartPage(this, allCards);
		add(p1, "1");
		
		SettingPage sp = new SettingPage(this, txtFileName, allCards);
		add(sp, "2");
		
		pp = new PlayPage(this, allCards);
		add(pp, "3");
		
		InfoPage ip = new InfoPage(this, allCards);	
		add(ip, "4");
		
		hsp = new HighScorePage(this, allCards);
		add(hsp, "5");	
	}
	
	//getter method for PlayPage
	public PlayPage getpp()
	{
		return pp;
	}
	
	//getter method for HighScorePage
	public HighScorePage gethsp()
	{
		return hsp;
	}
	
	//getter method for txtFileName
	public String getTXTName()
	{
		return fileName;
	}
	
	//setter method for txtFileName
	public void setTXTName(String txtFileNameIn)
	{
		fileName = txtFileNameIn;
	}
	
	//getter method for time
	public int getTime()
	{
		return time;
	}
	
	//setter method for time
	public void setTime(int timeIn)
	{
		time = timeIn;
	}
	
	//getter method for name
	public String getName()
	{
		return name;
	}
	
	//setter method for name
	public void setName(String nameIn)
	{
		name = nameIn;
	}
}
	
// Includes the buttons to navigate to the play, instructions, and high score pages. 
// Has option to exit the game using exit buttons.
// Contains one button handler with if statements to move to different pages
// Layout: Null Layout
class StartPage extends JPanel
{
	private GamePanel panel; // instance of panel with card layout
	private Image picture; // plate image
	private CardLayout allCards; // instance of CardLayout to shift pages
	private HighScorePage hsp; // instance of the highScore Page
	
	// creates all the buttons and adds them to the panel
	// includes setBounds() methods to positon the image and buttons manually
	// calls the getStartImage() to open the image 
	public StartPage(GamePanel gp, CardLayout ac)
	{
		allCards = ac;
		panel = gp;
		Image picture = null;
		hsp = panel.gethsp();
		
		setLayout(null);
		
		JLabel startLabel = new JLabel("Welcome to Healthy Choices", JLabel.CENTER);
		startLabel.setFont(new Font("Serif", Font.BOLD, 30));
		startLabel.setBounds(0,0, 800,100);
		add(startLabel);
		ButtonHandler bh = new ButtonHandler();
		
		JButton startB = new JButton("Start Game");
		startB.setBounds(50,200, 200,100);
		startB.addActionListener(bh);
		add(startB);
		
		JButton endB = new JButton("End Game");
		endB.setBounds(50,300, 200,100);
		endB.addActionListener(bh);
		add(endB);
		
		JButton infoB = new JButton("Instructions/Info");
		infoB.setBounds(50,500, 200,100);
		infoB.addActionListener(bh);
		add(infoB);
		
		JButton highScoreB = new JButton("High Scores");
		highScoreB.setBounds(50,600, 200,100);
		highScoreB.addActionListener(bh);
		add(highScoreB);
		
		getStartImage();
	}
	
	// uses try-catch method to open image file
	public void getStartImage()
	{
		File pictureFile = new File("plate1.jpeg");
		try
		{
			picture = ImageIO.read(pictureFile);
		}
		catch(IOException e)
		{
			System.err.println("\n\n" + "plate1.jpeg" + " can't be found.\n\n");
			e.printStackTrace();
		}
	}
	
	// shows image on the panel
	// uses values to place image
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(picture, 300 ,200, 450 ,500, this);
			
	}
	
	// button handler class for all buttons
	// uses if-else statements to check which button was pressed
	class ButtonHandler implements ActionListener
	{
		public void actionPerformed( ActionEvent evt ) 
		{
			String command = evt.getActionCommand();
			if(command.equals("Start Game"))
				allCards.show(panel, "2");
			else if(command.equals("End Game"))
				System.exit(1);
			else if(command.equals("Instructions/Info"))
				allCards.show(panel, "4");
			else if(command.equals("High Scores"))
			{
				allCards.show(panel, "5");
			}
		}
	}
}

// Contains JSlider, JButtons, and JRadioButtons to ask the user for amount of 
// time the word is shown for, the meal they prefer, and if they are ready to 
// starts the game. 
class SettingPage extends JPanel
{
	private GamePanel panel; // instance of panel with card layout
	private JSlider timeSlider; // Slider that the user uses to select time
	private CardLayout allCards; // instance of the Card Layout
	private String txtFileName; // name of txt file
	private int time; // time for which the word is shown for
	private JTextField userName; // the user of name that the player enters
	private Color lightPurple; // background color for this panel

	// adds the classes containing the slider and radio buttons to the panel.
	// Creates a button that take the user to the playPage
	public SettingPage(GamePanel gp, String tfn, CardLayout ac)
	{
		lightPurple = new Color(228, 230, 245);
		panel = gp;
		allCards = ac;
		txtFileName = tfn;
		time = 0;
		setLayout(new GridLayout(5,1));
		setBackground(lightPurple);
		JLabel labelOnTop = new JLabel("Let's start! Click a meal, set "+
			"the slider, and click the start button", JLabel.CENTER);
		labelOnTop.setFont(new Font("Serif", Font.BOLD, 20));
		add(labelOnTop);
		
		userName = new JTextField("Please Enter Your Name");
		userName.setEditable(true);
		TextHandler1 th1 = new TextHandler1();
		userName.addActionListener(th1);
		JPanel namePanel = new JPanel();
		namePanel.setBackground(lightPurple);
		namePanel.add(userName);
		add(namePanel);
		
		Slider ss = new Slider();
		add(ss);
		
		RB rbw = new RB();
		add(rbw);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1,2));
		buttonPanel.setBackground(lightPurple);
		JButton startButton = new JButton("Play Game");
		ButtonH sb = new ButtonH();
		startButton.addActionListener(sb);
		JButton backButton = new JButton("Go Back");
		backButton.addActionListener(sb);
		buttonPanel.add(backButton);
		buttonPanel.add(startButton);
		add(buttonPanel);
	}
	
	// creates the radio buttons and adds them to the panel
	// 1. Creates a Jlabel to make sure the user knows what the radio buttons mean
	// 2. Adds the buttons to a button group
	// 3. Add the buttons to the RB panel
	// According to the radio button that is picked a file will be opened
	class RB extends JPanel
	{
		private JRadioButton breakfastB; // breakfast RadioButton
		private JRadioButton lunchB; // lunch RadioButton
		private JRadioButton dinnerB; // dinner RadioButton
		public RB()
		{
			setBackground(lightPurple);
			ButtonGroup bg = new ButtonGroup();
			setLayout(new GridLayout(1,4));
			
			JLabel topofRB = new JLabel("Pick a Meal: ", JLabel.CENTER);
			topofRB.setFont(new Font("Serif", Font.BOLD, 20));
			add(topofRB);
			
			RBHandler rbh = new RBHandler();
			breakfastB = new JRadioButton("Breakfast");
			bg.add(breakfastB);	
			breakfastB.addActionListener(rbh); 
			add(breakfastB);
				
								
			lunchB = new JRadioButton( "Lunch" );
			bg.add(lunchB);	
			lunchB.addActionListener(rbh); 		
			add( lunchB );
			
				
			dinnerB = new JRadioButton( "Dinner" );
			bg.add(dinnerB);	
			dinnerB.addActionListener(rbh); 	
			add( dinnerB );
		}
	}
	
	// handler class for textField
	// calls the setter method for the user name
	class TextHandler1 implements ActionListener
	{
		public void actionPerformed( ActionEvent evt ) 
		{
			String name = "";
			name = userName.getText();
			panel.setName(name);
			
		}
	}
	
	// creates the slider and adds it to the panel
	// sets the spacing, ticks, and strating value of the slider
	// the value of the slider decides the time the word is shown for
	class Slider extends JPanel
	{
		public Slider()
		{
			setBackground(lightPurple);
			setLayout(new BorderLayout());
			JLabel slideLabel = new JLabel("Pick amount of time ( 1 - hard; 5- easy):",
				JLabel.CENTER);
				slideLabel.setFont(new Font("Serif", Font.BOLD, 20));
			add(slideLabel, BorderLayout.NORTH);
			
			timeSlider = new JSlider(1, 5);
			timeSlider.setMajorTickSpacing(1);
			timeSlider.setPaintTicks(true);
			timeSlider.setLabelTable( timeSlider.createStandardLabels(1) );
			timeSlider.setPaintLabels(true);
			timeSlider.setValue(3);
			timeSlider.setOrientation(JSlider.HORIZONTAL);
			
			SliderHandler sl = new SliderHandler();
			timeSlider.addChangeListener(sl);
			add(timeSlider, BorderLayout.CENTER);
			time = 3;
			panel.setTime(time);
		}	
	}
	
	// This is the handler class for the button that take you to the playpage
	// It uses the cardlayout instance
	class ButtonH implements ActionListener
	{
		public void actionPerformed( ActionEvent evt ) 
		{
			String command = evt.getActionCommand();
			if(command.equals("Play Game"))
			{
				allCards.show(panel, "3");
				PlayPage pp = panel.getpp();
				pp.setPage();
			}
			else
			{
				allCards.show(panel, "1");
			}
			
		}
	}
	
	// The handler class is for the radio button. It stores the name of the file
	// and then sends the name of the file to the setter methods. 
	class RBHandler implements ActionListener
	{
		public void actionPerformed( ActionEvent evt ) 
		{
			String command = evt.getActionCommand();
			getFileName(command);
		}
		
		//sends the name of the file to setter method
		public void getFileName(String commandIn)
		{
			String txtFileName = commandIn + ".txt";
			panel.setTXTName(txtFileName);
		}
	}
	
	// Gets the value of the slider and sends the value to the setter method for
	// the time variable.
	class SliderHandler implements ChangeListener
	{
		public void stateChanged (ChangeEvent evt)  
		{
			time = timeSlider.getValue();
			panel.setTime(time);
		}
	}
}

// The page is the page in which the game is played in
// It contains healthy and unhealthy buttons which the user clicks according to 
// the food shown. The JLabel shows the amount of points they have earned and 
// the amount of errors they have made.
class PlayPage extends JPanel
{
	private CardLayout allCards; // instance of CardLayout
	private GamePanel panel; // instance of the class with card layout
	private Scanner input; // input which reads from the file
	private String fileName; // name of the file that is opened
	private int errors; // the # of errors the player has made
	private int points; // the # of points the player has earned
	private int time; // the amount of time the word is shown for
	private Timer timer; // swing Timer 
	private JLabel showFood; // JLabel that shows the food
	private boolean healthy; // true: the food is healthy; false: food is unhealthy
	private boolean healthyButton; // true: healthy button is pressed
	private boolean unhealthyButton; // true: unhealhty button is pressed
	private JFrame frame2; // instance of Frame that is used in the pop-up
	private JLabel pointsErrors; // JLabel that shows the points and errors
	private PrintWriter output;  // object is used to print to the file
	private JPanel theLabels; // panel for the color change
	private int colorNum; // number for changing color screen.
	private String[] foods; // so that words don't repeat
	private int counter; // counter for changing the color of the background
	
	// intializes variables
	// Creates Panel for the buttons (healthy/unhealthy) and Jlabels to show 
	// point, error, and the food
	public PlayPage(GamePanel gp, CardLayout ac)
	{
		healthy = false;
		healthyButton = false;
		unhealthyButton = false;
		points = 0;
		allCards = ac;
		setLayout(new BorderLayout());
		panel = gp;
		input = null;
		output = null;
		counter = 0;
		
		foods = new String[50];
		
		pointsErrors = new JLabel("Points: " + points + "\t\tErrors: " + errors,
			JLabel.CENTER);
		
		theLabels = new JPanel();
		theLabels.setLayout(new BorderLayout());
		add(theLabels, BorderLayout.CENTER);
		
		pointsErrors.setFont(new Font("Serif", Font.BOLD, 20));
		
		theLabels.add(pointsErrors, BorderLayout.NORTH);
		
		showFood = new JLabel("", JLabel.CENTER);
		showFood.setFont(new Font("Serif", Font.BOLD, 30));
		theLabels.add(showFood, BorderLayout.CENTER);
		
		ButtonOnNorth bon = new ButtonOnNorth();
		theLabels.add(bon, BorderLayout.SOUTH);
		
	}
	
	// creates the healthy and unhealthy button and adds them to the panel
	class ButtonOnNorth extends JPanel
	{
		public ButtonOnNorth()
		{
			setLayout(new GridLayout(1,2));
			JButton healthy = new JButton("Healthy");
			ButtonH2 bh2 = new ButtonH2();
			healthy.addActionListener(bh2);
			healthy.setPreferredSize(new Dimension(295, 150));
			add(healthy);	
			JButton unhealthy = new JButton("Unhealthy");
			unhealthy.addActionListener(bh2);
			unhealthy.setPreferredSize(new Dimension(295, 150));
			add(unhealthy);	
		}
	}
	
	// calls the openFile() to open the file accoriding to the meal selected
	// called the getter methods for time and fileName in gamePanel
	// calls the setTimer() to start the timer
	public void setPage()
	{
		points = 0;
		errors = 0;
		
		pointsErrors.setText("Points: " + points + "\t\tErrors: " + errors);
		fileName = panel.getTXTName();
		openFile();
		time = panel.getTime();
		startTime();
	}
	
	//opens files according to the radio button choosen in Setting Page
	public void openFile()
	{
		File inFile = new File(fileName);
		try
		{
			input = new Scanner (inFile);
		}
		catch(FileNotFoundException e)
		{
			System.err.printf("\n\n\nERROR: "+
				"Cannot find/open file %s. Please click a radio button"+
				" before clicking the play game button. \n\n\n", fileName);
			System.exit(1);		
		}
	}
	
	// creates the timer and creates the TimerHandler
	// calls the getRandomWord()
	public void startTime()
	{
		TimerHandler th = new TimerHandler();
		timer = new Timer(time*1000, th);
		timer.start();
		getArray();
		getRandomWord();
	}
	
	//add all the food to an array and then shuffles it by using a for-loops. 
	public void getArray()
	{
		String temp = new String("");
		int count = 0;
		counter = 0;
		while(input.hasNext())
		{
			foods[count] = input.nextLine();
			count++;
		}
		
		int randomNum = 0;
		
		for(int i = 0; i < count - 1; i++)
		{
			randomNum = (int)((Math.random() * count - 1) + 1);
			
			temp = foods[i];
			foods[i] = foods[randomNum];
			foods[randomNum] = temp;
		}
		
	}
	
	// checks if the correct button was pressed according to if the food was 
	// healthy or unhealthy. Re-calculates the points and changes the text in the JLabel
	class TimerHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			if(errors<=4)
			{
				if(healthy == true && healthyButton == true && 
					unhealthyButton == false) 
				{
					points++;
				}
				else if(healthy == true && healthyButton == false)
				{
					errors++;
				}
				
				else if(healthy == false && unhealthyButton == true && 
					healthyButton == false)
				{
					points++;
				}
				else if(healthy == false && unhealthyButton == false)
				{
					errors++;
				}
				else
				{
					errors++;
				}
				
				healthyButton = false;
				unhealthyButton = false;
				pointsErrors.setText("Points: " + points + "\t\tErrors: " + errors);
				getRandomWord();
			}
			else
			{
				setBackground(Color.RED);
				timer.stop();
				newPanel();
				highScore();
			}
		}
	}
	
	// creates a new feedback panel which displays an encouraging message and 
	// shows the points and errors the user has earned.
	public void newPanel()
	{
		Color lightYellow = new Color(245, 242, 228);
		frame2 = new JFrame("Feedback");
		frame2.setSize(500, 500);
		frame2.setLocation(350, 150);
		frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel feedback = new JPanel();
		frame2.getContentPane().add(feedback);
		frame2.setVisible(true);
		feedback.setLayout(new BorderLayout());
		ButtonHandlerSouth bhs = new ButtonHandlerSouth();
		JButton startPage = new JButton("Go to Start Page");
		startPage.setPreferredSize(new Dimension(295, 100));
		startPage.addActionListener(bhs);
		JPanel buttonSouth = new JPanel();
		buttonSouth.setLayout(new GridLayout(1,1));
		buttonSouth.add(startPage);
		feedback.add(buttonSouth, BorderLayout.SOUTH);
		JLabel message = new JLabel("<html> <center><br>Way To GO!</center> </html>",
			JLabel.CENTER);
		message.setFont(new Font("Serif", Font.PLAIN, 30));
		feedback.add(message, BorderLayout.NORTH);
		JLabel infoForFood = new JLabel("<html> <center>You have " + points + 
			" points!!! <br><br>" +"Compare your score with other's! <br><br>"+
			"Start by clicking the button below. <br><br> " + 
			"To learn more go to the Instructions/Info page. </center> </html>"
			, JLabel.CENTER);
		infoForFood.setFont(new Font("Serif", Font.PLAIN, 20));
		feedback.add(infoForFood, BorderLayout.CENTER);
		feedback.setBackground(lightYellow);
	}
	
	// if the button is pressed the frame closes and the cardlayout is set to 
	// the first page again.
	class ButtonHandlerSouth implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			String command = evt.getActionCommand();
			allCards.show(panel, "1");
			frame2.dispose();
		}
	}
	
	// opens the highScore.txt file to append the new scores to it. 
	public void highScore()
	{
		String name = "";
		name = panel.getName();
		File outFile = new File("HighScore.txt");
		try
		{
			output = new PrintWriter(new FileWriter(outFile, true));
		}
		catch(IOException e )
		{
			System.out.println("\n\n\nERROR: Cannot create " + 
				"HighScore.txt" + "file\n\n\n"); 
			System.exit(1);
		}
		
		output.println(name + " - " + points);
		output.close();
	}
	
	
	// It reads one food from the array each time it is called.
	// It calculates if it is Healthy or unhealthy according to the H and N
	// If all the word are done before 5 errors a cogragulations message is shown.
	public void getRandomWord()
	{
		String line = new String("");
		if(foods[counter] != null)
		{
			line = foods[counter];
		
			int indexOfDash = line.indexOf('-');
			if(line.substring(indexOfDash+1).trim().equals("H"))
			{
				healthy = true;
			}
			else
			{
				healthy = false;
			}
			showFood.setText(line.substring(0,indexOfDash).trim());
			colors();
		}
		else
		{
			showFood.setText("<html> <center>Congratulations! <br> " + 
				"You have finished all the words "+
				"in this meal.<br>"+
				" You can play again with a new"+
				" meal or give yourself a challenge by reducing the "+
				"time.</center> </html>");
			errors = 5;
			counter = 0;
		}
		
		counter++;
		input.close();
		openFile();
	}
	
	// Creates multiple colors using rgb
	// Changes the background of the panel each time a new word is shown
	public void colors()
	{
		Color lightYellow = new Color(245, 242, 228);
		Color lightGreen = new Color(228, 245, 229);
		Color lightBlue = new Color(228, 245, 245);
		Color lightPurple = new Color(228, 230, 245);
		Color lightPink = new Color(245, 228, 244);
		
		if(colorNum < 5)
		{
			if(colorNum == 0)
				theLabels.setBackground(lightYellow);
			else if(colorNum == 1)
				theLabels.setBackground(lightGreen);
			else if(colorNum == 2)
				theLabels.setBackground(lightBlue);
			else if(colorNum == 3)
				theLabels.setBackground(lightPurple);
			else if(colorNum == 4)
				theLabels.setBackground(lightPink);
		}
		else
		{
			colorNum = 0;
			theLabels.setBackground(lightYellow);
		}
		colorNum++;
	}
	
	//Handlers for both buttons (healthy and unhealthy)
	class ButtonH2 implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			String command = evt.getActionCommand();
			if(command.equals("Healthy"))
			{
				healthyButton = true;
			}
			else if(command.equals("Unhealthy"))
			{
				unhealthyButton = true;
			}
		}
	}
}
//contains instructions on how to play the game
// Also has extra information on healthy food choices such as nutrition
class InfoPage extends JPanel
{
	private CardLayout allCards; //instance of CardLayout
	private GamePanel panel; // instance of class with CardLayout
	
	// creates the JScrollPane and adds instructions on how to play it
	public InfoPage(GamePanel gp, CardLayout ac)
	{
		Color lightGreen = new Color(228, 245, 229);
		allCards = ac;
		panel = gp;
		setBackground(lightGreen);
		JTextArea info = new JTextArea(40,60);
		info.setText("Instructions for Healthy Choices: \n\n How To Set-Up The Game:" +
			" First click the Start Game button on the start page," +
			" you will be taken to your settings page. Here you can pick the amount "+
			"of time the food will be shown for. 1 corresponds to 1 second and  5 corre"+
			"sponds to 5 seconds. After picking the time you can click on meal options"+
			". The options are breakfast, lunch, and dinner. According to what you pick "+
			"foods from that meal will be shown. \n\n How To Play The Game: Once you Click" + 
			" the \"Play Game\" button you will be taken to the play "+
			"page where you will play the game. Different words will be shown and you need to"+
			" click the healthy or unhealthy button according to the word. Remeber to click in"+
			" time or else you will get an error. Once the errors reaches 5, the game will end "+
			"and a screen will show giving you your final points. On the bottom you will see a "+
			"button which will take you back to the start page once pressed. You can then play "+
			"the game again, look at the instructions, or see the high scores. \n\n\n More "+
			"Information On Nutrition: \n\t Carbohydrates - These are our primary "+
			"energy source and are turned into glucose to be used by our cells. "+
			"They come in 2 categorizes, simple carbohydrates, sugars, and complex "+
			"carbohydrates, whole wheat bread. Carbohydrates also contains a glycemic "+
			"index. Gylcemic Index is how quickly a food rasies your blood sugar "+
			"levels. You should avoid high gylcemic carbs, such as White bread,"+
			" white rice, pasta, snack, cookies, candy, and soda, and go for low "+
			"glycemic carbs, such as whole grains, dairy products. \n\t Fats - "+
			"Fats come in many different forms, there are monounsaturated fats "+
			"(omega 3 fatty acid), saturated fats, and trans fats. Omega 3 fats "+
			"are the best they are found in fish, soybeans, flaxseed, nuts and "+
			"avocados. Saturated fats are neutral fats, they are ok when eaten "+
			"in moderation, some examples are red meat and cheese. Trans fats "+
			"are the last and worst type of fats. They are also known are "+
			"hydrogenated oils and are found in processed and fried foods. "+
			"\n\tProteins - Proteins help in building and repairing tissues "+
			"and for creating hormones and enzymes. Since our bod does not "+
			"make all the amino acids need we need to consume them. Some protein "+
			"rich foods are lean meats, dairy products, nuts, beans and seafood."+
			" \n\t Micronutrients - Vitamins and minerals are important for the "+
			"functions of enzymes, bone strength, and the immune system. Antiox"+
			"idants and Phytochemicals are another micronutrient which are "+
			"found in colorful fruits and vegetables. They help protect "+
			"against cancer, aging, and effects of stress. \n\n Final Takeaways:"+
			"  Eat a variety of healthy foods every day and exercise regularly, "+
			"eat lots of different colorful fruits, vegetables, whole grains, "+
			"and consume good proteins and fats. Remember to avoid highly "+
			"processed foods, foods that are low in nutritional value, "+
			"and bad fats.");
		info.setFont(new Font("Serif", Font.PLAIN, 17));
		info.setLineWrap(true); 
		info.setWrapStyleWord(true); 
		info.setEditable(false);
		JScrollPane infoPane = new JScrollPane(info);
		add(infoPane);
		
		JButton backButton = new JButton("Click To Go Back");
		BackButtonH bbh = new BackButtonH();
		backButton.addActionListener(bbh);
		add(backButton);
	}
	
	// go back to start page
	class BackButtonH implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			allCards.show(panel, "1");
		}
	} 
}
// has high score for players who have played the game
class HighScorePage extends JPanel
{
	private CardLayout allCards; //instance of CardLayout
	private GamePanel panel; // instance of class with CardLayout
	private String points; // the amount of points the user has
	private Scanner inputScore; // it is the instance of the Scanner
	private int[] scores; // array to store scores
	private String[] names; // array to store corresponding names
	
	// creates go back button
	// creates the textArea and scrollpane for the high scores
	// calls open file and sets the text that comes back to the textArea
	public HighScorePage(GamePanel gp, CardLayout ac)
	{
		Color lightPink = new Color(245, 228, 244);
		scores = new int[50];
		names = new String[50];
		allCards = ac;
		panel = gp;
		inputScore = null;
		String text  = new String("");
		setBackground(lightPink);
		
		JTextArea score = new JTextArea(40,60);
		score.setText("");
		score.setLineWrap(true); 
		score.setWrapStyleWord(true); 
		score.setEditable(false);
		JScrollPane scorePane = new JScrollPane(score);
		add(scorePane);
		
		text = openFile();
		
		score.setText("These are the top 10 High Score's\n\n" +text);
		score.setFont(new Font("Serif", Font.PLAIN, 17));
		
		JButton backButton = new JButton("Click To Go Back");
		BackButtonH bbh = new BackButtonH();
		backButton.addActionListener(bbh);
		add(backButton);
	}
	
	// opens the highScore.txt file and then calls order()
	public String openFile()
	{
		String textOut = new String("");
		File inFile = new File("HighScore.txt");
		try
		{
			inputScore = new Scanner (inFile);
		}
		catch(FileNotFoundException e)
		{
			System.err.printf("\n\n\nERROR: "+
				"Cannot find/open file %s\n\n\n", "HighScore.txt");
			System.exit(1);		
		}
		
		textOut = order();
		 
		inputScore.close();
		return textOut;
	
		
	}
	
	// reads from the file and saves everything in a array
	// then sorts the array from greatest to least, at the same time keeping 
	// the names and points corresponding to one another.
	// The last loop goes through the first 10 values of the array and puts 
	// it in a string. The string is returned and then shown on the textArea.
	public String order()
	{
		String highScores = new String("");
		int temp = 0;
		String tempName = new String("");
		int index = 0;
		int score1 = 0;
		int score2 = 0;
		String line = new String("");
		int indexOfHypen = 0;
		
		while(inputScore.hasNext())
		{
			line = inputScore.nextLine();
			indexOfHypen = line.indexOf('-');
			names[index] = line.substring(0, indexOfHypen).trim();
			scores[index] = Integer.parseInt(line.substring(indexOfHypen + 1).trim());
			index++;
		}
		
		
		for (int i = 0; i < 50; i++) 
        {
            for (int j = i + 1; j < 50; j++) 
            { 
                if ( scores[i] < scores[j] ) 
                {
                    temp = scores[i];
                    tempName = names[i];
                    scores[i] = scores[j];
                    names[i] = names[j];
                    scores[j] =  temp;
                    names[j] = tempName;
                }
            }
        }
        
        for(int y = 0; y < 10; y++)
        {
			if(names[y] != null)
				highScores = highScores + "\n\n" + names[y] + "     -     " + scores[y];
		}
        
		return highScores;
	}
	
	// go back to start page
	class BackButtonH implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			allCards.show(panel, "1");
		}
	} 
}
