/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javax.swing.JFrame;

/**
 *
 * @author franq
 */
public class LogOut {
    public static void LogOut(JFrame jframe)
    {
        //logout
        main.Login.main(new String[9]);
        jframe.dispose();
    }
}
