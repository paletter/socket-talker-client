package com.paletter.socket.talker.sample.aio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;

public class AIOClient {

	public static void main(String[] args) throws Exception {
		
		AsynchronousSocketChannel socket = AsynchronousSocketChannel.open();
		socket.connect(new InetSocketAddress("127.0.0.1", 1001)).get();
		socket.write(ByteBuffer.wrap("## Go".getBytes()));
		
		Thread.currentThread().sleep(1000);
		socket.close();
	}
}
