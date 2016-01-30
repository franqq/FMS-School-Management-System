/*
 * This class should only be used by a super admin
 * contains inserts to the department and department related table
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
public class NewDepartment {
    
    private static DBConnection dbconn;
    
    
    public static void createDepartment(String name, String desc) throws SQLException
    {
         PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "INSERT INTO `department`(`Name`, `Description`)"
                + "VALUES(?,?)";
       
            stmt = conn.prepareStatement(insert);
            stmt.setString(1, name);
            stmt.setString(2, desc);
            
            
             stmt.executeUpdate();
       
        //close connection if still open
	if (stmt != null) {
		stmt.close();
        }
        if (conn != null) {
		conn.close();
	}
        
    }
    
    
    public static void setDepartmentHead(int staffid) throws SQLException
    {
         PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "INSERT INTO `department_head`(`departmentID`, `staffID`)"
                + "VALUES(?,?)";
    
            stmt = conn.prepareStatement(insert);
            stmt.setInt(1, getDepartmantID());
            stmt.setInt(2, staffid);
            
            
             stmt.executeUpdate();
        
     
 
	if (stmt != null) {
		stmt.close();
	}
 
	if (conn != null) {
		conn.close();
	}

    }
    
    
    private static int getDepartmantID() throws SQLException
    {
         PreparedStatement stmt = null;
        int contid = 0;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        
        
        String select = "SELECT  `ID` FROM  `department` ORDER BY ID DESC LIMIT 1";
        
            stmt = conn.prepareStatement(select);
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
 
				String userid = rs.getString("ID");
                                contid = Integer.parseInt(userid);
 
			}
            
 
			if (stmt != null) {
				stmt.close();
			}
 
			if (conn != null) {
				conn.close();
			}
 
		
        return contid;
    }
    
    
     public static void main(String args[]) {
          try {
              setDepartmentHead(1);
          } catch (SQLException ex) {
              Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
}
