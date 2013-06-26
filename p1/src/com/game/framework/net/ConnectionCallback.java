package com.game.framework.net;

import org.json.JSONObject;
/*
 * interface that act as a receiver.  every time the connection receives
 * an update, the method obtainedData() will be call.
 */

public interface ConnectionCallback {

	/**
	 * this method will call by the connection when receiving data
	 * @param data - the data received by the connection
	 */
	public void obtainedData(JSONObject data);
	
	/**
	 * this method is call if something wrong with the connection
	 * i.e. crash
	 * @param err - the error occur
	 */
	public void connectionCrash(String err);
}
