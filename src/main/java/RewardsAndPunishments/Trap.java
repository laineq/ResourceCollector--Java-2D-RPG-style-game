package RewardsAndPunishments;

import java.awt.*;

/**
 * A static enemy class that takes away points from user.
 */
public class Trap extends main.Object{
	/**
	 * Creates a Trap object
	 */ 
	public Trap()
    {
        name ="Punishment";

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        image =toolkit.getImage("src/main/resources/Punishment.png");

        points =-10;
        collision =true;
    }
	
}
