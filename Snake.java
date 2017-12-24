import java.awt.Point;

//Use singleton design pattern so only one snake can be created
//Represents the snake in the game
public class Snake {
	
	//Create ones object of Snake
	private static Snake instance = new Snake();	
	private Point[] bodies;
	//private ArrayList<Point> bodies; //Coordinates of each body of the snake
	private int numBodies;
	private Direction direction; //Direction the snake is heading
	
	//Make constructor private so the class cannot be instantiated
	private Snake(){
		//Initialize the bodies
		bodies = new Point[Board.TOTALBOXES];
		numBodies = 1;

		//Add the first body point to be in the middle of the board
		bodies[0] = (new Point(Board.WIDTH/2, Board.HEIGHT/2));
	}	
	
	//Get the only object available
	public static Snake getInstance(){
		return instance;
	}
	
	//Slither around the board, in the appropriate direction
	public void slither(){
		bodies[numBodies] = new Point();
		//Iterate over the snake body
		for (int i = numBodies; i > 0; i--) {
			//Move the each coordinate of the snake's body
			bodies[i].x = bodies[i-1].x;
			bodies[i].y = bodies[i-1].y;
			//System.out.println(i + "\nX: " + bodies[i].x + "\nY: " + bodies[i].y);
		}
		
		//Slither right
		if (direction == Direction.RIGHT)
			bodies[0].x += Board.BOXSIZE;
	    //Slither left
	    if (direction == Direction.LEFT)
	        bodies[0].x -= Board.BOXSIZE;
	    //Slither up
	    if (direction == Direction.UP)
	        bodies[0].y -= Board.BOXSIZE;
	    //Slither down
	    if (direction == Direction.DOWN)
	        bodies[0].y += Board.BOXSIZE;
	}
	
	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public Point getHead(){
		return bodies[0];
	}
	
	public Point[] getBodies() {
		return bodies;
	}
	
	//Add bodies to the snake to make it bigger
	public void grow(){
		numBodies ++;
	}
	
	public int getNumBodies() {
		return numBodies;
	}

	//Returns the Point at the given index (section of the body)
	public Point getSection(int index){
		return bodies[index];
	}
	
	//Reset the snake
	public void reset(){
		//Reset direction
		direction = null;
		//Reset the bodies
		bodies = new Point[Board.TOTALBOXES];
		numBodies = 1;
		//Set the first body point to be in the middle of the board
		bodies[0] = (new Point(Board.WIDTH/2, Board.HEIGHT/2));
	}
}
