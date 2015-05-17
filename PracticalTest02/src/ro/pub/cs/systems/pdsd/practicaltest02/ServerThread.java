package ro.pub.cs.systems.pdsd.practicaltest02;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class ServerThread extends Thread{
	private int port = 0;
	private ServerSocket serverSocket = null;
	private HashMap<String, WeatherForecastInformation> data = null;

	
	public synchronized void setData(String city, WeatherForecastInformation weatherForecastInformation) {
		  this.data.put(city, weatherForecastInformation);
		}
		 
	public synchronized HashMap<String, WeatherForecastInformation> getData() {
		  return data;
	}
	public ServerThread(int port){
		
		try {
			this.port = port;
			this.serverSocket = new ServerSocket(port);
			this.data = new HashMap<String, WeatherForecastInformation>();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void stopThread(){
		if(serverSocket != null){
			interrupt();
		}
		try {
			if(serverSocket != null)
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void run() {
		try {
			while(!Thread.currentThread().isInterrupted()){

				Socket socket = serverSocket.accept();
				CommunicationThread communicationThread = new CommunicationThread(this, socket);
				communicationThread.start();
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
