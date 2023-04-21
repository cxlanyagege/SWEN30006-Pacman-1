package src;

import ch.aplu.jgamegrid.GGBackground;
import ch.aplu.jgamegrid.Location;
import src.utility.GameCallback;

import java.awt.*;
import java.util.Properties;


public class SimpleGame extends Game {

    protected Monster troll = new Troll(this);
    protected Monster tx5 = new Tx5(this);

    public SimpleGame(GameCallback gameCallback, Properties properties) {
        super(gameCallback, properties);

        setTitle("[PacMan Simple]");

        //Setup Random seeds
        seed = Integer.parseInt(properties.getProperty("seed"));
        pacMan.setSeed(seed);
        troll.setSeed(seed);
        tx5.setSeed(seed);
        addKeyRepeatListener (pacMan);
        setKeyRepeatPeriod(150);
        troll.setSlowDown(10);
        tx5.setSlowDown(10);
        pacMan.setSlowDown(3);
        tx5.stopMoving(5);
        setupActorLocations();

        //Run the game
        doRun();
        show();


        // Loop to look for collision in the application thread
        // This makes it improbable that we miss a hit
        boolean hasPacmanBeenHit;
        boolean hasPacmanEatAllPills;
        setupPillAndItemsLocations();
        int maxPillsAndItems = countPillsAndItems();
        do {
            hasPacmanBeenHit = troll.getLocation().equals(pacMan.getLocation()) ||
                    tx5.getLocation().equals (pacMan.getLocation());
            hasPacmanEatAllPills = pacMan.getNbPills() >= maxPillsAndItems;
            delay(10);
        } while(!hasPacmanBeenHit && !hasPacmanEatAllPills);
        delay(120);

        // game ends
        troll.setStopMoving(true);
        tx5.setStopMoving(true);
        gameStops(hasPacmanBeenHit, hasPacmanEatAllPills);
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
        addActor(troll, new Location(trollX, trollY), Location.NORTH);
        addActor(pacMan, new Location(pacManX, pacManY));
        addActor(tx5, new Location(tx5X, tx5Y), Location.NORTH);


    }
}