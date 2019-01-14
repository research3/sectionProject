package com.accp.util.file;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

/**
 * 图片本地化
 * 
 * @author yuno
 *
 */
public class ImageLocalization {
	/**
	 * 远程图片本地化
	 * 
	 * @param imgUrl
	 *            远程图片地址
	 * @return 图片访问路径
	 * @throws Exception
	 */
	public static String saveImage(String imgUrl) throws Exception {
		byte[] data = ImageLocalization.readInputStream(imgUrl);
		File file = new File(UUID.randomUUID().toString().replace("-", "") + ".jpg");
		OutputStream output = new FileOutputStream(file);
		BufferedOutputStream bufferedOutput = new BufferedOutputStream(output);
		bufferedOutput.write(data);
		bufferedOutput.close();
		FileInputStream fileInputStream = new FileInputStream(file);
		String fileName = file.getName();
		MultipartFile multipartFile = new MockMultipartFile(fileName, fileName, "", fileInputStream);
		String url = Upload.uploadFile(multipartFile);
		file.delete();
		return url;
	}

	/**
	 * GET请求读取文件流
	 * 
	 * @param imageUrl
	 *            远程图片地址
	 * @return 文件字节流
	 * @throws Exception
	 */
	private static byte[] readInputStream(String imageUrl) throws Exception {
		URL realUrl = new URL(imageUrl);
		URLConnection connection = realUrl.openConnection();
		connection.setRequestProperty("accept", "*/*");
		connection.setRequestProperty("connection", "Keep-Alive");
		connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
		InputStream inStream = connection.getInputStream();
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		inStream.close();
		return outStream.toByteArray();
	}
}