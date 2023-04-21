/*
 *  Team Name: monday-16-15-team-04
 *  Team Member:
 *               Xinyi Yuan
 *               He Shen
 *               Yuchen Dong
 */

package src;

import ch.aplu.jgamegrid.Location;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Alien extends Monster {
    private final Game game;

    public Alien(Game game) {
        super(game, MonsterType.Alien);
        this.game = game;
    }

    @Override
    protected void walkApproach() {
        Location pacLocation = game.pacMan.getLocation();
        double oldDirection = getDirection();
        Location next = null;

        // Walking approach:
        // Alien: Calculate distance to PacMan for each neighbouring location and move to the closest one.
        ArrayList<Location> neighbours = getLocation().getNeighbourLocations(1);
        Collections.shuffle(neighbours);
        neighbours.removeIf(l -> !canMove(l)); // Remove locations where Alien can't move
        neighbours.sort(Comparator.comparingDouble(l -> l.getDistanceTo(pacLocation)));

        if (!neighbours.isEmpty()) {
            next = neighbours.get(0);
            if (!game.isFurious()) {
                setDirection(getLocation().getDirectionTo(next));
                setLocation(next);
            } else {//if furious
                int i = 0;
                while (i < neighbours.size()) {
                    Location next2 = next.getAdjacentLocation(getLocation().getDirectionTo(next), 1);
                    if (canMove(next2)) {
                        setDirection(getLocation().getDirectionTo(next2));
                        setLocation(next2);
                        break;
                    } else {//if 2 step cannot move, choose another neighbor
                        i++;
                        next = neighbours.get(i);
                    }
                }
            }
        } else {
            setDirection(oldDirection);
        }

        game.getGameCallback().monsterLocationChanged(this);
    }
}



