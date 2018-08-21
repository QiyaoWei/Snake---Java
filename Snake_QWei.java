
public class Snake_QWei 
{
	
	private int x , y;
	private int xi , yi;
	private int[][]mtx;
	private int dir;
	private int length , count;
	private int SIZE;
	
	public Snake_QWei(Board_QWei myBoard , int r , int c , int d)
	{
		x         = r;
		xi        = r;
		y         = c;
		yi        = c;
		SIZE      = myBoard.retSIZE();
		mtx       = myBoard.retMtx();
		mtx[y][x] = 1;
		dir       = d;
		length    = 4;
		count     = 0;//how many times snake has moved
	}
	
	//test if out of bounds
	//***why is it >=? Decision first, then move
	public boolean determineDir()
	{
		int m = y;
		int n = x;
		
		if ((dir == 1) && (m-SIZE < 0 || mtx[m-SIZE][n] > 0) || (dir == 2) && (n+SIZE >= mtx[0].length || mtx[m][n+SIZE] > 0) || (dir == 3) && (m+SIZE >= mtx.length || mtx[m+SIZE][n] > 0) || (dir == 4) && (n-SIZE < 0 || mtx[m][n-SIZE] > 0))
			return true;

		return false;
	}
	
	public void choose()
	{
		boolean choice = determineDir();
		int m = y;
		int n = x;
		int i=0, j=0;
		for (int a = 0; a<mtx.length; a=a+SIZE)
			for (int b = 0; b<mtx[0].length; b=b+SIZE)
				if (mtx[a][b] == -1)
				{
					i = a;
					j = b;
				}
		
		//turning without dying
		if (choice == true)
		{
			if ((dir == 1 || dir == 3) && (n+SIZE >= mtx[0].length || mtx[m][n+SIZE] > 0))
				dir = 4;
			else if (dir == 1 || dir == 3)
				dir = 2;
			else if ((dir == 2 || dir == 4) && (m+SIZE >= mtx.length || mtx[m+SIZE][n] > 0))
				dir = 1;
			else if (dir == 2 || dir == 4)
				dir = 3;
			/*
			else if (dir == 3 && mtx[y][x-SIZE] > 0)//j <= x
				dir = 4;
			else if (dir == 3 && mtx[y][x+SIZE] > 0)//j > x
				dir = 2;
			else if (dir == 4 && mtx[y+SIZE][x] > 0)//i <= y
				dir = 1;
			else if (dir == 4 && mtx[y-SIZE][x] > 0)//i > y
				dir = 3;
			*/
		}
		
		//optimization for fruit
		else
		{
			if (j==n && i>m && dir != 1 && mtx[m+SIZE][n] <= 0)
				dir = 3;
			else if (j==n && i<m && dir != 3 && mtx[m-SIZE][n] <= 0)
				dir = 1;
			else if (i==m && j>n && dir != 4 && mtx[m][n+SIZE] <= 0)
				dir = 2;
			else if (i==m && j<n && dir != 2 && mtx[m][n-SIZE] <= 0)
				dir = 4;
		}
	}
	
	/*
	public void setDir(int d)
	{
		if (d == 1 && dir!=3)
			dir = 1;
		else if (d == 2 && dir!=4)
			dir = 2;
		else if (d == 3 && dir!=1)
			dir = 3;
		else if (d == 4 && dir!=2)
			dir = 4;
	}
	*/
	
	public void move()//smartest move using lowest memory
	{
		choose();
		
		int d = dir;
		int v;
		
		switch (d)
		{
		case 1:
			mtx[y][x] = 1;
			y = y-SIZE;
			break;
		case 2:
			mtx[y][x] = 2;
			x = x+SIZE;
			break;
		case 3:
			mtx[y][x] = 3;
			y = y+SIZE;
			break;
		case 4:
			mtx[y][x] = 4;
			x = x-SIZE;
			break;
		}
		
		count++;
		
		if (count > length)//tail remove
		{
			v = mtx[yi][xi];
			mtx[yi][xi] = 0;
			switch (v)
			{
			case 1:
				yi = yi-SIZE;
				break;
			case 2:
				xi = xi+SIZE;
				break;
			case 3:
				yi = yi+SIZE;
				break;
			case 4:
				xi = xi-SIZE;
				break;
			}
			
			count--;
		}
	}
	
	public void eat()
	{
		int m = y;
		int n = x;
			
		if (mtx[m][n] == -1)
		{
			mtx[m][n] = 0;
			length++;
		}
	}
	
	public boolean checkCollision()
	{
		int m = y;
		int n = x;
		
		if (m<0 || n>mtx[0].length || m>mtx.length || n<0 || mtx[m][n]>0)
			return true;
		
		return false;
	}
}