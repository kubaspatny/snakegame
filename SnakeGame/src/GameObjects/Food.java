/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import snake_game.DrawingPanel;

/**
 *
 * @author Kuba
 */
public class Food {
    
    private Node node;
    
    public Food(int x, int y){
        node = new Node(x, y);
    }
    
    public Node getFoodNode(){
        return this.node;
    }
    
    public static Food createFood(Snake snake){
        int x;
        int y;
        Node newNode;
        
        do {
            x = (int) (Math.random() * 23);
            y = (int) (Math.random() * 23);
            newNode = new Node(x, y);
        } while(snake.contains(newNode));
        return new Food(x, y);
    }
    
    public void printFood(Graphics2D g2d){
        g2d.setColor(Color.GREEN);
        DrawingPanel.drawRectangle(g2d, new Point(node.x, node.y));
    }
    
}
