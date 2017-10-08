package com.paletter.socket.talker.test.nio2;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;

public class NIOSelectorClientTest2 {

	public static void main(String[] args) {
		
		try {
			
			Map<SocketChannel, String> map = new HashMap<SocketChannel, String>();
			
			for(int i = 0; i < 5; i ++) {
				SocketChannel sc = SocketChannel.open();
				sc.connect(new InetSocketAddress("127.0.0.1", 1001));
				
//				if(i == 3) {
//					sc.write(ByteBuffer.wrap("Connection".getBytes()));
//				}
				
				String s = "Connection-" + i;
				sc.write(ByteBuffer.wrap(s.getBytes()));
				
				map.put(sc, "Connection-" + i);
			}
			
			Thread.currentThread().sleep(10000);
			
			for(Map.Entry<SocketChannel, String> entry : map.entrySet()) {
				entry.getKey().write(ByteBuffer.wrap(entry.getValue().getBytes()));
			}
			
			System.out.println("Over");
			
			while(true) {
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
