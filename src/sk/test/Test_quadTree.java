/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.test;

import sk.structure.quadTree.NodeQTree;
import sk.structure.quadTree.Point;
import sk.structure.quadTree.QuadTree;

/**
 *
 * @author Tomas
 */
public class Test_quadTree {
    
    public static void main(String[] args){
        
        QuadTree qt = new QuadTree(10, 10);
        
        qt.insertNode(new NodeQTree(new Point(2.0, 2.0, "bod1"), 10.0, 10.0));
        
        if( qt.existNode(2.0, 2.0)){
            System.out.println("Existuje");
        }
    }
    
}
