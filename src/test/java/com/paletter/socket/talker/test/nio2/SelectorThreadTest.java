package com.paletter.socket.talker.test.nio2;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import com.paletter.iotool.IOFileTool;
import com.paletter.socket.talker.test.NIOServerReaderThread;

public class SelectorThreadTest extends Thread {

	Selector selector;

	public SelectorThreadTest(Selector selector) {
		super();
		this.selector = selector;
	}

	public void run() {
		
		try {
			
			int j = 0;
			
			while(true) {

				while(selector.select() > 0) {
					
					Iterator it = selector.selectedKeys().iterator();
					
					while(it.hasNext()) {
						
						SelectionKey key = (SelectionKey) it.next();
						
						if(key.isAcceptable()) {

							it.remove();
							
							ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
							SocketChannel channel = ssc.accept();
							if(channel == null) continue;
							
							j ++;
							
							IOFileTool.writeFileLn("Accpet: " + channel, "D:\\Zemp\\log.txt");
							System.out.println("Accpet: " + channel);
							
							channel.configureBlocking(false);
							channel.register(selector, SelectionKey.OP_READ);
							System.out.println("keysize:" + selector.keys().size());
						}
						
						if(key.isReadable()) {
							
							SocketChannel sc = (SocketChannel) key.channel();
							
							NIOServerReaderThread t = new NIOServerReaderThread(sc);
							System.out.println("## NIOServerReaderThread-" + j);
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
