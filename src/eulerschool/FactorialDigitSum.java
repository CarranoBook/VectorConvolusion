/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eulerschool;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Stack;

/**
 *
Problem 20
n! means n × (n − 1) × ... × 3 × 2 × 1

For example, 10! = 10 × 9 × ... × 3 × 2 × 1 = 3628800,
and the sum of the digits in the number 10! is 3 + 6 + 2 + 8 + 8 + 0 + 0 = 27.

Find the sum of the digits in the number 100!

 * @author nbleier
 */
public class FactorialDigitSum {
    ArrayList<Integer> p;
    int fac;
    HashMap<Integer, Integer> m1; //maps primes in p to the number of times they occur in the prime factorization of fac!
    HashMap<Integer, ArrayDeque<Integer>> m2;
    
    
    public FactorialDigitSum(int fac) {
        this.fac = fac;
        m1 = null;
        p = null;
        m2 = new HashMap<>();
        generateFacVectors();
        
    }
    
    
    public ArrayDeque<Integer> getDeque(Integer key) {
        return m2.get(key);
    }
    /**
     * This method generates vector representations of n! for n = 1 to fac.
     * The vectors basis vectors are powers of 10.
     */
    private void generateFacVectors() {
        ArrayDeque<Integer> aD1 = new ArrayDeque<Integer>();
        aD1.addFirst(0);
        aD1.addFirst(1);
        ArrayDeque<Integer> temp;
        
        m2.put(1, aD1);
        
        for ( int i = 2; i <= fac; i++ ) {
            temp = this.listToDeque(convoluteVectors( m2.get(i-1), this.generateVector(i)));
            m2.put(i, temp);
        }
        
    }
    
    private ArrayList<Integer> convoluteVectors(ArrayDeque<Integer> a, ArrayDeque<Integer> b) {
        equalizeVectorSizes(a, b);
        ArrayList<Integer> aL = this.dequeToList(a);
        ArrayList<Integer> bL = this.dequeToList(b);
        ArrayList<Integer> cL = new ArrayList<>();
        
        int u = 1;
        int n = aL.size();
        int d;
        
        for (int x = 1; x <= 2 * n - 1; x++) {
            u = max(1, x-n+1);
            d = min(x, n);
            int cx = 0;
            
            while (u <= d) {
                cx += aL.get(u) * bL.get(x - u + 1);
                u = max(1, x-n+1);
                d = min(x, n);
            }
            
            cL.add(x, cx);

        } // end for
       
        return cL;
    }
    
    private int min(int a, int b) {
        return (a <= b) ? a : b;
    }
    
    private int max(int a, int b) {
        return (a >= b) ? a : b;
    }
    
    private ArrayList<Integer> dequeToList(ArrayDeque<Integer> d) {
        ArrayList<Integer> a = new ArrayList<>();
        a.add(0, 0);
        while (!d.isEmpty())
            a.add(d.removeFirst());
            
        return a;
    }
    
    private ArrayDeque<Integer> listToDeque(ArrayList<Integer> a) {
        ArrayDeque<Integer> d = new ArrayDeque<>();
        ListIterator<Integer> it = a.listIterator();
        
        while (it.hasNext())
            d.addFirst(it.next());
        
        return d;
    }
    
    private void equalizeVectorSizes(ArrayDeque<Integer> a, ArrayDeque<Integer> b) {
        while ( a.size() < b.size() )
            a.addFirst(0);
        
        while ( b.size() < a.size() )
            b.addFirst(0);
    }
    
    /**
     * Generates polynomial vectors with largest power first, smallest power last
     * @param n
     * @return 
     */
    private ArrayDeque<Integer> generateVector(int n) {
        ArrayDeque<Integer> result = new ArrayDeque<>();    
        
        while (n > 0) {
            result.addFirst(n % 10);
            n %= 10;
        }
        return result;
    }
    
    
    /**
     * Generates the prime factors mapping of fac!
     */
    private void generatePrimeFactorsMap() {
        p = new PrimeSieve(fac).getArrayList(); // all primes <= fac
        ListIterator<Integer> it = p.listIterator();
        
        Integer a;
        int count;
        double pow;
        double n = (double) fac;
        double floor;
        m1 = new HashMap<>();
        
        while (it.hasNext()) {
            a = it.next();
            count = 0;
            pow = 1;
            floor = Math.floor(n / Math.pow(a.doubleValue(), pow));
            
            while ( floor >= 1) {
                count += floor;
                floor = Math.floor((n) / Math.pow(a.doubleValue(), ++pow));
            }//end while
            
            m1.put(a, count);
            
        } //end while
        
    }
    
    /**
     * Generates the prime factors mapping of fac!
     * @return HashMap with prime numbers < fac as keys and the number of times
     * the key is a factor of fac! as values.
     */
    public HashMap<Integer, Integer> getPrimeFactors() {
        
        generatePrimeFactorsMap();
        return m1; 
    }
    
    public int getNumber() {return fac;}

    
}
