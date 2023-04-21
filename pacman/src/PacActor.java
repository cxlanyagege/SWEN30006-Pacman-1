/*
 *  Team Name: monday-16-15-team-04
 *  Team Member:
 *               Xinyi Yuan
 *               He Shen
 *               Yuchen Dong
 */


// PacActor.java
// Used for PacMan
package src;

import ch.aplu.jgamegrid.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public abstract class PacActor extends Actor {

  protected Game game;
  private ArrayList<Location> visitedList = new ArrayList<Location>();

  private final int listLength = 15;
  private int seed;
  protected Random randomiser = new Random();
  public static int isFurious = 0;

  public PacActor(boolean isRotatable, String spriteName, int nbSprites) {
    super(isRotatable, spriteName, nbSprites);
  }

  public PacActor(String spriteName) {
    super(spriteName);
  }


  public void setSeed(int seed) {
    this.seed = seed;
    randomiser.setSeed(seed);
  }

  protected void addVisitedList(Location location) {
    visitedList.add(location);
    if (visitedList.size() == listLength)
      visitedList.remove(0);
  }

  protected boolean isVisited(Location location) {
    for (Location loc : visitedList)
      if (loc.equals(location))
        return true;
    return false;
  }

  protected boolean canMove(Location location) {
    Color c = getBackground().getColor(location);
    if (c.equals(Color.gray) || location.getX() >= game.getNumHorzCells()
            || location.getX() < 0 || location.getY() >= game.getNumVertCells() || location.getY() < 0)
      return false;
    else
      return true;
  }
}

