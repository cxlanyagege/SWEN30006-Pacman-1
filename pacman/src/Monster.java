// Monster.java
// Used for PacMan
package src;

import ch.aplu.jgamegrid.*;

import java.util.*;

public abstract class Monster extends PacActor
{
  //private Game game;
  private MonsterType type;
  //private ArrayList<Location> visitedList = new ArrayList<Location>();
  //private final int listLength = 10;
  private boolean stopMoving = false;
  //private int seed = 0;
  //private Random randomiser = new Random(0);

  public Monster(Game game, MonsterType type)
  {
    super("sprites/" + type.getImageName());
    this.game = game;
    this.type = type;
  }

  public void stopMoving(int seconds) {
    this.stopMoving = true;
    Timer timer = new Timer(); // Instantiate Timer Object
    int SECOND_TO_MILLISECONDS = 1000;
    final Monster monster = this;
    timer.schedule(new TimerTask() {
      @Override
      public void run() {
        monster.stopMoving = false;
      }
    }, seconds * SECOND_TO_MILLISECONDS);
  }
/*
  public void setSeed(int seed) {
    this.seed = seed;
    randomiser.setSeed(seed);
  }
*/

  public void setStopMoving(boolean stopMoving) {
    this.stopMoving = stopMoving;
  }

  public void act()
  {
    if (stopMoving) {
      return;
    }

    walkApproach();
    if (getDirection() > 150 && getDirection() < 210)
      setHorzMirror(false);
    else
      setHorzMirror(true);
  }

  protected abstract void walkApproach();


  protected void furiousWalkApproach(){

  }

  protected Location walkRandom(double oldDirection)
  {
    // Random walk
    int sign = randomiser.nextDouble() < 0.5 ? 1 : -1;
    setDirection(oldDirection);
    turn(sign * 90);  // Try to turn left/right
    Location next = getNextMoveLocation();
    Location next2 = next.getAdjacentLocation(oldDirection,1);
    if(game.isFurious()){
      checkMove(next,oldDirection,sign);
      checkMove(next,oldDirection,sign);
    }else {
      checkMove(next,oldDirection,sign);
    }

    //checkMove(next,oldDirection,sign);

//    if (canMove(next))
//    {
//      // System.out.println("Turn Left/Right");
//      setLocation(next);
//    }
//    else
//    {
//      setDirection(oldDirection);
//      next = getNextMoveLocation();
//      if (canMove(next)) // Try to move forward
//      {
//        // System.out.println("Move Forward");
//        setLocation(next);
//      }
//      else
//      {
//        setDirection(oldDirection);
//        turn(-sign * 90);  // Try to turn right/left
//        next = getNextMoveLocation();
//        if (canMove(next))
//        {
//          // System.out.println("Turn Right/Left");
//          setLocation(next);
//        }
//        else
//        {
//          // System.out.println("Turn Backward");
//          setDirection(oldDirection);
//          turn(180);  // Turn backward
//          next = getNextMoveLocation();
//          setLocation(next);
//        }
//      }
//    }
    return next;
  }

  private void checkMove(Location next, double oldDirection,int sign){
    if (canMove(next))
    {
      // System.out.println("Turn Left/Right");
      setLocation(next);
    }
    else
    {
      setDirection(oldDirection);
      next = getNextMoveLocation();
      if (canMove(next)) // Try to move forward
      {
        // System.out.println("Move Forward");
        setLocation(next);
      }
      else
      {
        setDirection(oldDirection);
        turn(-sign * 90);  // Try to turn right/left
        next = getNextMoveLocation();
        if (canMove(next))
        {
          // System.out.println("Turn Right/Left");
          setLocation(next);
        }
        else
        {
          // System.out.println("Turn Backward");
          setDirection(oldDirection);
          turn(180);  // Turn backward
          next = getNextMoveLocation();
          setLocation(next);
        }
      }
    }
  }


  public MonsterType getType() {
    return type;
  }
/*
  protected void addVisitedList(Location location)
  {
    visitedList.add(location);
    if (visitedList.size() == listLength)
      visitedList.remove(0);
  }

  protected boolean isVisited(Location location)
  {
    for (Location loc : visitedList)
      if (loc.equals(location))
        return true;
    return false;
  }

  protected boolean canMove(Location location)
  {
    Color c = getBackground().getColor(location);
    if (c.equals(Color.gray) || location.getX() >= game.getNumHorzCells()
            || location.getX() < 0 || location.getY() >= game.getNumVertCells() || location.getY() < 0)
      return false;
    else
      return true;
  }
 */

}