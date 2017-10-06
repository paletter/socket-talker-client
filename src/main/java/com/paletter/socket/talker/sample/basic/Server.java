package com.paletter.socket.talker.sample.basic;

import java.net.ServerSocket;
import java.net.Socket;

import com.paletter.iotool.IOReadTool;

public class Server {

	public static void main(String[] args) {
		
		try {
			
			int port = 1001;
			
			ServerSocket server = new ServerSocket(port);
			System.out.println("Server Start: " + port);
			
			System.out.println("Waiting accpet...");
			Socket socket = server.accept();
			System.out.println("Accept socket!");
			
			System.out.println("------------------------------------------");
			
			System.out.println("Waiting input...");
			String line = IOReadTool.readLine(socket.getInputStream());
			System.out.println("-- Receive: " + line);
			
			server.close();
						
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
