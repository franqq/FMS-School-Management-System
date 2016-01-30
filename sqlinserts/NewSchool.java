/*
 * reference to this class should be made only during initial setup of the software
 */
package sqlinserts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author franq
 */
public class NewSchool {
    private static DBConnection dbconn;
  
    /*
     * createSchool("Sample Schools", "Bording", "Boys", "Striving for excelence", "+254 060938", "0720069529", "info@sampleschools.com", "3148-00200", "Nairobi", "jafljihrhjga0");
     */
  public static void createSchool(String name, String border, String gender, String motto, String tel, String mobile,String email, String add,
          String town, String key) throws SQLException
   {
        PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "INSERT INTO `school_details`(`Name`, `BordingDetails`, `GenderDetails`, `Motto`, `contact Telephone`,"+
                " `Contact Mobile`, `email`, `Address`, `Town`, `ProductKey`)"
                
                + "VALUES(?,?,?,?,?,?,?,?,?,?)";
          stmt = conn.prepareStatement(insert);
            stmt.setString(1, name);
            stmt.setString(2,border);
            stmt.setString(3, gender);
            stmt.setString(4, motto);
            stmt.setString(5, tel);
            stmt.setString(6, mobile);
            stmt.setString(7, email);
            stmt.setString(8, add);
            stmt.setString(9, town);
            stmt.setString(10, key);
           
            
             stmt.executeUpdate();
        
        
 
			if (stmt != null) {
				stmt.close();
			}
 
			if (conn != null) {
				conn.close();
			}
 
		
   }
  
  public static void main(String args[]) {
  }
}