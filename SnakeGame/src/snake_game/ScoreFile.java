/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snake_game;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kuba
 */
public class ScoreFile {
    
    File file = new File("src/hs.txt");    
    Scanner in;
    
    public ArrayList<Integer> readScores(){
        ArrayList<Integer> result = new ArrayList<>();
        try {
            in = new Scanner(new FileReader(file));
        } catch (FileNotFoundException ex) {
            try {
                file.createNewFile();
            } catch (IOException ex1) {
                System.out.println("New file creation error.");
            }
            try {
                in = new Scanner(new FileReader(file));
            } catch (FileNotFoundException ex1) {              
            }
        }
        
        while(in.hasNextInt()){
            result.add(in.nextInt());            
        }
        
        return result;
    }
    
    private void writeScores(ArrayList<Integer> list){
        BufferedWriter out;
        try {
            out = new BufferedWriter(new FileWriter(file));
            
            for (Integer integer : list) {
                out.write(integer.toString());
                out.newLine();
            }
            out.close();
            
        } catch (IOException ex) {
            System.out.println("Write scores error.");
        }
    }
    
    public void updateScores(int new_score){
        
        ArrayList<Integer> scores = readScores();
        ArrayList<Integer> result = new ArrayList<>();
        if(!scores.contains(new_score) && new_score > 0){
            scores.add(new_score);
        }        
        Collections.sort(scores);
        int i = 0;
        while(i < 8 && i < scores.size()){
            result.add(scores.get(scores.size() - 1 - i));
            i++;
        }
        writeScores(result);
        
    }
}
