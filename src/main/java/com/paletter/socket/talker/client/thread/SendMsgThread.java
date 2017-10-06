package com.paletter.socket.talker.client.thread;

import java.net.Socket;

import org.json.JSONObject;

import com.paletter.iotool.IOReadTool;
import com.paletter.iotool.IOWriterTool;
import com.paletter.socket.talker.client.Consts;

public class SendMsgThread extends Thread {

	private Socket socket;
	private boolean isRun;

	public SendMsgThread(Socket socket) {
		
		super();
		this.socket = socket;
		this.isRun = true;
	}

	public void run() {

		while(isRun) {
			
		}
	}
	
	public void sendMsg(String msg) {

		try {

			JSONObject header = new JSONObject();
			header.put(Consts.HEADER_CONTENT_TYPE, Consts.CONTENT_TYPE_MSG);
			
			IOWriterTool.printlnWithFlush(socket.getOutputStream(), header.toString());
			IOReadTool.readLine(socket.getInputStream());
			
			IOWriterTool.printlnWithFlush(socket.getOutputStream(), msg);
			System.out.println("## Send msg succ: " + msg);
			IOReadTool.readLine(socket.getInputStream());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void interrupt() {
		
		isRun = false;
	}
}
