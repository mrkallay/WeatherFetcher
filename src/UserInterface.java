import java.util.Scanner;

public class UserInterface
{
    private Scanner input;


    public UserInterface()
    {
        input = new Scanner(System.in);
    }

    /**
     * @return
     */
    public String inputMode()
    {
        String mode = "";
        while (true)
        {
            System.out.println("Enter the desired mode:");
            System.out.println("1) Zip Code");
            System.out.println("2) City, State");
            System.out.println("Enter Q to quit");
            System.out.println();
            mode = input.nextLine();
            if(mode.toUpperCase().equals("Q") || mode.equals("1") || mode.equals("2") )
            {
                //exit state!
                break;
            }
            else
            {
              displayErrorMessage("Invalid Input!!!");
            }
        }

        return mode;
    }


    /**
     * @return
     */
    public String inputZipCode()
    {
        System.out.println("Enter a valid 5 digit zip code:");
        System.out.println();
        String zip = input.nextLine();
        return zip;
    }

    /**
     * @return
     */
    public String inputCityState()
    {
        System.out.println("Enter a City, State :");
        System.out.println("For example: Alameda, CA");
        System.out.println();
        String cityState = input.nextLine();
        return cityState;
    }

    public String inputUnits()
    {
        System.out.println("Enter the desired units:");
        System.out.println("1) " + WeatherFetcherRequest.IMPERIAL);
        System.out.println("2) " + WeatherFetcherRequest.STANDARD);
        System.out.println("3) " + WeatherFetcherRequest.METRIC);
        System.out.println();

        String units = input.nextLine();

        return units;
    }

    public String validateUnits(String units)
    {
        String validUnits = null;
        if(units.equals("1"))
        {
            validUnits = WeatherFetcherRequest.IMPERIAL;
        }
        else if(units.equals("2"))
        {
            validUnits = WeatherFetcherRequest.STANDARD;
        }
        else if(units.equals("3"))
        {
            validUnits = WeatherFetcherRequest.METRIC;
        }
        else
        {
            displayUnitsErrorMessage();
        }
        return validUnits;
    }

    public void displayUnitsErrorMessage()
    {
        String error = "Units must be a number between 1 - 3";
        displayErrorMessage(error);
    }

    public void displayErrorMessage(String msg)
    {
        System.out.println("************ERROR************");;
        System.out.println(msg);
    }

    public void displayWeatherData(double temp)
    {
        System.out.println("Current Temperature: " + temp);
        System.out.println();
    }

}
