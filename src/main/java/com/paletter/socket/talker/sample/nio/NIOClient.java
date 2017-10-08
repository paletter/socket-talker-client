package com.paletter.socket.talker.sample.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NIOClient {

	public static void main(String[] args) {
		
		try {
			
			SocketChannel sc = SocketChannel.open();
			sc.connect(new InetSocketAddress("127.0.0.1", 1001));

			sc.configureBlocking(false);
			
			sc.write(ByteBuffer.wrap("Connect1!".getBytes()));
			
			while(true) {
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
