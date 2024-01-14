package SetGame;

import GameTiles.EndTile;
import RewardsAndPunishments.RegularResource;
import main.GameWindow;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import java.text.DecimalFormat;


/**
 * implement different end scenes of the game: lost and win
 */
public class gameStat {
    GameWindow gw;
    Graphics2D g2;
    Font statFont;
    Font msgFont;
    public double playTime;
    DecimalFormat df = new DecimalFormat("#0.00");


    public gameStat(GameWindow gw) {
        this.gw = gw;

        try {
            statFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/PressStart2P-vaV7.ttf")).deriveFont(25f);
            GraphicsEnvironment ge  = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/PressStart2P-vaV7.ttf")));

            msgFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/PressStart2P-vaV7.ttf")).deriveFont(60f);
            ge  = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/PressStart2P-vaV7.ttf")));
        }
        catch(IOException | FontFormatException e)
        {

        }
    }


    /**
     * draw and display different end scenes
     * @param g2 the Graphics2D class to draw end scenes
     */
    public void draw(Graphics2D g2) throws IOException, FontFormatException {
        g2.setFont(statFont);
        g2.setColor(Color.white);
        String s = "Score: " + gw.score;
        g2.drawString(s, (gw.scWidth - s.length()) / 2 + 344, 60);

        String t = "Time: " + df.format(playTime);
        if(!(gw.gameLost || gw.gameWon)){
            playTime += (double) 1 / 60;
        }
        g2.drawString(t, (gw.scWidth - t.length()) / 2 - 176, 60);

        
        RegularResource regResource = new RegularResource();

        g2.setFont(statFont); // Font name, font style, font size
        g2.setColor(Color.white);
        g2.drawImage(regResource.image, gw.tileSize/2, gw.tileSize/4, gw.tileSize, gw.tileSize, null);
        g2.drawString("x " + gw.gatherer.resourcesGathered, 100, 60);




        //lost game end scenes
        if (gw.gameLost == true) {
            g2.setColor(new Color(0, 0, 0));
            g2.fillRect(0,0,gw.scWidth,gw.scHeight);

            Font displayFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/PressStart2P-vaV7.ttf")).deriveFont(20f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/PressStart2P-vaV7.ttf")));
            g2.setFont(displayFont);

            String text = "The Scavengers have captured you and taken all the resources!";
            int x = gw.scWidth / 2 - text.length()/2;
            x -= 580;
            int y = gw.tileSize * 3;
            gw.gatherer.image = null;

            // Text shadows
            g2.setColor(Color.GRAY);
            g2.drawString(text, x+5, y+5);

            // Main text
            g2.setColor(Color.red);
            g2.drawString(text, x, y);

            // Gatherer image
            x = gw.scWidth/2 - (gw.tileSize*2)/2;
            y += gw.tileSize*2;
            g2.drawImage(gw.gatherer.down1, x, y, gw.tileSize*2, gw.tileSize*2, null);

            // Scavenger image
            x = gw.scWidth/2 - (gw.tileSize*2)/2 + gw.tileSize*3;
            y += gw.tileSize-72;
            g2.drawImage(gw.scavengers[0].down1, x, y, gw.tileSize*2, gw.tileSize*2, null);

        }
        //win game end scenes
        else if (gw.gameWon == true) {
            g2.setColor(new Color(0, 0, 0));
            g2.fillRect(0,0,gw.scWidth,gw.scHeight);

            Font displayFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/PressStart2P-vaV7.ttf")).deriveFont(20f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/PressStart2P-vaV7.ttf")));
            g2.setFont(displayFont);

            String text = "You managed to get all the resources to the camp!";
            int x = gw.scWidth/2 - text.length()/2;
            x -= 580;
            int y = gw.tileSize * 3;
            gw.gatherer.image = null;

            // Text shadows
            g2.setColor(Color.GRAY);
            g2.drawString(text, x+5, y+5);

            // Main text
            g2.setColor(Color.green);
            g2.drawString(text, x, y);

            // Gatherer image
            x = gw.scWidth/2 - (gw.tileSize*2)/2;
            y += gw.tileSize*2;
            g2.drawImage(gw.gatherer.down1, x, y, gw.tileSize*2, gw.tileSize*2, null);

            // Camp image
            x = gw.scWidth/2 - (gw.tileSize*2)/2 + gw.tileSize*3;
            y += gw.tileSize-72;
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Image image = toolkit.getImage("src/main/resources/tiles/Exit.png");
            g2.drawImage(image, x, y, gw.tileSize*2, gw.tileSize*2, null);
        }

    }

}
