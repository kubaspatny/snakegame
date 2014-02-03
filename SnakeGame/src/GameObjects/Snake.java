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
public class Snake {

    private Node head;
    private Node tail;
    private static final Snake snake = new Snake();

    private Snake() {
        this.head = new Node(10, 10);
        this.tail = this.head;
    } // constructor

    public static Snake getInstance() {
        return snake;
    }

    public void add(int x, int y) {
        Node n = new Node(x, y);
        this.tail.next = n;
        this.tail = n;
    }

    public static Snake createSnake() {
        Snake snake = new Snake();
        for (int i = 1; i <= 6; i++) {
            snake.add(snake.head.x + i, snake.head.y);
        }
        return snake;
    }

    public Node getHead() {
        return this.head;
    }

    public Node getTail() {
        return this.tail;
    }

    public void printSnake(Graphics2D g2d) {
        printSnakeInner(this.head, g2d);
    }

    private void printSnakeInner(Node node, Graphics2D g2d) {
        //System.out.println("["+node.x+", "+node.y+"]");
        if(node.equals(this.head)){
           g2d.setColor(Color.RED); 
        } else {
           g2d.setColor(Color.DARK_GRAY);
        }
        
        DrawingPanel.drawRectangle(g2d, new Point(node.x, node.y));
        
        if (node.next != null) {
            printSnakeInner(node.next, g2d);
        }
    } // printListInner
    
    public void moveInDirection(boolean addNew) {
        Node node = this.head;
        int x = node.x;
        int y = node.y;
        node.set(x + DrawingPanel.getDX(), y + DrawingPanel.getDY());
        if (node.next != null) {
            moveInner(node.next, x, y, addNew);
        }        
    } //move

    private static void moveInner(Node node, int x_new, int y_new, boolean addNew) {
        int x_old = node.x;
        int y_old = node.y;
        node.set(x_new, y_new);
        if (node.next != null) {
            moveInner(node.next, x_old, y_old, addNew);
        }

        if (node.next == null && addNew) {
            DrawingPanel.getSnake().add(x_old, y_old);
        }
    } //moveInner
    
    public boolean contains(Node node){
        return this.head.contains(node);
    }
    
    public boolean checkCollision(){
        int dx;
        int dy;
        switch(DrawingPanel.getDirection()){
            case UP: dx=0; dy= -1;
                break;
            case DOWN: dx=0; dy= 1;
                break;
            case LEFT: dx=-1; dy= 0;
                break;
            case RIGHT: dx=1; dy= 0;
                break;
            default: dx=-1; dy= 0;
                break;
        }
        
        return contains(new Node(head.x+dx, head.y+dy));        
    }
    
    public boolean checkBounds(){
        int dx;
        int dy;
        switch(DrawingPanel.getDirection()){
            case UP: dx=0; dy= -1;
                break;
            case DOWN: dx=0; dy= 1;
                break;
            case LEFT: dx=-1; dy= 0;
                break;
            case RIGHT: dx=1; dy= 0;
                break;
            default: dx=-1; dy= 0;
                break;
        }
        int x = head.x + dx;
        int y = head.y + dy;
        
        return (x>=0 && x <= 23 && y>=0 && y <= 23);        
    }
    
    public boolean checkFood(){
        int dx;
        int dy;
        switch(DrawingPanel.getDirection()){
            case UP: dx=0; dy= -1;
                break;
            case DOWN: dx=0; dy= 1;
                break;
            case LEFT: dx=-1; dy= 0;
                break;
            case RIGHT: dx=1; dy= 0;
                break;
            default: dx=-1; dy= 0;
                break;
        }
        int x = head.x + dx;
        int y = head.y + dy;
        
        return new Node(x, y).equals(DrawingPanel.getFood().getFoodNode());
    }
}