package com.accp.util.third;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ThirdLogin {
	// 第三方平台参数
	private final static String APPID = "15bc4577ae08ad";
	private final static String TOKEN = "b5224f0f06a35d41b0937305fa2e7478";

	/**
	 * 第三方登录
	 * 
	 * @param code
	 *            登陆码
	 * @return 登录信息
	 * @throws Exception
	 */
	public static ThirdInfo thirdlogin(String code) throws Exception {
		String url = ThirdLogin.sendPost("http://open.51094.com/user/auth.html",
				"type=get_user_info&code=" + code + "&appid=" + APPID + "&token=" + TOKEN + "");
		ObjectMapper json = new ObjectMapper();
		ThirdInfo info = json.readValue(url, ThirdInfo.class);
		return info;
	}

	/**
	 * 发送POST请求
	 * 
	 * @param url
	 *            请求地址
	 * @param param
	 *            请求参数
	 * @return 响应信息
	 * @throws IOException
	 */
	private static String sendPost(String url, String param) throws IOException {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			URLConnection conn = realUrl.openConnection();
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			out = new PrintWriter(conn.getOutputStream());
			out.print(param);
			out.flush();
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} finally {
			try {
				if (out != null)
					out.close();
				if (in != null)
					in.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
}