import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Converts transcripts from Udacity courses into a formatted txt file.
 * Date: June 2016
 * @author lilyz622
 *
 */

public class UdacityTranscriptConverter {
	public static File folder = null;;
	public static void main(String[] args) throws FileNotFoundException{
		
		Logger.getGlobal().setLevel(Level.INFO);
		Logger.getGlobal().setLevel(Level.OFF);
		
		//Get all files		
		folder = new File("\\C:\\Users\\yzhan265\\Documents\\CUS\\Android Udacity\\Lesson 1- Create Project Sunshine Subtitles");
		Logger.getGlobal().info(folder.getName());
		
		File[] listOfInputFiles = folder.listFiles();
		
		File[] txtFiles = srtToTxt(listOfInputFiles);
		Logger.getGlobal().info(txtFiles.length+"");
		
		for (int i = 0; i < txtFiles.length; i++){
			formatTxtFile(txtFiles[i]);
		}
		
		
	}
	
	//Converts files into text files.
	public static File[] srtToTxt(File[] srtFiles){
		File[] txtFiles = new File[srtFiles.length];
		
		for (int i = 0; i<srtFiles.length; i++){
			Logger.getGlobal().info("File name:"+srtFiles[i].getName());
			if (srtFiles[i].getName().endsWith(".srt")){
				//Create name with txt ending
				String newName = srtFiles[i].getName().substring(0, srtFiles[i].getName().length()-3) + ".txt";
				
				//renames srt file into txt file
				txtFiles[i] = new File(folder.getAbsolutePath()+File.separator+newName);
				Logger.getGlobal().info(txtFiles[i].getAbsolutePath());
				Logger.getGlobal().info("new name: "+txtFiles[i].getName());
				srtFiles[i].renameTo(txtFiles[i]);
			}
			else {
				Logger.getGlobal().info("ALREADY TXT: "+srtFiles[i].getName());
				txtFiles[i] = srtFiles[i];
			}
		}
		return txtFiles; 
	}
	
	//Parses text file into desired format.
	public static void formatTxtFile(File txtFile) throws FileNotFoundException{
		Scanner in = new Scanner(txtFile);
		String newName = "FORMATTED-"+txtFile.getName().substring(0, txtFile.getName().length());
		Logger.getGlobal().info(newName);
		File formattedTxtFile = new File(folder.getAbsolutePath()+File.separator+newName);
		PrintWriter out = new PrintWriter(formattedTxtFile);
		
		int lineCounter = 1;
		while (in.hasNextLine()){
			if (lineCounter%3 ==1){
				String time = in.nextLine();
			}
			else if (lineCounter%3 ==2){
				String words = in.nextLine();
				out.print(words+" ");
			}
			else{
				String blank = in.nextLine();
			}
			lineCounter++;
		}
		in.close();
		out.close();
	}
}
