//// PacMan.java
//// Simple PacMan implementation
//package src;
//
//import ch.aplu.jgamegrid.*;
//import src.utility.GameCallback;
//
//import java.awt.*;
//import java.util.ArrayList;
//import java.util.Properties;
//
//public class Game extends GameGrid
//{
//  private final static int nbHorzCells = 20;
//  private final static int nbVertCells = 11;
//  protected PacManGameGrid grid = new PacManGameGrid(nbHorzCells, nbVertCells);
//
//  protected pacMan = new pacMan(this);
//  private Monster troll = new Monster(this, MonsterType.Troll);
//  private Monster tx5 = new Monster(this, MonsterType.TX5);
//
//
//  private ArrayList<Location> pillAndItemLocations = new ArrayList<Location>();
//  private ArrayList<Actor> iceCubes = new ArrayList<Actor>();
//  private ArrayList<Actor> goldPieces = new ArrayList<Actor>();
//  private GameCallback gameCallback;
//  private Properties properties;
//  private int seed = 30006;
//  private ArrayList<Location> propertyPillLocations = new ArrayList<>();
//  private ArrayList<Location> propertyGoldLocations = new ArrayList<>();
//
//  public Game(GameCallback gameCallback, Properties properties)
//  {
//    //Setup game
//    super(nbHorzCells, nbVertCells, 20, false);
//    this.gameCallback = gameCallback;
//    this.properties = properties;
//    setSimulationPeriod(100);
//    setTitle("[PacMan in the Multiverse]");
//
//    //Setup for auto test
//    pacMan.setPropertyMoves(properties.getProperty("PacMan.move"));
//    pacMan.setAuto(Boolean.parseBoolean(properties.getProperty("PacMan.isAuto")));
//    loadPillAndItemsLocations();
//
//    GGBackground bg = getBg();
//    drawGrid(bg);
//
//    //Setup Random seeds
//    seed = Integer.parseInt(properties.getProperty("seed"));
//    pacMan.setSeed(seed);
//    troll.setSeed(seed);
//    tx5.setSeed(seed);
//    addKeyRepeatListener pacMan);
//    setKeyRepeatPeriod(150);
//    troll.setSlowDown(3);
//    tx5.setSlowDown(3);
//    pacMan.setSlowDown(3);
//    tx5.stopMoving(5);
//    setupActorLocations();
//
//
//
//    //Run the game
//    doRun();
//    show();
//    // Loop to look for collision in the application thread
//    // This makes it improbable that we miss a hit
//    boolean hasPacmanBeenHit;
//    boolean hasPacmanEatAllPills;
//    setupPillAndItemsLocations();
//    int maxPillsAndItems = countPillsAndItems();
//
//    do {
//      hasPacmanBeenHit = troll.getLocation().equals pacMan.getLocation()) ||
//              tx5.getLocation().equals pacMan.getLocation());
//      hasPacmanEatAllPills = pacMan.getNbPills() >= maxPillsAndItems;
//      delay(10);
//    } while(!hasPacmanBeenHit && !hasPacmanEatAllPills);
//    delay(120);
//
//    Location loc = pacMan.getLocation();
//    troll.setStopMoving(true);
//    tx5.setStopMoving(true);
//    pacMan.removeSelf();
//
//    String title = "";
//    if (hasPacmanBeenHit) {
//      bg.setPaintColor(Color.red);
//      title = "GAME OVER";
//      addActor(new Actor("sprites/explosion3.gif"), loc);
//    } else if (hasPacmanEatAllPills) {
//      bg.setPaintColor(Color.yellow);
//      title = "YOU WIN";
//    }
//    setTitle(title);
//    gameCallback.endOfGame(title);
//
//    doPause();
//  }
//
//  public GameCallback getGameCallback() {
//    return gameCallback;
//  }
//
//  private void setupActorLocations() {
//    String[] trollLocations = this.properties.getProperty("Troll.location").split(",");
//    String[] tx5Locations = this.properties.getProperty("TX5.location").split(",");
//    String[] pacManLocations = this.properties.getProperty("PacMan.location").split(",");
//    int trollX = Integer.parseInt(trollLocations[0]);
//    int trollY = Integer.parseInt(trollLocations[1]);
//
//    int tx5X = Integer.parseInt(tx5Locations[0]);
//    int tx5Y = Integer.parseInt(tx5Locations[1]);
//
//    int pacManX = Integer.parseInt(pacManLocations[0]);
//    int pacManY = Integer.parseInt(pacManLocations[1]);
//
//    addActor(troll, new Location(trollX, trollY), Location.NORTH);
//    addActor pacMan, new Location(pacManX, pacManY));
//    addActor(tx5, new Location(tx5X, tx5Y), Location.NORTH);
//  }
//
//  private int countPillsAndItems() {
//    int pillsAndItemsCount = 0;
//    for (int y = 0; y < nbVertCells; y++)
//    {
//      for (int x = 0; x < nbHorzCells; x++)
//      {
//        Location location = new Location(x, y);
//        int a = grid.getCell(location);
//        if (a == 1 && propertyPillLocations.size() == 0) { // Pill
//          pillsAndItemsCount++;
//        } else if (a == 3 && propertyGoldLocations.size() == 0) { // Gold
//          pillsAndItemsCount++;
//        }
//      }
//    }
//    if (propertyPillLocations.size() != 0) {
//      pillsAndItemsCount += propertyPillLocations.size();
//    }
//
//    if (propertyGoldLocations.size() != 0) {
//      pillsAndItemsCount += propertyGoldLocations.size();
//    }
//
//    return pillsAndItemsCount;
//  }
//
//  public ArrayList<Location> getPillAndItemLocations() {
//    return pillAndItemLocations;
//  }
//
//
//  private void loadPillAndItemsLocations() {
//    String pillsLocationString = properties.getProperty("Pills.location");
//    if (pillsLocationString != null) {
//      String[] singlePillLocationStrings = pillsLocationString.split(";");
//      for (String singlePillLocationString: singlePillLocationStrings) {
//        String[] locationStrings = singlePillLocationString.split(",");
//        propertyPillLocations.add(new Location(Integer.parseInt(locationStrings[0]), Integer.parseInt(locationStrings[1])));
//      }
//    }
//
//    String goldLocationString = properties.getProperty("Gold.location");
//    if (goldLocationString != null) {
//      String[] singleGoldLocationStrings = goldLocationString.split(";");
//      for (String singleGoldLocationString: singleGoldLocationStrings) {
//        String[] locationStrings = singleGoldLocationString.split(",");
//        propertyGoldLocations.add(new Location(Integer.parseInt(locationStrings[0]), Integer.parseInt(locationStrings[1])));
//      }
//    }
//  }
//  private void setupPillAndItemsLocations() {
//    for (int y = 0; y < nbVertCells; y++)
//    {
//      for (int x = 0; x < nbHorzCells; x++)
//      {
//        Location location = new Location(x, y);
//        int a = grid.getCell(location);
//        if (a == 1 && propertyPillLocations.size() == 0) {
//          pillAndItemLocations.add(location);
//        }
//        if (a == 3 &&  propertyGoldLocations.size() == 0) {
//          pillAndItemLocations.add(location);
//        }
//        if (a == 4) {
//          pillAndItemLocations.add(location);
//        }
//      }
//    }
//
//
//    if (propertyPillLocations.size() > 0) {
//      for (Location location : propertyPillLocations) {
//        pillAndItemLocations.add(location);
//      }
//    }
//    if (propertyGoldLocations.size() > 0) {
//      for (Location location : propertyGoldLocations) {
//        pillAndItemLocations.add(location);
//      }
//    }
//  }
//
//  private void drawGrid(GGBackground bg)
//  {
//    bg.clear(Color.gray);
//    bg.setPaintColor(Color.white);
//    for (int y = 0; y < nbVertCells; y++)
//    {
//      for (int x = 0; x < nbHorzCells; x++)
//      {
//        bg.setPaintColor(Color.white);
//        Location location = new Location(x, y);
//        int a = grid.getCell(location);
//        if (a > 0)
//          bg.fillCell(location, Color.lightGray);
//        if (a == 1 && propertyPillLocations.size() == 0) { // Pill
//          putPill(bg, location);
//        } else if (a == 3 && propertyGoldLocations.size() == 0) { // Gold
//          putGold(bg, location);
//        } else if (a == 4) {
//          putIce(bg, location);
//        }
//      }
//    }
//
//    for (Location location : propertyPillLocations) {
//      putPill(bg, location);
//    }
//
//    for (Location location : propertyGoldLocations) {
//      putGold(bg, location);
//    }
//  }
//
//  private void putPill(GGBackground bg, Location location){
//    bg.fillCircle(toPoint(location), 5);
//  }
//
//  private void putGold(GGBackground bg, Location location){
//    bg.setPaintColor(Color.yellow);
//    bg.fillCircle(toPoint(location), 5);
//    Actor gold = new Actor("sprites/gold.png");
//    this.goldPieces.add(gold);
//    addActor(gold, location);
//  }
//
//  private void putIce(GGBackground bg, Location location){
//    bg.setPaintColor(Color.blue);
//    bg.fillCircle(toPoint(location), 5);
//    Actor ice = new Actor("sprites/ice.png");
//    this.iceCubes.add(ice);
//    addActor(ice, location);
//  }
//
//  public void removeItem(String type,Location location){
//    if(type.equals("gold")){
//      for (Actor item : this.goldPieces){
//        if (location.getX() == item.getLocation().getX() && location.getY() == item.getLocation().getY()) {
//          item.hide();
//        }
//      }
//    }else if(type.equals("ice")){
//      for (Actor item : this.iceCubes){
//        if (location.getX() == item.getLocation().getX() && location.getY() == item.getLocation().getY()) {
//          item.hide();
//        }
//      }
//    }
//  }
//
//  public int getNumHorzCells(){
//    return this.nbHorzCells;
//  }
//  public int getNumVertCells(){
//    return this.nbVertCells;
//  }
//}

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
  protected Monster troll = new Troll(this);
  protected Monster tx5 = new Tx5(this);
  protected  Monster orion = new Orion(this);
  protected  Monster alien = new Alien(this);
  protected  Monster wizard = new Wizard(this);


  protected ArrayList<Location> pillAndItemLocations = new ArrayList<Location>();
  protected ArrayList<Actor> iceCubes = new ArrayList<Actor>();
  protected ArrayList<Actor> goldPieces = new ArrayList<Actor>();
  protected GameCallback gameCallback;
  protected Properties properties;
  protected int seed = 30006;
  protected ArrayList<Location> propertyPillLocations = new ArrayList<>();
  protected ArrayList<Location> propertyGoldLocations = new ArrayList<>();
  protected ArrayList<Location> goldLocations = new ArrayList<>();

  private boolean furious = false;

  public boolean isFurious(){
    return furious;
  }

  public void setFurious(boolean furious){
    this.furious = furious;
  }

  public void changeFuriousState() {
    setFurious(true);
    Timer timer = new Timer();
    timer.schedule(new TimerTask() {
      @Override
      public void run() {
        setFurious(false);
      }
    }, 3000);
  }

