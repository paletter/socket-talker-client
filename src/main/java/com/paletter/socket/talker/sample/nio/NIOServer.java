package com.paletter.socket.talker.sample.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NIOServer {

	public static void main(String[] args) {
		
		try {
			
			blocking();
//			notBlocking();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void blocking() throws Exception {

		ServerSocketChannel ssc = ServerSocketChannel.open();
		ssc.socket().bind(new InetSocketAddress(1001));
		
		ssc.configureBlocking(true);
		
		accept(ssc);
	}
	
	/**
	 * ServerSocketChannel.accpet() will return null
	 */
	public static void notBlocking() throws Exception {
		
		ServerSocketChannel ssc = ServerSocketChannel.open();
		ssc.socket().bind(new InetSocketAddress(1001));
		
		ssc.configureBlocking(false);
		
		accept(ssc);
	}
	
	public static void accept(ServerSocketChannel ssc) throws Exception {

		System.out.println("Waiting Accept...");
		
		int i = 0;
		while(true) {
			
			SocketChannel sc = ssc.accept();
			if(sc != null) {
				System.out.println("Accept");
				
				ByteBuffer b = ByteBuffer.allocate(1048576);
				sc.read(b);
				
				String content = new String(b.array());
				System.out.println("Content: " + content);
			}
			
			Thread.currentThread().sleep(2000);
			System.out.println(i ++);
		}
	}
}
