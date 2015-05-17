package ro.pub.cs.systems.pdsd.practicaltest02;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class PracticalTest02MainActivity extends Activity {

	//Server
	private EditText 	serverPortEditText;
	private Button		connectButton;
	
	//Client
	private EditText	clientAddressEditText;
	private EditText	clientPortEditText;
	private EditText	cityEditText;
	private Spinner		informationTypeSpinner;
	private	Button		getWeatherForecastButton;
	private TextView	weatherForecastTextView;
	
	private ServerThread 	serverThread;
	private ClientThread	clientThread;
	
	private ConnectButtonClickListener connectButtonClickListener = new ConnectButtonClickListener();
	private class ConnectButtonClickListener implements Button.OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int serverPort = Integer.parseInt(serverPortEditText.getText().toString());
			serverThread = new ServerThread(serverPort);
			serverThread.start();
			
		}
		
	}
	private GetWeatherForecastButtonClickListener getWeatherForecastButtonClickListener = new GetWeatherForecastButtonClickListener();
	private class GetWeatherForecastButtonClickListener implements Button.OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String clientAddress = clientAddressEditText.getText().toString();
			int clientPort = Integer.parseInt(clientPortEditText.getText().toString());
			String city = cityEditText.getText().toString();
			String informationType = informationTypeSpinner.getSelectedItem().toString();
			
			weatherForecastTextView.setText("");
			
			clientThread = new ClientThread(
					clientAddress,
					clientPort,
					city,
					informationType,
					weatherForecastTextView);
			clientThread.start();
			
			
		}
		
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_practical_test02_main);
		
		//Server
		serverPortEditText = (EditText)findViewById(R.id.server_port_edit_text);
		connectButton = (Button) findViewById(R.id.connect_button);
		connectButton.setOnClickListener(connectButtonClickListener);
		//Client
		clientAddressEditText = (EditText)findViewById(R.id.client_address_edit_text);
		clientPortEditText = (EditText)findViewById(R.id.client_port_edit_text);
		cityEditText = (EditText)findViewById(R.id.city_edit_text);
		informationTypeSpinner = (Spinner)findViewById(R.id.information_type_spinner);
		getWeatherForecastButton = (Button)findViewById(R.id.get_weather_forecast_button);
		getWeatherForecastButton.setOnClickListener(getWeatherForecastButtonClickListener);
		weatherForecastTextView = (TextView)findViewById(R.id.weather_forecast_text_view);
	}
	@Override
	protected void onDestroy() {
		if(serverThread != null){
			serverThread.stopThread();
		}
	}

		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.practical_test02_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
		
	
}

