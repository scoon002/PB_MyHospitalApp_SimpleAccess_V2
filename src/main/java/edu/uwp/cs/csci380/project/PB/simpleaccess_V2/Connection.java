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
    Connection conn = null;
    Configuration appConfig;

    public Connection(Configuration appConfig) {
        this.appConfig = appConfig;
    }

    // Create the SSH tunnel
    void sshConnect() throws JSchException {

        String un = this.appConfig.getProperty("ssh.username");
        String hn = this.appConfig.getProperty("ssh.host");
        int port = Integer.parseInt(this.appConfig.getProperty("ssh.port"));

        // Establish SSH connection
        System.out.print("Building the session...");
        JSch jsch = new JSch();


        Session session = jsch.getSession(un, hn, port);
        String pass = this.appConfig.getProperty("ssh.password");
        session.setPassword(pass);
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();


        session.setPortForwardingL(
                Integer.parseInt(this.appConfig.getProperty("local.database.port")),
                this.appConfig.getProperty("remote.database.host"),
                Integer.parseInt(this.appConfig.getProperty("remote.database.port")));

    }

}



