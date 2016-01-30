/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlinserts;


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
public class InsertStaffPic extends javax.swing.JFrame {
    // Variables declaration    
     private static DBConnection dbconn;
   
    private javax.swing.JFileChooser jFileChooser;
    private boolean done;
    private static int caller;
    
    public InsertStaffPic(int verifycaller) throws SQLException, FileNotFoundException {
        caller = verifycaller;
        done = false;
        initComponents();
       
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() throws SQLException, FileNotFoundException {
        this.setIconImage(selects.QueryMainDetails.setIcon());
        if(caller ==-1)
        {
        while(done == false)
        {
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
        }
        else{
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
    }
   
    
    //method to add the staff passport photo
     //called from the insertStaffPic Class
     protected void addStaff_passport(File file) throws SQLException, FileNotFoundException
    {
        
        PreparedStatement stmt;
        int staffid;
        if(caller == -1)
        staffid = getStaffID();
        else
          staffid = caller;
                
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
            String insert = "INSERT INTO `staff_passport`(`staffID`, `passport`)"
                + "VALUES(?,?)";
            stmt = conn.prepareStatement(insert);
            stmt.setInt(1,staffid);
            stmt.setBinaryStream(2, (InputStream)in, (int)file.length());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Passport Details Successfully Set \n" +
                        "Press OK to continue", "Success ", JOptionPane.INFORMATION_MESSAGE);
            done = true;
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Passport was not uploaded Since it does not meet the requirements \n" +
                        "Please Set The Passport to a size 150 by 150 pixels before uploading", "Failed ", JOptionPane.INFORMATION_MESSAGE);
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
                        "Please Contact The System Administrator for Assistance", "Error "+ex.getErrorCode(), JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException ex) {
             Logger.getLogger(InsertStaffPic.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
     
   //gets the last id from the contact ID table to fill the new contact details
    //returns integer (largestvalue+1)
    private static int getStaffID() throws SQLException
    {
        PreparedStatement stmt = null;
        int contid = 0;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        
        
        String select = "SELECT  `ID` FROM  `staff` ORDER BY ID DESC LIMIT 1";
        try {
            stmt = conn.prepareStatement(select);
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
 
				String userid = rs.getString("ID");
                                contid = Integer.parseInt(userid);
 
			}
            
            
        } catch (SQLException ex) {
            Logger.getLogger(NewStaff.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Unexpected Error Occured \n" +
                        "Please Contact the System Administrator", "Error "+ex.getErrorCode(), JOptionPane.INFORMATION_MESSAGE);
        }
        finally {
 
			if (stmt != null) {
				stmt.close();
			}
 
			if (conn != null) {
				conn.close();
			}
 
		}
        return contid;
    }
    
    
}
