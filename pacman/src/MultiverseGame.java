package src;

import ch.aplu.jgamegrid.Actor;
import ch.aplu.jgamegrid.GGBackground;
import ch.aplu.jgamegrid.Location;
import src.utility.GameCallback;

import java.awt.*;
import java.util.Properties;

public class MultiverseGame extends Game {

    protected Monster troll = new Troll(this);
    protected Monster tx5 = new Tx5(this);
    protected  Monster orion = new Orion(this);
    protected  Monster alien = new Alien(this);
    protected  Monster wizard = new Wizard(this);


    public MultiverseGame(GameCallback gameCallback, Properties properties) {
        super(gameCallback, properties);

        String gameTitle = "[PacMan in the Multiverse]";
        setTitle(gameTitle);
        pacMan.setGameTitle(gameTitle);

        //Setup Random seeds
        seed = Integer.parseInt(properties.getProperty("seed"));
        pacMan.setSeed(seed);
        troll.setSeed(seed);
        tx5.setSeed(seed);
        orion.setSeed(seed);
        addKeyRepeatListener (pacMan);
        setKeyRepeatPeriod(150);
        troll.setSlowDown(10);
        alien.setSlowDown(10);
        orion.setSlowDown(10);
        tx5.setSlowDown(10);
        wizard.setSlowDown(5);
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
                    tx5.getLocation().equals (pacMan.getLocation())||orion.getLocation().equals(pacMan.getLocation())||alien.getLocation().equals(pacMan.getLocation())||wizard.getLocation().equals(pacMan.getLocation()) ;
            hasPacmanEatAllPills = pacMan.getNbPills() >= maxPillsAndItems;
            delay(10);
        } while(!hasPacmanBeenHit && !hasPacmanEatAllPills);
        delay(120);

        // game ends
        troll.setStopMoving(true);
        tx5.setStopMoving(true);
        orion.setStopMoving(true);
        alien.setStopMoving(true);
        wizard.setStopMoving(true);
        gameStops(hasPacmanBeenHit, hasPacmanEatAllPills);
    }

    public void changeFrozenState() {
        troll.stopMoving(3);
        tx5.stopMoving(3);
        orion.stopMoving(3);
        alien.stopMoving(3);
        wizard.stopMoving(3);
        super.changeFrozenState();
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
}
