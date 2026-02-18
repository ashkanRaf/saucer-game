package org.example.features;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Score {
    public Integer score = 0 ;
    public void setHighScore(File myFile){
        try{
            if(myFile.createNewFile()) {
                System.out.println("file created ! ");
            }
            FileWriter fileWriter = new FileWriter("highScore.txt");
            fileWriter.write(score.toString());
            fileWriter.close();
        } catch (IOException e ){
            System.out.println("Unexcepted error ! ");
        }
    }
    public int getHighScore(){
        File myFile = new File("highScore.txt");
        try(Scanner scanner = new Scanner(myFile)){
            return scanner.nextInt();
        }
        catch (FileNotFoundException e ){
            System.out.println("Unexcepted error ! ");
        }
        return 0 ;
    }
    public void highScore(){
        File myFile = new File("highScore.txt");
        if(myFile.exists()) {
            try (Scanner scanner = new Scanner(myFile)) {
                if (score > scanner.nextInt()) {
                    this.setHighScore(myFile);
                }
            } catch (FileNotFoundException e) {
                System.out.println("Unexcepted error ! ");
            }
        }
        else{
            this.setHighScore(myFile);
        }
    }
    public Integer getScore() {
        return score;
    }
    public void setScore(Integer score) {
        this.score = score;
    }

}
