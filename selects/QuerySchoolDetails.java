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
public class QuerySchoolDetails {
     public static ArrayList getSchoolInfo()
    {
            DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            ArrayList srddetails = new ArrayList<String>();
            String Sql = "SELECT `Name`, `BordingDetails`, `GenderDetails`, "
                    + "`Motto`, `contact Telephone`, `Contact Mobile`, `email`, `Address`, `Town` FROM `school_details` WHERE 1";
          try {            
            pstmt = conn.prepareStatement(Sql);
           
             rs = pstmt.executeQuery();
             
             
             //some code to check whether the result set is empty
             //not working
             if(rs.first() == true)
             {
                 rs.previous();
                  while(rs.next())
                  {
                   //get all the staff details
                   
                  srddetails.add(rs.getString("Name"));
                  srddetails.add(rs.getString("BordingDetails"));
                  srddetails.add(rs.getString("GenderDetails"));
                  srddetails.add(rs.getString("Motto"));
                  srddetails.add(rs.getString("contact Telephone"));
                  srddetails.add(rs.getString("Contact Mobile"));
                  srddetails.add(rs.getString("email"));
                  srddetails.add(rs.getString("Address"));
                  srddetails.add(rs.getString("Town"));
                                   
                  }
                   }
             else{
                       // catch student not found error
                       JOptionPane.showMessageDialog(null,
                      "The School details Were not Found\n"+
                      "Please Contact System Developers for Guidance","Warning",JOptionPane.ERROR_MESSAGE);
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
