package com.paletter.socket.talker.test;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOServerListenReadThread extends Thread {

	SocketChannel channel;
	int socketCnt;

	public NIOServerListenReadThread(SocketChannel channel, int socketCnt) {
		super();
		this.channel = channel;
		this.socketCnt = socketCnt;
	}

	public void run() {

		try {
			
			channel.configureBlocking(false);
			
			Selector selector = Selector.open();
			channel.register(selector, SelectionKey.OP_READ);
			
			while(selector.select() > 0) {
				
				Iterator it = selector.selectedKeys().iterator();
				
				while(it.hasNext()) {
					
					SelectionKey key = (SelectionKey) it.next();
					
					if(key.isReadable()) {
						
						SocketChannel sc = (SocketChannel) key.channel();
						NIOServerReaderThread t = new NIOServerReaderThread(sc);
						System.out.println("## NIOServerReaderThread-" + socketCnt);
						t.setName("NIOServerReaderThread");
						t.start();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
