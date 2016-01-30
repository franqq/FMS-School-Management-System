/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frigfactor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import selects.QueryMainDetails;

/**
 *
 * @author franq
 */
public class MealsFee {
    public static Double getMealsFee(int classid, String fordetails)
    {
        DBConnection dbconn =new DBConnection();
        Connection conn = dbconn.vconnection;
        PreparedStatement pstmt;
        Double schoolname= 0.00;
        String sql = "SELECT `Fee` FROM `meals` WHERE `termID`=? AND `classID`=? AND `For`=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, getCurrentTerm());
            pstmt.setInt(2, classid);
            pstmt.setString(3, fordetails);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
            schoolname = rs.getDouble("Fee");
                     
        } catch (SQLException ex) {
            Logger.getLogger(QueryMainDetails.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(null, "Some unexpected error occured \nMeals Fee Details not found\n" +
                        "Please contact the system administrator", "Error "+ex.getErrorCode(), JOptionPane.WARNING_MESSAGE);
        }
        return schoolname;
    }
    
    public static int  getCurrentTerm()
    {
        DBConnection dbconn =new DBConnection();
        Connection conn = dbconn.vconnection;
        PreparedStatement pstmt;
        int termid=-1;
        String sql = "SELECT `ID` FROM `term` WHERE 1 ORDER BY `ID` DESC LIMIT 1;";
        try {
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
            termid = rs.getInt("ID");
            else
                JOptionPane.showMessageDialog(null, "Term ID Not Found\n" +
                        "Please Set Term Details First", "Error ", JOptionPane.WARNING_MESSAGE);
            
        
        } catch (SQLException ex) {
            Logger.getLogger(QueryMainDetails.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(null, "Some unexpected error occured \nTerm Details not found\n" +
                        "Please contact the system administrator", "Error "+ex.getErrorCode(), JOptionPane.WARNING_MESSAGE);
        }
        return termid;
    }
}
