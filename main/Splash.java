/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author franq
 */

import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;

public class Splash {
    

    private JLabel SplashImage;
    private JLabel SplashText;
    private JWindow window;
    private JPanel panel;
    public Splash(int duration) {
        window=new JWindow();               
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        window.setBounds((screen.width-881)/2,(screen.height-388)/2,892,458); 
        
        
        panel=(JPanel)window.getContentPane();
        SplashImage = new JLabel(new ImageIcon(ClassLoader.getSystemResource("graphics/splash.png")));
        SplashText=new JLabel("copyright 2013  www.ventilab.com",SwingConstants.CENTER);
        panel.add(SplashImage, BorderLayout.CENTER);
        panel.add(SplashText,BorderLayout.SOUTH);
        
        
        window.setVisible(true);
        try{
            Thread.sleep(duration);            
        }catch(Exception ex){            
        }//try catch closed
        window.setVisible(false);
        window.dispose();
    }//constructr closed

    public static void main(String[] args)
    {
        Splash splash = new Splash(5000);
    }
}//class closed



