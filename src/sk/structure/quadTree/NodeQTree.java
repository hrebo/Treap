/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.structure.quadTree;

/**
 *
 * @author Tomas Hreben
 * 
 * Node for Quad Tree
 */
public class NodeQTree {
    
    private NodeQTree NW_node;
    private NodeQTree NE_node;
    private NodeQTree SE_node;
    private NodeQTree SW_node;
    private NodeQTree father_node;
    
    private Point point;
    
    private double x_coordinate_min;
    private double x_coordinate_max;
    private double y_coordinate_min;
    private double y_coordinate_max;

    public NodeQTree(Point point, double x_coordinate_min, double x_coordinate_max, double y_coordinate_min, double y_coordinate_max) {
        this.point = point;
        this.x_coordinate_min = x_coordinate_min;
        this.x_coordinate_max = x_coordinate_max;
        this.y_coordinate_min = y_coordinate_min;
        this.y_coordinate_max = y_coordinate_max;
        
        this.NW_node = null;
        this.NE_node = null;
        this.SE_node = null;
        this.SW_node = null;
        this.father_node = null;
    }

    public NodeQTree getNW_node() {
        return NW_node;
    }

    public void setNW_node(NodeQTree NW_node) {
        this.NW_node = NW_node;
    }

    public NodeQTree getNE_node() {
        return NE_node;
    }

    public void setNE_node(NodeQTree NE_node) {
        this.NE_node = NE_node;
    }

    public NodeQTree getSE_node() {
        return SE_node;
    }

    public void setSE_node(NodeQTree SE_node) {
        this.SE_node = SE_node;
    }

    public NodeQTree getSW_node() {
        return SW_node;
    }

    public void setSW_node(NodeQTree SW_node) {
        this.SW_node = SW_node;
    }

    public NodeQTree getFather_node() {
        return father_node;
    }

    public void setFather_node(NodeQTree father_node) {
        this.father_node = father_node;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public double getX_coordinate_min() {
        return x_coordinate_min;
    }

    public void setX_coordinate_min(double x_coordinate_min) {
        this.x_coordinate_min = x_coordinate_min;
    }

    public double getX_coordinate_max() {
        return x_coordinate_max;
    }

    public void setX_coordinate_max(double x_coordinate_max) {
        this.x_coordinate_max = x_coordinate_max;
    }

    public double getY_coordinate_min() {
        return y_coordinate_min;
    }

    public void setY_coordinate_min(double y_coordinate_min) {
        this.y_coordinate_min = y_coordinate_min;
    }

    public double getY_coordinate_max() {
        return y_coordinate_max;
    }

    public void setY_coordinate_max(double y_coordinate_max) {
        this.y_coordinate_max = y_coordinate_max;
    }
    
    
}
