package net.magnusfrater;

import Tools.RandomNumberGenerator;

import java.awt.*;

public class World {

    //general
    private RandomNumberGenerator rng = new RandomNumberGenerator();

    //world
    private int wSize;
    private Tile[][] world;

    private int wCenter;

    //tiles
    private int tSize;

    public World(int wSize, int tSize, int smoothLevel){ //specific world
        //tiles
        this.tSize = tSize;

        //world
        this.wSize = wSize;
        this.wCenter = (wSize*tSize)/2;

        this.world = new Tile[wSize][wSize];

        //generate
        world = generateRandomWorld(wSize, smoothLevel);
    }
    public World(Tile[][] world, int[][] heightMap, int tSize){ //premade world
        //tiles
        this.tSize = tSize;

        //world
        this.wSize = world.length;
        this.wCenter = (wSize*tSize)/2;

        this.world = world;
    }

    private int[][] generateHeightMap(int size, int smoothLevel){
        int[][] map = new int[size][size];

        for (int y=0; y<size; y++){ //random noise
            for (int x=0; x<size; x++){
                int randHeight = rng.getRandInt(0,1000);
                map[y][x] = randHeight;
            }
        }

        for (int i=0; i<smoothLevel; i++){ //smooth noise
            map = smoothHeightMap(map);
        }

        return map;
    }
    protected int[][] smoothHeightMap(int[][] map){
        for (int y=0; y<map.length; y++){ //world columns
            for (int x=0; x<map[y].length; x++){ //world rows

                int sum = 0;
                int count = 0;

                for (int dY=-1; dY<=1; dY++){ //change in column
                    for (int dX=-1; dX<=1; dX++){ //change in row

                        if (y+dY>=0 && y+dY<map.length){ //within column bounds
                            if (x+dX>=0 && x+dX<map[y].length){ //within row bounds
                                sum+=map[y+dY][x+dX];
                                count++;
                            }
                        }
                    }
                }

                int averageHeight = sum/count;

                map[y][x] = averageHeight; //reset height
            }
        }

        return map;
    }

    private int getMinHeight(int[][] heightMap){
        int minHeight = Integer.MAX_VALUE;

        for (int y=0; y<heightMap.length; y++){
            for (int x=0; x<heightMap[y].length; x++){
                if (heightMap[y][x]<minHeight)
                    minHeight = heightMap[y][x];
            }
        }

        return minHeight;
    }
    private int getMaxHeight(int[][] heightMap){
        int maxHeight = Integer.MIN_VALUE;

        for (int y=0; y<heightMap.length; y++){
            for (int x=0; x<heightMap[y].length; x++){
                if (heightMap[y][x]>maxHeight)
                    maxHeight = heightMap[y][x];
            }
        }

        return maxHeight;
    }
    private int getMapRange(int[][] heightMap){
        return (getMaxHeight(heightMap) - getMinHeight(heightMap));
    }

    private String getGenericTileType(int[][] heightMap, int height, int minHeight,int maxHeight, int range){
        double percentOcean = .35;
        double percentShore = .05;
        double percentBeach = .03;

        double percentMountain = .15;
        double percentSnowcap = .15;

        int oceanHeight = minHeight + (int)(range*percentOcean);
        int shoreHeight = oceanHeight + (int)(range*percentShore);
        int beachHeight = shoreHeight + (int)(range*percentBeach);

        int snowcapHeight = maxHeight - (int)(range*percentSnowcap);
        int mountainHeight = snowcapHeight - (int)(range*percentMountain);

        if (height < oceanHeight) //ocean
            return "ocean";
        if (height < shoreHeight) //shore
            return "shore";
        if (height < beachHeight) //beach
            return "beach";

        if (height > snowcapHeight) //snowcap
            return "snowcap";
        if (height > mountainHeight) //mountain
            return "mountain";

        return "grass"; //grass
    }

    private Tile[][] generateRandomWorld(int size, int smoothLevel){
        Tile[][] world = new Tile[size][size];

        int[][] heightMap = generateHeightMap(size, smoothLevel);
        int minHeight = getMinHeight(heightMap);
        int maxHeight = getMaxHeight(heightMap);
        int rangeHeight = maxHeight - minHeight;

        int[][] forestMap = generateHeightMap(size, 10);
        int minForest = getMinHeight(forestMap);
        int maxForest = getMaxHeight(forestMap);
        int rangeForest = maxForest - minForest;

        for (int y=0; y<size; y++){
            for (int x=0; x<size; x++){
                String tileType = getGenericTileType(heightMap, heightMap[y][x], minHeight,maxHeight, rangeHeight);

                if (tileType.equals("grass")){
                    if (forestMap[y][x] > maxForest - (int)(.5*rangeForest))
                        tileType = "tree";
                }

                world[y][x] = new Tile(x*tSize,y*tSize, tSize, tileType);
            }
        }

        return world;
    }

    protected void tick(){
        //NOT USED
    }

    protected void draw(Dimension frame, double sX,double sY, Graphics window){
        //draw tiles
        for (int y=0; y<wSize; y++){ //world column
            for (int x=0; x<wSize; x++){ //world row

                if ((y+1)*tSize-sY>=0 && (y-1)*tSize+tSize-sY<=frame.getHeight()){ //within frame height
                    if ((x+1)*tSize-sX>=0 && (x-1)*tSize+tSize-sX<=frame.getWidth()){ //within frame width
                        world[y][x].draw(sX,sY, window);
                    }
                }
            }
        }
    }

    public Tile getTile(int x, int y){ return this.world[y][x]; }

    public int getWorldSize() {
        return this.wSize;
    }

    public int getTileSize() {
        return this.tSize;
    }

    public int getCenter(){ return this.wCenter; }
}