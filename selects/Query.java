/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package selects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author franq
 */
public class Query {
     public static int getReg() throws SQLException
    {
           DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            int classtaughtid = 0;
            
            String Sql = "SELECT `subject` FROM `subject_class` WHERE 1";
                    
            pstmt = conn.prepareStatement(Sql);
            rs = pstmt.executeQuery();
            boolean next = rs.next();
            classtaughtid = rs.getInt("subject");
           
            
       return classtaughtid;
    }
     
     public static int getKeys(String k) throws SQLException
    {
           DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            int classtaughtid = -84;
            
            String Sql = "SELECT `ID` FROM `keys` WHERE `key`=?";
                    
            pstmt = conn.prepareStatement(Sql);
            pstmt.setString(1, k);
            rs = pstmt.executeQuery();
            
            if(rs.next())
            classtaughtid = rs.getInt("ID");
           
            
       return classtaughtid;
    }
}
