package com.xahl.weixin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Test {
	public static void main(String[] args) throws IOException {
		
		
		
		
		FeatWeixin wx = new FeatWeixin();
		List<MediaImgTextPojo> _mediaItems = new ArrayList<MediaImgTextPojo>();
		MediaImgTextPojo mt = new MediaImgTextPojo();
		
		MediaMessage mg = new MediaMessage();
		String mgContent="";
		
		mg = new MediaMessage();
		mgContent="\t继短信和彩信业务被微信等OTT企业“革命”之后，运营商语音业务也终于受到互联网巨头、终端厂商与移动互联网企业的联手夹击。";
		mgContent+="\n";
		mgContent+="\t12月23日，360宣布推出“免费电话”内测，够拨打全国手机、座机，并且免费支持来电显示。而在此之前，腾讯推出的微信电信本，触宝科技推出触宝电话，中国电信与网易合作推出的易信3.0版本，小米也推出畅聊内侧版本，而百度在收购点心团队之后也曾推出点心拨号一度想进入免费电话领域。";
		mg.setContent(mgContent);
		mg.setDigest("谁将成为运营商语音“革命”的先行者呢？占据运营商收入半壁江山的语音市场会在2015年继续快速萎缩吗？小米、360、腾讯等语音OTT企业又是否会迎来运营商的反击和牵制？拭目以待。");
		mg.setShow_cover_pic("1");
		//mg.setThumb_media_id("6");
		mg.setTitle("继微信之后，运营商又迎来语音OTT“革命”");
		
		
		mt.setImgPath("D:/141935665541365001.JPEG");
		mt.setMediaMessage(mg);
		_mediaItems.add(mt);
		
		//content 1
		mg = new MediaMessage();
		mgContent="\t在上周举行的中国移动全球合作伙伴大会上，移动官方明确表态，“2015年在终端的营销资源及补贴将全部投向4G终端，不再对2G、3G终端进行补贴。”";
		mgContent+="\n";
		mgContent+="\t根据中国移动的调研数据，2014年国内终端整体市场规模预计4.2亿部。值得注意的是，到年底，中移动3G、4G终端预计将达到57%的市场份额。其中，4G手机将突破1亿部，千元智能机占比65%。";
		mgContent+="\n";
		mgContent+="\t虽说3G、4G打包在一起计算，但实质上4G才是主角。中移动预测，2015年国内3G终端市场规模将下降75%。按照中移动公布的目标—4G基站累计将达100万，4G用户明年年底达2.5亿。";
		mgContent+="\n";
		mgContent+="\t“2015年将把终端营销资源全部投向4G终端销售，更加关注终端客户规模和终端带来的客户价值。”中移动市场部总经理高念书表示，未来将进一步优化终端酬金和补贴。";
		mgContent+="\n";
		mgContent+="\t所谓优化，很大程度上是在运营商减少补贴的大浪潮下做出的“妥协”。“运营商把优势资源优化到个别品牌，实质上还是有很大机会的。”一位不愿具名的手机企业负责人向南都记者表示，瘦死的骆驼比马大，运营商渠道在未来一段很长的时间内，仍能够保持30%-40%的市场占有率。这个量已经很大了，关键在于怎样的企业能够获得运营商的垂青。";
		mg.setContent(mgContent);
		mg.setDigest("2014年即将过去，手机产业上下游开始关心2015年运营商将做怎样的调整。如果削减补贴不可逆转，那还会有其他什么方法作为支持。");
		mg.setShow_cover_pic("0");
		//mg.setThumb_media_id("6");
		mg.setTitle("运营商补贴将全部投向4G终端");
		
		mt = new MediaImgTextPojo();
		mt.setImgPath("D:/left-ad1.jpg");
		mt.setMediaMessage(mg);
		_mediaItems.add(mt);
		
//		FeatWeixin wx = new FeatWeixin();
		//获取分组列表
//		List<Groups> groups = wx.getAllGroup();
		
//		List<MediaImgTextPojo> _mediaItems = new ArrayList<MediaImgTextPojo>();
		//上传图文消息
//		MediaMessage mg = new MediaMessage();
//		mg.setAuthor("汇龙科技");
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
		String itReSult = wx.uploadTextAndImg(_mediaItems);
		System.out.println("media_id:"+itReSult);
		//群发图文消息
		String  res = wx.sendImgTextMessageAll(itReSult);
		System.out.println("msg_id:"+res);
		
		//上传图片
//		String uImgResponse = wx.uploadPictrue("C:/Users/mengjie/Desktop/101201065934zg8y4cldbjjl.jpg");
//		System.out.println(uImgResponse);
		
		//发送纯文本信息
//		String sTextResponse = wx.sendTextMessageToAll("good afternoon,gentlemen and gentlewomen!");
//		System.out.println(sTextResponse);
	}
}
