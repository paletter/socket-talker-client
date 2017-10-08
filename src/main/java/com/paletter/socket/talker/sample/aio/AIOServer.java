package com.paletter.socket.talker.sample.aio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.Future;

public class AIOServer {

	public static AsynchronousServerSocketChannel server2;
	
	public static void main(String[] args) throws Exception {
		
//		future();
		callback();
	}
	
	public static void future() throws Exception {
		
		AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open()
				.bind(new InetSocketAddress(1001));
		
		System.out.println("Waiting Accept...");
		Future<AsynchronousSocketChannel> future = server.accept();
		AsynchronousSocketChannel socket = future.get();
		System.out.println("Accpet:" + socket);

		ByteBuffer b = ByteBuffer.allocate(10);
		socket.read(b);
		
		String content = new String(b.array());
		System.out.println("Content: " + content);
	}
	
	public static void callback() throws Exception {
		

		final AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open()
				.bind(new InetSocketAddress(1001));
		
		System.out.println("Waiting Accept...");
		server.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {

			@Override
			public void completed(AsynchronousSocketChannel channel, Object attachment) {
				server.accept();
				System.out.println("Accpet:" + channel);
				
				final ByteBuffer b = ByteBuffer.allocate(10);
				final AsynchronousSocketChannel fchannel = channel;
				
				fchannel.read(b, attachment, new CompletionHandler<Integer, Object>() {

					@Override
					public void completed(Integer result, Object attachment) {
						
						if(result == -1) {
							try {
								fchannel.close();
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							return;
						}
						
						b.flip();
						System.out.println("Content:" + new String(b.array()).trim());
						b.clear();
						
						fchannel.read(b, attachment, this);
					}

					@Override
					public void failed(Throwable exc, Object attachment) {
						exc.printStackTrace();
					}
				});
			}

			@Override
			public void failed(Throwable exc, Object attachment) {
				exc.printStackTrace();
			}
			
		});
		
		while(true) {
			
		}
	}
}
