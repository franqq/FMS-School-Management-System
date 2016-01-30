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
public class QuerySearchDepartment {
    
    public static ArrayList<String> names;
    public static ArrayList<Integer> ids;
    
    
    public static ArrayList getDepartmentInfo()
    {
            DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            ArrayList srddetails = new ArrayList<String>();
            ArrayList srdid= new ArrayList <Integer>();
            
            srddetails.add("Select");
            srdid.add(0);
            String Sql = "SELECT `ID`, `Name` FROM `department` WHERE 1";
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
                   //get all the student details on searching
                  srdid.add(rs.getInt("ID"));
                  srddetails.add(rs.getString("Name"));
                  
                  }
                  }
             else{
                       // catch student not found error
                       JOptionPane.showMessageDialog(null,
                      "The Department Details were not Found\n"+
                      "Contact Admin for Assistance","Warning",JOptionPane.ERROR_MESSAGE);
                   }
            } catch (SQLException ex) {
            Logger.getLogger(QueryStudentDetails.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
                "Some unexpected error occured\n"+
                "Was not able to load class name details\n"+
                "Please contact the systems administrator for assistance","Warning",JOptionPane.ERROR_MESSAGE);
        }
    
           
       names = srddetails;
       ids = srdid;
       return srddetails;
    }
    
}
