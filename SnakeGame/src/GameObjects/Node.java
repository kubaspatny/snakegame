/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjects;

/**
 *
 * @author Kuba
 */
public class Node {

    int x;
    int y;
    Node next;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        next = null;
    }

    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public boolean equals(Object other){
        if(other == null){
            return false;
        }
        
        if(other instanceof Node){
            Node o = (Node) other;
            if(this.x == o.x && this.y == o.y){
                return true;
            }
        }
        return false;
    }
    
    public boolean contains(Node node){        
        if(equals(node)){
            return true;
        }
        if(next!=null){
            return next.contains(node);
        }        
        return false;
    }
}
