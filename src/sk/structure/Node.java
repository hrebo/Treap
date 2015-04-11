package sk.structure;

import java.util.Random;

/**
 *
 * @author Tomas Hreben
 * @date 11.april 2015
 */
public class Node {
    
    private Comparable element;
    private Node leftSon;
    private Node rightSon;
    private Node father;
    private int priority;
    
    private static Random genPriority = new Random();

    public Node(Comparable element) {
        this.element = element;
        this.leftSon = null;
        this.rightSon = null;
        this.father = null;
        this.priority = genPriority.nextInt(2000000)+1;
    }

    public Comparable getElement() {
        return element;
    }

    public void setElement(Comparable element) {
        this.element = element;
    }

    public Node getLeftSon() {
        return leftSon;
    }

    public void setLeftSon(Node leftSon) {
        this.leftSon = leftSon;
    }

    public Node gerRightSon() {
        return rightSon;
    }

    public void setRightSon(Node rightSon) {
        this.rightSon = rightSon;
    }

    public Node getFather() {
        return father;
    }

    public void setFather(Node father) {
        this.father = father;
    }

    public int getPriority() {
        return priority;
    }
}
