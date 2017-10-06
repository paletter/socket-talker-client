package com.paletter.socket.talker.client.thread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class AcceptThread extends Thread {

	private boolean isRun;
	private ServerSocket ss;

	public AcceptThread(ServerSocket ss) {
		this.ss = ss;
	}
	
	public void run() {
		
		try {
			
			isRun = true;
			
			while(isRun) {
				
				Socket socket = ss.accept();
				System.out.println("Accpet socket: " + socket.getInetAddress());
				
				ReceiveThread receiveThread = new ReceiveThread(socket);
				receiveThread.start();
			}
			
			ss.close();
			System.out.println("Server Interrupt");
		
		} catch (Exception e) {
			if(!(e instanceof SocketException)) {
				System.out.println("Server error.");
				e.printStackTrace();
			}
		}
	}
	
	public void interrupt() {
		
		try {
			
			System.out.println("Server Interrupt");
			isRun = false;
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
