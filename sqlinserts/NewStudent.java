/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlinserts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;




//another class to call the jfile chooser to set the passport size photo if required



/**
 *
 * @author franq
 */
public class NewStudent {
     private static DBConnection dbconn;
     
     
     /*
      * method to set the student contact details
      */
     public static int setStudentContacts(String mobile, String email, String physical, String town) throws SQLException
     {
         PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "INSERT INTO `student_contact`(`Mobile Number`, `Email`, `Physical Address`, `Town`)"
                
                + "VALUES(?,?,?,?)";
        try {
            stmt = conn.prepareStatement(insert);
            stmt.setString(1,mobile);
            stmt.setString(2, email);
            stmt.setString(3, physical);
            stmt.setString(4, town);
           
            
             stmt.executeUpdate();
             return 1;
        } catch (SQLException ex) {
            Logger.getLogger(NewDepartment.class.getName()).log(Level.SEVERE, null, ex);
            
             if(ex.getErrorCode() == 1062)
            {
                JOptionPane.showMessageDialog(null, "Student Contact details have already been saved. \n" +
                        "Press Ok to continue", "Error "+ex.getErrorCode(), JOptionPane.WARNING_MESSAGE);
            }
             return 0;
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
     
     
     
     
     /*
      * method to update the studant details table
      * can be called by the clerk
      * createNewStudent("Mbui", "Francis" ,"Githae", "Male", "2013-12-26", 1, 1, 1, 1, 1);
      */
     public static void createNewStudent(String surname, String firstname, String lastname, String gender, String dob,
             int classid, int residenceid, int mealsid, int transportid, int registeredby) throws SQLException
     {
         PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "INSERT INTO `student`(`Year`, `surname`, `First Name`, `Last Name`, `Gender`,"+
                " `DOB`,`student_contactID`, `classID`, `residenceID`, `mealsID`, `transportID`, `registeredBy`)"
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
       
            stmt = conn.prepareStatement(insert);
            stmt.setString(1, selects.QueryMainDetails.getCurrentYear());
            stmt.setString(2, surname);
            stmt.setString(3, firstname);
            stmt.setString(4, lastname);
            stmt.setString(5, gender);
            stmt.setString(6, dob);
            stmt.setInt(7, getNewStudentContactID());
            stmt.setInt(8,classid);
            stmt.setInt(9,residenceid);
            stmt.setInt(10,mealsid);
            stmt.setInt(11,transportid);
            stmt.setInt(12,registeredby);
            
            stmt.executeUpdate();
        
			if (stmt != null) {
				stmt.close();
			}
 
			if (conn != null) {
				conn.close();
			}
 
		
     }
     
     
    //method to add student passportpic
     protected static void addStudentPic(File file) throws FileNotFoundException, SQLException
     {
         PreparedStatement stmt;
        int stid;
        stid = getStudentID();
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        
         String insert = "INSERT INTO `student_passport`(`studentID`, `passport Photo`)"
                + "VALUES(?,?)";
        try {
            FileInputStream in = new FileInputStream(file);
            stmt = conn.prepareStatement(insert);
            stmt.setInt(1,stid);
            stmt.setBinaryStream(2, (InputStream)in, (int)file.length());
            
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(NewStudent.class.getName()).log(Level.SEVERE, null, ex);
            
            if(ex.getErrorCode()==1062)
            {
            JOptionPane.showMessageDialog(null, "Student passport photo already exists. \n" +
                        "Press Update to upload new image", "Error "+ex.getErrorCode(), JOptionPane.INFORMATION_MESSAGE);
            }
        }
     }
     
     private static int getNewStudentContactID() throws SQLException
     {
         PreparedStatement stmt = null;
        int contid = 0;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        
        
        String select = "SELECT  `ID` FROM  `student_contact` ORDER BY ID DESC LIMIT 1";
        try {
            stmt = conn.prepareStatement(select);
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
 
				String userid = rs.getString("ID");
                                contid = Integer.parseInt(userid);
 
			}
            
            
        } catch (SQLException ex) {
            Logger.getLogger(NewStudent
                    .class.getName()).log(Level.SEVERE, null, ex);
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
     
     
     
     private static int getStudentID() throws SQLException
     {
         PreparedStatement stmt = null;
        int contid = 0;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        
        
        String select = "SELECT  `ID` FROM  `student_contact` ORDER BY ID DESC LIMIT 1";
        try {
            stmt = conn.prepareStatement(select);
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
 
				String userid = rs.getString("ID");
                                contid = Integer.parseInt(userid);
 
			}
            
            
        } catch (SQLException ex) {
            Logger.getLogger(NewStudent.class.getName()).log(Level.SEVERE, null, ex);
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
      
     
     
     
     
     public static void main(String args[]) {
          try {
            createNewStudent("Mbui", "Francis" ,"Githae", "Male", "2013-12-26", 1, 1, 1, 1, 1);
          } catch (SQLException ex) {
              Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
          }
      }

   //method to get todays date
      private static String getCurrentYear() {
 
		java.util.Date today = new java.util.Date();
		String yr = new java.sql.Date(today.getYear()).toString();
                return yr;
	}

   
}
