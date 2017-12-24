import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInputHandler implements ActionListener, KeyListener{

	private SnakePanel snkPanel;
	
	public KeyInputHandler(SnakePanel panel){
		snkPanel = panel;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		snkPanel.repaint();
	}
	
	//Given whichever key was pressed, have the snake go the appropriate way
	@Override
	public void keyPressed(KeyEvent e){		
		//If the game just started or ended, play (again)
		if (snkPanel.getState() == gameState.BEGINNING || snkPanel.getState() == gameState.END){
			//Remove the instructions from the panel
			snkPanel.removeInstructions();
			
			//If the game just ended, restart the game
			if(snkPanel.getState() == gameState.END)
				snkPanel.restartGame();
			
			//Set state to middle of game
			snkPanel.setState(gameState.MIDDLE);
		}
		
		//Update the direction of the snake with the given pressed key
		updateDirection(e.getKeyCode(),	snkPanel.getSnake());
	}
		
	private void updateDirection(int key, Snake snake){
		//If the key was right and the snake wasn't already going right
		if(key == KeyEvent.VK_RIGHT && snake.getDirection() != Direction.RIGHT){
			if(snake.getDirection() != Direction.LEFT)
				//go right
				snake.setDirection(Direction.RIGHT);
		}
		//If the key was left and the snake wasn't already going left
		else if(key == KeyEvent.VK_LEFT && snake.getDirection() != Direction.LEFT){
			if(snake.getDirection() != Direction.RIGHT)
				//go left
				snake.setDirection(Direction.LEFT);
		}
		//If the key was up and the snake wasn't already going up
		else if(key == KeyEvent.VK_UP && snake.getDirection() != Direction.UP){
			if(snake.getDirection() != Direction.DOWN)
				//go up
				snake.setDirection(Direction.UP);
		}
		//If the key was down and the snake wasn't already going down
		else if(key == KeyEvent.VK_DOWN && snake.getDirection() != Direction.DOWN){
			if(snake.getDirection() != Direction.UP)
				//go down
				snake.setDirection(Direction.DOWN);	
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}
	
	public SnakePanel getSnkPanel(){
		return snkPanel;
	}
}
