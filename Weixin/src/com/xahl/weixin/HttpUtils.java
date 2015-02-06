package com.xahl.weixin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * 网络访问基础类
 * @author mengjie 2014-12-16 下午01:56:53
 */
public class HttpUtils {

	
	/**
	 * 上传文件
	 * @param strUrl
	 * @param filePath
	 * @return
	 */
	public static String responseHttpURLConnection(String strUrl,
			String filePath) {
		HttpURLConnection conn = null;
		StringBuffer sb = new StringBuffer();
		try {
			// 定义数据分隔符
			String boundary = "------------7da2e536604c8";
			File uploadFile = new File(filePath);
			URL url = new URL(strUrl);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("Content-Type",
					"multipart/form-data;boundary=" + boundary);
			conn.setUseCaches(false);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setConnectTimeout(20000000);
			conn.setReadTimeout(600000);
			OutputStream out = conn.getOutputStream();
			out.write(("--" + boundary + "\r\n").getBytes());
			out.write(String
					.format("Content-Disposition: form-data; name=\"media\"; filename=\"%s\"\r\n",
							uploadFile.getName()).getBytes());
			out.write(String.format("Content-Type: %s\r\n\r\n",
					"multipart/form-data").getBytes());
			FileInputStream inf = new FileInputStream(uploadFile);
			byte[] b = new byte[512];
			while ((inf.read(b)) != -1) {
				out.write(b);
			}
			out.write(("\r\n--" + boundary + "--\r\n").getBytes());
			out.flush();
			out.close();
			InputStream in = conn.getInputStream();
			InputStreamReader inStream = new InputStreamReader(in, "utf-8");
			BufferedReader buffer = new BufferedReader(inStream);
			int length = 0;
			char[] buf = new char[1024 * 2048];
			String text = null;
			double sum = 0;
			while ((length = buffer.read(buf)) != -1) {
				sum = sum + length;
				text = new String(buf, 0, length);
				sb.append(text);
			}
			return sb.toString();
		} catch (Exception ex) {
			return null;
		} finally {
			if (null != conn) {
				conn.disconnect();
			}
		}
	}
	
	/**
	 * 请求网络
	 * @param method GET/POST
	 * @param strUrl
	 * @param para Map/String
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String responseHttpURLConnection(String method,
			String strUrl, Object para) {
		HttpURLConnection conn = null;
		StringBuffer sb = new StringBuffer();
		boolean isWrite = false;
		String contentData = "";
		Map<String, String> map = null;
		if (para instanceof Map) {
			map = (Map<String, String>) para;
		} else if (para instanceof String) {
			contentData = (String) para;
		}
		try {
			URL url = new URL(strUrl);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded; charset=UTF-8");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setUseCaches(false);
			conn.setRequestProperty(
					"User-Agent",
					"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.76 Safari/537.36");
			if (method != null
					&& (method.equals("POST") || method.equals("post"))) {
				conn.setDoInput(true);
				conn.setDoOutput(true);
				conn.setRequestMethod("POST");
				// Post 请求不能使用缓存
				conn.setUseCaches(false);
				StringBuffer params = new StringBuffer();
				if (null != map && map.size() != 0) {
					for (String key : map.keySet()) {
						if (key.equals("currentUrl")) {
							continue;
						}
						String value = map.get(key);
						value = value == null ? "" : value;
						params.append(key).append("=").append(value)
								.append("&");
					}
				}
				if (params.length() != 0) {
					String paramsStr = params.toString();
					contentData = paramsStr.endsWith("&") ? paramsStr
							.substring(0, paramsStr.lastIndexOf("&"))
							: paramsStr;

				}
				isWrite = true;
			}
			conn.setConnectTimeout(20000);
			conn.setReadTimeout(60000);
			if (isWrite) {
				OutputStreamWriter out = new OutputStreamWriter(
						conn.getOutputStream(), "UTF-8");
				out.write(contentData.toString());
				out.flush();
				out.close();
			}
			InputStream in = conn.getInputStream();
			InputStreamReader inStream = new InputStreamReader(in, "utf-8");
			BufferedReader buffer = new BufferedReader(inStream);
			int length = 0;
			char[] buf = new char[1024 * 2048];
			String text = null;
			double sum = 0;
			while ((length = buffer.read(buf)) != -1) {
				sum = sum + length;
				text = new String(buf, 0, length);
				sb.append(text);
			}
			return sb.toString();
		} catch (Exception ex) {
			return null;
		} finally {
			if (null != conn) {
				conn.disconnect();
			}
		}
	}
}
