import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

//Represents the Snake's JPanel 
@SuppressWarnings("serial")
public class SnakePanel extends JPanel{
	
	private gameState state; //The state of the game: BEGINNING, MIDDLE, END
	private Timer timer; //Timer to perform task repeatedly
	private KeyInputHandler inputHandler; //KeyInputHandler to handle the keys given and the actions performed
	private Snake snake; //Snake in the game
	private Food food; //Food in the game
	private JLabel instructions; //The text display in the beginning and end of the game
	
	public SnakePanel(){
		super();
		//Set the state to be beginning
		state = gameState.BEGINNING;
		//Initialize the instructions (font, color)
		instructions = new JLabel();
		instructions.setFont(new Font("Verdana",1,20));
		instructions.setForeground(Color.LIGHT_GRAY);
		//Initialize the snake and food
		snake = Snake.getInstance();
		food = Food.getInstance();
		//Set the background of the panel to black
		setBackground(Color.BLACK);
		//Add key listener
		inputHandler = new KeyInputHandler(this);
		addKeyListener(inputHandler);
		//Initialize the timer with speed 65
		timer = new Timer(65, inputHandler);
	}
	
	//To request focus for the keyboard when the panel is added to the screen
	@Override
	public void addNotify() {
        super.addNotify();
        requestFocus();
    }
	
	//Paint our snake and food on the screen, or end the game if the snake dies
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		//Draw the game
		draw(g);
	}
	
	//Called on repaint()
	//Draws the appropriate: beginning/end (instructions) or game (snake and food)
	private void draw(Graphics g){
		//Draw the snake and the food
		drawSnake(g);
        //Toolkit.getDefaultToolkit().sync();
		drawFood(g);
			
		//If the game just began
		if(state == gameState.BEGINNING)
			startGame(g);
		//If the game is still going on
		else if(state == gameState.MIDDLE)
			continueGame(g);
		//Otherwise, the game is over
		else
			endGame(g);
	}
	
	//If the game just started, write out the instructions
	private void startGame(Graphics g){
		//Write out the beginning instructions to the screen
		instructions.setText("<html>Ready to slither?<br>Press any key to play.</html>");
		add(instructions);
	}
	
	//Remove the instructions from the panel and start the timer
	public void removeInstructions(){
		try{
		Container parent = instructions.getParent();
		parent.remove(instructions);
		parent.validate();
		parent.repaint();
		}
		catch (NullPointerException e){
			e.printStackTrace();
		}
		//Start the timer (start playing)
		timer.start();
	}
	
	//Continues playing the game
	private void continueGame(Graphics g){
		//Snake slithers in the appropriate direction
		snake.slither();
		
		//Check if the game is over
		if(hitBoundary() || hitSelf())
			state = gameState.END;
		//Check if the food is eaten
		if(hitFood()){
			snake.grow();
			//Draw new food in new location
			food.setLocation();
			drawFood(g);
		}
	}

	//If the snake hits any walls, returns true. Otherwise, returns false.
	private boolean hitBoundary(){
		if (snake.getHead().y < 0 || snake.getHead().y > Board.HEIGHT-(Board.BOXSIZE*3))
	    	return true;
	    else if(snake.getHead().x < 0 || snake.getHead().x > (Board.WIDTH-(Board.BOXSIZE*2)))
	    	return true;
	    else
	    	return false;
	}
	
	//If the snake hits itself, returns true. Otherwise, returns false.
	private boolean hitSelf(){
	    for (int i = snake.getNumBodies(); i > 0; i--) {
	    	//Snake must be 5 or longer to hit itself
	    	if (i >= 5){
	    		if (snake.getHead().x == snake.getBodies()[i].x && snake.getHead().y == snake.getBodies()[i].y)
	    			return true;
	    	}
	    }
	    return false;
	}
	
	//If the snake hits the food, return true. Otherwise, return false.
	private boolean hitFood(){
		if (snake.getHead().equals(food.getLocation())){
			System.out.println("FOOD");
			return true;
		}
		return false;
	}
	
	//If the game just ended, write out a statement and instructions
	private void endGame(Graphics g){
		//Stop the timer
		timer.stop();
		//Write out the end of the game information/instructions to the screen
		instructions.setText("<html>Game Over!<br>Press any key to play again.</html>");
		add(instructions);
	}
	
	//Restart the game
	public void restartGame(){
		//Initialize the snake and food
		snake.reset();
		food.reset();
	}

	//Draw the snake in the panel
	private void drawSnake(Graphics g){
		g.setColor(new Color(0, 153, 0));
		for(int i = 0; i < snake.getNumBodies(); i++){
			//Draw the snake green
			g.fillRect(snake.getSection(i).x, snake.getSection(i).y, Board.BOXSIZE, Board.BOXSIZE);
        }
	}
	
	//Draw the food in the panel
	private void drawFood(Graphics g){
		//Make sure the food doesn't overlap the snake
		if ((food.getLocation().x == snake.getHead().x) && (food.getLocation().y == snake.getHead().x))
			food.setLocation();
		//Draw the food pink
		g.setColor(new Color(255, 153, 255));
		g.fillOval(food.getLocation().x, food.getLocation().y, Board.BOXSIZE, Board.BOXSIZE);
	}
	
	public Snake getSnake() {
		return snake;
	}

	public gameState getState() {
		return state;
	}

	public void setState(gameState state) {
		this.state = state;
	}
}
