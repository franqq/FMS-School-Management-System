/*
 *class to be used by a department head
 * contains inserts to the subject and subject related tables
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
public class NewSubject {
    private static DBConnection dbconn;
    
    
    public static void createNewSubject(String name, int deptid, String desc) throws SQLException
    {
        PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "INSERT INTO `subject`(`Subject`, `departmentID`, `description`)"
                + "VALUES(?,?,?)";
        
            stmt = conn.prepareStatement(insert);
            stmt.setString(1, name);
            stmt.setInt(2, deptid);
            stmt.setString(3,desc);
            
            
             stmt.executeUpdate();
        
 
            if (stmt != null) {
		stmt.close();
		}
 
            if (conn != null) {
		conn.close();
		}
 
		
    }
    
    
    //method to set the specific subject grade details
    public static void setSubjectGrade(double low, double high,String grade,String remarks) throws SQLException
    {
         PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "INSERT INTO `subject_grade`(`Low`, `High`, `Grade`, `subjectID`, `Remarks`)"
                + "VALUES(?,?,?,?,?)";
        try {
            stmt = conn.prepareStatement(insert);
            stmt.setDouble(1, low);
            stmt.setDouble(2, high);
            stmt.setString(3,grade);
            stmt.setInt(4,getSubjectID());
            stmt.setString(5,remarks);
            
            
             stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(NewDepartment.class.getName()).log(Level.SEVERE, null, ex);
            
             if(ex.getErrorCode() == 1062)
            {
                JOptionPane.showMessageDialog(null, "The Grade for this subject has already been set. \n" +
                        "Press Ok to continue", "Error "+ex.getErrorCode(), JOptionPane.INFORMATION_MESSAGE);
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
    
    public static void setsubjectTeacher(int subjectid,int staffid,int classid) throws SQLException
    {
        PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "INSERT INTO `subject_teacher_class` (`subjectID`, `staffID`, `classID`)"
                + "VALUES(?,?,?)";
        
            stmt = conn.prepareStatement(insert);
            stmt.setInt(1,subjectid);
            stmt.setInt(2,staffid);
            stmt.setInt(3, classid);
            
            
             stmt.executeUpdate();
       
			if (stmt != null) {
				stmt.close();
			}
 
			if (conn != null) {
				conn.close();
			}
 
		
    }
    
    //private method to get the subjectID
    private static int getSubjectID() throws SQLException
    {
         PreparedStatement stmt = null;
        int contid = 0;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        
        
        String select = "SELECT  `ID` FROM  `subject` ORDER BY ID DESC LIMIT 1";
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
    
     public static int getSubjectIDwithDeptID(int deptid) throws SQLException
    {
         PreparedStatement stmt = null;
        int contid = 0;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        
        
        String select = "SELECT `ID` FROM `subject` WHERE `departmentID`=?";
        try {
            stmt = conn.prepareStatement(select);
            stmt.setInt(1, deptid);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
 
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
    
    
}
