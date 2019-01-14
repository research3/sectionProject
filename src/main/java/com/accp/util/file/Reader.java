package com.accp.util.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 文件读取
 * 
 * @author yuno
 *
 */
public class Reader {
	// 文件访问前缀
	public final static String VISIT_FOLDER = "/api/upload/";

	/**
	 * 文件转换字节流
	 * 
	 * @param file
	 *            文件
	 * @return
	 * @throws IOException
	 */
	public static byte[] readFileToBytes(File file) throws IOException {
		int size = (int) file.length();
		FileInputStream inputStream = new FileInputStream(file);
		byte[] bytes = new byte[size];
		int offset = 0;
		int readed;
		while (offset < size && (readed = inputStream.read(bytes, offset, inputStream.available())) != -1) {
			offset += readed;
		}
		inputStream.close();
		return bytes;
	}
}