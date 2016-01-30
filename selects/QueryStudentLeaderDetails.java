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
public class QueryStudentLeaderDetails {
    public static ArrayList getStudentInfo(Long studentid)
    {
            DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            ArrayList srddetails = new ArrayList<String>();
            String Sql = "SELECT  `student`.`Gender`,`student`.`surname`, `student`.`First Name`,"
                         + " `student`.`Last Name`, `class`.`Class`, `class`.`Level` \n" +
                            "FROM `student` INNER JOIN `class`\n" +
                            "ON `student`.`classID`=`class`.`ID`\n" +
                            "WHERE `student`.`ID`=?";
          try {            
            pstmt = conn.prepareStatement(Sql);
            pstmt.setLong(1, studentid);
             rs = pstmt.executeQuery();
             
             
             //some code to check whether the result set is empty
             //not working
             if(rs.first() == true)
             {
                 rs.previous();
                  while(rs.next())
                  {
                   //get all the student details on searching
                  srddetails.add(rs.getString("Gender"));
                  srddetails.add(rs.getString("surname"));
                  srddetails.add(rs.getString("First Name"));
                  srddetails.add(rs.getString("Last Name"));
                  srddetails.add(rs.getString("Class") + " " +rs.getString("Level"));
                  
                  
                  }
                   }
             else{
                       // catch student not found error
                       JOptionPane.showMessageDialog(null,
                      "The Student You Specified for Was not Found\n"+
                      "Please Confirm Details Then Try Again","Warning",JOptionPane.ERROR_MESSAGE);
                   }
            } catch (SQLException ex) {
            Logger.getLogger(QueryStudentDetails.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
                "Some unexpected error occured\n"+
                "Was not able to load details\n"+
                "Please contact the systems administrator for assistance","Warning",JOptionPane.ERROR_MESSAGE);
        }
    
           
            
       return srddetails;
    } 
}
