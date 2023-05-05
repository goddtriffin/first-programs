package net.magnusfrater;

public class Tool {

    private int woodDamage, stoneDamage;

    public Tool(String type){
        this.woodDamage = 1;
        this.stoneDamage = 1;

        switch (type){
            case "axe": woodDamage = 2;
                break;
            case "pick": stoneDamage = 3;
                break;
        }
    }

    public int getWoodDamage() {
        return woodDamage;
    }

    public int getStoneDamage() {
        return stoneDamage;
    }
}