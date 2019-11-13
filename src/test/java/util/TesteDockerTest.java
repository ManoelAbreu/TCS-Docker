/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import junit.framework.Test;
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

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        t = new TesteDocker();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testSomarDoisNumeros() {
        int num1 = 5;
        int num2 = 10;

        int soma = t.somarDoisNumeros(num1, num2);
        assertEquals(soma, (num1 + num2));
    }

    public void testMultiplicarDoisNumeros() {
        int num1 = 5;
        int num2 = 10;
        
        int mult = t.multiplicarDoisNumeros(num1, num2);
        assertEquals(mult, (num1 * num2));
    }

}
