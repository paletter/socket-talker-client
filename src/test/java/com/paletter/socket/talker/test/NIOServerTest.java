package com.paletter.socket.talker.test;

import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NIOServerTest {

	public static void main(String[] args) {
		
		try {
			
			blocking();
			
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
	
	public static void accept(ServerSocketChannel ssc) throws Exception {
		
		int i = 0;
		while(true) {
			
			System.out.println("Waiting Accept...");
			SocketChannel sc = ssc.accept();
			if(sc != null) {
				System.out.println("Accept");
				
				NIOServerReaderThread t = new NIOServerReaderThread(sc);
				t.setName("NIOServerReaderThread-" + i);
				t.start();
			}
			
			System.out.println("Total Accept: " + (i ++));
		}
	}
}
