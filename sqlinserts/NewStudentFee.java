/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlinserts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import selects.QueryStudentDetails;

/**
 *
 * @author franq
 */
public class NewStudentFee {
     private static DBConnection dbconn;
      
      public static void createBorderFee(double total) throws SQLException
      {
           PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "INSERT INTO `fee_details_borders`(`studentID`, `Total`) VALUES (?,?)";
       
            stmt = conn.prepareStatement(insert);
            stmt.setLong(1, getLastStudentID());
            stmt.setDouble(2, total);
            stmt.executeUpdate();
        
            if (stmt != null) {
		stmt.close();
            }
 
            if (conn != null) {
		conn.close();
            }
 
		
      }
      
      public static void createDayScholarFee(double total) throws SQLException
      {
           PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "INSERT INTO `fee_details_non_borders`(`studentID`, `Total`) VALUES (?,?)";
       
            stmt = conn.prepareStatement(insert);
            stmt.setLong(1, getLastStudentID());
            stmt.setDouble(2, total);
            stmt.executeUpdate();
        
            if (stmt != null) {
		stmt.close();
            }
 
            if (conn != null) {
		conn.close();
            }
 
		
      }
      
      private static long getLastStudentID()
      {
          DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            Long lastcontid=null;
           
            String Sql = "SELECT `ID` FROM `student` WHERE 1 ORDER BY `ID` DESC LIMIT 1;";
        try {            
            pstmt = conn.prepareStatement(Sql);
             rs = pstmt.executeQuery();
             rs.next();
             lastcontid = rs.getLong("ID");
        } catch (SQLException ex) {
            Logger.getLogger(QueryStudentDetails.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
                "Some unexpected error occured\n"+
                "Was not able to load Student details\n"+
                "Please contact the systems administrator for assistance","Warning",JOptionPane.ERROR_MESSAGE);
        }
                     
       return lastcontid;
      }
}
