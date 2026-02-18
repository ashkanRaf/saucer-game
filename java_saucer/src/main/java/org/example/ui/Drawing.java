package org.example.ui;

import org.example.entities.Entity;
import org.example.features.Hearts;

import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.imageio.ImageIO;

import org.example.features.*;
public class Drawing extends Canvas {
    public List<Entity> entityList;
    private final Map<String, Image> images = new HashMap<>();
    private Image dbImage;
    private Graphics dbg;
    private Image backGroundImage;
    private Image gameOverBackGround;
    private Hearts heartsObject = new Hearts();
    private Score scoreObject = new Score();
    public void paint(Graphics g) {
        dbImage = createImage(getWidth(), getHeight());
        dbg = dbImage.getGraphics();
        paintComponent(dbg);
        g.drawImage(dbImage, 0, 0, this);
        if( heartsObject.getHearts() == -1 ){
            g.drawImage(gameOverBackGround , 140 , 180 , this);
            g.drawString("Game Over ! " , 150,200);
        }
    }

    public void paintComponent(Graphics g) {
        ClassLoader classLoader = getClass().getClassLoader();
        if(backGroundImage == null ){
            try {
                backGroundImage = ImageIO.read(Objects.requireNonNull(classLoader.getResource("backGround.png")));
                backGroundImage = backGroundImage.getScaledInstance(getWidth(),getHeight(),Image.SCALE_SMOOTH);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            g.drawImage(backGroundImage,0,0,this);

            for(Entity e : entityList) {
                String entityImageName = e.getClass().getSimpleName() + ".png";
                if(!images.containsKey(entityImageName)) {
                    Image iMg = ImageIO.read(Objects.requireNonNull(classLoader.getResource(entityImageName)));
                    iMg = iMg.getScaledInstance(e.getWidth(), e.getHeight(), 0);
                    images.put(entityImageName, iMg);
                }
                g.drawImage(images.get(entityImageName), e.getxPosition(), e.getyPosition(), this);
                g.setColor(Color.WHITE);
                g.drawString("High Score : " + scoreObject.getHighScore(), 10, 20);
                g.drawString("Score : " + scoreObject.getScore().toString(), 10, 40);
                Integer h = (heartsObject.getHearts() >= 0) ? heartsObject.getHearts() : 0 ;
                g.drawString("hearts : " + h.toString() , 10, 60);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(gameOverBackGround == null ){
            try{
                gameOverBackGround = ImageIO.read(Objects.requireNonNull(classLoader.getResource("blue.png")));
                gameOverBackGround = gameOverBackGround.getScaledInstance(100,30,Image.SCALE_SMOOTH);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public Score getScoreObject() {
        return scoreObject;
    }

    public void setScoreObject(Score scoreObject) {
        this.scoreObject = scoreObject;
    }
    public Hearts getHeartsObject() {
        return heartsObject;
    }

    public void setHeartsObject(Hearts hearts) {
        this.heartsObject = hearts;
    }

}
