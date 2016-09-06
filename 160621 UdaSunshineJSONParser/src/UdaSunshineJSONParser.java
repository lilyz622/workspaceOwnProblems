import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UdaSunshineJSONParser {
	
	public static void main(String[] args) throws FileNotFoundException, JSONException{
		File jsonFile = new File("VancouverJsonOutput.txt");
		Logger.getGlobal().setLevel(Level.INFO);
		Logger.getGlobal().info(jsonFile.toString());
		
		Scanner in = new Scanner(jsonFile);
		String weatherJsonString= "";
		
		while (in.hasNext()){
			weatherJsonString += in.next();
		}
		
		double maxTempForDay = getMaxTempForDay(weatherJsonString,0);
		Logger.getGlobal().info(maxTempForDay+"");
	}
	
	public static double getMaxTempForDay(String weatherJsonString, int dayIndex) throws JSONException{
		
		JSONObject weatherJsonObject = new JSONObject (weatherJsonString);
		//System.out.println(weatherJsonObject.toString());
		
		JSONArray list =  /*(JSONArray)*/ weatherJsonObject.getJSONArray("list"); 
		//System.out.println(list.toString());
		
		JSONObject desiredDay = /*(JSONObject)*/ list.getJSONObject(dayIndex);
		//System.out.println(desiredDay.toString());
		
		JSONObject listTemp = /*(JSONObject)*/ desiredDay.getJSONObject("temp");
		
		double maxTempForDay = listTemp.getDouble("max");
		
		return maxTempForDay;
		}

}
