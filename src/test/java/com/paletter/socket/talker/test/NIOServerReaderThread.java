package com.paletter.socket.talker.test;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import com.paletter.iotool.IOFileTool;

public class NIOServerReaderThread extends Thread {

	private SocketChannel channel;

	public NIOServerReaderThread(SocketChannel channel) {
		super();
		this.channel = channel;
	}
	
	public void run() {
		
		try {
			
			System.out.println("Waiting input...");
			ByteBuffer b = ByteBuffer.allocate(1048576);
			channel.read(b);
			
			String content = new String(b.array()).trim();
			System.out.println("Content: " + content);
			IOFileTool.writeFileLn("Content: " + content, "D:\\Zemp\\log.txt");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
