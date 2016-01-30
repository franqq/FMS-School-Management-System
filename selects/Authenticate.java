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
public class Authenticate {
    
    
    
    public static Boolean authenticateLogin(String id,String password,int level)
    {
            DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            String userpassword,hint;
            int userlevel=-6;
            Boolean exists = confirmUserExists(id);
            Boolean login =false;
            
            userpassword = null;
            
            hint = null;
            
            
            
          
                String Sql = "SELECT `password`, `hint`, `level` FROM `login` WHERE `nationalID`=?";
                try {            
                        pstmt = conn.prepareStatement(Sql);
                        pstmt.setString(1, id);
                        rs = pstmt.executeQuery();
                        while(rs.next())
                        {
                              userpassword = rs.getString("password");
                              userlevel = rs.getInt("level");
                              hint = rs.getString("hint");
                        }
                    } catch (SQLException ex) {
                    Logger.getLogger(QueryStudentDetails.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null,
                    "Some unexpected error occured\n"+
                    "Was not able to load User Details\n"+
                    "Contact the systems administrator for assistance","Warning",JOptionPane.ERROR_MESSAGE);
                    }
                //hash the password
                password = selects.QueryMainDetails.SHA256(password);
               
                //compare the details
                if(password.equals(userpassword) && level == userlevel)
                {
                    login = true;
                }
                else {
                    login = false;
                }
                
            
            return login;
    }
    
     public static Boolean confirmUserExists(String nationalid)
    {
        DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            String found = null;
            Boolean results;
            
            String Sql = "SELECT `nationalID` FROM `login` WHERE `nationalID`=?";
        try {            
            pstmt = conn.prepareStatement(Sql);
            pstmt.setString(1, nationalid);
            rs = pstmt.executeQuery();
            while(rs.next())
               found = rs.getString("nationalid");
            
        } catch (SQLException ex) {
            Logger.getLogger(QueryStudentDetails.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
                "Some unexpected error occured\n"+
                "Was not able to load User Details\n"+
                "Contact the systems administrator for assistance","Warning",JOptionPane.ERROR_MESSAGE);
        }
        if(found == null)
            results = false;
        else
        results = true;            
            
        return results;
                 
    }
     
      public static String getHashedPassword(String id)
    {
            DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            String userpassword;
            
            userpassword = null;
            
           
            
          
                String Sql = "SELECT `password` FROM `login` WHERE `nationalID`=?";
                try {            
                        pstmt = conn.prepareStatement(Sql);
                        pstmt.setString(1, id);
                        rs = pstmt.executeQuery();
                        if(rs.next())
                        {
                              userpassword = rs.getString("password");
                        }
                    } catch (SQLException ex) {
                    Logger.getLogger(QueryStudentDetails.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null,
                    "Some unexpected error occured\n"+
                    "Was not able to load User Details\n"+
                    "Contact the systems administrator for assistance","Warning",JOptionPane.ERROR_MESSAGE);
                    }
               
            
            return userpassword;
    }
      
}
