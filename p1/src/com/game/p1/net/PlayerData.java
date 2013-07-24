package com.game.p1.net;

import org.json.JSONObject;

public class PlayerData extends Data {

	public static final String KEY = "pd";
	public static final String NAME = "name";
	
	private String name;

	public PlayerData(JSONObject object) {
		super(object);
		try {
			setName(object.getString(NAME));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public PlayerData() {
		super(KEY);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public JSONObject getJSON() {
		JSONObject object = new JSONObject();
		try {
			object.put(TYPE, getType());
			object.put(NAME, getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}
}
