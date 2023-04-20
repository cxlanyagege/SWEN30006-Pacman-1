package src;

import ch.aplu.jgamegrid.Location;

import java.util.ArrayList;
import java.util.Random;

public class Orion extends Monster {
    private final Game game;
    private ArrayList<Location> goldLocation;
    private ArrayList<Location> goldVisited = new ArrayList<>();
    private Location gold;
    public Orion(Game game) {
        super(game, MonsterType.Orion);
        this.game = game;
    }

    @Override
    protected void walkApproach() {
        if (gold == null)
        {
            // get all golds location
            goldLocation = game.getGoldLocation();

            // pick one gold randomly
            genRandom();
        }

        double oldDirection = getDirection();

        Location.CompassDirection compassDir =
                getLocation().get4CompassDirectionTo(gold);
        Location next = getLocation().getNeighbourLocation(compassDir);
        setDirection(compassDir);

        if (!isVisited(next) && canMove(next))
        {
            System.out.println(next + " Not visited");
            setLocation(next);
        }
        else
        {
            next = walkRandom(oldDirection);
        }

        // orion finds a gold
        if (next.equals(gold)) {
            System.out.println("Find a gold");
            goldVisited.add(next);
            genRandom();
        }
        game.getGameCallback().monsterLocationChanged(this);
        addVisitedList(next);
    }

    private void genRandom() {
        Random random = new Random();
        Location randLoc = goldLocation.get(random.nextInt(goldLocation.size()));

        // empty visited list when orion visit every gold
        if (goldVisited.size() == goldLocation.size()) {
            goldVisited = new ArrayList<>();
        }

        if (goldVisited.size() != 0) {
            for (Location loc : goldVisited) {
                if (loc.equals(randLoc)) {
                    System.out.println("Random regenerated");
                    genRandom();
                } else {
                    System.out.println("Locate next random gold");
                    gold = randLoc;
                }
            }
        } else {
            System.out.println("Locate first random gold");
            gold = randLoc;
        }
    }
}
