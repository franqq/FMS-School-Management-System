/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlupdates;


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
public class UpdateStaffPic extends javax.swing.JFrame {
    // Variables declaration    
     private static DBConnection dbconn;
   
    private javax.swing.JFileChooser jFileChooser;
    private boolean done;
    private static int staffid;
    
    public UpdateStaffPic(int staffidno) throws SQLException, FileNotFoundException {
        staffid = staffidno;
        done = false;
        initComponents();
       
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() throws SQLException, FileNotFoundException {
        this.setIconImage(selects.QueryMainDetails.setIcon());
        
        File file;
        jFileChooser = new JFileChooser();
        jFileChooser.setDialogTitle("Choose Passport Picture");
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
               addStaff_passport(file); 
            }
    
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        
    }
   
    
    //method to add the staff passport photo
     //called from the insertStaffPic Class
     protected void addStaff_passport(File file) throws SQLException, FileNotFoundException
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
            
            if(height == 150 && width == 150)
            {
            String insert = "UPDATE `staff_passport` SET `passport`=? WHERE `staffID`=?";
            stmt = conn.prepareStatement(insert);
            stmt.setBinaryStream(1, (InputStream)in, (int)file.length());
            stmt.setInt(2,staffid);
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Passport Details Successfully Updated \n" +
                        "Press OK to continue", "Success ", JOptionPane.INFORMATION_MESSAGE);
            done = true;
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Passport was not uploaded Since it does not meet the requirements \n" +
                        "Please Set The Passport to a size 150 by 150 pixels Before uploading", "Failed ", JOptionPane.INFORMATION_MESSAGE);
                done = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewStaff.class.getName()).log(Level.SEVERE, null, ex);
            
            if(ex.getErrorCode()==1062)
            {
            JOptionPane.showMessageDialog(null, "Staff passport photo already exists. \n" +
                        "Press Update to upload new image", "Error "+ex.getErrorCode(), JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {
            JOptionPane.showMessageDialog(null, "Staff passport photo Was not Inserted \n" +
                        "Please Contact The System Administrator for Assistance", "Unknown Error "+ex.getErrorCode(), JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException ex) {
             Logger.getLogger(InsertStaffPic.class.getName()).log(Level.SEVERE, null, ex);
         }
    } 
    
}
