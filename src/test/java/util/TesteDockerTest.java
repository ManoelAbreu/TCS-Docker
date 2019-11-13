/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import junit.framework.TestCase;

/**
 *
 * @author brunoz
 */
public class TesteDockerTest extends TestCase {
   
    private TesteDocker t;
    
    public TesteDockerTest(String testName) {
        super(testName);
    }

    public TesteDockerTest() {
        t = new TesteDocker();
    }
    
    


    public void testSomarDoisNumeros() {
       int num1  = 5;
       int num2  = 10;
       
       t.somarDoisNumeros(num1, num2);
        assertEquals(t.somarDoisNumeros(num1, num2), 15);
       
    }

    public void testMultiplicarDoisNumeros() {
          
       int num1  = 5;
       int num2  = 10;
       
       t.somarDoisNumeros(num1, num2);
        assertEquals(t.somarDoisNumeros(num1, num2), 50);
    }
    
}
