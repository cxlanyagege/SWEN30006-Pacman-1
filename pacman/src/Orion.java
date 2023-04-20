package src;

import ch.aplu.jgamegrid.Location;

import java.util.ArrayList;
import java.util.Random;

public class Orion extends Monster {
    private final Game game;
    private final ArrayList<Location> goldLocation;
    private Location gold;
    public Orion(Game game) {
        super(game, MonsterType.Orion);
        this.game = game;

        // get all golds location
        goldLocation = game.getGoldLocation();

        // pick one gold randomly
        genRandom();
    }

    @Override
    protected void walkApproach() {

        double oldDirection = getDirection();

        Location.CompassDirection compassDir =
                getLocation().get4CompassDirectionTo(gold);
        Location next = getLocation().getNeighbourLocation(compassDir);
        setDirection(compassDir);

        if (canMove(next))
        {
            setLocation(next);
            if (next.getX() == gold.getX() && next.getX() == gold.getY())
            {
                addVisitedList(next);
                gold = genRandom();
            }
        }
        else
        {
            walkRandom(oldDirection);
        }

        game.getGameCallback().monsterLocationChanged(this);
    }

    private Location genRandom() {
        Random random = new Random();
        Location randLoc = goldLocation.get(random.nextInt(goldLocation.toArray().length));

        if (!isVisited(randLoc))
        {
            return randLoc;
        }
        else
        {
            return genRandom();
        }
    }
}
