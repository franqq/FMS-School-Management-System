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
public class QuerySearchStaff {
    public static ArrayList getStudentInfo(String nationalid)
    {
            DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            ArrayList srddetails = new ArrayList<String>();
            String Sql = "SELECT `Gender`,`DOB`,`nationalID`, `Title`, `Surname`, `First Name`,"
                    + " `Last Name`, `Job Title`, `Role`, `Whether Working`,`ID`,`ContactID` FROM `staff` WHERE `nationalID`=?";
          try {            
            pstmt = conn.prepareStatement(Sql);
            pstmt.setString(1, nationalid);
             rs = pstmt.executeQuery();
             
             
             //some code to check whether the result set is empty
             //not working
             if(rs.first() == true)
             {
                 rs.previous();
                  while(rs.next())
                  {
                   //get all the staff details
                   
                  srddetails.add(rs.getString("Gender"));
                  srddetails.add(rs.getString("DOB"));
                  srddetails.add(rs.getString("nationalID"));
                  srddetails.add(rs.getString("Title"));
                  srddetails.add(rs.getString("Surname"));
                  srddetails.add(rs.getString("First Name"));
                  srddetails.add(rs.getString("Last Name"));
                  srddetails.add(rs.getString("Job Title"));
                  srddetails.add(rs.getString("Role"));
                  srddetails.add(rs.getString("Whether Working"));
                  
                  //add student id
                  srddetails.add(rs.getString("ID"));
                  
                  //add the contact id
                  srddetails.add(rs.getString("ContactID"));
                  
                  }
                   }
             else{
                       // catch student not found error
                       JOptionPane.showMessageDialog(null,
                      "The Staff Details Were not Found\n"+
                      "Please Try Searching With A Different Criteria","Warning",JOptionPane.ERROR_MESSAGE);
                   }
            } catch (SQLException ex) {
            Logger.getLogger(QueryStudentDetails.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
                "Some unexpected error occured\n"+
                "Was not able to load class name details\n"+
                "Please contact the systems administrator for assistance","Warning",JOptionPane.ERROR_MESSAGE);
        }
    
           
            
       return srddetails;
    } 
         public static ArrayList getStudentInfo(String surname,String fname, String lname)
    {
            DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            ArrayList srddetails = new ArrayList<String>();
            String Sql = "SELECT `Gender`,`DOB`,`nationalID`, `Title`, `Surname`, `First Name`,"
                    + " `Last Name`, `Job Title`, `Role`, `Whether Working`,`ID` FROM `staff` WHERE `Surname`=? AND `First Name`=? AND `Last Name`=?";
          try {            
            pstmt = conn.prepareStatement(Sql);
            pstmt.setString(1, surname);
            pstmt.setString(2, fname);
            pstmt.setString(3, lname);
             rs = pstmt.executeQuery();
             
             
             //some code to check whether the result set is empty
             //not working
             if(rs.first() == true)
             {
                 rs.previous();
                  while(rs.next())
                  {
                   //get all the staff details
                   
                  srddetails.add(rs.getString("Gender"));
                  srddetails.add(rs.getString("DOB"));
                  srddetails.add(rs.getString("nationalID"));
                  srddetails.add(rs.getString("Title"));
                  srddetails.add(rs.getString("Surname"));
                  srddetails.add(rs.getString("First Name"));
                  srddetails.add(rs.getString("Last Name"));
                  srddetails.add(rs.getString("Job Title"));
                  srddetails.add(rs.getString("Role"));
                  srddetails.add(rs.getString("Whether Working"));
                  
                  //add student id
                  srddetails.add(rs.getString("ID"));
                  
                  }
                   }
             else{
                       // catch student not found error
                       JOptionPane.showMessageDialog(null,
                      "The Student You Searched for Was not Found\n"+
                      "Please Try Searching With A Different Criteria","Warning",JOptionPane.ERROR_MESSAGE);
                   }
            } catch (SQLException ex) {
            Logger.getLogger(QueryStudentDetails.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
                "Some unexpected error occured\n"+
                "Was not able to load class name details\n"+
                "Please contact the systems administrator for assistance","Warning",JOptionPane.ERROR_MESSAGE);
        }
    
           
            
       return srddetails;
    } 
         
         
    public static ArrayList getStaffContact(String contactId)
    {
            DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            ArrayList srddetails = new ArrayList<String>();
            String Sql = "SELECT  `Mobile Number` ,  `Email` ,  `Physical Address` ,  `Town` \n" +
                         "FROM  `staff_contact` \n" +
                         "WHERE  `ID` =?";
          try {            
            pstmt = conn.prepareStatement(Sql);
            pstmt.setString(1, contactId);
            rs = pstmt.executeQuery();
            
            while(rs.next())
            {
             //get all the student details on searching
            srddetails.add(rs.getString("Mobile Number"));
            srddetails.add(rs.getString("Email"));
            srddetails.add(rs.getString("Physical Address"));
            srddetails.add(rs.getString("Town"));
            
            }
            } catch (SQLException ex) {
            Logger.getLogger(QueryStudentDetails.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
                "Some unexpected error occured\n"+
                "Was not able to load class name details\n"+
                "Please contact the systems administrator for assistance","Warning",JOptionPane.ERROR_MESSAGE);
        }
           
            
       return srddetails;
    } 
    
         
}
