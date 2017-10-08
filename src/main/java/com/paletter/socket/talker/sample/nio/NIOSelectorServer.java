package com.paletter.socket.talker.sample.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOSelectorServer {

	public static void main(String[] args) {
		
		try {

			ServerSocketChannel ssc = ServerSocketChannel.open();
			ssc.socket().bind(new InetSocketAddress(1001));
			
			System.out.println("Server startup");
			
			ssc.configureBlocking(false);

			Selector selector = Selector.open();
			
			ssc.register(selector, SelectionKey.OP_ACCEPT);

			int j = 0;
			while((j = selector.select()) > 0) {
				
				System.out.println("select: " + j);
				
				Iterator it = selector.selectedKeys().iterator();
				
				while(it.hasNext()) {
					
					SelectionKey key = (SelectionKey) it.next();
					
					if(key.isAcceptable()) {
				
						SocketChannel sc = ssc.accept();
						if(sc != null) {
							System.out.println("Accept" + sc);
							
							sc.configureBlocking(false);
							sc.register(selector, SelectionKey.OP_READ);
						}
					} else if(key.isReadable()) {
						
						SocketChannel sc = (SocketChannel) key.channel();
						ByteBuffer b = ByteBuffer.allocate(1048576);
						sc.read(b);
						
						String content = new String(b.array());
						System.out.println("Content: " + content);
					}
				}
			}
			
			System.out.println("Over:" + j);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
