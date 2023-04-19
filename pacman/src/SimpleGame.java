package src;

import ch.aplu.jgamegrid.GGBackground;
import ch.aplu.jgamegrid.Location;
import src.utility.GameCallback;

import java.awt.*;
import java.util.Properties;


public class SimpleGame extends Game {

    public SimpleGame(GameCallback gameCallback, Properties properties) {
        super(gameCallback, properties);
    }

    @Override
    protected void setupActorLocations() {
        String[] trollLocations = this.properties.getProperty("Troll.location").split(",");
        String[] tx5Locations = this.properties.getProperty("TX5.location").split(",");
        // String[] orionLocations = this.properties.getProperty("Orion.location").split(",");
        String[] pacManLocations = this.properties.getProperty("PacMan.location").split(",");
        int trollX = Integer.parseInt(trollLocations[0]);
        int trollY = Integer.parseInt(trollLocations[1]);
        int tx5X = Integer.parseInt(tx5Locations[0]);
        int tx5Y = Integer.parseInt(tx5Locations[1]);
        // int orionX = Integer.parseInt(orionLocations[0]);
        // int orionY = Integer.parseInt(orionLocations[1]);
        int pacManX = Integer.parseInt(pacManLocations[0]);int pacManY = Integer.parseInt(pacManLocations[1]);
        addActor(troll, new Location(trollX, trollY), Location.NORTH);addActor(pacMan, new Location(pacManX, pacManY));addActor(tx5, new Location(tx5X, tx5Y), Location.NORTH);


    }

    @Override
    protected void drawGrid(GGBackground bg) {
        bg.clear(Color.gray);
        bg.setPaintColor(Color.white);
        for (int y = 0; y < nbVertCells; y++)
        {
            for (int x = 0; x < nbHorzCells; x++)
            {
                bg.setPaintColor(Color.white);
                Location location = new Location(x, y);
                int a = grid.getCell(location);
                if (a > 0)
                    bg.fillCell(location, Color.lightGray);
                if (a == 1 && propertyPillLocations.size() == 0) { // Pill
                    putPill(bg, location);
                } else if (a == 3 && propertyGoldLocations.size() == 0) { // Gold
                    putGold(bg, location);
                } else if (a == 4) {
                    putIce(bg, location);
                }
            }

    }
}}