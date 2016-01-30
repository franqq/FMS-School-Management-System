/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frigfactor;

import selects.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author franq
 */

public class DBConnection {
    
    protected static Connection vconnection;
    
    protected DBConnection()
    {
        try {
            vconnection = DriverManager.getConnection("jdbc:mysql://localhost/schooldb?" +"user=root&password=jangdap804");
            
            // Do something with the Connection
   
            } catch (SQLException ex) {
    // handle any errors
     JOptionPane.showMessageDialog(null, "Sorry, Some Error Occured: \n" +
                        ex.getMessage(), "Error "+ex.getErrorCode(), JOptionPane.INFORMATION_MESSAGE);
    }
    
    }
}
