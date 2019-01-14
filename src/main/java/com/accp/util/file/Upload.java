package com.accp.util.file;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.accp.util.DateTime;

/**
 * 文件上传
 * 
 * @author yuno
 *
 */
public class Upload {
	// 文件上传文件夹
	public final static String UPLOADED_FOLDER = "C://Upload/";

	/**
	 * 上传文件
	 * 
	 * @param file
	 *            文件
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public static String uploadFile(MultipartFile file) throws IllegalStateException, IOException {
		return saveFile(file, Reader.VISIT_FOLDER);
	}

	/**
	 * 保存文件
	 * 
	 * @param file
	 *            文件
	 * @return 文件访问路径
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	private static String saveFile(MultipartFile file, String suffix) throws IllegalStateException, IOException {
		String route = createFileRoute(file);
		String url = suffix + route;
		String path = UPLOADED_FOLDER + route;
		File dest = new File(path);
		if (!dest.getParentFile().exists())
			dest.getParentFile().mkdirs();
		file.transferTo(dest);
		return url;
	}

	/**
	 * 获取文件后缀名
	 * 
	 * @param file
	 *            文件
	 * @return 文件后缀名
	 */
	private static String getSuffix(MultipartFile file) {
		String name = file.getOriginalFilename();
		String suffix = name.substring(name.lastIndexOf("."));
		return suffix;
	}

	/**
	 * 生成文件基本路径
	 * 
	 * @param file
	 *            文件
	 * @return 文件基本路径
	 */
	private static String createFileRoute(MultipartFile file) {
		String uuid = UUID.randomUUID().toString().replace("-", "");
		String suffix = getSuffix(file);
		String time = DateTime.nowDate();
		String url = time + "/" + uuid + suffix;
		return url;
	}
}