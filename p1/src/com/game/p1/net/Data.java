package com.game.p1.net;

import org.json.JSONObject;


public class Data {
	
	public static final String TYPE = "T";

	private JSONObject object;

	public Data(JSONObject object) {
		this.object = object;
	}
	
	public Data(String type) {
		try{
		this.object = new JSONObject();
		object.put(TYPE, type);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JSONObject getJSON() {
		return null;
	}
	
	public  String getString() {
		return getJSON().toString();
		
	}
	
	public  byte[] getBytes() {
		return getString().getBytes();
	}
	
	public String getType() {
		String type = "none";
		try{
			type = this.object.getString(TYPE);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return type;
	}
	
}
