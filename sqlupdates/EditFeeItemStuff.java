/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlupdates;

import sqlinserts.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author franq
 */
public class EditFeeItemStuff {
    private static DBConnection dbconn;
    
    public static void insertData(String tablename,int classid,int termid, double fee) throws SQLException
      {
          PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "UPDATE `" + tablename + "` SET `Fee`=? WHERE `termID`=? AND`classID`=?";
        
              stmt = conn.prepareStatement(insert);
              stmt.setDouble(1, fee);
               stmt.setInt(2, termid);
              stmt.setInt(3, classid);
             
              
                            
              stmt.executeUpdate();
              
           
              
          
         
 
			if (stmt != null) {
				stmt.close();
			}
 
			if (conn != null) {
				conn.close();
			}
  
      }
      
      
      
      
}
