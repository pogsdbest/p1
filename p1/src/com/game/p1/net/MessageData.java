package com.game.p1.net;

import org.json.JSONObject;

public class MessageData extends Data {
	
	public static final String KEY = "md";
	public static final String MESSAGE = "msg";
	
	private String message;
	
	public MessageData(JSONObject object) {
		super(object);
		try {
			setMessage(object.getString(MESSAGE));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public MessageData() {
		super(KEY);
	}

	public String getMessage() {
		return message!=null ? message : "";
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public JSONObject getJSON() {
		JSONObject object = new JSONObject();
		try {
			object.put(TYPE, getType());
			object.put(MESSAGE, getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}

}
