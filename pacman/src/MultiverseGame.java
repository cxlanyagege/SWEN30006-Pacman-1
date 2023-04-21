package src;

import ch.aplu.jgamegrid.GGBackground;
import ch.aplu.jgamegrid.Location;
import src.utility.GameCallback;

import java.awt.*;
import java.util.Properties;

public class MultiverseGame extends Game {


    public MultiverseGame(GameCallback gameCallback, Properties properties) {
        super(gameCallback, properties);
    }

    @Override
    protected void setupActorLocations() {
        String[] trollLocations = this.properties.getProperty("Troll.location").split(",");
        String[] tx5Locations = this.properties.getProperty("TX5.location").split(",");
        String[] orionLocations = this.properties.getProperty("Orion.location").split(",");
        String[] pacManLocations = this.properties.getProperty("PacMan.location").split(",");
        String[] alienLocations = this.properties.getProperty("Alien.location").split(",");
        String[] wizardLocations = this.properties.getProperty("Wizard.location").split(",");


        int trollX = Integer.parseInt(trollLocations[0]);
        int trollY = Integer.parseInt(trollLocations[1]);
        int tx5X = Integer.parseInt(tx5Locations[0]);
        int tx5Y = Integer.parseInt(tx5Locations[1]);
        int orionX = Integer.parseInt(orionLocations[0]);
        int orionY = Integer.parseInt(orionLocations[1]);
        int wizardX = Integer.parseInt(wizardLocations[0]);
        int wizardY = Integer.parseInt(wizardLocations[1]);
        int alienX = Integer.parseInt(alienLocations[0]);
        int alienY = Integer.parseInt(alienLocations[1]);
        int pacManX = Integer.parseInt(pacManLocations[0]);
        int pacManY = Integer.parseInt(pacManLocations[1]);
        addActor(troll, new Location(trollX, trollY), Location.NORTH);
        addActor(tx5, new Location(tx5X, tx5Y), Location.NORTH);
        addActor(orion, new Location(orionX, orionY), Location.NORTH);
        addActor(alien, new Location(alienX, alienY), Location.NORTH);
        addActor(wizard,new Location(wizardX,wizardY),Location.NORTH);
        addActor(pacMan, new Location(pacManX, pacManY));

    }

//    @Override
//    protected void drawGrid(GGBackground bg) {
//        bg.clear(Color.gray);
//        bg.setPaintColor(Color.white);
//        for (int y = 0; y < nbVertCells; y++)
//        {
//            for (int x = 0; x < nbHorzCells; x++)
//            {
//                bg.setPaintColor(Color.white);
//                Location location = new Location(x, y);
//                int a = grid.getCell(location);
//                if (a > 0)
//                    bg.fillCell(location, Color.lightGray);
//                if (a == 1 && propertyPillLocations.size() == 0) { // Pill
//                    putPill(bg, location);
//                } else if (a == 3 && propertyGoldLocations.size() == 0) { // Gold
//                    putGold(bg, location);
//                } else if (a == 4) {
//                    putIce(bg, location);
//                }
//            }
//
//        }
//
//        if (propertyPillLocations.size() > 0) {
//            for (Location location : propertyPillLocations) {
//                putPill(bg,location);
//            }
//        }
//        if (propertyGoldLocations.size() > 0) {
//            for (Location location : propertyGoldLocations) {
//                putGold(bg,location);
//            }
//        }
//    }
}
