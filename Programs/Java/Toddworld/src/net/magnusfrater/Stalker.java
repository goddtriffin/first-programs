package net.magnusfrater;

public class Stalker extends AI {

    @Override
    protected void update(World w, Player p, NPC npc) {
        if (npc.getY() < p.getY()){ //npc up of player
            setDown(true);
        }else {
            setDown(false);
        }

        if (npc.getY() > p.getY()) //npc down of player
            setUp(true);

        if (npc.getX() < p.getX()) //npc left of player
            setRight(true);

        if (npc.getX() > p.getX()) //npc right of player
            setLeft(true);
    }
}
