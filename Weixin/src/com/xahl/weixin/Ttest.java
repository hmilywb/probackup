package com.xahl.weixin;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;




public class Ttest {

	public static void main(String[] args) {
		
//		StringBuffer sb = new StringBuffer();
		String Url="http://www.xazxc.com/serve/getPositionList";
		System.out.println("start get url..");
		String response= HttpUtils.responseHttpURLConnection("POST", Url, "");
		System.out.println("get url end,start to json...");
		if(response.endsWith("]")){
			
			JSONArray myJson=JSONArray.fromObject(response);
			System.out.println("json done..");
			for(int i=0;i<myJson.size();i++){
				JSONObject jsonTemp=myJson.getJSONObject(i);
				if(jsonTemp.get("pId").equals("5570")||jsonTemp.get("pId").equals("5566")){
					System.out.println(jsonTemp.get("name")+"\t现存-"+jsonTemp.get("bikeNum")+",空位-"+jsonTemp.get("blankNum"));
				}
				
			}
		}else{
			System.out.println("no right result");
			System.out.println(response);
		}
		
		System.out.println("all end");

	}
	
	
	public static class HttpUtils {
		
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
}



