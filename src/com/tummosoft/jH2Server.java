package com.tummosoft;

import anywheresoftware.b4a.BA;
import java.io.IOException;
import java.sql.SQLException;
import org.h2.server.TcpServer;

@BA.ShortName("jH2Server")
public class jH2Server {
    org.h2.tools.Server h2server;
    org.h2.server.TcpServer tcpserver = new TcpServer();
    public void InitializeServer(String port, String pass, String DatabasePath) {
        try {				
            h2server = org.h2.tools.Server.createTcpServer("-tcp", "-tcpPort", port, "-ifNotExists", "-tcpPassword", pass, "-baseDir", DatabasePath, "-web", "-webAllowOthers");
            
        } catch (SQLException ex) {
            BA.Log(ex.getMessage());            
        }
    }
    
    public void start() throws SQLException {
        h2server.start();
    }
    
    public static boolean isTcpPortOpen(String host, int port) throws IOException {        
            java.net.Socket socket = new java.net.Socket(host, port);			
            if (socket.isConnected()) {
                return true;
            } else {
                return false;
            }
    }
    
    public void Stop() {
       h2server.stop();       
    }
    
    public void ShutDown() {
       h2server.shutdown();
    }
     
    public String getURL() {        
        return h2server.getURL();
    }
}

