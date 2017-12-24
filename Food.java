import java.awt.Point;
import java.util.Random;

//Use singleton design pattern so only one snake can be created
//Represents the food around the board
public class Food {

	//Creates one object of Food
	private static Food instance = new Food();
	private Point location; //Coordinates of the food
	private Random ran;
	
	private Food(){
		ran = new Random();
		setLocation();
	}
	
	//Get the only object available
	public static Food getInstance(){
		return instance;
	}
	
	//Set the location to a random location on the board
	public void setLocation(){
		location = new Point(ran.nextInt(Board.WIDTH) * Board.BOXSIZE, ran.nextInt(Board.HEIGHT) * Board.BOXSIZE);
		//If the food is outside of the panel boundaries
		if(location.x > Board.WIDTH-(Board.BOXSIZE*2) || location.y > Board.HEIGHT-(Board.BOXSIZE*3))
			setLocation();
	}

	public void reset(){
		setLocation();
	}
	
	public Point getLocation() {
		return location;
	}
}
