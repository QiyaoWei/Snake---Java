import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SnakeGame_QWei extends JFrame
{
		
	Board_QWei myBoard    = new Board_QWei();
	Snake_QWei mySnake    = new Snake_QWei(myBoard , 40 , 200 , 2);
	
	public SnakeGame_QWei()
	{
		super ("Snake");
		
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		add(myBoard , BorderLayout.CENTER);
		
		//keyListener();
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
		gameLoop();
	}
	
	/*
	public void keyListener()
	{
		addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				switch (e.getKeyCode())
				{
					//checks if keys are pressed
					case KeyEvent.VK_W:
					case KeyEvent.VK_UP:
						mySnake.setDir(1);
						break;
						
					case KeyEvent.VK_D:
					case KeyEvent.VK_RIGHT:
						mySnake.setDir(2);
						break;
						
					case KeyEvent.VK_S:
					case KeyEvent.VK_DOWN:
						mySnake.setDir(3);
						break;
						
					case KeyEvent.VK_A:
					case KeyEvent.VK_LEFT:
						mySnake.setDir(4);
						break;
				}
			}
		});
	}
	*/
	
	public void gameLoop()
	{
		do
		{
			mySnake.move();
			
			try 
			{
			    Thread.sleep(50);
			} 
			catch(InterruptedException ex) 
			{
			    Thread.currentThread().interrupt();
			}
			
			myBoard.repaint();
		
			if (mySnake.checkCollision() == false)
			{
			mySnake.eat();//eat
			myBoard.repaint();
		
			if (myBoard.checkFruit() == false)//put fruit
				myBoard.setRandom();
		
			myBoard.repaint();
			}
			
		} while (mySnake.checkCollision() == false);
		
		if (mySnake.checkCollision() == true)//gameover
		{
			myBoard.gameOver(1);
			return;
		}
		
		myBoard.repaint();
	}
	
	public static void main(String[] args)
	{		
		SnakeGame_QWei snakeGame = new SnakeGame_QWei();
	}
}