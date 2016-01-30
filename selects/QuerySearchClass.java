/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package selects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author franq
 */
public class QuerySearchClass {
    
    
     public static int getClassCapacity(String name,String level)
    {
        DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            int capacity =0;
            
            String Sql = "SELECT `Capacity` FROM `class` WHERE `Class`=? AND `Level`=?";
        try {            
            pstmt = conn.prepareStatement(Sql);
            pstmt.setString(1, name);
            pstmt.setString(2, level);
            rs = pstmt.executeQuery();
            if(rs.next())
            {
            capacity = rs.getInt("Capacity");
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuerySearchClass.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
                "Some unexpected error occured\n"+
                "Was not able to load Staff Names details\n"+
                "Please contact the systems administrator for assistance","Warning",JOptionPane.ERROR_MESSAGE);
        }
           
       
       return capacity;
    }
     
         public static ArrayList<String> getClassDetails(int id)
    {
        DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            ArrayList<String> details = new ArrayList<String>();
            
            String Sql = "SELECT `Class`, `Level`, `Capacity` FROM `class` WHERE `ID`=?";
        try {            
            pstmt = conn.prepareStatement(Sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            
            if(rs.next())
            {
            details.add(rs.getString("Class")); 
            details.add(rs.getString("Level")); 
            details.add(rs.getString("Capacity")); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuerySearchClass.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
                "Some unexpected error occured\n"+
                "Was not able to load Staff Names details\n"+
                "Please contact the systems administrator for assistance","Warning",JOptionPane.ERROR_MESSAGE);
        }
           
       
       return details;
    }
         
          public static ArrayList<String> getClassTeacherDetails(int id)
    {
        DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            ArrayList<String> details = new ArrayList<String>();
            
            String Sql = "SELECT  `teachersID`, `Post`, `Date Started` FROM `class_teacher` WHERE `ID`=?";
        try {            
            pstmt = conn.prepareStatement(Sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            
            if(rs.next())
            {
            details.add(rs.getString("teachersID")); 
            details.add(rs.getString("Post")); 
            details.add(rs.getString("Date Started")); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuerySearchClass.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
                "Some unexpected error occured\n"+
                "Was not able to load Staff Names details\n"+
                "Please contact the systems administrator for assistance","Warning",JOptionPane.ERROR_MESSAGE);
        }
           
       
       return details;
    }
}
