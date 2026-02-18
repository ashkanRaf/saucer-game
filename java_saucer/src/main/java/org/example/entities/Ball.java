package org.example.entities;

public class Ball extends Entity {
    static final int DEFAULT_WIDTH = 20;
    static final int DEFAULT_HEIGHT = 20;
    private static final int DEFAULT_Y_SPEED = -20;
    private static final int DEFAULT_X_SPEED = 0;

    public Ball(int xp, int yp) {
        super(xp, yp, Ball.DEFAULT_WIDTH, Ball.DEFAULT_HEIGHT);
    }

    public void move(Entity world) {
        move(DEFAULT_X_SPEED, DEFAULT_Y_SPEED, world);
    }

    public void move(int xDelta, int yDelta, Entity world) {
        if(!world.isInside(getShiftedCopy(xDelta, yDelta))) {
            exists = false;
        }
        super.move(xDelta, yDelta, world);
    }
}
