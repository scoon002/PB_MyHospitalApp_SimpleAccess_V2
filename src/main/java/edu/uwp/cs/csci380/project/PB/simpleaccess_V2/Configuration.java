package edu.uwp.cs.csci380.project.PB.simpleaccess_V2;

// Java program to demonstrate Properties class to get
// information from the properties file

import java.util.*;
import java.io.*;

public class Configuration {

    private final Properties p;

    public Configuration() throws Exception {
        FileReader reader = new FileReader();

        // create properties object
        p = new Properties();

        // add wrapper around reader object
        p.load(reader);
    }

    public String getProperty(String propName) {
        return p.getProperty(propName);
    }

    public void setProperty(String propKey, String propValue) {
        p.setProperty(propKey,propValue);
    }

}
