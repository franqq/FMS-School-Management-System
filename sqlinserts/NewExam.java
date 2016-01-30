/*
 * this class should be used by the super admin or the academic department head
 */
package sqlinserts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author franq
 */
public class NewExam {
    
    private static DBConnection dbconn;
    
    //method to set the Exam table details
    public static void createNewExam(String name,String type, String startdate, int termid) throws SQLException
    {
        PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "INSERT INTO `exam`(`Exam Name`,`Exam Type`, `Start Date`, `termID`, `Year`)"
                + "VALUES(?,?,?,?,?)";
       
            stmt = conn.prepareStatement(insert);
            stmt.setString(1, name);
            stmt.setString(2, type);
            stmt.setString(3, startdate);
            stmt.setInt(4, termid);
            stmt.setString(5,selects.QueryMainDetails.getCurrentYear());
           
            stmt.executeUpdate();
        
        
 
            if (stmt != null) {
		stmt.close();
            }
 
            if (conn != null) {
		conn.close();
            }
 
		
    }
    
    
     public static void main(String args[]) {
          try {
              
              createNewExam("Jesma 34", "Opener", "2014-01-13",1);
              
          } catch (SQLException ex) {
              Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
}
