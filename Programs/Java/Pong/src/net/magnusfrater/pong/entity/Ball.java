package net.magnusfrater.pong.entity;

import net.magnusfrater.pong.game.GameFrame;
import net.magnusfrater.pong.gui.Level;
import net.magnusfrater.pong.tools.RNG;

import java.awt.*;

public class Ball {

    public int x,y;
    private int size;
    private int speed;
    private int xDir,yDir;
    private Color c;

    private final Point spawn = new Point((GameFrame.WIDTH/2) - (size/2), (GameFrame.HEIGHT/2) - (size/2));

    private long iTime;
    private long s = 1000;

    public Ball(){
        size = Level.lineWidth*2;
        c = Color.WHITE;

        spawn();
    }

    private void spawn(){
        x = spawn.x;
        y = new RNG().getRandInt(Level.ceiling.y+Level.ceiling.height+25, Level.floor.y-25);

        speed = 5;

        if (new RNG().getRandInt(0,1) == 0){ //randomly sets xDir
            xDir = -1;
        }else{
            xDir = 1;
        }
        if (new RNG().getRandInt(0,1) == 0){ //randomly sets yDir
            yDir = -1;
        }else{
            yDir = 1;
        }

        iTime = System.currentTimeMillis();
    }

    public void tick(Player p1, Player p2){
        x+=speed*xDir;
        y+=speed*yDir;

        if (y < Level.ceiling.y+Level.ceiling.height){ //top bound collision
            y = Level.ceiling.y+Level.ceiling.height;
            yDir*=-1;
        }
        if (y+size > Level.floor.y){ //bottom bound collision
            y = Level.floor.y - size;
            yDir*=-1;
        }

        if (x < 0){ //left bound collision - p2 score
            p2.score++;
            spawn();
        }
        if (x+size > GameFrame.WIDTH){ //right bound collision - p1 score
            p1.score++;
            spawn();
        }

        //p1 collision
        if (x < p1.x+p1.width){ //(ball-left) past (p1-right)
            if ((y >= p1.y) && (y <= p1.y+p1.height)){ //(ball-top) between (p1-top) && (p1-bottom)
                if (x+(size/2) < p1.x+p1.width){ //(ball-x-mid) past (p1-right)
                    if(!(x+size < p1.x)){ //(ball-right) past (p1-left)
                        y = p1.y+p1.height;
                        yDir = 1;
                    }
                }else{
                    xDir = 1;
                }
            }else if ((y+size >= p1.y) && (y+size <= p1.y+p1.height)){ //(ball-bottom) between (p1-top) && (p1-bottom)
                if (x+(size/2) < p1.x+p1.width){ //(ball-x-mid) past (p1-right)
                    if(!(x+size < p1.x)){ //(ball-right) past (p1-left)
                        y = p1.y - size;
                        yDir = -1;
                    }
                }else{
                    xDir = 1;
                }
            }
        }

        //p2 collision
        if (x+size > p2.x){ //(ball-right) past p2-left
            if ((y >= p2.y) && (y <= p2.y+p2.height)){ //(ball-top) between (p2-top) && (p2-bottom)
                if (x+(size/2) > p2.x){ //(ball-x-mid) past (p2-left)
                    if (!(x > p2.x+p2.width)){ //(ball-left) past (p2-right)
                        y = p2.y+p2.height;
                        yDir = 1;
                    }
                }else {
                    xDir = -1;
                }
            } else if ((y+size >= p2.y) && (y+size <= p2.y+p2.height)){ //(ball-bottom) between (p2-top) && (p2-bottom)
                if (x+(size/2) > p2.x){ //(ball-x-mid) past (p2-left)
                    if (!(x > p2.x+p2.width)){ //(ball-left) past (p2-right)
                        y = p2.y - size;
                        yDir = -1;
                    }
                }else {
                    xDir = -1;
                }
            }
        }

        long fTime = System.currentTimeMillis(); //every certain amount of seconds, increase speed by 1
        if (fTime-iTime >= (s*speed*1.5)){
            speed++;
            iTime = System.currentTimeMillis();
        }
    }

    public void draw(Graphics2D g2d){
        g2d.setColor(c);
        g2d.fillRect(x,y,size,size);
    }
}
