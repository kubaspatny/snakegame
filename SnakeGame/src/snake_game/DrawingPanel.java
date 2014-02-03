/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snake_game;

import GameObjects.Food;
import GameObjects.Snake;
import GameObjects.Direction;
import java.awt.AlphaComposite;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;

/**
 *
 * @author Kuba
 */
public class DrawingPanel extends JPanel implements KeyListener {

    static final int SIDE_SIZE = 25;
    static Direction direction;
    static Direction direction_update;
    static int score = 0;
    GameState state;
    static int sleep_time = 300;
    static Snake snake;
    static Food food;
    Thread gameThread;
    static int refrest_period = 4;    
    static boolean[] levels = new boolean[10];
    
    public DrawingPanel(Container container) {
        setFocusable(true);
        requestFocus();
        container.setPreferredSize(new Dimension(600, 625));
        this.setBackground(Color.LIGHT_GRAY);        
        addKeyListener(this);
    } // end of constructor  
    
    // **********************************************************

    public void gameInitialize() {        
        score = 0;
        refrest_period = 4;
        direction = Direction.LEFT;
        direction_update = Direction.LEFT;
        snake = Snake.createSnake();
        food = Food.createFood(snake);
        playEaten(false);
    }

    public void gameStart() {
        gameThread = new Thread() {
            @Override
            public void run() {
                gameLoop();
            }
        };
        gameThread.start();
    }

    public void gameEnd() {
        state = GameState.GAMEOVER;
        Game.high_scores.updateScores(score);
        repaint();
        try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(DrawingPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
        switchToHighScores();
    }
    
    public void gameLoop() {
        long time_start;
        long time_end;
        long time_total;
        long time_wait;
        long refresh_time;
        state = GameState.PLAYING;        
        while (true) {
            refresh_time = 1000000000L / refrest_period;
            time_start = System.nanoTime();
            if (state == GameState.GAMEOVER) {
                break;
            }
            if (state == GameState.PLAYING) {
                gameUpdate();                
                repaint();                
            }
            repaint();
            time_end = System.nanoTime();
            time_total = time_end - time_start;
            time_wait = (refresh_time - time_total) / 1000000L;
            if(time_wait < 0){
                time_wait = 10;
            }
            try {
                Thread.sleep(time_wait);
            } catch (InterruptedException ex) {
            }
        }
    } // gameLoop

    private void gameUpdate() {
        tryChangeDirection(direction_update);
        if(!checkCollision() && checkBounds()){
        boolean ate = checkFood();
        if(ate){
            ++score;
            playEaten(true);
            food = Food.createFood(snake);
            updateLevel();
        }
        snake.moveInDirection(ate);
        } else {
            gameEnd();
        }
    }
    
    public void updateLevel(){
        if(score > 5){
            refrest_period = 6;
            levels[0] = true;
        }
        if(score > 10 && levels[1] != true){
            refrest_period = 9;
            levels[1] = true;
        }
        if(score > 15 && levels[2] != true){
            refrest_period = 12;
            levels[2] = true;
        }
    }
    
    // ********************************************************** 
    
    // ************************ DRAWING METHODS *****************    

    public static void drawRectangle(Graphics2D g2d, Point r) {
        g2d.fillRect(25 * r.x + 1, 25 * r.y + 26, SIDE_SIZE - 2, SIDE_SIZE - 2);
    } // drawRectangle

    public static void drawScoreBox(Graphics2D g2d) {
        g2d.fillRect(0, 0, 600, 25);
    } // drawRectangle
    
    public static void drawPause(Graphics2D g2d){
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, 600, 625);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        g2d.setColor(Color.WHITE);
        g2d.drawString("PAUSED", 540, 17);
    }
    
    public static void drawGameOver(Graphics2D g2d){
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, 600, 625);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        g2d.setColor(Color.WHITE);
        g2d.drawString("GAME OVER", 520, 17);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;
        snake.printSnake(g2d);        
        food.printFood(g2d);

        g2d.setColor(Color.DARK_GRAY);
        drawScoreBox(g2d);
        g2d.setColor(Color.WHITE);
        g2d.drawString("SCORE: "+score, 10, 17);
        
        if(state == GameState.GAMEOVER){
            drawGameOver(g2d);
        }
        
        if(state == GameState.PAUSED){
            drawPause(g2d);
        }
        
        

    } // end of paint
    
    // **********************************************************
    
    // ************************ CHECKING METHODS ****************
    
    private boolean checkCollision(){
        
        return snake.checkCollision();
        
    }
    
    private boolean checkBounds(){
        return snake.checkBounds();
    }
    
    private boolean checkFood(){
        return snake.checkFood();
    }
    
    // **********************************************************
    // **********************************************************
    
    public static int getDX(){
        switch(direction){
            case UP: return 0;
            case DOWN: return 0;
            case LEFT: return -1;
            case RIGHT: return 1;
            default:return 0;
        }
    }
    
    public static int getDY(){
        switch(direction){
            case UP: return -1;
            case DOWN: return 1;
            case LEFT: return 0;
            case RIGHT: return 0;
            default:return 0;
        }
    }
    
    public static Snake getSnake(){
        return snake;
    }
    
    public static Direction getDirection(){
        return direction;
    }
    
    public static Food getFood(){        
        return food;
    }
    // **********************************************************
    
    public Clip playEaten(boolean bol){
        try {
         // Open an audio input stream.
         URL url = this.getClass().getClassLoader().getResource("sound.wav");
         AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
         // Get a sound clip resource.
         Clip clip = AudioSystem.getClip();
         // Open audio clip and load samples from the audio input stream.
         clip.open(audioIn);
         clip.start();
         if(!bol){
             clip.stop();
         }
      } catch (UnsupportedAudioFileException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (LineUnavailableException e) {
         e.printStackTrace();
      }
        return null;
    }
    
    
    public void switchToHighScores() {
        CardLayout cl = (CardLayout) (Game.game.cards.getLayout());
        cl.next(Game.game.cards);
        Game.game_over.setLabel();        
    }
    
    static void tryChangeDirection(Direction newDir){
        switch(newDir){
            case UP: if(direction != Direction.DOWN){
                direction = newDir;
            }
                break;
            case DOWN: if(direction != Direction.UP){
                direction = newDir;
            }
                break;
            case LEFT: if(direction != Direction.RIGHT){
                direction = newDir;
            }
                break;
            case RIGHT: if(direction != Direction.LEFT){
                direction = newDir;
            }
                break;
        }
    }

    

    private void changeSleepTime(int x) {
        sleep_time = x;
    }

    // use enum DIRECTION
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                direction_update = Direction.UP;
                break;
            case KeyEvent.VK_DOWN:
                direction_update = Direction.DOWN;
                break;
            case KeyEvent.VK_LEFT:
                direction_update = Direction.LEFT;
                break;
            case KeyEvent.VK_RIGHT:
                direction_update = Direction.RIGHT;
                break;            
            case KeyEvent.VK_E:
                gameEnd();
                break;
            case KeyEvent.VK_P:
                if(state==GameState.PLAYING){
                    state = GameState.PAUSED;
                } else if(state==GameState.PAUSED){
                    state = GameState.PLAYING;
                }
                break;
        }
    }

    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
} // class

