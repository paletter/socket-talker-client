package com.paletter.socket.talker.client.thread;

import java.io.DataInputStream;
import java.net.Socket;
import java.net.SocketException;

import javax.swing.filechooser.FileSystemView;

import org.json.JSONObject;

import com.paletter.iotool.IOFileTool;
import com.paletter.iotool.IOReadTool;
import com.paletter.iotool.IOWriterTool;
import com.paletter.socket.talker.client.Consts;

public class ReceiveThread extends Thread {

	private boolean isRun;
	private Socket socket;

	public ReceiveThread(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		
		try {
			
			isRun = true;
			
			while(isRun) {
				
				String content = IOReadTool.readLine(socket.getInputStream());
				
				JSONObject header = new JSONObject(content);
				String contentType = header.optString(Consts.HEADER_CONTENT_TYPE);
				
				if(contentType.equals(Consts.CONTENT_TYPE_MSG)) {
					
					IOWriterTool.printlnWithFlush(socket.getOutputStream(), "resp");
					
					String msg = IOReadTool.readLine(socket.getInputStream());
					System.out.println("## Receive msg: " + msg + " /" + socket.getInetAddress());
					IOWriterTool.printlnWithFlush(socket.getOutputStream(), "resp");
					
				} else if(contentType.equals(Consts.CONTENT_TYPE_FILE)) {
					
					IOWriterTool.printlnWithFlush(socket.getOutputStream(), "resp");
					
					DataInputStream dis = new DataInputStream(socket.getInputStream());
					String fileName = dis.readUTF();
					
					FileSystemView fsv = FileSystemView.getFileSystemView();
					String saveFilePath = fsv.getDefaultDirectory().getAbsolutePath() + "\\" + fileName;
					
					IOFileTool.writeFile(dis, saveFilePath);
					
					System.out.println("Save File Successfully: " + saveFilePath);
				}
			}
			
			socket.close();
			System.out.println("Socket Interrupt: " + socket.getInetAddress());
		
		} catch (Exception e) {
			if(!(e instanceof SocketException)) {
				System.out.println("Socket error.");
				e.printStackTrace();
			}
		}
	}

	public void interrupt() {
		
		System.out.println("Socket Interrupt");
		isRun = false;
	}
}
