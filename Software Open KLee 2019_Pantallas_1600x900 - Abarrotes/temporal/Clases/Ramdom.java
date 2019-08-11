/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.math.BigInteger;
import java.util.Random;

/**
 *
 * @author RAUL
 */
public class Ramdom {

    /**
     * @param args the command line arguments
     */
//        Random rnd = new Random();           
//        int numaleat = rnd.nextInt(900000000)+99999999;
//        System.out.println("" + numaleat);
    public static BigInteger prueba(BigInteger max) {
        Random rnd = new Random();
        do {
            BigInteger i = new BigInteger(max.bitLength(), rnd);
            if (i.compareTo(max) <= 0) {
                return i;
            }
        } while (true);
    }

    public static void main(String... args) {
        System.out.println(prueba(new BigInteger("9999999999999")));
    }
}

/*Random rnd = new Random();
 for(int i = 1; i<=1; i++)
 System.out.println(rnd.nextInt(1000000000-1+1)+1);*/
/* int num1=0;
 int num2 = 0;
        
 System.out.println("Números generados entre 50 y 120, sin decimales (sin incluir el 50 y el 120)");
 for (int i = 0; i < 1; i++) {
 int numAleatorio = (int) Math.floor(Math.random() * (50 - 999999999) + 50);
 System.out.println(numAleatorio);*/
