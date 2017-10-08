package com.paletter.socket.talker.test;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;

public class NIOSelectorClientTest {

	public static void main(String[] args) {
		
		try {
			
			Map<SocketChannel, String> map = new HashMap<SocketChannel, String>();
			
			for(int i = 0; i < 20; i ++) {
				SocketChannel sc = SocketChannel.open();
				sc.connect(new InetSocketAddress("127.0.0.1", 1001));
				
				map.put(sc, "Connection-" + i);
			}
			
//			Thread.currentThread().sleep(10000);
//			
//			for(Map.Entry<SocketChannel, String> entry : map.entrySet()) {
//				entry.getKey().write(ByteBuffer.wrap(entry.getValue().getBytes()));
//			}
			
			System.out.println("Over");
			
			while(true) {
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
