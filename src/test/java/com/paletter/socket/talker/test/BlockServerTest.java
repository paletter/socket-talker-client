package com.paletter.socket.talker.test;

import java.net.ServerSocket;
import java.net.Socket;

public class BlockServerTest {

	public static void main(String[] args) {

		try {
			
			int port = 1001;
			
			ServerSocket server = new ServerSocket(port);
			System.out.println("Server Start: " + port);
			
			int i = 0;
			while(true) {
			
				System.out.println("Waiting accpet...");
				Socket socket = server.accept();
				System.out.println("Accept socket!");
				
				ServerReaderThread t = new ServerReaderThread(socket);
				t.setName("ServerReaderThread-" + i);
				t.start();
				
				System.out.println("Total acception: " + (i ++));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
