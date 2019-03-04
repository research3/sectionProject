package com.accp.action.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	// public static String app_id = "2016092800616330";// my:2088102177660606
	// 2016092200569070
	public static String app_id = "2016092200569070";

	// 商户私钥，您的PKCS8格式RSA2私钥
	public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCz2UbabLWeAUuXOxT4Ht1CSSJcQRgpLMS9PDMBMnJjzf+UdSgq/eSDr9sU8n2NP/+7U/3M1Z812I0ZlqaThjAxfOVOq6F2EERQWI6WNHYUoVivKS4W3f5MF7Iz6BK+Ub5Xq/X0eLYK7aIypED29kLDz1rrBVDqhtRld3xt5hhTkRiGul86GZ1Lakcfegb+g5DWBPoh4R8guoA+cG3P8OqfsiqsR/36nnOSNBGPL47BKMfGIF8zhXgsu5oPsvezO+KwIiUt4txe43LxEr2beV0jeskAjiAKt8kbNEBKsDAlNrDSbAjtNdkmDLPqmFNPPWNCBE0j7Jtr7048TZb3oCcLAgMBAAECggEAPsTRZsBULr4y/KghLGvROyY9OMd7QDuguo79GijaJZ0q20x8ZWzRSWih5NCxIxA4F9A7nzbbZzaaIFkk8A4AWfVGLsvWAVeyD/yoXyIWKCMbyZ5suIxoCXSnvFf92rC65uxcXiOXHX9MgbIz/j16lc6T47yqPgAqtvMZ2V1aFifSZUAHIDioA1Dm2Oi9FnfS9e0ybPGHgLurC6cPoEC6gW6n/zi2SxVussNxgkLv7MJGJyLUW0ye5rIeuVixl5uR4s/CbmPZqQBwmkgW0AShLHmOdbMXrvU5PiRNzPaUUIDhX7+KWfjLhmQH8S/i0Ig0XrsCOqBC9YQTG95B1O1lWQKBgQDrX0H96wiH1XcJl6qjb32vMcKG8GaeUBThESiF5ersAhRWYh6mtmRyGl1HNcfusBUwO3Aw2Xw9hTavY7mkZ8C97wgdP+qnobcmuDx4uhreebM8rbEEju97LoBA5ediqJ8AWBMWZ3g69LWLrniyP5NzS9fwRzPTFpNu0GhF8rWAdQKBgQDDnE/7rjm+Gww7FN7YbPooiTqCv7iddtarK96Vd/h3RCTb2IesqmGVe7W/dmYcuq5y3xPuk7AAm58iqUtXvvjN8WwrBtjENsDP22V+u1gaE9FXkmJ4QWHkqFW3ArD+Yne5yGcuyXDeAj+dvVy6QA8Pb1YQwlM02xgyjtsw3KIZfwKBgEdbuOkfgZWU7SL2XWBxY7xirKQ2bBYhF0No7U0y+HcW5k2OL9bBQ1Col5EISBYgQjw+OPRGa3lHUt33sQecAP5UYrRjdWPfDmTpxEOhwJWkdee2jQ1hvE8vOOj0CqOJlbMIALlxIy1UlVnEyojX5380BkKtg5PCq24UNxjFzaslAoGBAJDx2MqT1TGq9SsqUd5pEgzLctHDotb7YiJARZTX6wLlObK2BgLJ1blgi9Itt4h+RBnWdvyH7xjkN80FSVDhr5da114imqj6MXFUDycTRzs7iBmt3M1jFVjL64fKtMAq0rCVcO/YUIwRenY2BOn14bL8Ovf8Jo+58Xo1JPJV6r+dAoGAN1f0o7EixrNqgB9dp5QDazke/P5FsMoO8QYv5XRQUr0InWpxDRPQtDZbdb6jJhpxFmfBnlLn2EAgUcyjTdiqOrlclSzJGMX6SXBp0hMXxZzT63DOTN8FXfOKf6uqWm6nfcTqdkfBYjjHBplAd3o7l6n1Hc6uF+0wjwPkg+dCY2o=";;
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
	public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxau6Frn57TIWW0nbRll8K3rQQTtNI+YaEunyfEq8uhLPVMXHpAXSc48WKnagLD5luUKZwt0IZe3qE2pZi033uX2xfOoTAdUa9q/uuDXHkIKJ9xwEnXICOHXd1zzmTCmFI9jbVz2Oxcs9XuWPfbEWMR/xide9sysbG3qUlis68yLRozTixCHGrtXeLZ6TRhMO8Ob1VxpLd5f1cVEgX/+Ih7wY+Gzi+tpwMxUXvL4uSsy17sTCa4YYkVuPQm+8O7QeNdTOrtibXhP7TQcnJXdb5qLfDRU1tUwJtdlJbqOccnPLO4Uvn2PsCU/ustKRdp6dvhYpREFDDYDY8CrfSzrz8QIDAQAB";

	// 服务器异步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://127.0.0.1:3001/c/lwx/notify_url";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://127.0.0.1:3001/c/lwx/return_url";

	// 签名方式
	public static String sign_type = "RSA2";

	// 字符编码格式
	public static String charset = "utf-8";

	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

	// 支付宝网关
	public static String log_path = "C:\\";

	/**
	 * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
	 * 
	 * @param sWord
	 *            要写入日志里的文本内容
	 */
	public static void logResult(String sWord) {
		FileWriter writer = null;
		try {
			writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");
			writer.write(sWord);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
