/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author franq
 */

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class FMS extends Thread{
    



    private final JFrame frame;
    public FMS(JFrame frm){
        this.frame=frm;
    }//constructor closed

    @Override
    public void run(){
        frame.setVisible(true);
    }//run method closed
    public static void main(String args[]){
        Runnable r1 = new Runnable() {

            @Override
            public void run() {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
               new main.Splash(3000);
            }
        };
        Runnable r2 = new Runnable() {

            @Override
            public void run() {
                try {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                   int l = selects.Query.getReg();
                   if(l==84)
                   {
                   main.Login login = new main.Login();
                   login.main(new String[9]);
                   }
                   else
                   {
                       int wannareg = JOptionPane.showConfirmDialog(null,
                                "Congratulations!! You Successfully installed Frigate Management System.\n"+
                                "Press Ok to Continue With The Setup and Configuation.","Welcome Message",JOptionPane.OK_CANCEL_OPTION);
                       if(wannareg == JOptionPane.OK_OPTION)
                       {
                           AfterInstallation.SetSchool setSchool = new AfterInstallation.SetSchool();
                           setSchool.main(new String[9]);
                           
                       }
                       else
                       {
                           System.exit(0);
                       }
                   }
                } catch (SQLException ex) {
                   JOptionPane.showMessageDialog(null,
                             "Connection To The Server Failed \n"+
                             "Please Restart The Server to continue","Error Message",JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        
        
       
        r1.run();
        r2.run();
        
    }//main method closed

}//class closed


