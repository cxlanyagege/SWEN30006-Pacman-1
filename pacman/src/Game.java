

package src;

import ch.aplu.jgamegrid.*;
import src.utility.GameCallback;

import java.awt.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;




public abstract class Game extends GameGrid{
  protected final static int nbHorzCells = 20;
  protected final static int nbVertCells = 11;
  protected PacManGameGrid grid = new PacManGameGrid(nbHorzCells, nbVertCells);
  // modified
  protected PacMan pacMan = new PacMan(this);
//  protected Monster troll = new Troll(this);
//  protected Monster tx5 = new Tx5(this);
//  protected  Monster orion = new Orion(this);
//  protected  Monster alien = new Alien(this);
//  protected  Monster wizard = new Wizard(this);


  protected ArrayList<Location> pillAndItemLocations = new ArrayList<Location>();
  protected ArrayList<Actor> iceCubes = new ArrayList<Actor>();
  protected ArrayList<Actor> goldPieces = new ArrayList<Actor>();
  protected GameCallback gameCallback;
  protected Properties properties;
  protected int seed = 30006;
  protected ArrayList<Location> propertyPillLocations = new ArrayList<>();
  protected ArrayList<Location> propertyGoldLocations = new ArrayList<>();
  protected ArrayList<Location> goldLocations = new ArrayList<>();

  // add two state for extensions
  private boolean furious = false;
  private boolean frozen = false;
  private GGBackground bg;





  public Game(GameCallback gameCallback, Properties properties) {
    // Setup game
    super(nbHorzCells, nbVertCells, 20, false);
    this.gameCallback = gameCallback;
    this.properties = properties;
    setSimulationPeriod(100);

    //Setup for auto test pacMan.setPropertyMoves(properties.getProperty("PacMan.move")); pacMan.setAuto(Boolean.parseBoolean(properties.getProperty("PacMan.isAuto")));
    loadPillAndItemsLocations();

    bg = getBg();
    drawGrid(bg);
  }



  public void gameStops(boolean hasPacmanBeenHit, boolean hasPacmanEatAllPills) {

    Location loc = pacMan.getLocation();
    pacMan.removeSelf();
    String title = "";
    if (hasPacmanBeenHit) {
      bg.setPaintColor(Color.red);
      title = "GAME OVER";
      addActor(new Actor("sprites/explosion3.gif"), loc);
    } else if (hasPacmanEatAllPills) {
      bg.setPaintColor(Color.yellow);
      title = "YOU WIN";
    }
    setTitle(title);
    gameCallback.endOfGame(title);

    doPause();
  }

  public GameCallback getGameCallback() {
    return gameCallback;
  }
  public int getNumHorzCells(){
    return this.nbHorzCells;
  }
  public int getNumVertCells(){
    return this.nbVertCells;
  }
  public ArrayList<Location> getPillAndItemLocations() {
    return pillAndItemLocations;
  }


  // return the current furious state of game
  public boolean isFurious(){
    return furious;
  }

  public void setFurious(boolean furious){
    this.furious = furious;
  }

  public void setFrozen(boolean frozen){
    this.frozen = frozen;
  }

  // set furious state = true for 3s
  public void changeFuriousState() {
    if (!frozen) {
      setFurious(true);
      Timer timer = new Timer();
      timer.schedule(new TimerTask() {
        @Override
        public void run() {
          setFurious(false);
        }
      }, 3000);
    }
  }


  // set furious state = true for 3s

  public void changeFrozenState() {

    // all mobs frozen 3s
    setFrozen(true);

    // life mobs from furious state
    setFurious(false);

    // end frozen state after 3s
    Timer timer = new Timer();
    timer.schedule(new TimerTask() {
      @Override
      public void run() {
        setFrozen(false);
      }
    }, 3000);
  }




  // abstract method, will implement by child class
  protected abstract void setupActorLocations();



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

