/*
 * This class should only be used by a superadmin
 * contains inserts to the term and term related tables
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
public class NewTerm {
     private static DBConnection dbconn;
     
     
     
     //method to create term
    public static void createnewTerm(String name, String starts, String ends,String year, String remarks) throws SQLException
     {
         PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "INSERT INTO `term`(`Name`,`Start Date`, `Close Date`, `Year`, `Remarks`)"
                + "VALUES(?,?,?,?,?)";
       
            stmt = conn.prepareStatement(insert);
            stmt.setString(1, name);
            stmt.setString(2, starts);
            stmt.setString(3,ends);
            stmt.setString(4,year);
            stmt.setString(5,remarks);
            
             stmt.executeUpdate();
             
           
             //close connection
            if (stmt != null) {
		stmt.close();
		}
            if (conn != null) {
		conn.close();
        	}
 
     }
     
      public static void main(String args[]) {
          try {
              createnewTerm("1st Term", "2014-01-09", "2014-04-09","2014", "The first term of the year");
          } catch (SQLException ex) {
              Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
}
