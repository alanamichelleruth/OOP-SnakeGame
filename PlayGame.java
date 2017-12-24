import javax.swing.SwingUtilities;

//Contains the main method to play the Snake game
public class PlayGame {
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Board();
			}
		});
	}	
}
