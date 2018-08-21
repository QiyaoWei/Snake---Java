import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
	
public class Board_QWei extends JPanel
{
	
	private final int NUMROW;
	private final int NUMCOL;
	private final int SIZE;
	private int[][] mtx;
	private int gameOver;
	
	public Board_QWei()
	{
		NUMROW   = 20;
		NUMCOL   = 20;
		SIZE     = 20;
		mtx      = new int[NUMROW*SIZE][NUMCOL*SIZE];
		gameOver = 0;
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(NUMCOL*SIZE , NUMROW*SIZE));
	}

	public int[][] retMtx()//returns the matrix for Snake to use
	{
		return mtx;
	}
	
	public int retSIZE()
	{
		return SIZE;
	}
	
	public int gameOver(int m)//sets the int gameOver for paintComponent
	{
		gameOver = m;
		return gameOver;
	}
	
	public boolean checkZero()
	{
		for (int p = 0; p < NUMROW; p++) 
			for (int q = 0; q < NUMCOL; q++) 
				if (mtx[p*SIZE][q*SIZE] == 0)
					return true;
		
		return false;
	}
	
	public void setRandom()
	{
		boolean zero = checkZero();
		while (zero == true) 
		{
			int a = (int) (Math.random() * NUMROW);
			int b = (int) (Math.random() * NUMCOL);
			
			if (mtx[a*SIZE][b*SIZE] == 0)
			{
				mtx[a*SIZE][b*SIZE] = -1;
				return;
			}
		}
	}
	
	public boolean checkFruit()
	{
		for (int p = 0; p < NUMROW; p++) 
			for (int q = 0; q < NUMCOL; q++) 
				if (mtx[p*SIZE][q*SIZE] == -1)
					return true;
		
		return false;
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		//Do you think this drew a box or a grid?
		g.setColor(Color.LIGHT_GRAY);
		for ( int j=0 ; j<NUMROW ; j++)
			g.drawLine(0 , j*SIZE , NUMROW*SIZE , j*SIZE);
		for ( int i=0 ; i<NUMCOL ; i++)
			g.drawLine(i*SIZE , 0 , i*SIZE , NUMCOL*SIZE);
		
		g.setColor(Color.RED);
		for ( int j=0 ; j<NUMROW ; j++)
			for ( int i=0 ; i<NUMCOL ; i++)
				if ( mtx[j*SIZE][i*SIZE] > 0)
					g.fillRect(i*SIZE, j*SIZE, SIZE, SIZE);
		
		g.setColor(Color.CYAN);
		for ( int j=0 ; j<NUMROW ; j++)
			for ( int i=0 ; i<NUMCOL ; i++)
				if ( mtx[j*SIZE][i*SIZE] == -1)
					g.fillOval(i*SIZE, j*SIZE, SIZE, SIZE);
		
		g.setColor(Color.WHITE);
		if (gameOver == 1)
			g.drawString("YOU LOSE!", SIZE, 14*SIZE);
	}
}