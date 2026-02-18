package org.example.entities;

public class Entity {
    protected int xPosition;
    protected int yPosition;
    protected int width;
    protected int height;
    protected boolean exists;

    public Entity(int xp, int yp, int w, int h) {
        this.xPosition = xp;
        this.yPosition = yp;
        this.width = w;
        this.height = h;
        this.exists = true;
    }

    public void move(int xDelta, int yDelta, Entity world) {
        if(world.isInside(getShiftedCopy(xDelta, yDelta))) {
            xPosition += xDelta;
            yPosition += yDelta;
        }
    }

    public boolean collides(Entity e) {
        if(this.xPosition + this.width < e.xPosition || e.xPosition + e.width < this.xPosition) {
            return false;
        }
        if(this.yPosition + this.height < e.yPosition || e.yPosition + e.height < this.yPosition) {
            return false;
        }
        return true;
    }

    public boolean isInside(Entity e) {
        if(e.getxPosition() < xPosition || e.getyPosition() < yPosition) {
            return false;
        }
        if(e.getxPosition() + e.getWidth() > xPosition + width || e.getyPosition() + e.getHeight() > yPosition + height) {
            return false;
        }
        return true;
    }

    public Entity getShiftedCopy(int xDelta, int yDelta) {
        return new Entity(xPosition + xDelta, yPosition + yDelta, width, height);
    }

    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setExists(boolean e) {
        exists = e;
    }

    public boolean getExist() {
        return exists;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }
}
