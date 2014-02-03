/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snake_game;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Kuba
 */
public class GameOver extends JPanel {

    JButton new_game = new JButton("New Game");
    JButton high_scores = new JButton("Main Menu");
    JButton exit = new JButton("exit");    
    JLabel label = new JLabel("");
    
    public GameOver(Container container) {
        this.setLayout(null);
        container.setPreferredSize(new Dimension(600, 625));
        this.setBackground(new Color(225, 219, 208));
        
        BufferedImage bg_top_image = null;
        BufferedImage bg_bottom_image = null;
        BufferedImage bg_left_image = null;
        BufferedImage bg_right_image = null;
        BufferedImage playButton = null;
        BufferedImage playButton_hover = null;
        BufferedImage playButton_pressed = null;
        BufferedImage HSButton = null;
        BufferedImage HSButton_hover = null;
        BufferedImage HSButton_pressed = null;
        BufferedImage exitButton = null;
        BufferedImage exitButton_hover = null;
        BufferedImage exitButton_pressed = null;
        BufferedImage final_score_img = null;
        BufferedImage final_score_box = null;
        try {
            bg_top_image = ImageIO.read(new File("src/images/bg_top_gameover.jpg"));
            bg_bottom_image = ImageIO.read(new File("src/images/bg_bottom.jpg"));
            bg_left_image = ImageIO.read(new File("src/images/bg_left.jpg"));
            bg_right_image = ImageIO.read(new File("src/images/bg_right.jpg"));
            playButton = ImageIO.read(new File("src/images/play_normal.png"));
            playButton_hover = ImageIO.read(new File("src/images/play_hover.png"));
            playButton_pressed = ImageIO.read(new File("src/images/play_pressed.png"));
            HSButton = ImageIO.read(new File("src/images/HS_normal.png"));
            HSButton_hover = ImageIO.read(new File("src/images/HS_hover.png"));
            HSButton_pressed = ImageIO.read(new File("src/images/HS_pressed.png"));
            exitButton = ImageIO.read(new File("src/images/exit_normal.png"));
            exitButton_hover = ImageIO.read(new File("src/images/exit_hover.png"));
            exitButton_pressed = ImageIO.read(new File("src/images/exit_pressed.png"));
            final_score_img = ImageIO.read(new File("src/images/label_final_score.jpg"));
            final_score_box = ImageIO.read(new File("src/images/final_score_box.jpg"));
        } catch (IOException ex) {
            System.out.println("ERROR");
        }  
        // ********** BACKGROUND IMAGES *********************
        JLabel bg_top = new JLabel(new ImageIcon(bg_top_image));
        bg_top.setBounds(0, 0, 600, 149);
        this.add(bg_top);
        JLabel bg_bottom = new JLabel(new ImageIcon(bg_bottom_image));
        bg_bottom.setBounds(0, 535, 600, 90);
        this.add(bg_bottom);
        JLabel bg_left = new JLabel(new ImageIcon(bg_left_image));
        bg_left.setBounds(0, 149, 110, 386);
        this.add(bg_left);
        JLabel bg_right = new JLabel(new ImageIcon(bg_right_image));
        bg_right.setBounds(490, 149, 110, 386);
        this.add(bg_right);
        JLabel final_score = new JLabel(new ImageIcon(final_score_img));
        final_score.setBounds(185, 183, 230, 22);
        this.add(final_score);
        JLabel final_score_box_bg = new JLabel(new ImageIcon(final_score_box));
        final_score_box_bg.setBounds(185, 216, 230, 30);
        label.setBounds(195, 200, 100, 60);
        label.setForeground(Color.BLACK);
        this.add(label);
        this.add(final_score_box_bg);
        // ************************************************** 

        
        
        new_game = new JButton(new ImageIcon(playButton)); 
        new_game.setRolloverIcon(new ImageIcon(playButton_hover));
        new_game.setPressedIcon(new ImageIcon(playButton_pressed));
        new_game.setBounds(185, 285, 230, 52);
        new_game.setBorder(null);
        new_game.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (Game.game.cards.getLayout());
                cl.previous(Game.game.cards);
                Game.game.DP.gameInitialize();
                Game.game.DP.requestFocus();
                Game.game.DP.gameStart();
            }
        });
        this.add(new_game);
        
        high_scores = new JButton(new ImageIcon(HSButton)); 
        high_scores.setRolloverIcon(new ImageIcon(HSButton_hover));
        high_scores.setPressedIcon(new ImageIcon(HSButton_pressed));
        high_scores.setBounds(185, 355, 230, 52);
        high_scores.setBorder(null);
        high_scores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (Game.game.cards.getLayout());                
                cl.last(Game.game.cards);
                Game.high_scores.setLabels();
            }
        });
        
        exit = new JButton(new ImageIcon(exitButton)); 
        exit.setRolloverIcon(new ImageIcon(exitButton_hover));
        exit.setPressedIcon(new ImageIcon(exitButton_pressed));
        exit.setBounds(185, 425, 230, 52);
        exit.setBorder(null);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        
        this.add(high_scores);
        this.add(exit);

        
        
    } // end of constructor
    
    public void setLabel(){        
        label.setText(""+DrawingPanel.score);
    }
}
