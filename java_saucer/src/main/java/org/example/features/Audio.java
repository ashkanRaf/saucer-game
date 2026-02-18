package org.example.features;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

public class Audio {
    private AudioInputStream shoot;
    private AudioInputStream explode;

    public void soundEffect(AudioInputStream audioIn , String fileName){
        Clip myClip;
        ClassLoader classLoader = getClass().getClassLoader();
        AudioInputStream audioInputStream;
        try {
            try {
                audioInputStream = AudioSystem.getAudioInputStream(classLoader.getResource(fileName));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

        try {
             myClip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        try {
            try {
                myClip.open(audioInputStream);
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        myClip.setFramePosition(0);
        myClip.start();
    }

    public AudioInputStream getShoot() {
        return shoot;
    }

    public void setShoot(AudioInputStream shoot) {
        this.shoot = shoot;
    }

    public AudioInputStream getExplode() {
        return explode;
    }

    public void setExplode(AudioInputStream explode) {
        this.explode = explode;
    }
}
