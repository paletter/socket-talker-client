package com.paletter.socket.talker.test;

import java.net.Socket;

import com.paletter.iotool.IOReadTool;

public class ServerReaderThread extends Thread {

	private Socket socket;

	public ServerReaderThread(Socket socket) {
		super();
		this.socket = socket;
	}
	
	public void run() {
		
		try {
			
			System.out.println("Waiting input...");
			String line = IOReadTool.readLine(socket.getInputStream());
			System.out.println("-- Receive: " + line);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
