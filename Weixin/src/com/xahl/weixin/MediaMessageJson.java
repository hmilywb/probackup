package com.xahl.weixin;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

public class MediaMessageJson {
	private List<MediaMessage> Articles = new ArrayList<MediaMessage>();//	是	图文消息，一个图文消息支持1到10条图文

	public List<MediaMessage> getArticles() {
		return Articles;
	}

	public void setArticles(List<MediaMessage> articles) {
		Articles = articles;
	}

	public static void main(String[] args) {
		MediaMessageJson mm = new MediaMessageJson();
		MediaMessage mg = new MediaMessage();
		mg.setAuthor("1");mg.setContent("2");mg.setContent_source_url("3");mg.setDigest("4");mg.setShow_cover_pic("5");
		mg.setThumb_media_id("6");mg.setTitle("7");
		mm.getArticles().add(mg);
		mm.getArticles().add(mg);
		String result = JSONObject.fromObject(mm).toString();
		System.out.println(result);
	}
}
