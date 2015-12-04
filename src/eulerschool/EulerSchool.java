/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eulerschool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author nbleier
 */
public class EulerSchool {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int a = 10;
        FactorialDigitSum test = new FactorialDigitSum(a);
        HashMap<Integer, Integer> m1 = test.getPrimeFactors();
        
        Set<Integer> s1 = m1.keySet();
        Integer[] a1 = new Integer[s1.size()];
        s1.toArray(a1);
        Arrays.sort(a1);

        
        System.out.println(a + "!'s prime factorization has: ");
        for (int i = 0; i < a1.length; i++ ) {
            System.out.println(a1[i] + " is a factor of a " + m1.get(a1[i]) + " times");
        }
        
    }
    
}
