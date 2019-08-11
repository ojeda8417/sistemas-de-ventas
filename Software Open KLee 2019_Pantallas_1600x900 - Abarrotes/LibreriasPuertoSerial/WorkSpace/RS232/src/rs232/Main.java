/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rs232;

import app.Com;
import app.Parameters;

/**
 *
 * @author giovynet
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
     		Parameters param = new Parameters();
		param.setPort("COM1");
		Com com = new Com(param);
		for (int i = 0; i < 10; i++) {
			Thread.sleep(800);
			System.out.println("Send 0XFF");
			com.sendSingleData(0xFF);
		}
    }
}
