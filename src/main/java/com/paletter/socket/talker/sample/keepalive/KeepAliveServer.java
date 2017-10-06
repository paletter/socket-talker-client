package com.paletter.socket.talker.sample.keepalive;

import java.net.ServerSocket;
import java.net.Socket;

import com.paletter.iotool.IOReadTool;
import com.paletter.iotool.IOWriterTool;

public class KeepAliveServer {

	public static void main(String[] args) {
		
		try {
			
			int port = 1001;
			
			ServerSocket server = new ServerSocket(port);
			System.out.println("Server Start: " + port);

			System.out.println("Waiting accpet...");
			Socket socket = server.accept();
			System.out.println("Accept socket!");
			
			for(int i = 0; i < 5; i ++) {
			
				System.out.println("Waiting input...");
				String content = IOReadTool.readLine(socket.getInputStream());
				System.out.println(content);
				
//				IOWriterTool.printlnWithFlush(socket.getOutputStream(), "Reponse");
			}
			
			server.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
