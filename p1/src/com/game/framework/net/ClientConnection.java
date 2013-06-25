package com.game.framework.net;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.badlogic.gdx.Gdx;

public class ClientConnection {

	private Socket socket;
	private OutputStream outputStream;
	private InputStream inputStream;

	public void connectTo(String port, String ip) {
		try {
			socket = new Socket(ip, Integer.parseInt(port));
			outputStream = socket.getOutputStream();
			inputStream = socket.getInputStream();
			Gdx.app.log("Client Succesfully Connected to ", ""
					+ socket.getInetAddress().getHostAddress());
			//Thread thread = new Thread(this);
			//thread.start();
			//isConnected = true;
			//this.ip = getLocalIpAddress();
		} catch (UnknownHostException e) {
			Gdx.app.log("Connection failed ","Unknown Host "+ip);
		} catch (Exception e) {
			for(StackTraceElement element:e.getStackTrace()) {
				Gdx.app.log("", element.toString()	);
			}
			e.printStackTrace();
		}
		
	}
}