    if (propertyPillLocations.size() > 0) {
      for (Location location : propertyPillLocations) {
        putPill(bg,location);
      }
    }
    if (propertyGoldLocations.size() > 0) {
      for (Location location : propertyGoldLocations) {
        putGold(bg,location);
      }
    }
  }


  protected void loadPillAndItemsLocations() {
    String pillsLocationString = properties.getProperty("Pills.location");
    System.out.println(pillsLocationString);
    if (pillsLocationString != null) {
      String[] singlePillLocationStrings = pillsLocationString.split(";");
      for (String singlePillLocationString : singlePillLocationStrings) {
        String[] locationStrings = singlePillLocationString.split(",");
        propertyPillLocations.add(new Location(Integer.parseInt(locationStrings[0]), Integer.parseInt(locationStrings[1])));
      }
    }

    String goldLocationString = properties.getProperty("Gold.location");
    //System.out.println(goldLocationString);
    if (goldLocationString != null) {
      String[] singleGoldLocationStrings = goldLocationString.split(";");
      for (String singleGoldLocationString : singleGoldLocationStrings) {
        String[] locationStrings = singleGoldLocationString.split(",");
        propertyGoldLocations.add(new Location(Integer.parseInt(locationStrings[0]), Integer.parseInt(locationStrings[1])));
      }
    }
  }

  protected void setupPillAndItemsLocations() {
    System.out.println(propertyPillLocations.size());
    System.out.println(propertyGoldLocations.size());
    for (int y = 0; y < nbVertCells; y++) {
      for (int x = 0; x < nbHorzCells; x++) {
        Location location = new Location(x, y);
        int a = grid.getCell(location);
        if (a == 1 && propertyPillLocations.size() == 0) {
          pillAndItemLocations.add(location);
        }
        if (a == 3 && propertyGoldLocations.size() == 0) {
          pillAndItemLocations.add(location);
          goldLocations.add(location);
          System.out.println(goldLocations);
        }
        if (a == 4) {
          pillAndItemLocations.add(location);
        }
      }
    }

    if (propertyPillLocations.size() > 0) {
      for (Location location : propertyPillLocations) {
        pillAndItemLocations.add(location);
      }
    }
    if (propertyGoldLocations.size() > 0) {
      for (Location location : propertyGoldLocations) {
        pillAndItemLocations.add(location);
      }
    }
  }


  protected int countPillsAndItems() {
    int pillsAndItemsCount = 0;
    for (int y = 0; y < nbVertCells; y++)
    {
      for (int x = 0; x < nbHorzCells; x++)
      {
        Location location = new Location(x, y);
        int a = grid.getCell(location);
        if (a == 1 && propertyPillLocations.size() == 0) { // Pill
          pillsAndItemsCount++;
        } else if (a == 3 && propertyGoldLocations.size() == 0) { // Gold
          pillsAndItemsCount++;
        }
      }
    }
    if (propertyPillLocations.size() != 0) {
      pillsAndItemsCount += propertyPillLocations.size();
    }

    if (propertyGoldLocations.size() != 0) {
      pillsAndItemsCount += propertyGoldLocations.size();
    }

    return pillsAndItemsCount;
  }

  protected void putPill(GGBackground bg, Location location){
    bg.fillCircle(toPoint(location), 5);
  }

  protected void putGold(GGBackground bg, Location location){
    bg.setPaintColor(Color.yellow);
    bg.fillCircle(toPoint(location), 5);
    Actor gold = new Actor("sprites/gold.png");
    this.goldPieces.add(gold);
    addActor(gold, location);
  }

  protected void putIce(GGBackground bg, Location location){
    bg.setPaintColor(Color.blue);
    bg.fillCircle(toPoint(location), 5);
    Actor ice = new Actor("sprites/ice.png");
    this.iceCubes.add(ice);
    addActor(ice, location);
  }

  public void removeItem(String type,Location location){
    if(type.equals("gold")){
      for (Actor item : this.goldPieces){
        if (location.getX() == item.getLocation().getX() && location.getY() == item.getLocation().getY()) {
          item.hide();
        }
      }
    }else if(type.equals("ice")){
      for (Actor item : this.iceCubes){
        if (location.getX() == item.getLocation().getX() && location.getY() == item.getLocation().getY()) {
          item.hide();
        }
      }
    }
  }


  //
  public ArrayList<Location> getGoldLocation() {
    //return propertyGoldLocations;
    if(propertyGoldLocations.size() == 0){
      return goldLocations;
    }else{
      return propertyGoldLocations;
    }
  }
}
