/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snake_game;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Kuba
 */
public class HighScores extends JPanel {
 
    JButton main_menu = new JButton("Main Menu");
    JButton exit = new JButton("exit");    
    JLabel label1 = new JLabel("");
    JLabel label2 = new JLabel("");
    JLabel label3 = new JLabel("");
    JLabel label4 = new JLabel("");
    JLabel label5 = new JLabel("");
    JLabel label6 = new JLabel("");
    JLabel label7 = new JLabel("");
    JLabel label8 = new JLabel("");
    ArrayList<JLabel> label_list = new ArrayList<>();
    ScoreFile score_file = new ScoreFile();
    
    
    public HighScores(Container container) {
        this.setLayout(null);
        container.setPreferredSize(new Dimension(600, 625));
        this.setBackground(new Color(225, 219, 208));
        
        BufferedImage bg_top_image = null;
        BufferedImage bg_bottom_image = null;
        BufferedImage bg_left_image = null;
        BufferedImage bg_right_image = null;
        
        BufferedImage MMButton = null;
        BufferedImage MMButton_hover = null;
        BufferedImage MMButton_pressed = null;
        
        BufferedImage final_score_box = null;
        try {
            bg_top_image = ImageIO.read(new File("src/images/bg_top_highscores.jpg"));
            bg_bottom_image = ImageIO.read(new File("src/images/bg_bottom.jpg"));
            bg_left_image = ImageIO.read(new File("src/images/bg_left.jpg"));
            bg_right_image = ImageIO.read(new File("src/images/bg_right.jpg"));
            
            MMButton = ImageIO.read(new File("src/images/mainmenu_normal.png"));
            MMButton_hover = ImageIO.read(new File("src/images/mainmenu_hover.png"));
            MMButton_pressed = ImageIO.read(new File("src/images/mainmenu_pressed.png"));            
            
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
        
        
        JLabel final_score_box_bg = new JLabel(new ImageIcon(final_score_box));
        final_score_box_bg.setBounds(185, 165, 230, 30);
        JLabel final_score_box_bg2 = new JLabel(new ImageIcon(final_score_box));
        final_score_box_bg2.setBounds(185, 200, 230, 30);
        JLabel final_score_box_bg3 = new JLabel(new ImageIcon(final_score_box));
        final_score_box_bg3.setBounds(185, 235, 230, 30);
        JLabel final_score_box_bg4 = new JLabel(new ImageIcon(final_score_box));
        final_score_box_bg4.setBounds(185, 270, 230, 30);
        JLabel final_score_box_bg5 = new JLabel(new ImageIcon(final_score_box));
        final_score_box_bg5.setBounds(185, 305, 230, 30);
        JLabel final_score_box_bg6 = new JLabel(new ImageIcon(final_score_box));
        final_score_box_bg6.setBounds(185, 340, 230, 30);
        JLabel final_score_box_bg7 = new JLabel(new ImageIcon(final_score_box));
        final_score_box_bg7.setBounds(185, 375, 230, 30);
        JLabel final_score_box_bg8 = new JLabel(new ImageIcon(final_score_box));
        final_score_box_bg8.setBounds(185, 410, 230, 30);
        
        label1.setBounds(195, 165, 100, 30);
        label1.setForeground(Color.BLACK);
        label2.setBounds(195, 200, 100, 30);
        label2.setForeground(Color.BLACK);
        label3.setBounds(195, 235, 100, 30);
        label3.setForeground(Color.BLACK);
        label4.setBounds(195, 270, 100, 30);
        label4.setForeground(Color.BLACK);
        label5.setBounds(195, 305, 100, 30);
        label5.setForeground(Color.BLACK);
        label6.setBounds(195, 340, 100, 30);
        label6.setForeground(Color.BLACK);
        label7.setBounds(195, 375, 100, 30);
        label7.setForeground(Color.BLACK);
        label8.setBounds(195, 410, 100, 30);
        label8.setForeground(Color.BLACK);
        this.add(label1);
        this.add(label2);
        this.add(label3);
        this.add(label4);
        this.add(label5);
        this.add(label6);
        this.add(label7);
        this.add(label8);
        this.add(final_score_box_bg);
        this.add(final_score_box_bg2);
        this.add(final_score_box_bg3);
        this.add(final_score_box_bg4);
        this.add(final_score_box_bg5);
        this.add(final_score_box_bg6);
        this.add(final_score_box_bg7);
        this.add(final_score_box_bg8);
        // ************************************************** 
        
        main_menu = new JButton(new ImageIcon(MMButton)); 
        main_menu.setRolloverIcon(new ImageIcon(MMButton_hover));
        main_menu.setPressedIcon(new ImageIcon(MMButton_pressed));
        main_menu.setBounds(185, 460, 230, 52);
        main_menu.setBorder(null);
        main_menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (Game.game.cards.getLayout());
        cl.first(Game.game.cards);
            }
        });
        
        this.add(main_menu);
        this.add(exit);
        
        this.fillList();
        
    } // end of constructor
    
    public void setLabels(){
        JLabel label;
        ArrayList<Integer> scores = score_file.readScores();
        for (int i = 0; i < scores.size(); i++) {
            label = label_list.get(i);
            label.setText(scores.get(i).toString());
        }
    }
    
    public void updateScores(int new_score){
        score_file.updateScores(new_score);
    }
    
    private void fillList(){
        label_list.add(label1);
        label_list.add(label2);
        label_list.add(label3);
        label_list.add(label4);
        label_list.add(label5);
        label_list.add(label6);
        label_list.add(label7);
        label_list.add(label8);        
    }
    
}
