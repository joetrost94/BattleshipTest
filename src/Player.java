import java.util.ArrayList;
import java.util.List;

public class Player {
	
	//List<Boat> ships = new ArrayList<Boat>();
	public int patrolCount, battleshipCount, submarineCount, destroyerCount, carrierCount;
	
	
	public Player ()
	{
		patrolCount = 2;
		battleshipCount = 2;
		submarineCount = 1;
		destroyerCount = 1;
		carrierCount = 1;
		/*ships.add(new Boat(1, 2, "Patrol Boat"));
		ships.add(new Boat(1, 2, "Patrol Boat"));
		
		ships.add(new Boat(1, 3, "Battleship"));
		ships.add(new Boat(1, 3, "Battleship"));
		
		ships.add(new Boat(1, 4, "Destroyer"));
		
		ships.add(new Boat(1, 3, "Carrier"));*/
	}
}
