package com.game.framework.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

import org.json.JSONObject;

import com.badlogic.gdx.utils.Disposable;
import com.game.framework.utils.L;

public class ClientConnection implements Runnable,Disposable{

	private Socket socket;
	private OutputStream outputStream;
	private InputStream inputStream;
	private boolean isConnected;
	private String ip;
	private ConnectionCallback callback;
	public boolean isDebug;
	
	public ClientConnection() {
		// TODO Auto-generated constructor stub
		socket = null;
		outputStream = null;
		inputStream = null;
		isConnected = false;
		isDebug = false;
		this.ip = getLocalIpAddress();
	}
	
	/**
	 * this method is used to attempt to connect to a certain port and ip using TCP
	 * if the attempt is successfully established, a thread will be start and listen for incoming
	 * server response.
	 * @param port - the port which the server listen
	 * @param ip - the ip address or the name of the domain you wish to connect
	 */
	public void connectTo(String port, String ip) {
		if (port == null) throw new IllegalArgumentException("port cannot be null.");
		if (ip == null) throw new IllegalArgumentException("ip cannot be null.");
		try {
			socket = new Socket(ip, Integer.parseInt(port));
			outputStream = socket.getOutputStream();
			inputStream = socket.getInputStream();
			if(isDebug) {
				L.wtf("Client Succesfully Connected to "
					+ socket.getInetAddress().getHostAddress());
			}
			isConnected = true;
			Thread thread = new Thread(this);
			thread.start();
		} catch (UnknownHostException e) {
			L.wtf("Connection failed : Unknown Host "+ip);
			if(callback!=null) callback.connectionCrash("Unknown Host "+ip);
		} catch (Exception e) {
			isConnected = false;
			e.printStackTrace();
			if(callback!=null) callback.connectionCrash("Connection failed to "+ip);
		}
		
	}
	
	public void setCallback(ConnectionCallback callback) {
		this.callback = callback;
	}
	
	public String getLocalIpAddress() {
	    try {
	        for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
	            NetworkInterface intf = en.nextElement();
	            for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
	                InetAddress inetAddress = enumIpAddr.nextElement();
	                if (!inetAddress.isLoopbackAddress()) {
	                    return inetAddress.getHostAddress().toString();
	                }
	            }
	        }
	    } catch (SocketException ex) {
	    	ex.printStackTrace();
	        L.e("Error Getting localIpAddress");
	    }
	    return null;
	}
	
	public void close() {
		// TODO Auto-generated method stub
		isConnected = false;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		try {
			if(inputStream!=null)
				inputStream.close();
			if(outputStream!=null)
				outputStream.close();
			if(socket!=null)
				socket.close();
		}  catch(Exception ioEx) {
			ioEx.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			byte[] buffer = new byte[1024];
			
			while(isConnected) { 
				buffer = new byte[1024];
				inputStream.read(buffer);
				if(callback!=null) {
					//must implement object pooling here.
					callback.obtainedData(new JSONObject(new String(buffer)));
				}
			}
			dispose();
		} catch(Exception e) {
			e.printStackTrace();
			if(callback!=null)
				callback.connectionCrash("Server stop responding.");
		} finally {
			isConnected = false;
			dispose();
		}
	}
	
	/**
	 * this method is used for sending data on the server
	 * @param the object to send in JSON format
	 */
	public void sendData(JSONObject object) {
		try {
			outputStream.write(object.toString().getBytes("utf-8"));
		} catch (IOException e) {
			e.printStackTrace();
			L.e("Error Sending data to server.");
			if(callback!=null) callback.connectionCrash("Sending Data failed.");
		}
	}

	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	
	public boolean isConnected() {
		return isConnected;
	}
}