//  public void checkFurious(){
//    if(furious){
//      changeFuriousState();
//    }
//  }





  public Game(GameCallback gameCallback, Properties properties) {
    // Setup game
    super(nbHorzCells, nbVertCells, 20, false);
    this.gameCallback = gameCallback;
    this.properties = properties;
    setSimulationPeriod(100);
    setTitle("[PacMan in the Multiverse]");

    //Setup for auto test pacMan.setPropertyMoves(properties.getProperty("PacMan.move")); pacMan.setAuto(Boolean.parseBoolean(properties.getProperty("PacMan.isAuto")));
    loadPillAndItemsLocations();

    GGBackground bg = getBg();
    drawGrid(bg);

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
    Game.this.pacMan.setSlowDown(3);
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
      hasPacmanBeenHit = troll.getLocation().equals( Game.this.pacMan.getLocation()) ||
              tx5.getLocation().equals (Game.this.pacMan.getLocation())||orion.getLocation().equals(Game.this.pacMan.getLocation())||alien.getLocation().equals( Game.this.pacMan.getLocation())||wizard.getLocation().equals( Game.this.pacMan.getLocation()) ;
      hasPacmanEatAllPills = Game.this.pacMan.getNbPills() >= maxPillsAndItems;
      delay(10);
    } while(!hasPacmanBeenHit && !hasPacmanEatAllPills);
    delay(120);

    Location loc = Game.this.pacMan.getLocation();
    troll.setStopMoving(true);
    tx5.setStopMoving(true); Game.this.pacMan.removeSelf();

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



  protected abstract void setupActorLocations();
  protected abstract void drawGrid(GGBackground bg);


  protected void loadPillAndItemsLocations() {
    String pillsLocationString = properties.getProperty("Pills.location");
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

  public ArrayList<Location> getGoldLocation() {
    //return propertyGoldLocations;
    if(propertyGoldLocations.size() == 0){
      return goldLocations;
    }else{
      return propertyGoldLocations;
    }


  }

}
