package Clases;


import static java.lang.Thread.sleep;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;

/**
 *
 * @author 
 */
public class relojLog extends Thread{
 private  JLabel label;
    

    public relojLog(JLabel label) {
           this.label = label;
             
    } 

    
public  void run(){
    while (true) {        
        Date  fecha = new Date();
        SimpleDateFormat sd= new SimpleDateFormat("HH:mm:ss a");
        label.setText(sd.format(fecha));
      
        try {
            sleep(1000);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
}