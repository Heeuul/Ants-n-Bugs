/* 
Directions: 
0: Up 
1: Down 
2: Left 
3: Right 
*/ 
public class Ant extends Organism 
{
	boolean debug = false; 
	
	//Constructor 
	public Ant() {} 
	public Ant(int id) 
	{
		super(id); 
		breedAge = 3; 
		age = 0; 
	} 
	
	//Method 
	public void move(World world) //Ant move (Aiman Hans, Lim Ee Tien, Lim Kee Hian) 
	{
		int direction = 0; 
		
		boolean up, down, left, right; 
		up = down = left = right = true; 
		
		if(posX == 0) 					left = false; 
		if(posX == world.getSizeX()-1) 	right = false; 
		if(posY == 0) 					up = false; 
		if(posY == world.getSizeY()-1) 	down = false; 
		
		if(up) 
		{
			if(world.getContent(posX, posY-1) instanceof Ant || world.getContent(posX, posY-1) instanceof Bug) up = false; 
		} 
		if(down) 
		{
			if(world.getContent(posX, posY+1) instanceof Ant || world.getContent(posX, posY+1) instanceof Bug) down = false; 
		} 	
		if(left) 
		{
			if(world.getContent(posX-1, posY) instanceof Ant || world.getContent(posX-1, posY) instanceof Bug) left = false; 
		} 
		if(right) 
		{
			if(world.getContent(posX+1, posY) instanceof Ant || world.getContent(posX+1, posY) instanceof Bug) right = false; 
		} 
		
		if(up || down || left || right) 
		{
			direction = getDirection(up, down, left, right); 
			breedAble = true; 
			
			switch(direction) 
			{
				case 0: setPos(posX, posY-1); break; 
				case 1: setPos(posX, posY+1); break; 
				case 2: setPos(posX-1, posY); break; 
				case 3: setPos(posX+1, posY); break; 
			} 
		} 
		else { if(debug) System.out.println("a" + getID() + " cannot move!"); } 
	} 
	public void breed(World world) //Ant breed (Aiman Hans, Lim Ee Tien, Lim Kee Hian) 
	{
		age = age + 1; 
		
		if(age%breedAge == 0) 
		{
			int bPosX, bPosY; 
			bPosX = posX; 
			bPosY = posY; 
			move(world); 
			if(breedAble) 
			{
				world.breedAnt(bPosX, bPosY); 
				breedAble = false; 
			} 
			else { if(debug) System.out.println("a" + getID() + " cannot breed!"); } 
		} 
	} 
}