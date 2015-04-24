package sk.test;

import java.util.LinkedList;
import java.util.Random;
import sk.structure.treap.Node;
import sk.structure.treap.Treap;

/**
 *
 * @author Tomas Hreben
 * @date 11.april 2015
 */
public class Test {
    
     public static void main(String[] args) {
        // TODO code application logic here
        Treap strom = new Treap();
        LinkedList<NumberElement> numberElements = new LinkedList<>();
        Random gen = new Random();
        Random genO = new Random();
        boolean us = false;

        //cyklus na otestovanie vkladania a mazania operaci
        NumberElement numberElement = null;
        int indexM = 0;

        //Zaciatocne naplnenie
        for (int i = 0; i < 100; i++) {
            numberElement = new NumberElement(gen.nextInt());
            us = strom.insert(new Node(numberElement));
            if (!us) {
                System.out.println("wrong insert: " + numberElement);
            } else {
                numberElements.add(numberElement);
                System.out.println("inserted");
            }
        }
        
        System.out.println("Check treap");
        strom.checkTreap(strom.getRoot());

        for (int i = 0; i < 100000; i++) {
            numberElement = new NumberElement(gen.nextInt());

            if (genO.nextDouble() < 0.5) {
                us = strom.insert(new Node(numberElement));
                if (!us) {
                    System.out.println("wrong insert: " + numberElement);
                } else {
                    numberElements.add(numberElement);
                    System.out.println("inserted");
                }
            } else {
                if (strom.getSize() != 0) {
                    indexM = gen.nextInt(strom.getSize());
                    numberElement = numberElements.remove(indexM);
                    if (strom.delete(numberElement)) {
                        System.out.println("success delete");
                    } else {
                        System.out.println("delete - element isn't in treap");
                    }
                }
            }
        }
        
        System.out.println("Check treap:");
        strom.checkTreap(strom.getRoot());

    }
}
