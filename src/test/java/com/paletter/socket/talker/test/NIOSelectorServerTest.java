package com.paletter.socket.talker.test;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import com.paletter.iotool.IOFileTool;

public class NIOSelectorServerTest {

	public static void main(String[] args) {

		try {

			ServerSocketChannel ssc = ServerSocketChannel.open();
			ssc.socket().bind(new InetSocketAddress(1001));
			
			ssc.configureBlocking(false);

			int socketCnt = 0;
			
			while(true) {
				
				SocketChannel channel = ssc.accept();
				if(channel == null) continue;
				
				socketCnt ++;
				
				IOFileTool.writeFileLn("Accpet: " + channel, "D:\\Zemp\\log.txt");
				System.out.println("Accpet: " + channel);
				
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
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
