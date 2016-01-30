/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlupdates;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author franq
 */
public class TermDetails {
    private static DBConnection dbconn;
     public static void updateTerm(String name,String start,String close,String remarks,String prevname,String year) throws SQLException
     {
         PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
              
        String update = "UPDATE `term` SET `Name`=?,`Start Date`=?,`Close Date`=?,`Remarks`=? WHERE `Name`=? AND `Year`=?";
       
            stmt = conn.prepareStatement(update);
            stmt.setString(1, name);
            stmt.setString(2, start);
            stmt.setString(3,close);
            stmt.setString(4, remarks);
            stmt.setString(5, prevname);
            stmt.setString(6, year);
           
            stmt.executeUpdate();
        
            if (stmt != null) {
            stmt.close();
            }
 
            if (conn != null) {
		conn.close();
            }
 
		
     }
}
