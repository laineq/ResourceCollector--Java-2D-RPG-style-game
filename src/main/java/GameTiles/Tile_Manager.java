package GameTiles;

import main.GameWindow;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.util.Scanner;

/**
 * draw and display settings according to map files
 */
public class Tile_Manager
{
    GameWindow gw;
    public static Game_Tiles[] gameTiles;
    public int[][] mapTileNum;

    /**
     * constructor the titles according to worldmap.txt
     * @param: gw game window
     */
    public Tile_Manager(GameWindow gw)
    {
        this.gw = gw;

        gameTiles = new Game_Tiles[10];
        mapTileNum = new int[gw.maxWorldCol][gw.maxWorldRow];

        getBGImg();
        loadMap("src/main/java/GameTiles/worldmap.txt");
    }

    /**
     * get building and street images
     */
    public static void getBGImg()
    {
        /*try
        {
            gameTiles[0] = new Game_Tiles();
            gameTiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/street.png"));

            gameTiles[1] = new Game_Tiles();
            gameTiles[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/building.png"));
            gameTiles[1].collide = true;
        }catch(IOException e)
        {
            e.printStackTrace();
        }*/

        Toolkit toolkit = Toolkit.getDefaultToolkit();

         gameTiles[0] = new Game_Tiles();
         gameTiles[0].image = toolkit.getImage("src/main/resources/tiles/building.png");
         gameTiles[0].collide = true;

         gameTiles[1] = new Game_Tiles();
         gameTiles[1].image = toolkit.getImage("src/main/resources/tiles/street.png");

    }

    /**
     * read in the map file
     * @param filePath: map file path
     */
    public void loadMap(String filePath)
    {
        try
        {
            //InputStream is = getClass().getResourceAsStream(filePath);
            //BufferedReader buffread = new BufferedReader(new InputStreamReader(is));
            File data = new File(filePath);
            Scanner myReader = new Scanner(data);

            int column = 0;
            int row = 0;

            while (column < gw.maxWorldCol && row < gw.maxWorldRow)
            {
                //String line = buffread.readLine(); // Reads the lines in the map text file
                String line = myReader.nextLine(); // Reads the lines in the map text file
                while(column < gw.maxWorldCol)
                {
                    String numbers[] = line.split(" "); // Split the lines at spaces

                    int num = Integer.parseInt(numbers[column]); // Changing from string data type to integer

                    mapTileNum[column][row] = num; // Store the extracted number in the 2D array
                    column++;
                }
                if(column == gw.maxWorldCol)
                {
                    column = 0;
                    row++;
                }
            }
            //buffread.close(); // Close the buffer reader

        }catch (Exception e)
        {

        }
    }

    /**
     * draw setting images within player's camera view
     * @param g2: the Graphics2D class to draw the sprites
     */
    public void draw(Graphics2D g2)
    {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gw.maxWorldCol && worldRow < gw.maxWorldRow)
        {
            int tileNum = mapTileNum[worldCol][worldRow]; // Extract tile number stored in [0][0] position of the 2D array mapTileNum

            // Check the world tiles
            int worldX = worldCol * gw.tileSize;
            int worldY = worldRow * gw.tileSize;
            int screenX = worldX - gw.gatherer.worldX + gw.gatherer.screenX;
            int screenY = worldY - gw.gatherer.worldY + gw.gatherer.screenY;

            // if statement to only draw tiles within the camera view rather than drawing all tiles of the map to improve performance
            if(worldX + gw.tileSize > gw.gatherer.worldX - gw.gatherer.screenX &&
                    worldX - gw.tileSize < gw.gatherer.worldX + gw.gatherer.screenX &&
                    worldY + gw.tileSize > gw.gatherer.worldY - gw.gatherer.screenY &&
                    worldY - gw.tileSize < gw.gatherer.worldY + gw.gatherer.screenY) {
                g2.drawImage(gameTiles[tileNum].image, screenX, screenY, gw.tileSize, gw.tileSize, null);
            }
            worldCol++;

            if(worldCol == gw.maxWorldCol)
            {
                worldCol = 0;
                worldRow++;
            }
        }

    }
}
