package testPackage;

public class FarmerResponse {
    public class Temperature {
    	public double hot;
    	public double cold;
    	public double medium;
    }
    
    public class SkyCondition {
    	public double cloudy;
    	public double sunny;
    	public double raining;
    }
    public class WeatherPerception{
    	public Temperature temperature;
    	public SkyCondition skyCondition;
    }
    
    public class WeatherForecast{
    	public double temperature;
    } 
    public WeatherForecast[] weatherForecast;
    public WeatherPerception weatherPrediction;
    public String[] suggestion;
}
