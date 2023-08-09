/* 
Directions: 
0: Up 
1: Down 
2: Left 
3: Right 
*/ 
import java.util.Random; 
import java.util.ArrayList; 

public class Organism 
{
	//Variables 
	private int id; 
	protected int posX; 
	protected int posY; 
	protected int age; 
	protected int breedAge; 
	protected boolean breedAble; 
	
	//Constructor 
	public Organism(){} 	
	public Organism(int id) 
	{
		this.id 	= id; 
		age			= 0; 
		breedAge 	= 0; 
		breedAble 	= false; 
	}
	
	//Methods 
	public void move() //Organism move (Aiman Hans, Lim Ee Tien, Lim Kee Hian) 
	{
		int direction = 0; 
		
		boolean up, down, left, right; 
		up = down = left = right = true; 
		
		switch(direction) 
		{
			case 0: setPos(posX, posY-1); break; 
			case 1: setPos(posX, posY+1); break; 
			case 2: setPos(posX-1, posY); break; 
			case 3: setPos(posX+1, posY); break; 
		} 
	} 
	public void breed() //Organism breed (Aiman Hans, Lim Ee Tien, Lim Kee Hian) 
	{
		if(breedAge%age == 0) 
		{
			move(); 
			if(breedAble) breedAble = false; 
		} 
	} 
	public int getDirection(boolean up, boolean down, boolean left, boolean right) //Get moving direction of organism (Aiman Hans, Lim Ee Tien, Lim Kee Hian) 
	{
		Random rand = new Random(); 
		ArrayList<Integer> direction = new ArrayList<Integer>(); 
		
		if(up) 		direction.add(0); 
		if(down) 	direction.add(1); 
		if(left) 	direction.add(2); 
		if(right) 	direction.add(3); 
		
		int i = rand.nextInt(direction.size()); 
		
		return direction.get(i); 
	} 
	public void setPos(int posX, int posY) //Update position of organism (Aiman Hans, Lim Ee Tien, Lim Kee Hian) 
	{
		this.posX = posX; 
		this.posY = posY; 
	} 
	public int getX() { return posX; } 												//Get x-coordinate of organism (Aiman Hans, Lim Ee Tien, Lim Kee Hian) 
	public int getY() { return posY; } 												//Get x-coordinate of organism (Aiman Hans, Lim Ee Tien, Lim Kee Hian) 
	public int getID() { return id; } 												//Get ID oforganism (Aiman Hans, Lim Ee Tien, Lim Kee Hian) 
	public void dispPos() { System.out.println("PosX:" + posX + " PosY:" + posY); } //Display position of organism (Aiman Hans, Lim Ee Tien, Lim Kee Hian) 
} 