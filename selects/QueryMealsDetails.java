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
public class QueryMealsDetails {
    
     public static ArrayList listofnames;
     public static ArrayList listofids;
    
     public static ArrayList getMealDetails()
    {
        DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            ArrayList classnames = new ArrayList<String>();
            ArrayList staffids = new ArrayList<Integer>();
            classnames.add("Select Class Name");
            staffids.add(0);
            String Sql = "SELECT `ID`, `For` FROM `meals` WHERE 1";
        try {            
            pstmt = conn.prepareStatement(Sql);
            rs = pstmt.executeQuery();
            while(rs.next())
            {
            staffids.add(rs.getInt("ID"));
            classnames.add(rs.getString("For"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryStudentDetails.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
                "Some unexpected error occured\n"+
                "Was not able to load Information\n"+
                "Please contact the systems administrator for assistance","Warning",JOptionPane.ERROR_MESSAGE);
        }
           
       listofnames = classnames;
       listofids = staffids;
       return classnames;
    }
      public static String getMealFee(int termid, int classid, String forstr)
    {
        DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            
            String fee="";
            String Sql = "SELECT `Fee` FROM `meals` WHERE `termID`=? AND `classID`=? AND `For`=?";
        try {            
            pstmt = conn.prepareStatement(Sql);
            pstmt.setInt(1, termid);
            pstmt.setInt(2, classid);
            pstmt.setString(3, forstr);
            rs = pstmt.executeQuery();
            if(rs.next())
                fee= rs.getString("Fee");
            else
                JOptionPane.showMessageDialog(null,
                "Meals Details not Found\nPress Ok to continue","Warning",JOptionPane.ERROR_MESSAGE);
            
        } catch (SQLException ex) {
            Logger.getLogger(QueryStudentDetails.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
                "Some unexpected error occured\n"+
                "Was not able to load Information\n"+
                "Please contact the systems administrator for assistance","Warning",JOptionPane.ERROR_MESSAGE);
        }
           
       
       return fee;
    }
    
}
