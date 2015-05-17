package ro.pub.cs.systems.pdsd.practicaltest02;

public class WeatherForecastInformation {
	String temperature;
	String windSpeed;
	String condition;
	String pressure;
	String humidity;
	public WeatherForecastInformation(String temperature,String windSpeed, String condition, String pressure, String humidity){
		 this.temperature = temperature;
		 this.windSpeed = windSpeed;
		 this.condition = condition;
		 this.pressure = pressure;
		 this.humidity = humidity;
	 }
	public String getTemperature() {
		// TODO Auto-generated method stub
		return "Temperature: " + temperature;
	}
	public String getWindSpeed() {
		// TODO Auto-generated method stub
		return "Wind Speed: " + windSpeed;
	}
	public String getCondition() {
		// TODO Auto-generated method stub
		return "Condition: "+condition;
	}
	public String getHumidity() {
		// TODO Auto-generated method stub
		return "Humidity: "+humidity;
	}
	public String getPressure() {
		// TODO Auto-generated method stub
		return "Pressure: " + pressure;
	}
	@Override
	public String toString(){
		return  this.getTemperature() + "\n" +
				this.getWindSpeed() + "\n"   +
				this.getCondition() + "\n"   +
				this.getHumidity() + "\n" +
				this.getPressure() + "\n";
				
				
		
	}

}
