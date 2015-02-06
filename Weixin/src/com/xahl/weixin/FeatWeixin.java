package com.xahl.weixin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;

/**
 * 微信公众号
 * @author mengjie
 * 2014-12-16 下午01:59:00
 */
public class FeatWeixin {
	
	private static  Logger logger =  Logger.getLogger(FeatWeixin.class);

	
	private static final String FLAG_TOKEN = "ACCESS_TOKEN";
	
	private static String _TOKEN = null;
	
	private static Date lastdate = null;
	
	public FeatWeixin(){
		//logger.info("开始获取_TOKEN ");
		//getToken() = getToken(Configura._APPID, Configura._APPSECRET);
		//logger.info("获取_TOKEN 为:"+getToken());
	}

	public static String getToken(){
		synchronized(FeatWeixin.class){
			if(_TOKEN==null){
				System.err.println("获取token");
				_TOKEN = login(Configura._APPID, Configura._APPSECRET);
				lastdate = new Date();
			}else if(lastdate!=null&&(new Date().getTime()-lastdate.getTime())>7000*1000){
				System.err.println("access_token即将过期,更换token");
				_TOKEN = login(Configura._APPID, Configura._APPSECRET);
				lastdate = new Date();
			}
			return _TOKEN;
		}
	}
	
	public static void main(String[] args) throws Exception {
//		FeatWeixin wx = new FeatWeixin();
//		while(true){
//			System.err.println("access_token:"+getToken());
//			Thread.sleep(1000);
//		}
//		System.out.println(Jsoup.connect("https://api.weixin.qq.com/cgi-bin/user/info?access_token="+getToken()+"&openid=oUBdcs1vy2QWggLqYRcaDpE0tO4Q&lang=zh_CN").ignoreContentType(true).execute().body());
		//获取分组列表
//		List<Groups> groups = wx.getAllGroup();
		
//		List<MediaImgTextPojo> _mediaItems = new ArrayList<MediaImgTextPojo>();
//		//上传图文消息
//		MediaMessage mg = new MediaMessage();
//		mg.setAuthor("西安汇龙科技股份有限公司");
//		mg.setContent("公司具有通信信息网络系统集成企业甲级资质、ISO9001质量管理体系认证证书、电信工程专业承包资质、通信用户管线建设企业资质、计算机信息系统集成企业资质、钢结构工程专业承包资质、对外承包工程资格证书、安全生产许可证书、双软认证证书、高新技术企业证书，西安市人民政府授予“守合同、重信用” 企业，西安市工商局授予“西安市著名商标企业”。");
//		mg.setContent_source_url("http://www.xahuilong.com/");
//		mg.setDigest("西安汇龙科技股份有限公司位于西安市高新技术产业开发区，是专业从事移动通信工程、软硬件产品研发及技术服务的高新技术企业。");
//		mg.setShow_cover_pic("1");
//		//mg.setThumb_media_id("6");
//		mg.setTitle("微信公众号测试群发");
//		MediaImgTextPojo mt = new MediaImgTextPojo();
//		mt.setImgPath("C:/Users/mengjie/Desktop/101201065934zg8y4cldbjjl.jpg");
//		mt.setMediaMessage(mg);
//		_mediaItems.add(mt);
//		_mediaItems.add(mt);
//		String itReSult = wx.uploadTextAndImg(_mediaItems);
//		System.out.println("media_id:"+itReSult);
//		//群发图文消息
//		String  res = wx.sendImgTextMessageAll(itReSult);
//		System.out.println("msg_id:"+res);
		
		//上传图片
//		String uImgResponse = wx.uploadPictrue("C:/Users/mengjie/Desktop/20141216143548.jpg");
//		System.out.println(uImgResponse);
		
		//发送纯文本信息
//		String sTextResponse = wx.sendTextMessageToAll("good afternoon,gentlemen and gentlewomen!");
//		System.out.println(sTextResponse);
	}
	
