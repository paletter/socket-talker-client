package com.paletter.socket.talker.sample.basic;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import com.paletter.iotool.IOReadTool;
import com.paletter.iotool.IOWriterTool;

public class Client {

	public static void main(String[] args) {
		
		try {
			
			Socket socket = new Socket("127.0.0.1", 1001);
			IOWriterTool.printlnWithFlush(socket.getOutputStream(), "Connect");
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
