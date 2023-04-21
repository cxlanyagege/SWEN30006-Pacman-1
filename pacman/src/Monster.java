/*
 *  Team Name: monday-16-15-team-04
 *  Team Member:
 *               Xinyi Yuan
 *               He Shen
 *               Yuchen Dong
 */

// Monster.java
// Used for PacMan
package src;

import ch.aplu.jgamegrid.*;

import java.util.*;

public abstract class Monster extends PacActor
{

  private MonsterType type;


  private boolean stopMoving = false;


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

    return next;
  }

  protected void checkMove(Location next, double oldDirection,int sign){
    if (canMove(next))
    {

      setLocation(next);
    }
    else
    {
      setDirection(oldDirection);
      next = getNextMoveLocation();
      if (canMove(next)) // Try to move forward
      {

        setLocation(next);
      }
      else
      {
        setDirection(oldDirection);
        turn(-sign * 90);  // Try to turn right/left
        next = getNextMoveLocation();
        if (canMove(next))
        {

          setLocation(next);
        }
        else
        {

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

}