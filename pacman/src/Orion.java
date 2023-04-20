package src;

import ch.aplu.jgamegrid.Location;

import java.util.ArrayList;
import java.util.Random;

public class Orion extends Monster {
    private final Game game;
    private ArrayList<Location> goldLocation;

    public Orion(Game game) {
        super(game, MonsterType.Orion);
        this.game = game;
        //goldLocation = game.getGoldLocation();
    }

    @Override
    protected void walkApproach() {
        Random random = new Random();
        goldLocation = game.getGoldLocation();

        Location gold = goldLocation.get(random.nextInt(goldLocation.size()));

        double oldDirection = getDirection();

        Location.CompassDirection compassDir =
                getLocation().get4CompassDirectionTo(gold);
        Location next = getLocation().getNeighbourLocation(compassDir);
        setDirection(compassDir);

        if (canMove(next))
        {
            setLocation(next);
        }
        else
        {
            walkRandom(oldDirection);
        }

        game.getGameCallback().monsterLocationChanged(this);
    }
}
