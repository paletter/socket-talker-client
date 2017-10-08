package com.paletter.socket.talker.test;

import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

public class NIOClientTest {

	public static void main(String[] args) {
		
		try {
			
			for(int i = 0; i < 5; i ++) {
				SocketChannel sc = SocketChannel.open();
				sc.connect(new InetSocketAddress("127.0.0.1", 1001));
			}
			
			while(true) {
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
