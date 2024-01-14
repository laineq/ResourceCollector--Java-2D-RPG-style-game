package main;

import java.awt.*;

public class UI
{
    GameWindow gw;
    Graphics2D g2;
    Font arial_40;
    Image resourceImage;
    public boolean notifOn = false;
    public String notification = "";
    public int commandNumber = 0;
    public int notif_timer = 0;

    public UI(GameWindow gw)
    {
        this.gw = gw;
    }

    public void draw(Graphics2D g2)
    {
        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor((Color.white));

        // Menu
        if(gw.game_state == gw.menu_state)
        {
            draw_menu_screen();
        }
        if(gw.game_state == gw.play_state)
        {
            g2.setFont(arial_40); // Font name, font style, font size
            g2.setColor(Color.white);
            g2.drawImage(resourceImage, gw.tileSize/2, gw.tileSize/2, gw.tileSize, gw.tileSize, null);

            // Notifications
            if(notifOn)
            {
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(notification, gw.tileSize/2,gw.tileSize*5);

                notif_timer++;

                if(notif_timer > 90)
                {
                    notif_timer = 0;
                    notifOn = false;
                }
            }
        }

    }
    // Redundant variables removed throughout the methods
    public void draw_menu_screen()
    {
        g2.setColor(new Color(0, 0, 0));
        g2.fillRect(0,0,gw.scWidth,gw.scHeight);

        // Title of game
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
        String text = "RESOURCE RUN";
        int x = get_x_for_centeredtext(text);
        int y = gw.tileSize * 3;

        // Text shadows
        g2.setColor(Color.gray);
        g2.drawString(text, x+5, y+5);

        // Main text
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        // Gatherer image
        x = gw.scWidth/2 - (gw.tileSize*2)/2;
        y += gw.tileSize*2;
        g2.drawImage(gw.gatherer.down1, x, y, gw.tileSize*2, gw.tileSize*2, null);

        // Refactor: split up draw_menu_screen for better organization -> smell resolved
        menu_options();
        controls();
    }

    public void menu_options()
    {
        // Menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));

        String text = "NEW GAME";
        int x = get_x_for_centeredtext(text);
        int y = 0;
        y += gw.tileSize*8;
        g2.drawString(text,x,y);
        if(commandNumber == 0)
        {
            g2.drawString("> ",x-gw.tileSize,y);
        }

        text = "QUIT";
        x = get_x_for_centeredtext(text);
        y += gw.tileSize;
        g2.drawString(text,x,y);
        if(commandNumber == 1)
        {
            g2.drawString("> ",x-gw.tileSize,y);
        }
    }

    public void controls(){
        // Display controls
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,30F));
        String text = "CONTROLS";
        int x = get_x_for_centeredtext(text);
        int y = gw.tileSize * 11;

        // Text shadows
        g2.setColor(Color.gray);
        g2.drawString(text, x+3, y+3);

        // Main text
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        text = "W - MOVE UP";
        x = get_x_for_centeredtext(text) - 200;
        y += gw.tileSize*0.7;
        g2.drawString(text,x,y);

        text = "S - MOVE DOWN";
        x = get_x_for_centeredtext(text) - 200;
        y += gw.tileSize*0.5;
        g2.drawString(text,x,y);

        text = "A - MOVE LEFT";
        x = get_x_for_centeredtext(text) - 200;
        y += gw.tileSize*0.5;
        g2.drawString(text,x,y);

        text = "D - MOVE RIGHT";
        x = get_x_for_centeredtext(text) - 200;
        y += gw.tileSize*0.5;
        g2.drawString(text,x,y);

        get_x_for_centeredtext(text);
        y = gw.tileSize * 11;

        text = "P - PAUSE";
        x = get_x_for_centeredtext(text) + 200;
        y += gw.tileSize*0.7;
        g2.drawString(text,x,y);

        text = "R - RESUME";
        x = get_x_for_centeredtext(text)+ 200;
        y += gw.tileSize*0.5;
        g2.drawString(text,x,y);

        text = "ESC - QUIT";
        x = get_x_for_centeredtext(text)+ 200;
        y += gw.tileSize*0.5;
        g2.drawString(text,x,y);

        text = "SPACE - RESET";
        x = get_x_for_centeredtext(text)+ 200;
        y += gw.tileSize*0.5;
        g2.drawString(text,x,y);
    }

    public int get_x_for_centeredtext(String text)
    {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gw.scWidth/2 - length/2;
    }
}
