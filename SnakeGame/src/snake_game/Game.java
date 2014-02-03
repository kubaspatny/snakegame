/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snake_game;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Kuba
 */
public class Game {
    
    static JFrame frame = new JFrame("The Snake Game");
    static Game game = new Game();
    static GameOver game_over = new GameOver(frame.getContentPane());
    static HighScores high_scores = new HighScores(frame.getContentPane());
    DrawingPanel DP = new DrawingPanel(frame.getContentPane());
    JPanel cards;
    static JPanel card1 = new JPanel();

    private Game() {      
    }

    public void addComponentToPane(Container pane) {

        // CARD 1
                
        card1.setLayout(null);
        card1.setPreferredSize(new Dimension(600, 625));
        card1.setBackground(new Color(225, 219, 208));
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
        try {
            bg_top_image = ImageIO.read(new File("src/images/bg_top.jpg"));
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
        } catch (IOException ex) {
            System.out.println("ERROR");
        }  
        // ********** BACKGROUND IMAGES *********************
        JLabel bg_top = new JLabel(new ImageIcon(bg_top_image));
        bg_top.setBounds(0, 0, 600, 149);
        card1.add(bg_top);
        JLabel bg_bottom = new JLabel(new ImageIcon(bg_bottom_image));
        bg_bottom.setBounds(0, 535, 600, 90);
        card1.add(bg_bottom);
        JLabel bg_left = new JLabel(new ImageIcon(bg_left_image));
        bg_left.setBounds(0, 149, 110, 386);
        card1.add(bg_left);
        JLabel bg_right = new JLabel(new ImageIcon(bg_right_image));
        bg_right.setBounds(490, 149, 110, 386);
        card1.add(bg_right);
        // **************************************************        
        JButton j1 = new JButton(new ImageIcon(playButton)); 
        j1.setRolloverIcon(new ImageIcon(playButton_hover));
        j1.setPressedIcon(new ImageIcon(playButton_pressed));
        j1.setBounds(185, 216, 230, 52);
        j1.setBorder(null);
        j1.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.next(cards);
                DP.gameInitialize();
                DP.requestFocus();
                DP.gameStart();
            }
        });
        card1.add(j1);
        
        JButton j2 = new JButton(new ImageIcon(HSButton)); 
        j2.setRolloverIcon(new ImageIcon(HSButton_hover));
        j2.setPressedIcon(new ImageIcon(HSButton_pressed));
        j2.setBounds(185, 286, 230, 52);
        j2.setBorder(null);
        j2.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.last(cards);
                Game.high_scores.setLabels();
            }
        });
        card1.add(j2);
        
        JButton j3 = new JButton(new ImageIcon(exitButton)); 
        j3.setRolloverIcon(new ImageIcon(exitButton_hover));
        j3.setPressedIcon(new ImageIcon(exitButton_pressed));
        j3.setBounds(185, 356, 230, 52);
        j3.setBorder(null);
        j3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        card1.add(j3);
        
        // CARD 2 
        JPanel card2 = DP;
        
        cards = new JPanel(new CardLayout());
        cards.add(card1);
        cards.add(card2);
        cards.add(game_over);
        cards.add(high_scores);

        pane.add(cards, BorderLayout.CENTER);
    }

    private static void createAndShowGUI() {
        //Create and set up the content pane.        
        game.addComponentToPane(frame.getContentPane());
        frame.setResizable(false);        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    } 

    public static void main(String[] args) {

        try {            
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }        
        UIManager.put("swing.boldMetal", Boolean.FALSE);         
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });

    } // end of main
}
