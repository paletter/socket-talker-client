package com.paletter.socket.talker.sample.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.paletter.iotool.IOWriterTool;

public class FileClient {

	public static void main(String[] args) {
		
		try {
			
			Socket socket = new Socket("127.0.0.1", 1001);
		
			OutputStream os = socket.getOutputStream();
			
			File file = new File("D:\\Zemp\\temp.txt");
			InputStream is = new FileInputStream(file);
			
			byte[] b = new byte[1024];
			while(true) {
				int ins = is.read(b);
				if(ins == -1) {
					is.close();
					break;
				}
				
				os.write(b, 0, ins);
			}
			
			os.close();
			socket.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
