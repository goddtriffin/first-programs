package net.magnusfrater;

public abstract class AI {

    private boolean up,down,left,right;

    public AI(){
        up = false;
        down = false;
        left = false;
        right = false;
    }

    protected abstract void update(World w, Player p, NPC npc);

    public boolean getUp() {
        return up;
    }
    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean getDown() {
        return down;
    }
    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean getLeft() {
        return left;
    }
    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean getRight() {
        return right;
    }
    public void setRight(boolean right) {
        this.right = right;
    }
}
