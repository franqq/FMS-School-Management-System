/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AfterInstallation;


import sqlinserts.*;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 *
 * @author franq
 */
public class InsertSchoolLogo extends javax.swing.JFrame {
    // Variables declaration    
     private static DBConnection dbconn;
   
    private javax.swing.JFileChooser jFileChooser;
    private boolean done;
    private static int caller;
    
    public InsertSchoolLogo() throws SQLException, FileNotFoundException {
                done = false;
                initComponents();
       
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() throws SQLException, FileNotFoundException {
        this.setIconImage(selects.QueryMainDetails.setIcon());
        {
        while(done == false)
        {
        File file;
        jFileChooser = new JFileChooser();
        jFileChooser.setDialogTitle("Choose Logo");
        jFileChooser.setPreferredSize(new Dimension(500, 350));
        
        //filters to ensure only image files are shown
        jFileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        jFileChooser.setFileSelectionMode(jFileChooser.FILES_ONLY);
        jFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bmp"));
        jFileChooser.setAcceptAllFileFilterUsed(false);
        
         int returnCode = jFileChooser.showOpenDialog(this);
        if (returnCode == JFileChooser.APPROVE_OPTION) {
               file = jFileChooser.getSelectedFile();
               
               //Insert image into database for the selected ID
               addLogo(file); 
            }
    
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        }
        
        }
    }
   
    
    //method to add the staff passport photo
     //called from the insertStaffPic Class
     protected void addLogo(File file) throws SQLException, FileNotFoundException
    {
        
        PreparedStatement stmt;
                        
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        
        try {
            FileInputStream in = new FileInputStream(file);
           
            //check the file size
            BufferedImage buff = ImageIO.read(file);
            int height = buff.getHeight();
            int width = buff.getWidth();
            
            if(height == 300 && width == 300)
            {
            String insert = "INSERT INTO `school_logo`(`logo`)"
                + "VALUES(?)";
            stmt = conn.prepareStatement(insert);
            stmt.setBinaryStream(1, (InputStream)in, (int)file.length());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "SchoolSuccessfully Set \n" +
                        "Press OK to continue", "Success ", JOptionPane.INFORMATION_MESSAGE);
            done = true;
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Logo was not uploaded Since it does not meet the requirements \n" +
                        "Please Set The Logo to a size 300 by 300 pixels before uploading", "Failed ", JOptionPane.INFORMATION_MESSAGE);
                done = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewStaff.class.getName()).log(Level.SEVERE, null, ex);
            
            if(ex.getErrorCode()==1062)
            {
            JOptionPane.showMessageDialog(null, "School Logo already exists. \n" +
                        "", "Error "+ex.getErrorCode(), JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {
            JOptionPane.showMessageDialog(null, "School Logo Was not Inserted \n" +
                        "Please Contact The System Administrator for Assistance", "Error "+ex.getErrorCode(), JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException ex) {
             Logger.getLogger(InsertStaffPic.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
     
   //gets the last id from the contact ID table to fill the new contact details
    //returns integer (largestvalue+1)
   
    
}
