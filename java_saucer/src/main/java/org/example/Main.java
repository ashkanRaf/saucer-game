package org.example;

import org.example.entities.*;
import org.example.features.Audio;
import org.example.ui.Drawing;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Drawing drawing = new Drawing();
        Audio audio = new Audio();
        JFrame frame = new JFrame("saucer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        drawing.setSize(400, 400);
        frame.add(drawing);
        frame.pack();
        frame.setVisible(true);
        for(drawing.getHeartsObject().setHearts(3); drawing.getHeartsObject().getHearts() >= 0; drawing.getHeartsObject().setHearts(drawing.getHeartsObject().getHearts()-1)){
            
        drawing.entityList = new LinkedList<>();
        Entity world = new Entity(0, 0, frame.getWidth(), frame.getHeight());
        List<Saucer> saucerList = new LinkedList<>();
        for(int i=0; i<5; i++) {
            saucerList.add(Saucer.spawn(world));
        }
        drawing.entityList.addAll(saucerList);
        Canon canon = new Canon(200, 300);
        List<Ball> ballList = new LinkedList<>();
        drawing.entityList.add(canon);

        final List<Boolean> termination = new LinkedList<>();
        javax.swing.SwingUtilities.invokeLater(() -> frame.addKeyListener( new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_LEFT){
                    canon.move(-15, 0, world);
                }
                if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                    canon.move(15, 0, world);
                }
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                    termination.add(true);
                }
                if(e.getKeyCode() == KeyEvent.VK_SPACE) {
                    Ball b = canon.shoot();
                    ballList.add(b);
                    Saucer s = Saucer.spawn(world);
                    saucerList.add(s);
                    drawing.entityList.add(s);
                    audio.soundEffect(audio.getShoot() , "shootingSound.wav");
                }
            }
        }));


            while (termination.isEmpty()) {
                try {
                    Thread.sleep(120);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                world.setWidth(frame.getWidth());
                world.setHeight(frame.getHeight());
                for (Saucer s : saucerList) {
                    s.move(world);
                    if (s.collides(canon) || s.getyPosition() >= 300) {
                        termination.add(true);
                    }
                }
                for (Ball b : ballList) {
                    b.move(world);
                    if (!drawing.entityList.contains(b)) {
                        drawing.entityList.add(b);
                    }
                }
                ballList.removeIf(b -> b.getHeight() < 0);
                for (Saucer s : saucerList) {
                    for (Ball b : ballList) {
                        if (s.collides(b)) {
                            s.setExists(false);
                            b.setExists(false);
                            drawing.getScoreObject().setScore(drawing.getScoreObject().getScore()+1);
                        }
                    }
                }
                if(saucerList.removeIf(e -> !e.getExist())){
                    audio.soundEffect(audio.getExplode() , "explodeSound.wav");
                }
                ballList.removeIf(b -> !b.getExist());
                for (Ball b : ballList) {
                    if (b.getyPosition() <= 0) {
                        drawing.getScoreObject().setScore(drawing.getScoreObject().getScore()-1);
                    }
                }
                drawing.entityList.removeIf(e -> e instanceof Ball && !ballList.contains((Ball) e));
                drawing.entityList.removeIf(e -> e instanceof Saucer && !saucerList.contains((Saucer) e));
                drawing.getScoreObject().highScore();
                drawing.repaint();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        drawing.repaint();
    }
}
