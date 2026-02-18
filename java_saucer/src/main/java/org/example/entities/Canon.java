package org.example.entities;

public class Canon extends Entity {
    private static final int DEFAULT_WIDTH = 60;
    private static final int DEFAULT_HEIGHT = 100;
    public Canon(int xp, int yp) {
        super(xp, yp, Canon.DEFAULT_WIDTH, Canon.DEFAULT_HEIGHT);
    }

    public Ball shoot() {
        // return new Ball(this.xPosition + DEFAULT_WIDTH / 2 - Ball.DEFAULT_WIDTH / 2, this.yPosition - Ball.DEFAULT_HEIGHT);
        return new Ball(this.xPosition + DEFAULT_WIDTH / 2 - Ball.DEFAULT_WIDTH / 2, this.yPosition);
    }
}
