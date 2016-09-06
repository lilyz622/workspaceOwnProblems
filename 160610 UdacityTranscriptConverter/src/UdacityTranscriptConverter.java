import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Converts transcripts from Udacity courses into a formatted chosen format file.
 * Date: June 2016
 * @author lilyz622
 *
 */

public class UdacityTranscriptConverter {
	public static File folder = null;;
	public static void main(String[] args) throws IOException{
		
		Logger.getGlobal().setLevel(Level.INFO);
		//Logger.getGlobal().setLevel(Level.OFF);
		
		//Get all files		
		folder = new File("\\C:\\Users\\yzhan265\\Documents\\CUS\\Android Udacity\\Lesson 4B- Content Providers Subtitles");
		Logger.getGlobal().info(folder.getName());
		
		File[] listOfInputFiles = folder.listFiles();
		
		File[] newExtensionFiles = toFileType(listOfInputFiles, "srt","doc");
				
		File[] formattedFiles = formatFiles(newExtensionFiles);
		
		File mergedTranscript = mergeTranscript(formattedFiles);
		
		
	}
	
	/**
	 * merges formatted transcripts into one doc file
	 * @param formattedFiles
	 * @return
	 * @throws FileNotFoundException
	 */
	public static File mergeTranscript(File[] formattedFiles) throws FileNotFoundException {
		String mergedTranscriptName = "Merged Transcript.doc";
		File mergedFile = new File(folder.getAbsolutePath() + File.separator + mergedTranscriptName);
		PrintWriter out = new PrintWriter(mergedFile);
		for (int i = 0; i<formattedFiles.length; i++){
			Scanner in = new Scanner(formattedFiles[i]);
			out.println(formattedFiles[i].getName());
			while (in.hasNextLine()){
				out.print(in.nextLine());
			}
			out.println();
			out.println();
			in.close();
		}
		out.close();
		return null;
	}

	/**
	 * Parses text file into desired format.
	 * @param unformattedFiles
	 * @throws IOException 
	 */
	public static File[] formatFiles(File[] unformattedFiles) throws IOException{
		File[] formattedFiles = new File[unformattedFiles.length];
		for (int i = 0; i < unformattedFiles.length; i++) {
			BufferedReader reader = new BufferedReader(new FileReader(unformattedFiles[i]));
//			Scanner in = new Scanner(unformattedFiles[i]);
			String newName = "FORMATTED-" + unformattedFiles[i].getName().substring(0, unformattedFiles[i].getName().length());
			Logger.getGlobal().info(newName);
			File formattedFile = new File(folder.getAbsolutePath() + File.separator + newName);
			formattedFiles[i] = formattedFile;
			PrintWriter out = new PrintWriter(formattedFile);
			//This is for the old transcript formats.
//			int lineCounter = 1;
//			while (in.hasNextLine()) {
//				if (lineCounter % 3 == 1) {
//					String time = in.nextLine();
//				} else if (lineCounter % 3 == 2) {
//					String words = in.nextLine();
//					out.print(words + " ");
//				} else {
//					String blank = in.nextLine();
//				}
//				lineCounter++;
//			}
			
			//This is for the new transcript formats as of Lesson 4A
			int numbering =1;
			boolean isTimeFrameLine = false;
			while (reader.ready()) {
				String str = reader.readLine().trim();
				if (isTimeFrameLine) {
					String timeFrameLine = str;
					isTimeFrameLine =false;
					continue;
				}
				if (str.equals(Integer.toString(numbering))) {
					numbering++;
					String numberingLine = str;
					isTimeFrameLine = true;
					continue;
				}
				if (!str.isEmpty()) {
					out.print(str+" ");
				}
			}
			reader.close();
			out.close();
		}
		Logger.getGlobal().info(Arrays.toString(formattedFiles));
		return formattedFiles;
	}
	
	/**
	 * Converts files into desired type of file.
	 * @param originalFiles
	 * @param oldExtension
	 * @param newExtension
	 * @return
	 */
	public static File[] toFileType(File[] originalFiles, String oldExtension, String newExtension){
		File[] newExtensionFiles = new File[originalFiles.length];
		int oldExtensionLength = oldExtension.length();
		
		for (int i = 0; i<originalFiles.length; i++){
			Logger.getGlobal().info("File name:"+originalFiles[i].getName());
			if (originalFiles[i].getName().endsWith(oldExtension)){
				//Create name with new extension ending
				String newName = originalFiles[i].getName().substring(0, originalFiles[i].getName().length()-oldExtensionLength) + newExtension;
				
				//renames original file into file with new extension
				newExtensionFiles[i] = new File(folder.getAbsolutePath()+File.separator+newName);
				Logger.getGlobal().info(newExtensionFiles[i].getAbsolutePath());
				Logger.getGlobal().info("new name: "+newExtensionFiles[i].getName());
				originalFiles[i].renameTo(newExtensionFiles[i]);
			}
			else {
				Logger.getGlobal().info("NOT A "+oldExtension.toUpperCase()+" FILE:"+originalFiles[i].getName());
				newExtensionFiles[i] = originalFiles[i];
			}
		}
		return newExtensionFiles; 
	}
}
