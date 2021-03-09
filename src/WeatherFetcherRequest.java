import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import java.net.*;
import java.util.Map;
import java.io.*;

public class WeatherFetcherRequest
{
    private String zipCode;
    private String cityState;
    private String units;
    private int mode;

    //API connection constants
    private static final String URL = "https://api.openweathermap.org/data/2.5/weather";
    private static final String API_KEY = "3c38c4cc9274664fadb44d2bf3d80298";

    //Mode constants
    public static final int ZIP_CODE = 1;
    public static final int CITY_STATE = 2;

    //Unit constants
    public static String IMPERIAL = "imperial";
    public static String METRIC = "metric";
    public static String STANDARD = "standard";

    public void setMode(int mode)
    {
        this.mode = mode;
    }

    public String validateZipCode(String zip)
    {
        //check for valid length first
        if (zip.length() != 5)
        {
            return "Invalid Length: Zip Code must be exactly 5 digits";
        }

        //if we survive the parseInt() method without an exception we have all digits
        try
        {
            Integer.parseInt(zip);
        }
        catch(Exception e)
        {
            return "Invalid characters: Zip Code must include digits only!";
        }

        //null will signify no error so succussful validation
        return null;
    }

    public String setZipCode(String zipCode)
    {
        String valid = validateZipCode(zipCode);

        if(valid == null)
        {
            this.zipCode = zipCode;
        }

        return valid;
    }

    public String validateCityState(String cityState)
    {
        return null;
    }

    public String setCityState(String cityState)
    {
        String valid = validateCityState(cityState);

        if(valid == null)
        {
            this.cityState = cityState;
        }
        return valid;
    }

    public String validateUnits(String units)
    {
        if (units.equals(IMPERIAL) || units.equals(STANDARD) || units.equals(METRIC))
        {
            return null;
        }
        else
        {
            return "Invalid Units: Must be either " + IMPERIAL + ", " + STANDARD + " or " + METRIC;
        }
    }

    public String setUnits(String units)
    {
        String valid = validateUnits(units);

        if(valid == null)
        {
            this.units = units;
        }

        return valid;
    }

    public WeatherFetcherResponse getWeather() throws Exception
    {

        //create a string representing the url with the appropriate parameters
        String requestURL;
        if(this.mode == ZIP_CODE)
        {
            requestURL = URL + "?zip=" + zipCode;
        }
        else
        {
            requestURL = URL + "?q=" + cityState;
        }

        //add the unit and api key
        requestURL += "&units=" + units + "&APPID=" + API_KEY;

        URL url = new URL(requestURL);
        System.out.println(requestURL);

        //this is the request to our URL and it returns an text input stream that we can read from
        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream()));

        JSONParser parser = new JSONParser();
        JSONObject weatherData = (JSONObject) parser.parse(in);

        Map mainWeatherData = (Map)weatherData.get("main");
        Double temperature = (Double)mainWeatherData.get("temp");

        //build the response object and return it
        WeatherFetcherResponse response = new WeatherFetcherResponse(temperature);
        return response;
    }

}
