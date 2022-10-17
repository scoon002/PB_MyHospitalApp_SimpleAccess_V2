package edu.uwp.cs.csci380.project.PB.simpleaccess_V2;

import java.io.IOError;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AppMain  {
    public static void main(String[] args) throws IOError, Exception {
        Configuration config = new Configuration();
    }

    String query = "SELECT * FROM CUSTOMERS";
    Connection conn = new Connection();
    ResultSet rs = null;

    // create SSH tunnel
    // conn.sshConnect();

    // connect to database
    // conn.dbConnect();

    // run query
    try {
        rs = conn.dbRunQuery(query);
    } catch (SQLException e){
        System.out.println("failed to build SQL statement from given query.");
        e.printStackTrace();
        System.exit();
    }

    try {
        System.out.print("{\n    [\n");
        while(rs.next()){
            try {
                System.out.print("        {" +
                        "Customer ID: " + rs.getInt(1) + ", " +
                        "LastName: '" + rs.getString(2) + "', " +
                        "FirstName: '" + rs.getString(3) + "', " +
                        "EmailAddress: '" + rs.getString(4) + "', " +
                        "Phone: '" + rs.getString(10) + "'");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            System.out.println(" },");
        }
        System.out.println("    ]\n}");
        System.out.println("done");
    } catch (SQLException e) {
        System.out.println("Failed to output data from SQL entry."):
        e.printStackTrace();
    }

//    conn.dbClose();
//
//    conn.closeSession();

}
