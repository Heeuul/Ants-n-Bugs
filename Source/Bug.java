/* 
Directions: 
0: Up 
1: Down 
2: Left 
3: Right 
*/ 
public class Bug extends Organism 
{
	boolean debug = false; 
	
	//Variables 
	private int hunger; 
	private int starving; 
	
	//Constructor 
	public Bug() {} 
	public Bug(int id) 
	{
		super(id); 
		breedAge 	= 8; 
		age 		= 0; 
		hunger 		= 3; 
		starving 	= 0; 
	} 
	
	//Methods 
	public void move(World world) //Bug move (Aiman Hans, Lim Ee Tien, Lim Kee Hian) 
	{
		int direction = 0; 
		
		boolean up, down, left, right, devour; 
		up = down = left = right = true; 
		devour = false; 
		
		if(posX == 0) 					left = false; 
		if(posX == world.getSizeX()-1) 	right= false; 
		if(posY == 0) 					up 	 = false; 
		if(posY == world.getSizeY()-1) 	down = false; 
		
		if(up) 
		{
			if(world.getContent(posX,posY-1) instanceof Ant) 		{ devour = true; direction = 0; starving = 0; } 
			else if(world.getContent(posX,posY-1) instanceof Bug) 	up	 = false; 
		} 
		if(down) 
		{
			if(world.getContent(posX,posY+1) instanceof Ant) 		{ devour = true; direction = 1; starving = 0; } 
			else if(world.getContent(posX,posY+1) instanceof Bug) 	down = false; 
		} 
		if(left) 
		{
			if(world.getContent(posX-1,posY) instanceof Ant) 		{ devour = true; direction = 2; starving = 0; } 
			else if(world.getContent(posX-1,posY) instanceof Bug) 	left = false; 
		} 
		if(right) 
		{
			if(world.getContent(posX+1,posY) instanceof Ant) 		{ devour = true; direction = 3; starving = 0; } 
			else if(world.getContent(posX+1,posY) instanceof Bug) 	right= false; 
		} 
		
		if(up || down || left || right) 
		{
			
			if(!devour) direction = getDirection(up, down, left, right); 
			
			switch(direction) 
			{
				case 0: if(devour) world.eat(posX, posY-1, this); else setPos(posX, posY-1); break; 
				case 1: if(devour) world.eat(posX, posY+1, this); else setPos(posX, posY+1); break; 
				case 2: if(devour) world.eat(posX-1, posY, this); else setPos(posX-1, posY); break; 
				case 3: if(devour) world.eat(posX+1, posY, this); else setPos(posX+1, posY); break; 
			} 
		} 
		else { if(debug) System.out.println("b" + getID() + " cannot move!"); } 
	} 
	public void breed(World world) //Bug breed (Aiman Hans, Lim Ee Tien, Lim Kee Hian) 
	{
		age = age + 1; 
		starving = starving + 1; 
		
		if(age%breedAge == 0) 
		{
			int bPosX, bPosY; 
			bPosX = posX; 
			bPosY = posY; 

			int direction = 0; 
			
			boolean up, down, left, right; 
			up = down = left = right = true; 
			
			if(posX == 0) 					left = false; 
			if(posX == world.getSizeX()-1) 	right= false; 
			if(posY == 0) 					up 	 = false; 
			if(posY == world.getSizeY()-1) 	down = false; 
			
			if(up) 
			{
				if(world.getContent(posX, posY-1) instanceof Ant || world.getContent(posX, posY-1) instanceof Bug) up 	= false; 
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
				if(world.getContent(posX+1, posY) instanceof Ant || world.getContent(posX+1, posY) instanceof Bug) right= false; 
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
			else { if(debug) System.out.println("b" + getID() + " cannot breed!"); } 
			if(breedAble) 
			{
				world.breedBug(bPosX, bPosY); 
				breedAble = false; 
			} 
		} 
	} 
	public boolean starve() //Bug starve (Aiman Hans, Lim Ee Tien, Lim Kee Hian) 
	{
		if(starving == hunger) 
		{
			if(debug) System.out.println("b" + getID() + " starved to death!"); 
			return true; 
		} 
		else 
		{
			if(debug) System.out.println("b" + getID() + " starve meter:" + starving); 
			return false; 
		} 
	} 
}