	/**
	 * 根据appid,appsecret获取access_token
	 * @param appId
	 * @param appSecret
	 * @return
	 */
	public static String login(String appId,String appSecret){
		String url = Configura.GET_TOKEN_URL.replaceAll("APPID", Configura._APPID).replaceAll("APPSECRET", Configura._APPSECRET);
		for(int i=0;i<10;i++){
			try {
				String responseJson = Jsoup.connect(url).ignoreContentType(true).execute().body();
				return getAccessToken(responseJson);
			} catch (IOException e) {
				System.err.println("登录失败...重试中...");
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		logger.error("连续10次登陆失败！");
		return null;
	}
	
    /**
     * 登录，获取token认证
     * @param s
     * @return
     */
    private static String getAccessToken(String s) {
    	JSONObject jo = JSONObject.fromObject(s);
        return jo.getString("access_token");
    }
    
    /**
     * 上传图片，返回media-id
     * @param filePath
     * @return
     */
    public String uploadPictrue(String filePath){
    	String url = Configura.UPLOAD_PIC_URL.replaceAll(FLAG_TOKEN, getToken());
    	String response = HttpUtils.responseHttpURLConnection(url, filePath);
    	JSONObject jo = JSONObject.fromObject(response);
    	String media_id=null;
		try {
			media_id = jo.getString("media_id");
		} catch (Exception e) {
			System.err.println("上传失败！");
			System.err.println("error code ： "+response);
		}
		return media_id;
    }
   

    /**
     * 上传图文素材
     * @return
     */
    public String uploadTextAndImg(List<MediaImgTextPojo> _mediaItems){
    	String url = Configura.UPLOAD_TEXT_PIC_URL.replaceAll(FLAG_TOKEN, getToken());
    	MediaMessageJson _mmj = new MediaMessageJson();
    	for(MediaImgTextPojo _mi:_mediaItems){
    		String imgPath = _mi.getImgPath();
    		String imgID = "";
    		if(imgPath!=null&&!imgPath.isEmpty()){
    			imgID = uploadPictrue(imgPath);
    		}
    		_mi.getMediaMessage().setThumb_media_id(imgID);
    		_mmj.getArticles().add(_mi.getMediaMessage());
    	}
    	String json = JSONObject.fromObject(_mmj).toString();
		String response= HttpUtils.responseHttpURLConnection("POST", url, json);
		JSONObject _jo = JSONObject.fromObject(response);
    	String media_id=null;
		try {
			media_id = _jo.getString("media_id");
		} catch (Exception e) {
			System.err.println("上传失败！");
			System.err.println("error code ： "+response);
		}
		return media_id;
		
    }
    
    /**
     * 对所有人群发纯文本信息
     * @param text
     * @return
     */
    public String sendTextMessageToAll(String text){
    	return sendTextMessage(text, null);
    }
    
    
    /**
     * 对指定分组发送纯文本信息
     * @param text
     * @param group_id
     * @return
     */
    public String sendTextMessage(String text,String group_id){
    	String all = "false";
    	if(group_id==null){
    		all = "true";
    		group_id = "";
    	}
    	String url = Configura.SEND_TEXT_MESSAGE_URL.replaceAll(FLAG_TOKEN, getToken());
    	String json = "{\"filter\":{\"is_to_all\":"+all+",\"group_id\":\""+group_id+"\"},\"text\":{\"content\":\""+text+"\"},\"msgtype\":\"text\"}";
    	String response =  HttpUtils.responseHttpURLConnection("POST", url, json);
		JSONObject jo = JSONObject.fromObject(response);
    	String msg_id=null;
		try {
			msg_id = jo.getString("msg_id");
			int errcode = jo.getInt("errcode");
			if(errcode==0){
				logger.info("发送成功,返回json:"+response);
			}
		} catch (Exception e) {
			logger.error("发送失败！返回json: "+response);
		}
		return msg_id;
		
    }
    
    /**
     * 所有粉丝群发图文信息
     * @param _mediaId
     * @return
     */
    public String sendImgTextMessageAll(String _mediaId){
    	return sendImgTextMessageGroup(_mediaId, null);
    }
    
    /**
     * 指定分组群发图文信息
     * @param _mediaId
     * @param group_id
     * @return
     */
    public String sendImgTextMessageGroup(String _mediaId,String group_id){
    	String all = "false";
    	if(group_id==null){
    		all = "true";
    		group_id = "";
    	}
    	String url = Configura.SEND_TEXT_PIC_MESSAGE_URL.replaceAll(FLAG_TOKEN, getToken());
    	String json = "{\"filter\":{\"is_to_all\":"+all+",\"group_id\":\""+group_id+"\"},\"mpnews\":{\"media_id\":\""+_mediaId+"\"},\"msgtype\":\"mpnews\"}";
		String response =  HttpUtils.responseHttpURLConnection("POST", url, json);
		JSONObject jo = JSONObject.fromObject(response);
    	String msg_id=null;
		try {
			msg_id = jo.getString("msg_id");
			int errcode = jo.getInt("errcode");
			if(errcode==0){
				logger.info("发送成功,返回json:"+response);
			}
		} catch (Exception e) {
			logger.error("发送失败！返回json: "+response);
		}
		return msg_id;
    } 
    
    /**
     * 获取所有分组信息
     * @return
     */
    public List<Groups> getAllGroup(){
    	List<Groups> list = new ArrayList<Groups>();
    	String url = Configura.GET_ALLGROUPS_URL.replaceAll(FLAG_TOKEN, getToken());
    	try {
			String response = Jsoup.connect(url).ignoreContentType(true).execute().body().toString();
			JSONObject jo =  JSONObject.fromObject(response);
			JSONArray arr = (JSONArray) jo.get("groups");
			for(Object jso:arr){
				JSONObject jjo = (JSONObject)jso;
				Groups gs = new Groups();
				gs.setId(jjo.getInt("id"));
				gs.setName(jjo.getString("name"));
				gs.setCount(jjo.getString("count"));
				list.add(gs);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
    }
}

