package com.paletter.socket.talker.sample.keepalive;

import java.net.Socket;

import com.paletter.iotool.IOReadTool;
import com.paletter.iotool.IOWriterTool;

public class KeepAliveClient {

	public static void main(String[] args) {

		try {
			
			Socket socket = new Socket("127.0.0.1", 1001);
		
			for(int i = 0; i < 5; i ++) {
				
				IOWriterTool.printlnWithFlush(socket.getOutputStream(), "Test");
				
//				String response = IOReadTool.readLine(socket.getInputStream());
//				System.out.println(response);
				
				Thread.currentThread().sleep(1000);
			}
			
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
