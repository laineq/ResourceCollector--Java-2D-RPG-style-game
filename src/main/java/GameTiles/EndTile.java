package GameTiles;

import java.awt.*;

/**
 * Class for the end tile of the maze. It inherits from the Object class from the main package.
 */
public class EndTile extends main.Object{
    /**
     * Constructor sets the default values of the end tile object
     */
    public EndTile(){
        name = "EndTile"; //sets the name of the object

        //sets the image of the object
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        image = toolkit.getImage("src/main/resources/tiles/Exit.png");

        //sets the points to 0 and the tile to have no collision
        points = 0;
        collision = false;
    }
}
