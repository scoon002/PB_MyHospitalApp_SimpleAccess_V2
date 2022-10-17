package edu.uwp.cs.csci380.project.PB.simpleaccess_V2;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import netscape.javascript.JSException;

import java.nio.channels.Channel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connection {

    // Instantiate a new Connection object
    Connection conn = new Connection();

    // Create the SSH tunnel
    static void sshConnect() throws JSchException {
        // connect
        System.out.println("Establishing SSH tunnel...");
        session.connect();
        System.out.print("localhost:4321 -> localhost:3306");
        System.out.println("done.");
        session.setPortForwardingL(4321, "localhost", 3306);
    }

    // static String database = "QACS_db";
    // static String dbPass = "iN+9omBb";
    // static String userName = "scoons";
    // static String tableName = "CUSTOMER";
    // static String host = "localhost";
    // static Channel channel;
    // host credentials

    // private final String hostName = "basil.cs.uwp.edu";
    // private final int LPort = 1234;

    private static Session session;
    Channel channel;


    /**
     * Establishes connection with given credentials,
     * disables StrictHostKeyChecking, and executes
     * port forwarding.
     */
    void dbConnect() throws JSchException {
        System.out.print("Establishing database connection via tunnel...");
        JSch jsch = new JSch();
        String userName = "scoons";
        session = jsch.getSession(userName, hostName, 22);

        //disable StrictHostKeyChecking
        java.util.Properties config = new java.util.Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);
        String pass = "Hiflyer1!";
        session.setPassword(pass);
        System.out.println("done.");

        // connect to SSH
        System.out.print("Establishing SSH Connection...");
        session.connect();
        System.out.println("done.");

        // port forwarding
        System.out.println("Assigned port: 4321");
        int RPort = 3306;
        session.setPortForwardingL(LPort, "localhost", RPort);
        channel = (Channel) session.openChannel("exec");
    }

    /**
     * Retrieves the name of the host.
     *
     * @return the name of the host.
     */
    public String getHostName() {
        return hostName;
    }

    /**
     * Retrieves the channel.
     *
     * @return channel.
     */
    public Channel getChannel() {
        return channel;
    }

    /**
     * Retrieves the remote port.
     *
     * @return LPort.
     */
    public int getLPort() {
        return LPort;
    }

    // dbClose();

}

