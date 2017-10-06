package com.paletter.socket.talker.sample.file;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.paletter.iotool.IOFileTool;

public class FileServer {

	public static void main(String[] args) {
		
		try {
				
			int port = 1001;
			
			ServerSocket server = new ServerSocket(port);
			
			System.out.println("Server Start: " + port);

			System.out.println("Waiting accept...");
			Socket socket = server.accept();
			
			System.out.println("1");
			InputStream is = socket.getInputStream();
			System.out.println("2");
			IOFileTool.writeFile(is, "D:\\Zemp\\temp1.txt");
			System.out.println("3");
			
			server.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
