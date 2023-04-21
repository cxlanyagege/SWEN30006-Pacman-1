/*
 *  Team Name: monday-16-15-team-04
 *  Team Member:
 *               Xinyi Yuan
 *               He Shen
 *               Yuchen Dong
 */


package src;

import ch.aplu.jgamegrid.Location;

public class Troll extends Monster {
    private final Game game;
    public Troll(Game game) {
        super(game, MonsterType.Troll);
        this.game = game;
    }

    @Override
    protected void walkApproach() {

            double oldDirection = getDirection();

            // Walking approach:
            // Troll: Random walk.
            Location next = walkRandom(oldDirection);
            game.getGameCallback().monsterLocationChanged(this);
            addVisitedList(next);

    }
}
