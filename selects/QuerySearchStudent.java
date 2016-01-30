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
public class QuerySearchStudent {
    public static ArrayList getStudentInfo(int studentid)
    {
            DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            ArrayList srddetails = new ArrayList<String>();
            String Sql = "SELECT  `student`.`ID` ,  `student`.`surname` ,"+
                          "  `student`.`First Name` ,  `student`.`Last Name` ,"+
                          "  `student`.`Gender` ,  `student`.`DOB` ,  `student`.`student_contactID` ,"+
                          "  `class`.`Class`,`class`.`Level` ,  `residence`.`Name` AS `RSNM` ,  `meals`.`For` , "+
                          " `transport`.`Name` AS `PPOINT` ,  `staff`.`Title` , `staff`.`Surname`, `staff`.`First Name`, `student`.`Whether Student` \n" +
                          "FROM  `student` \n" +
                          "INNER JOIN `class` \n" +
                          "ON `student`.`classID` = `class`.`ID`\n" +
                          "INNER JOIN `residence`\n" +
                          "ON `student`.`residenceID` = `residence`.`ID`\n" +
                          "INNER JOIN `meals`\n" +
                          "ON `student`.`mealsID`=`meals`.`ID`\n" +
                          "INNER JOIN `transport`\n" +
                          "ON `student`.`transportID`=`transport`.`ID`\n" +
                          "INNER JOIN `staff`\n" +
                          "ON `student`.`registeredBy` = `staff`.`ID` \n" +
                          "WHERE `student`.`ID`=? \n" +
                          "";
          try {            
            pstmt = conn.prepareStatement(Sql);
            pstmt.setInt(1, studentid);
             rs = pstmt.executeQuery();
             
             
             //some code to check whether the result set is empty
             //not working
             if(rs.first() == true)
             {
                 rs.previous();
                  while(rs.next())
                  {
                   //get all the student details on searching
                  srddetails.add(rs.getString("Surname"));
                  srddetails.add(rs.getString("First Name"));
                  srddetails.add(rs.getString("Last Name"));
                  srddetails.add(rs.getString("Gender"));
                  srddetails.add(rs.getString("DOB"));
                  
                  srddetails.add(rs.getString("student_contactID"));
                  
                  //FROM CLASS FROM CLASS TABLE
                  srddetails.add(rs.getString("Level") + " " + rs.getString("Class"));
                  
                  //RESIDENCE NAME FROM RESIDENCE  TABLE
                  srddetails.add(rs.getString("RSNM"));
                  
                  //MEALS NAME FROM THE TABLE MEALS
                  srddetails.add(rs.getString("For"));
                  
                  //TRANSPORT PICK POINT
                  srddetails.add(rs.getString("PPOINT"));
                  
                  //STAFF DETAILS FROM STAFF TABLE
                  srddetails.add(rs.getString("Title")+rs.getString("Surname")+rs.getString("First Name"));
                  
                  
                  srddetails.add(rs.getString("Whether Student"));
                  
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
    
    
    
    
     public static ArrayList getStudentInfo(String surname,String fname, String lname)
    {
            DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            ArrayList srddetails = new ArrayList<String>();
            String Sql = "SELECT  `student`.`ID` ,  `student`.`surname` ,"+
                          "  `student`.`First Name` ,  `student`.`Last Name` ,"+
                          "  `student`.`Gender` ,  `student`.`DOB` ,  `student`.`student_contactID` ,"+
                          "  `class`.`Class`,`class`.`Level` ,  `residence`.`Name` AS `RSNM` ,  `meals`.`For` , "+
                          " `transport`.`Name` AS `PPOINT` ,  `staff`.`Title` , `staff`.`Surname`, `staff`.`First Name`, `student`.`Whether Student` \n" +
                          "FROM  `student` \n" +
                          "INNER JOIN `class` \n" +
                          "ON `student`.`classID` = `class`.`ID`\n" +
                          "INNER JOIN `residence`\n" +
                          "ON `student`.`residenceID` = `residence`.`ID`\n" +
                          "INNER JOIN `meals`\n" +
                          "ON `student`.`mealsID`=`meals`.`ID`\n" +
                          "INNER JOIN `transport`\n" +
                          "ON `student`.`transportID`=`transport`.`ID`\n" +
                          "INNER JOIN `staff`\n" +
                          "ON `student`.`registeredBy` = `staff`.`ID` \n" +
                          "WHERE `student`.`surname`=?  AND `student`.`First Name`=? AND `student`.`Last Name`=? \n" +
                          "";
          try {            
            pstmt = conn.prepareStatement(Sql);
            pstmt.setString(1, surname);
            pstmt.setString(2, fname);
            pstmt.setString(3, lname);
             rs = pstmt.executeQuery();
             
           //check whether the searched item has been found  
           if(rs.first() == true)
           {
                 rs.previous();
            while(rs.next())
            {
             //get all the student details on searching
            srddetails.add(rs.getString("Surname"));
            srddetails.add(rs.getString("First Name"));
            srddetails.add(rs.getString("Last Name"));
            srddetails.add(rs.getString("Gender"));
            srddetails.add(rs.getString("DOB"));
            
            srddetails.add(rs.getString("student_contactID"));
            
            //FROM CLASS FROM CLASS TABLE
            srddetails.add(rs.getString("Class") + " " + rs.getString("Level"));
            
            //RESIDENCE NAME FROM RESIDENCE  TABLE
            srddetails.add(rs.getString("RSNM"));
            
            //MEALS NAME FROM THE TABLE MEALS
            srddetails.add(rs.getString("For"));
            
            //TRANSPORT PICK POINT
            srddetails.add(rs.getString("PPOINT"));
            
            //STAFF DETAILS FROM STAFF TABLE
            srddetails.add(rs.getString("Title")+rs.getString("Surname")+rs.getString("First Name"));
            
            
            srddetails.add(rs.getString("Whether Student"));
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
    
   
    public static ArrayList getStudentContact(int contactId)
    {
            DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            ArrayList srddetails = new ArrayList<String>();
            String Sql = "SELECT  `Mobile Number` ,  `Email` ,  `Physical Address` ,  `Town` \n" +
                         "FROM  `student_contact` \n" +
                         "WHERE  `ID` =?";
          try {            
            pstmt = conn.prepareStatement(Sql);
            pstmt.setInt(1, contactId);
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
    
    
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
       getStudentInfo(1);
    }
}
