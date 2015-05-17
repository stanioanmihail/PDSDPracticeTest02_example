package ro.pub.cs.systems.pdsd.practicaltest02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import android.provider.SyncStateContract.Constants;
import android.util.Log;
import android.widget.TextView;

public class ClientThread extends Thread{
	private String address;
	private int port;
	private String city;
	private String informationType;
	private TextView weatherForecastTextView;

	private Socket socket;

	public ClientThread(
			String address,
			int port,
			String city,
			String informationType,
			TextView weatherForecastTextView
			){
		this.address = address;
		this.port = port;
		this.city = city;
		this.informationType = informationType;
		this.weatherForecastTextView = weatherForecastTextView;
	}

	@Override
	public void run(){
		try{
			socket = new Socket(address, port);

			BufferedReader bufferedReader = Utilities.getReader(socket);
			PrintWriter printWriter = Utilities.getWriter(socket);

			if(bufferedReader != null && printWriter != null){
				printWriter.println(city);
				printWriter.flush();
				printWriter.println(informationType);
				printWriter.flush();
				String weatherInformation;
				while ((weatherInformation = bufferedReader.readLine()) != null) {
					final String finalizedWeatherInformation = weatherInformation;
					weatherForecastTextView.post(new Runnable() {
						@Override
						public void run() {
							weatherForecastTextView.append(finalizedWeatherInformation + "\n");
						}
					});
				}
			}
			socket.close();
		}catch (IOException ioException) {

			ioException.printStackTrace();
		}
	}
}
