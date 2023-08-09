/* 
Model-View Controller 
*/ 
import java.util.*; 

public class World 
{
	boolean debug = false; 
	
	//Variables 
	private int sizeX; 
	private int sizeY; 
	private int antNo; 
	private int bugNo; 
	private int antCount; 
	private int bugCount; 
	private ArrayList<ArrayList<Organism>> map; 
	private ArrayList<Ant> ant; 
	private ArrayList<Bug> bug; 
	private Organism empty; 
	private int time; 
	
	//Constructor 
	public World(int sizeX, int sizeY, int antNo, int bugNo) 
	{
		this.sizeX = sizeX; 
		this.sizeY = sizeY; 
		this.antNo = antNo; 
		this.bugNo = bugNo; 
		
		time = 0; 
	} 
	
	//Methods 
	public void generate() //Create map and insert organism(s) into map (Aiman Hans, Lim Ee Tien, Lim Kee Hian) 
	{
		map = new ArrayList<ArrayList<Organism>>(); 
		for(int i = 0; i < sizeY; i++) map.add(new ArrayList<Organism>()); 
		
		ant = new ArrayList<Ant>(); 
		for(int i = 0; i < antNo; i++) ant.add(new Ant(i+1)); 
		
		bug = new ArrayList<Bug>(); 
		for(int i = 0; i < bugNo; i++) bug.add(new Bug(i+1)); 
		
		empty = new Organism(); 
		
		antCount = 0; 
		bugCount = 0; 
		for(int i = 0; i < sizeY; i++) 
		{
			for(int j = 0; j < sizeX; j++) 
			{
				if(antCount < antNo) 
				{
					map.get(j).add(ant.get(antCount)); 
					antCount++; 
				} 
				else if(bugCount < bugNo) 
				{
					map.get(j).add(bug.get(bugCount)); 
					bugCount++; 
				} 
				else 
					map.get(j).add(empty); 
			}
		}
		
		if(debug) debugDisplay("Initial:"); 
		for(int i = 0; i < sizeX; i++) 
		{
			for(int j = 0; j < 3; j++) 
			{
				Collections.shuffle(map); 
				Collections.shuffle(map.get(i)); 
			} 
		} 
		
		antCount = 0; 
		bugCount = 0; 
		for(int i = 0; i < sizeY; i++) 
		{
			for(int j = 0; j < sizeX; j++) 
			{
				if(getContent(i,j) instanceof Ant) 
				{
					ant.get(antCount).setPos(i,j); 
					antCount = antCount + 1; 
				} 
				if(getContent(i,j) instanceof Bug) 
				{
					bug.get(bugCount).setPos(i,j); 
					bugCount = bugCount + 1; 
				}
			} 
		} 
		updateWorld(); 
		
		if(debug) 
		{
			debugDisplay("Shuffled:"); 
			System.out.println("Ants:"); 
			for(int i = 0; i < antNo; i++) ant.get(i).dispPos();  
			System.out.println("\nBugs:"); 
			for(int i = 0; i < bugNo; i++) bug.get(i).dispPos(); 
			System.out.println(); 
		} 
	} 
	public void nextStep() //What happens before next time step (Aiman Hans, Lim Ee Tien, Lim Kee Hian) 
	{
		for(int i = 0; i < bugNo; i++) 
		{
			bug.get(i).move(this); updateWorld(); 
			bug.get(i).breed(this); updateWorld(); 
			if(bug.get(i).starve()) 
			{ 
				bug.remove(i); 
				bugNo = bugNo - 1; 
			} 
			updateWorld(); 
			
			if(debug) 
			{ 
				System.out.println("Moved bug " + bug.get(i).getID() + " to X:" + bug.get(i).getX() + " Y:" + bug.get(i).getY()); 
				debugDisplay("Time:" + time); 
			} 
		} 
		for(int i = 0; i < antNo; i++) 
		{
			ant.get(i).move(this); updateWorld(); 
			ant.get(i).breed(this); updateWorld(); 
			if(debug) 
			{ 
				System.out.println("Moved ant " + ant.get(i).getID() + " to X:" + ant.get(i).getX() + " Y:" + ant.get(i).getY()); 
				debugDisplay("Time:" + time); 
			} 
		} 
	} 
	public void updateWorld() //Update array of the map (Aiman Hans, Lim Ee Tien, Lim Kee Hian) 
	{
		clean(); 
		
		for(int i = 0; i < sizeY; i++) 
		{
			for(int j = 0; j < sizeX; j++) 
			{
				if(!bug.isEmpty()) 
				{
					for(int k = 0; k < bugNo; k++) 
					{
						if(bug.get(k).getX() == i && bug.get(k).getY() == j) map.get(i).set(j, bug.get(k)); 
					} 
				} 
				if(!ant.isEmpty()) 
				{
					for(int k = 0; k < antNo; k++) 
					{
						if(ant.get(k).getX() == i && ant.get(k).getY() == j) map.get(i).set(j, ant.get(k)); 
					} 
				} 
			} 
		} 
	} 
	public void clean() //Empty the map (Aiman Hans, Lim Ee Tien, Lim Kee Hian) 
	{
		for(int i = 0; i < sizeX; i++) 
		{
			for(int j = 0; j < sizeY; j++) map.get(i).set(j, empty); 
		} 
	}
	public void debugDisplay(String label) //Display map in console (Aiman Hans, Lim Ee Tien, Lim Kee Hian) 
	{
		System.out.println(label); 
		
		for(int i = 0; i < sizeX; i++) 
		{
			for(int j = 0; j < sizeY; j++) 
			{
				String disp; 
				if(map.get(i).get(j) instanceof Ant) 		disp = "a" + map.get(i).get(j).getID(); 
				else if(map.get(i).get(j) instanceof Bug) 	disp = "b" + map.get(i).get(j).getID(); 
				else 										disp = "00"; 
				System.out.print(disp + " "); 
			} 
			System.out.println(); 
		} 
		System.out.println(); 
	} 
	public void eat(int posX, int posY, Bug bug) //When a bug eats an ant (Aiman Hans, Lim Ee Tien, Lim Kee Hian) 
	{ 
		for(int i = 0; i < antNo; i++) 
		{
			if(ant.get(i).getX() == posX && ant.get(i).getY() == posY) 
			{
				if(debug) System.out.println("a" + ant.get(i).getID() + " was eaten by b" + bug.getID()); 
				antNo = antNo - 1; 
				ant.remove(i); 
				bug.setPos(posX, posY); 
				break; 
			} 
		} 
	} 
	public void breedAnt(int bPosX, int bPosY) //When an ant breeds (Aiman Hans, Lim Ee Tien, Lim Kee Hian) 
	{
		antNo = antNo + 1; 
		ant.add(new Ant(antNo)); 
		ant.get(antNo-1).setPos(bPosX, bPosY); 
	} 
	public void breedBug(int bPosX, int bPosY) //When a bug breeds (Aiman Hans, Lim Ee Tien, Lim Kee Hian) 
	{
		bugNo = bugNo + 1; 
		bug.add(new Bug(bugNo)); 
		bug.get(bugNo-1).setPos(bPosX, bPosY); 
	} 
	public void incrementTime() { time = time + 1; } 									//Increase time step (Aiman Hans, Lim Ee Tien, Lim Kee Hian) 
	public Organism getContent(int posX, int posY) { return map.get(posX).get(posY); } 	//Get content of specific cell (Aiman Hans, Lim Ee Tien, Lim Kee Hian) 
	public int getSizeX() { return sizeX; } 											//Get length of map (Aiman Hans, Lim Ee Tien, Lim Kee Hian) 
	public int getSizeY() { return sizeY; } 											//Get height of map (Aiman Hans, Lim Ee Tien, Lim Kee Hian) 
	public int getTime() { return time; } 												//Get current time step (Aiman Hans, Lim Ee Tien, Lim Kee Hian) 
	public int getBugNo() { return bugNo; } 											//Get number of bug(s) left (Aiman Hans, Lim Ee Tien, Lim Kee Hian) 
	public int getAntNo() { return antNo; } 											//Get number of ant(s) left (Aiman Hans, Lim Ee Tien, Lim Kee Hian) 
} 