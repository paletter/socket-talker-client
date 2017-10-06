package com.paletter.socket.talker.client;

import java.net.ServerSocket;
import java.net.Socket;

import com.paletter.iotool.IOReadTool;
import com.paletter.socket.talker.client.thread.AcceptThread;
import com.paletter.socket.talker.client.thread.SendMsgThread;
import com.paletter.socket.talker.client.thread.TransferFileThread;

public class Runner {

	public static void main(String[] args) {
		
		try {
			
			System.out.println("Socket Talker Client Startup!");

			ServerSocket server = null;
			String targetIp = null;
			Integer targetPort = null;
			Socket targetSocket = null;
			SendMsgThread sendThread = null;
			
			while(true) {
				
				if(server == null) {
					
					// Receive
					System.out.println("Input Server Port:");
					
					String port = IOReadTool.readLine(System.in);
					
					server = new ServerSocket(Integer.valueOf(port));
					System.out.println("## Server Run: " + port);
					
					AcceptThread acceptThread = new AcceptThread(server);
					acceptThread.start();
					
				} else if(targetIp == null) {
					
					// Target ip
					System.out.println("Input Target Server Ip:");
					
					targetIp = IOReadTool.readLine(System.in);
					
					
				} else if(targetPort == null) {
					
					// Target port
					System.out.println("Input Target Server Port:");
					
					String input = IOReadTool.readLine(System.in);
					
					targetPort = Integer.valueOf(input);
					
					System.out.println("Connect target server...");
					while(true) {
						try {
							targetSocket = new Socket(targetIp, targetPort);
							break;
						} catch (Exception e) {
							Thread.sleep(2000);
						}
					}
					
					sendThread = new SendMsgThread(targetSocket);
					sendThread.start();
					
					System.out.println("Target server connect successfully!");
					
				} else {
					
					showMenu();
					
					String input = IOReadTool.readLine(System.in);
					
					if(input.equals("-m")) {
						
						// Send Msg
						while(true) {
							System.out.println("Send Msg: ");
							String msg = IOReadTool.readLine(System.in);
							
							if(msg.equals("-q")) break;
							
							sendThread.sendMsg(msg);
						}
						
					} else if(input.equals("-f")) {
						
						// Send File
						System.out.println("Input Send File Path(eg: D:\\Zemp\\temp.txt):");
						String filePath = IOReadTool.readLine(System.in);
						
						if(filePath.equals("-q")) continue;
						
						TransferFileThread transferFileThread = new TransferFileThread(targetIp, targetPort, filePath);
						transferFileThread.start();
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void showMenu() {
		
		System.out.println("==========================");
		System.out.println("'-m': Send Msg");
		System.out.println("'-f': Trasfer File");
		System.out.println("'-q': Back To Menu");
		System.out.println("==========================");
	}
}
