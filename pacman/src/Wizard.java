//package src;
//
//import ch.aplu.jgamegrid.Location;
//
//public class Wizard extends Monster {
//    private final Game game;
//
//    public Wizard(Game game) {
//        super(game, MonsterType.Wizard);
//        this.game = game;
//    }
//
//    @Override
//    protected void walkApproach() {
//        double oldDirection = getDirection();
//        Location location = getLocation();
//        Location.CompassDirection direction = Location.CompassDirection.values()[(int) (Math.random() * 8)];
//
//        Location next = location.getNeighbourLocation(direction);
//        if (canMove(next)) {
//            setLocation(next);
//        } else if (game.getOneActorAt(next) instanceof MazeWall) {
//            Location adjacent = next.getNeighbourLocation(direction);
//            if (!canMove(adjacent) /*|| game.getOneActorAt(adjacent) instanceof MazeWall*/) {
//                // If the adjacent location is also a wall, pick another random direction
//                direction = Location.CompassDirection.values()[(int) (Math.random() * 8)];
//                next = location.getNeighbourLocation(direction);
//                while (!canMove(next)) {
//                    direction = Location.CompassDirection.values()[(int) (Math.random() * 8)];
//                    next = location.getNeighbourLocation(direction);
//                }
//                setLocation(next);
//            } else {
//                // Walk through the wall to the adjacent location
//                setLocation(adjacent);
//            }
//        }
//
//        setDirection(direction);
//        game.getGameCallback().monsterLocationChanged(this);
//    }
//}

//package src;
//
//import ch.aplu.jgamegrid.Location;
//import java.awt.Color;
//
//import java.util.ArrayList;
//
//public class Wizard extends Monster {
//    private final Game game;
//
//    public Wizard(Game game) {
//        super(game, MonsterType.Wizard);
//        this.game = game;
//    }
//
//    @Override
//    protected void walkApproach() {
//        Location pacLocation = game.pacMan.getLocation();
//        double oldDirection = getDirection();
//
//        // Walking approach:
//        // Wizard: Randomly select one of its neighbour locations (8 cells around).
//        ArrayList<Location> neighbours = getLocation().getNeighbourLocations(1);
//        neighbours.removeIf(l -> !canMove(l)); // Remove locations where Wizard can't move
//        if (!neighbours.isEmpty()) {
//            int index = (int) (Math.random() * neighbours.size());
//            Location next = neighbours.get(index);
//
//            // Check if the selected location is a wall
//            if (!canMove(next)) {
//                Location adjacent = getLocation().getAdjacentLocation(getLocation().getDirectionTo(next));
//                // Check if the adjacent location in the same direction is not a wall
//                if (canMove(adjacent)) {
//                    next = adjacent;
//                } else {
//                    // If both the selected location and the adjacent location are walls, select another neighbour
//                    neighbours.remove(next);
//                    index = (int) (Math.random() * neighbours.size());
//                    next = neighbours.get(index);
//                }
//            }
//
//            setDirection(getLocation().getDirectionTo(next));
//            setLocation(next);
//        } else {
//            setDirection(oldDirection);
//        }
//
//
//
//        game.getGameCallback().monsterLocationChanged(this);
//    }
//
//    protected boolean canMove(Location location, boolean canWalkThroughWalls) {
//        Color c = getBackground().getColor(location);
//        if (c.equals(Color.gray) || location.getX() >= game.getNumHorzCells()
//                || location.getX() < 0 || location.getY() >= game.getNumVertCells() || location.getY() < 0)
//            return false;
//        else if (canWalkThroughWalls) {
//            Location next = getLocation().getNeighbourLocation(getLocation().getDirectionTo(location));
//            Color nextC = getBackground().getColor(next);
//            if (nextC.equals(Color.gray))
//                return false;
//        }
//        return true;
//    }
//}

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

