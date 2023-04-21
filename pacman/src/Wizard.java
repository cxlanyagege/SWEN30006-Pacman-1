/*
 *  Team Name: monday-16-15-team-04
 *  Team Member:
 *               Xinyi Yuan
 *               He Shen
 *               Yuchen Dong
 */

package src;

import ch.aplu.jgamegrid.Location;
import java.awt.Color;

import java.util.ArrayList;

public class Wizard extends Monster {
    private final Game game;

    public Wizard(Game game) {
        super(game, MonsterType.Wizard);
        this.game = game;
    }

    @Override
    protected void walkApproach() {
        int a =1;
        ArrayList<Location> neighbours;
        //double oldDirection = getDirection();
        // Walking approach:
        if (!game.isFurious()) {
            neighbours = getLocation().getNeighbourLocations(1);
        }else{
            neighbours = getLocation().getNeighbourLocations(2);
            System.out.println("isFurious");
        }

        if (!neighbours.isEmpty()) {
            int index = (int) (Math.random() * neighbours.size());
            Location next = neighbours.get(index);// Wizard: Randomly select one of its neighbour locations (8 cells around).

            // Check if the selected location is a wall
            if (!canMove(next)) {
                Location adjacent = next.getAdjacentLocation(getLocation().getDirectionTo(next), 1);
                // Check if the adjacent location in the same direction is not a wall
                if (canMove(adjacent)) {
                    next = adjacent;
                } else {
                    // If both the selected location and the adjacent location are walls, random select a neighbour which can move
                    neighbours.removeIf(l -> !canMove(l));
                    index = (int) (Math.random() * neighbours.size());
                    next = neighbours.get(index);
                }
            }

            setDirection(getLocation().getDirectionTo(next));
            setLocation(next);

        game.getGameCallback().monsterLocationChanged(this);
        }
    }
}

