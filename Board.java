import javax.swing.JFrame;

//Represents the board for the snake game
@SuppressWarnings("serial")
public class Board extends JFrame{

	//Declare constants for the sizes of the display and boxes
	public static final int WIDTH = 588; //600
	public static final int HEIGHT = 588; //600
	public static final int BOXSIZE = 14; //15 12
	public static final int TOTALBOXES = (WIDTH*HEIGHT)/(BOXSIZE*BOXSIZE); //1600 2500
	
	private SnakePanel snkPanel;
	
	public Board(){
		//Call super (the constructor of JFrame())
		super();
		//Customize the frame
		setFrameSpecifics();
		//Add the SnakePanel
		snkPanel = new SnakePanel();
		getContentPane().add(snkPanel);
		//Set the board to be visible
		setVisible(true);
	}
	
	//Method to customize the frame specifics
	private void setFrameSpecifics(){
		//Set the title of the frame to "Snake"
		setTitle("Snake");
		//Set the size of the frame to be width x height
		setSize(WIDTH-7, HEIGHT);
		//Make sure the window always appears on top of other windows
		setAlwaysOnTop(true);
		//Do not allow user to resize the window
		setResizable(false);
		//Make sure the window appears in the middle of the screen
		setLocationRelativeTo(null);
		//Determines what should happen when the frame is closed
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
