package org.example.entities;

import java.util.Random;

public class Saucer extends Entity {
    public int direction;
    private static final int DEFAULT_WIDTH = 40;
    private static final int DEFAULT_HEIGHT = 40;
    private  int DEFAULT_Y_SPEED;
    private  int DEFAULT_X_SPEED;

    public Saucer(int xp, int yp) {
        super(xp, yp, Saucer.DEFAULT_WIDTH, Saucer.DEFAULT_HEIGHT);
        Random random = new Random();
        DEFAULT_X_SPEED = random.nextInt(10) + 2;
        DEFAULT_Y_SPEED = random.nextInt(4) + 1;
        direction = 1;
    }
    public void move(Entity world) {move(DEFAULT_X_SPEED, DEFAULT_Y_SPEED, world);}

    public void move(int xDelta, int yDelta, Entity world) {
        if(!world.isInside(getShiftedCopy(xDelta * direction, yDelta))) {
            direction = direction * -1;
        }
        super.move(xDelta * direction, yDelta, world);
    }

    public static Saucer spawn(Entity world) {
        Saucer s = new Saucer(new Random().nextInt((int) (world.getWidth()*0.9)), new Random().nextInt(world.getHeight()/3));
        return s;
    }
    public boolean loosingCondition(Canon canon){
        if(collides(canon)){
            return true;
        }
        return false;
    }
}
