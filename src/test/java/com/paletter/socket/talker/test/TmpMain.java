package com.paletter.socket.talker.test;

import java.nio.ByteBuffer;

import com.paletter.iotool.IOFileTool;

public class TmpMain {

	public static void main(String[] args) throws Exception {

		String str = "GET /index.html?a=1 HTTP/1.1";
		
		String[] s = str.split(" ");
		for(String ss : s) {
			System.out.println(ss);
		}
	}
}
