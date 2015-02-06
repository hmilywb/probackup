package com.xahl.weixin;

/**
 * 微信接口配置
 * @author mengjie
 * 2014-12-19 下午03:38:40
 */
public class Configura {
	/**APPID**/
	public static final String _APPID = "wxf7bc496ae5d08b74"; 
	/**APPSECRET**/
	public static final String _APPSECRET = "c1086987cffbb009c872a9a4730b3d76";
	/**获取access_token**/
	public static final String GET_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	/**上传图片**/
	public static final String UPLOAD_PIC_URL = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=image";
	/**上传图文**/
	public static final String UPLOAD_TEXT_PIC_URL = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=ACCESS_TOKEN";
	/**发送纯文本**/
	public static final String SEND_TEXT_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN";
	/**发送图文**/
	public static final String SEND_TEXT_PIC_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN";
	/**获取分组**/
	public static final String GET_ALLGROUPS_URL = "https://api.weixin.qq.com/cgi-bin/groups/get?access_token=ACCESS_TOKEN";
}
