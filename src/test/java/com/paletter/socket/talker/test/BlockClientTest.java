package com.paletter.socket.talker.test;

import java.net.Socket;

import com.paletter.iotool.IOWriterTool;

public class BlockClientTest {

	public static void main(String[] args) {

		try {
			
			for(int i = 0; i < 100; i ++) {
			
				Socket socket = new Socket("127.0.0.1", 1001);
				
				IOWriterTool.writeContent(socket.getOutputStream(), "T");
				System.out.println("Connection succ");
			}
			
			while(true) {
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
