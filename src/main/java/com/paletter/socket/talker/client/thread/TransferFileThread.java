package com.paletter.socket.talker.client.thread;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.Socket;

import org.json.JSONObject;

import com.paletter.iotool.IOReadTool;
import com.paletter.iotool.IOWriterTool;
import com.paletter.socket.talker.client.Consts;

public class TransferFileThread extends Thread {

	private String targetIp;
	private int targetPort;
	private String filePath;

	public TransferFileThread(String targetIp, int targetPort, String filePath) {
		super();
		this.targetIp = targetIp;
		this.targetPort = targetPort;
		this.filePath = filePath;
	}

	public void run() {
		
		try {
			
			System.out.println("Trasfering...");
			
			Socket socket = new Socket(targetIp, targetPort);
			
			File file = new File(filePath);
			
			JSONObject header = new JSONObject();
			header.put(Consts.HEADER_CONTENT_TYPE, Consts.CONTENT_TYPE_FILE);
			header.put(Consts.HEADER_FILENAME, file.getName());
			
			IOWriterTool.printlnWithFlush(socket.getOutputStream(), header.toString());
			IOReadTool.readLine(socket.getInputStream());
			
			InputStream is = new FileInputStream(file);
			
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			
			dos.writeUTF(file.getName());
			dos.writeLong(file.length());
			dos.flush();
			
			IOWriterTool.write(dos, is);
			
			System.out.println("Transfer File Successfully!");
			
			is.close();
			socket.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
