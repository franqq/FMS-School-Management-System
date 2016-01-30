/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlinserts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author franq
 */
public class NewFeeItemStuff {
    private static DBConnection dbconn;
      
      
      //method to enter new class details
      public static void createTable(String tablename) throws SQLException
      {
          PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "CREATE TABLE `"+tablename+"` (\n" +
                        "`ID` INT AUTO_INCREMENT,\n" +
                        "`termID` INT,\n" +
                        "`classID` INT,\n" +
                        "`Fee` DECIMAL(11,2),\n" +
                        "PRIMARY KEY(`ID`),\n" +
                        "INDEX cls_ind (`classID`),\n" +
                        "FOREIGN KEY (`classID`)\n" +
                        "REFERENCES `class`(`ID`)\n" +
                        "ON UPDATE CASCADE\n" +
                        "ON DELETE CASCADE,\n" +
                        "INDEX trm_ind (`termID`),\n" +
                        "FOREIGN KEY (`termID`)\n" +
                        "REFERENCES `term`(`ID`)\n" +
                        "ON UPDATE CASCADE\n" +
                        "ON DELETE CASCADE,\n" +
                        "UNIQUE (`classID` ,`Fee`)\n" +
                        ")ENGINE=INNODB;";
          
              stmt = conn.prepareStatement(insert);
                            
              stmt.executeUpdate();
              
           
              
          
         
 
			if (stmt != null) {
				stmt.close();
			}
 
			if (conn != null) {
				conn.close();
			}
  
      }
      
      
 
      
        public static void insertData(String tablename,int classid,int termid, double fee) throws SQLException
      {
          PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "INSERT INTO `" + tablename + "`(`classID`, `termID`, `Fee`) VALUES (?,?,?)";
          
              stmt = conn.prepareStatement(insert);
              stmt.setInt(1, classid);
              stmt.setInt(2, termid);
              stmt.setDouble(3, fee);
                            
              stmt.executeUpdate();
              
           
              
          
         
 
			if (stmt != null) {
				stmt.close();
			}
 
			if (conn != null) {
				conn.close();
			}
  
      }
      
      
      
      
}
