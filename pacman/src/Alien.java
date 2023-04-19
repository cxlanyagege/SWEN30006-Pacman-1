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
        Location pacLocation = game.PacMan.getLocation();
        double oldDirection = getDirection();

        // Walking approach:
        // Alien: Calculate distance to PacMan for each neighbouring location and move to the closest one.
        ArrayList<Location> neighbours = getLocation().getNeighbourLocations(1);
        Collections.shuffle(neighbours);
        neighbours.removeIf(l -> !canMove(l)); // Remove locations where Alien can't move
        neighbours.sort(Comparator.comparingDouble(l -> l.getDistanceTo(pacLocation)));

        if (!neighbours.isEmpty()) {
            Location next = neighbours.get(0);
            setDirection(getLocation().getDirectionTo(next));
            setLocation(next);
        } else {
            setDirection(oldDirection);
        }

        game.getGameCallback().monsterLocationChanged(this);
    }
}
