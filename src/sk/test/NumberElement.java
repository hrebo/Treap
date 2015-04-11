/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.test;

/**
 *
 * @author Tomas
 */
public class NumberElement implements Comparable{
    
     private int number;

    public NumberElement(int cislo) {
        this.number = cislo;
    }

    
    public int getNumber() {
        return number;
    }

    public void setNumber(int cislo) {
        this.number = cislo;
    }
    
    @Override
    public String toString(){
        return String.valueOf(number);
    }
    
    @Override
    public int compareTo(Object o) {
        if(this.number == ((NumberElement)o).getNumber() )
            return 0;
        if(this.number > ((NumberElement)o).getNumber() )
            return 1;
        else
            return -1;
    }
}
