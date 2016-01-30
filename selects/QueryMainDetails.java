/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package selects;

import clerk.Clerk;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;


/**
 *
 * @author franq
 */
public class QueryMainDetails {
     private static String getSchoolName(int id)
    {
        DBConnection dbconn =new DBConnection();
        Connection conn = dbconn.vconnection;
        PreparedStatement pstmt;
        String schoolname= "";
        String sql = "SELECT `Name` FROM `school_details` WHERE `ID` = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            schoolname = rs.getString("Name");
            
        
        } catch (SQLException ex) {
            Logger.getLogger(QueryMainDetails.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(null, "Some unexpected error occured \nSchool Name Details not found\n" +
                        "Please contact the system administrator", "Error "+ex.getErrorCode(), JOptionPane.WARNING_MESSAGE);
        }
        return schoolname;
    }
     
     private String getUserSurname(int userId)
    {
        DBConnection dbconn =new DBConnection();
        Connection conn = dbconn.vconnection;
        PreparedStatement pstmt;
        String userTitl= "";
        String sql = "SELECT `Title`, `Surname`, `First Name` FROM `staff` WHERE `ID` = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            userTitl = rs.getString("Title")+" "+rs.getString("Surname")+" "+rs.getString("First Name");
            
            
        
        } catch (SQLException ex) {
            Logger.getLogger(QueryMainDetails.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(null, "Some unexpected error occured \n User Details not found\n" +
                        "Please contact the system administrator", "Error "+ex.getErrorCode(), JOptionPane.INFORMATION_MESSAGE);
        }
        return userTitl;
    }
     public static BufferedImage setIcon()
    {
        DBConnection dbconn =new DBConnection();
        Connection conn = dbconn.vconnection;
        PreparedStatement pstmt;
        String sql = "SELECT `icon` FROM `icon` WHERE 1";
        
        BufferedImage bufImg = null;
        try {
            pstmt = conn.prepareStatement(sql);
            //should get the id of the current user and give it to 1
            ResultSet rs = pstmt.executeQuery();
            
            rs.next();
            InputStream in = rs.getBinaryStream("icon"); 
             bufImg = ImageIO.read(in); 
        } catch (SQLException ex) {
            Logger.getLogger(Clerk.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Clerk.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return bufImg;
    }
     
     public static String getCurrentYear() {
 
		GregorianCalendar calendar = new GregorianCalendar();
                return Integer.toString(calendar.get(Calendar.YEAR));

	}
     
     //gets todays sql date
      public static String getCurrentDate() {
 
		//GregorianCalendar calendar = new GregorianCalendar();
                //java.sql.Date sqldate = new java.sql.Date(calendar.YEAR,calendar.MONTH,calendar.DATE);
                //return sqldate.toString();
                
                java.util.Date today2 = new java.util.Date();
                java.sql.Date sqlToday = new java.sql.Date(today2.getTime());
         
                return sqlToday.toString();

	}
      
      public static String SHA256(String base) {
    try{
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(base.getBytes("UTF-8"));
        StringBuffer hexString = new StringBuffer();

        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }

        return hexString.toString();
    } catch(Exception ex){
       throw new RuntimeException(ex);
    }
}
      
}
