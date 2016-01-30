/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package selects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author franq
 */
public class QuerySupportStaffDetails {
    
public static int getLastContact()
{
            DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            int lastcontid=0;
           
            String Sql = "SELECT  `ID` \n" +
                         "FROM  `support_staff_contact` \n" +
                         "WHERE 1 \n" +
                         "ORDER BY  `ID` DESC ";
        try {            
            pstmt = conn.prepareStatement(Sql);
             rs = pstmt.executeQuery();
             rs.next();
             lastcontid = rs.getInt("ID");
        } catch (SQLException ex) {
            Logger.getLogger(QueryStudentDetails.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
                "Some unexpected error occured\n"+
                "Was not able to load class name details\n"+
                "Please contact the systems administrator for assistance","Warning",JOptionPane.ERROR_MESSAGE);
        }
                     
       return lastcontid;
    
}

public static int getCurrentContactID()
{
     DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            int lastcontid=0;
           
            String Sql = "SELECT  `ContactID` \n" +
                         "FROM  `support_staff` \n" +
                         "WHERE 1 \n" +
                         "ORDER BY  `ID` DESC";
        try {            
            pstmt = conn.prepareStatement(Sql);
             rs = pstmt.executeQuery();
             rs.next();
             lastcontid = rs.getInt("ContactID");
        } catch (SQLException ex) {
            Logger.getLogger(QueryStudentDetails.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
                "Some unexpected error occured\n"+
                "Was not able to load class name details\n"+
                "Please contact the systems administrator for assistance","Warning",JOptionPane.ERROR_MESSAGE);
        }
                     
       return lastcontid;
}
}
