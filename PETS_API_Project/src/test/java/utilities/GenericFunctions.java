package utilities;

import java.util.*;
import java.io.*;

public class GenericFunctions{
    public static String getConfigValue(String key)throws Exception{
        FileReader reader = null;
        try {
            reader = new FileReader("./src/test/java/resources/config.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Properties prop = new Properties();
        try {
            prop.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String keyValue = prop.getProperty(key);
        return keyValue;
    }

    public static String getURL(String endPoint, String status)throws Exception{
        String url = null;
        //Read Properties
        String env = GenericFunctions.getConfigValue("env.flag");
        System.out.println("**** Environment Selected: " + env);
        if (env.equals("PetsAPI")) {
            url = GenericFunctions.getConfigValue("api.url");
        } else if(env.equals("STUB")) {
            url = GenericFunctions.getConfigValue("stub.url");
        }
        String endPointValue = GenericFunctions.getConfigValue(endPoint);
        String apiURL = url + endPointValue + status;
        return  apiURL;
    }
}