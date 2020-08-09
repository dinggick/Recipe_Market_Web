package com.recipe.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Download {
	public Download(String path, OutputStream os)  throws IOException {
		FileInputStream fis = null;
		fis = new FileInputStream(path);
		
		byte[] bArr = new byte[1024];
		
		int readCnt = -1;
		while((readCnt = fis.read(bArr)) != -1) {
			os.write(bArr, 0, readCnt);
		}
		
		fis.close();
		os.close();
	}
}
