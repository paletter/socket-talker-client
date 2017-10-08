package com.paletter.socket.talker.test.nio2;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import com.paletter.iotool.IOFileTool;

public class NIOSelectorServerTest2 {

	public static void main(String[] args) {

		try {

			ServerSocketChannel ssc = ServerSocketChannel.open();
			ssc.socket().bind(new InetSocketAddress(1001));
			
			ssc.configureBlocking(false);

			Selector selector = Selector.open();
			
			ssc.register(selector, SelectionKey.OP_ACCEPT);
			
			System.out.println("keysize:" + selector.keys().size());
			
			SelectorThreadTest t = new SelectorThreadTest(selector);
			t.setName("SelectorThreadTest");
			t.start();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
