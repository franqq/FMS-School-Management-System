/*
 * This Class Should only be used by a super admin
 * contains inserts to the class and class related tables
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
public class NewClass {
    
      private static DBConnection dbconn;
      
      
      //method to enter new class details
      public static void createClass(String name, String level, String capacity) throws SQLException
      {
          PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "INSERT INTO `class`(`Class`, `Level`, `Capacity`)"
                + "VALUES(?,?,?)";
          
              stmt = conn.prepareStatement(insert);
              
              stmt.setString(1, name);
              stmt.setString(2, level);
              stmt.setString(3, capacity);
              
              stmt.executeUpdate();
              
           
              
          
         
 
			if (stmt != null) {
				stmt.close();
			}
 
			if (conn != null) {
				conn.close();
			}
 
		
        
        
      }
      
      
      //method to enter new class teacher details
      //newClassTeacher(1,1,"Assistant Class Teacher");
      
      public static void newClassTeacher(int classid, int teachersid,String post) throws SQLException
      {
        PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "INSERT INTO `class_teacher`(`classID`, `teachersID`, `Post`,`Date Started`)"
                + "VALUES(?,?,?,?)";
          try {
              stmt = conn.prepareStatement(insert);
              
              stmt.setInt(1, classid);
              stmt.setInt(2, teachersid);
              stmt.setString(3, post);
              stmt.setString(4, selects.QueryMainDetails.getCurrentDate());
              
              stmt.executeUpdate();
              
          } catch (SQLException ex) {
              Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
              
              if(ex.getErrorCode()==1062)
            {
            JOptionPane.showMessageDialog(null, "Class Teacher Details have already been set. \n" +
                        "Click update to change", "Error "+ex.getErrorCode(), JOptionPane.INFORMATION_MESSAGE);
            }
          }
          finally {
 
			if (stmt != null) {
				stmt.close();
			}
 
			if (conn != null) {
				conn.close();
			}
 
		}
        
        
      }
    
      
         //method to enter new class monitor details.. This method might not be called but its important
      public static void newClassMonitor(int classid, int studentid, String post) throws SQLException
      {
        PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "INSERT INTO `class_monitor`(`classID`, `studentID`, `Post`, `Date Started`)"
                + "VALUES(?,?,?,?)";
          try {
              stmt = conn.prepareStatement(insert);
              
              stmt.setInt(1, classid);
              stmt.setInt(2, studentid);
              stmt.setString(3, post);
              stmt.setDate(4, getCurrentTimeStamp());
              
              stmt.executeUpdate();
              
          } catch (SQLException ex) {
              Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
              
            if(ex.getErrorCode()==1062)
            {
            JOptionPane.showMessageDialog(null, "Class Monitor Details have already been set. \n" +
                        "Click update to change", "Error "+ex.getErrorCode(), JOptionPane.INFORMATION_MESSAGE);
            }
            else if(ex.getErrorCode()==1452)
            {
                JOptionPane.showMessageDialog(null, "Seems this student does not exist. \n" +
                        "Please check your entry and try again", "Error "+ex.getErrorCode(), JOptionPane.INFORMATION_MESSAGE);
            }
          }
          finally {
 
			if (stmt != null) {
				stmt.close();
			}
 
			if (conn != null) {
				conn.close();
			}
 
		}
        
        
      }
    
      
      public static void main(String args[]) {
          try {
              newClassMonitor(1,2,"Assistant Monitor");
          } catch (SQLException ex) {
              Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
      
     
      
      
      //method to get todays date
      private static java.sql.Date getCurrentTimeStamp() {
 
		java.util.Date today = new java.util.Date();
		return new java.sql.Date(today.getTime());
 
	}
      
}
