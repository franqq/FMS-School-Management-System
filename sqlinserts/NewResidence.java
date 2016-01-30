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

/**
 *
 * @author franq
 */
public class NewResidence {
    private static DBConnection dbconn;
    
    public static void createNewResidence(int termid, String name, int capacity, String gender, double fee) throws SQLException
    {
         PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "INSERT INTO `residence`(`termID`, `Name`, `Capacity`, `Gender`, `Fee`)"
                + "VALUES(?,?,?,?,?)";
        
            stmt = conn.prepareStatement(insert);
            stmt.setInt(1, termid);
            stmt.setString(2, name);
            stmt.setInt(3, capacity);
            stmt.setString(4,gender);
            stmt.setDouble(5,fee);
           
            
             stmt.executeUpdate();
        
 
            if (stmt != null) {
		stmt.close();
            }
 
            if (conn != null) {
		conn.close();
            }
 
		
    }
    
    //sets the residence master
    //setResidenceMaster(1, "House Master");
    public static void setResidenceMaster(int residenceid,int staffid, String post) throws SQLException
    {
         PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "INSERT INTO `residence_master`(`residenceID`,`staffID`, `Post`, `Date Started`)"
                + "VALUES(?,?,?,?)";
            stmt = conn.prepareStatement(insert);
            stmt.setInt(1, residenceid);
            stmt.setInt(2, staffid);
            stmt.setString(3,post);
            stmt.setDate(4,getCurrentTimeStamp());
            
            
           
            
             stmt.executeUpdate();
       
 
            if (stmt != null) {
		stmt.close();
		}
 
            if (conn != null) {
		conn.close();
		}
 
		
    }
    
    
    
    
    //sets the residence monitor
    public static void setResidenceMonitor(int residenceid, int studentid, String post) throws SQLException
    {
         PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "INSERT INTO `residence_prefect`(`residenceID`, `studentID`, `Post`, `Date Started`)"
                + "VALUES(?,?,?,?)";
        
            stmt = conn.prepareStatement(insert);
            stmt.setInt(1, residenceid);
            stmt.setInt(2, studentid);
            stmt.setString(3,post);
            stmt.setDate(4,getCurrentTimeStamp());
            
            
           
            
             stmt.executeUpdate();
       
         
 
            if (stmt != null) {
                stmt.close();
            }
 
            if (conn != null) {
            conn.close();
	    }
 
       }
    
    
    
    private static int getResidenceID() throws SQLException
    {
         PreparedStatement stmt = null;
        int contid = 0;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        
        
        String select = "SELECT  `ID` FROM  `residence` ORDER BY ID DESC LIMIT 1";
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
    
    //method to get todays date
      private static java.sql.Date getCurrentTimeStamp() {
 
		java.util.Date today = new java.util.Date();
		return new java.sql.Date(today.getTime());
 
	}
    
    
   public static void main(String args[]) {
          /*try {
              setResidenceMonitor(2, "House Captain");
              //createNewResidence("Gutu House", 350, "male", 3500);
          } catch (SQLException ex) {
              Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
          }*/
      }
}
