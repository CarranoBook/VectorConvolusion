/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eulerschool;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 *
 * @author nbleier
 */
public class PrimeSieve {
    ArrayList<Integer> p;
    int limit;
    
    /**
     * Finds all of the prime numbers <= the limit
     * @param limit limit for prime search
     */
    public PrimeSieve(int limit) {
        this.limit = limit;
        p = new ArrayList<Integer>();
        generatePrimes();
    }

    private void generatePrimes() {
        p.add(2);
        p.add(3);
        ListIterator<Integer> it;
        boolean prime1;
        boolean prime2;
        int temp;
        
        for (int i = 5; i<= limit; i +=6 ) {
            it = p.listIterator(2);
            prime1 = true;
            prime2 = true;
            
            while (it.hasNext() && prime1 && prime2) {
                temp = it.next();
                if (prime1 && i % temp == 0)
                    prime1 = false;
                
                if (prime2 && (i+2) % temp == 0)
                    prime2 = false;
            } //end while
            if (prime1)
                p.add(i);
            if (prime2)
                p.add(i+2);
            
        } //end for
        
    } //end generatePrimes()
    
    public ArrayList<Integer> getArrayList() { return p;}
    
    public Object[] getArray() {
        return p.toArray();
    }
    
}
