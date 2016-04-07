package code.game.utils;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import code.game.models.MapModel;

/**
 * Guys use the following methods for logging where appropriate
 * 
 * 
 *      Util.logGlobal(logText, addDate)  >> used to log global events
		Util.logTower(towerName, logText); >> used to log individual tower events
		Util.logTowerCollective(logText, addDate); >> used to log overall tower events 
		Util.logWave(logText) >> used to log any wave related events
 * 
 * 
 * I have already used these in some places, you can see them working in their respective log files
 * 
 */


/**
 * a general purpose file performing all the general functions e.g
 * - file writing
 * - file reading
 * - displaying dialogs
 * - contains constants
 * - log methods accessible globally
 * 
 * @author Armaghan
 * @author Iftikhar
 * @version 1.0.0
 */
public class Util {

	public static final int TOWER_1 = 1;
	public static final int TOWER_2 = 2;
	public static final int TOWER_3 = 3;

	public static final int FILE_LOG_TOWER_1 = TOWER_1;
	public static final int FILE_LOG_TOWER_2 = TOWER_2;
	public static final int FILE_LOG_TOWER_3 = TOWER_3;

	public static final String FILE_LOG_TOWER_COLLECTIVE = "log_tower_collective";
	public static final String FILE_LOG_WAVE = "log_wave";
	public static final String FILE_LOG_GLOBAL = "log_global";



	/**
	 * returns default directory's path where all the files are stored/read/written
	 * @return string containing path to default directory folder
	 */
	public static String getDefaultPath(){
		return System.getProperty("user.dir") + "/";
	}

	/**
	 * returns default directory for logs
	 * @return string containing path to logs folder
	 */
	public static String getLogsPath(){
		return getDefaultPath()+"logs/";
	}

	/**
	 * returns default directory for saved maps
	 * @return string containing path to maps folder
	 */
	public static String getMapsDirectory(){
		return getDefaultPath()+"maps/";
	}

	/**
	 * appends 'log' to a specified title
	 * @param title a string title to be appended
	 * @return title modified
	 */
	public static String getLogDialogTitle(String title){
		switch (title) {
		//		case FILE_LOG_TOWER_1:
		//			return "Castle Tower log";
		//		case FILE_LOG_TOWER_2:
		//			return "Imperial Tower log";
		//		case FILE_LOG_TOWER_3:
		//			return "Industrial Tower log";
		case FILE_LOG_TOWER_COLLECTIVE:
			return "Collective Tower log";
		case FILE_LOG_WAVE:
			return "Wave log";
		case FILE_LOG_GLOBAL:
			return "Global log";

		default:
			return getDefaultPath()+"logs/";
		}
	}

	/**
	 * return's tower's title based on tower's ID
	 * @param towerID tower ID which distinguishes each tower uniquely
	 * @return tower's title in string
	 */
	public static String getTowerTitle(int towerID){
		switch (towerID) {
		case FILE_LOG_TOWER_1:
			return "Castle Tower";
		case FILE_LOG_TOWER_2:
			return "Imperial Tower";
		case FILE_LOG_TOWER_3:
			return "Industrial Tower";
		default:
			return "Tower";
		}
	}


	/**
	 * returns today's time date day in a string
	 * @return today's time date day in a string fromat
	 */
	public static String getDate(){
		Date date = new Date();
		return date.toString();
	}

	/**
	 * adds current time and date to a string provided
	 * @param logText text string to add date to
	 * @return modified text string with date appended
	 */
	public static String addDate(String logText){
		return getDate() + "  ------  " + logText;
	}


	/**
	 * returns file path based on file name
	 * @param fName file name to be 
	 * @return file path
	 */
	public static String getFilePath(String fName){
		fName = fName.replaceAll(" ", "_");
		String path = getLogsPath() + fName + ".txt";
		return path;
	}


	/**
	 * writes log information related to individual towers
	 * @param towerName for selected tower
	 * @param logText for game
	 * @return logtower
	 */
	public static boolean logTower(String towerName, String logText){
		logText = addDate(logText);
		boolean wasWritten = false;
		wasWritten = writeLog(getFilePath(towerName), logText);
		wasWritten = logTowerCollective(logText, false);
		return wasWritten;
	}

	/**
	 * calls a dialog to view individual tower log
	 * @param towerName of selected tower
	 */
	public static void showLogTower(String towerName){
		showLog(towerName+ " log", readLog(getFilePath(towerName)));
	}


	/**
	 * writes log information related to collective tower log
	 * @param logText log info to be saved
	 * @param addDate specifies whether or not a date should be appended in the text string
	 * @return log file
	 */
	public static boolean logTowerCollective(String logText, boolean addDate){
		if(addDate)
			logText = addDate(logText);
		boolean wasWritten = false;
		wasWritten = writeLog(getFilePath(FILE_LOG_TOWER_COLLECTIVE), logText);
		wasWritten = logGlobal(logText, false);
		return wasWritten;
	}

	/**
	 * calls a method to view a dialog containing collective tower log information
	 */
	public static void showLogTowerCollective(){
		showLog(getLogDialogTitle(FILE_LOG_TOWER_COLLECTIVE), readLog(getFilePath(FILE_LOG_TOWER_COLLECTIVE)));
	}

	/**
	 * writes log information related to waves
	 * @param logText log text to be written in the file
	 * @return log text
	 */
	public static boolean logWave(String logText){
		logText = addDate(logText);
		boolean wasWritten = false;
		wasWritten = writeLog(getFilePath(FILE_LOG_WAVE), logText);
		wasWritten =  logGlobal(logText, false);

		return wasWritten;
	}

	/**
	 *  calls a dialog to display wave log information specifies two parameters in the showLog method
	 * - wave log title
	 * - content read from wave log file to be displayed
	 */
	public static void showLogWave(){
		showLog(getLogDialogTitle(FILE_LOG_WAVE), readLog(getFilePath(FILE_LOG_WAVE)));
	}

	/**
	 * saves global log data in a file
	 * @param logText log data to be saved
	 * @param addDate specifies if a date needs to be appended in the log line
	 * @return file log
	 */
	public static boolean logGlobal(String logText, boolean addDate){
		if(addDate)
			logText = addDate(logText);
		return writeLog(getFilePath(FILE_LOG_GLOBAL), logText);
	}

	/**
	 * calls a dialog to display global log information specifies two parameters in the showLog method
	 * - global log title
	 * - content read from global log file to be displayed
	 */
	public static void showLogGlobal(){
		showLog(getLogDialogTitle(FILE_LOG_GLOBAL), readLog(getFilePath(FILE_LOG_GLOBAL)));
	}




	/**
	 * reads log tile with the provided name
	 * @param logFile name of log file to be read
	 * @return returns log data of specified file
	 */
	public static String readLog(String logFile){

		String line = null;
		InputStream inputStream;
		InputStreamReader inputStreamReader;
		BufferedReader bufferReader = null;


		String logData = "";

		File file = new File (logFile);
		file.getParentFile().mkdirs();
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {

			inputStream = new FileInputStream(file);
			inputStreamReader = new InputStreamReader(inputStream);
			bufferReader = new BufferedReader(inputStreamReader);
			while ((line = bufferReader.readLine()) != null) {
				logData += line + "\n";
			}

		}catch (IOException e1) {
			return null;
		}finally{
			try {
				bufferReader.close();
			} catch (IOException e) {
				e.printStackTrace();

			}
		}

		return logData;

	}


	/**
	 * writes/appends log information to specified file
	 * @param logFile file to write log data
	 * @param logData log information to be written in a file
	 * @return boolean
	 */
	public static boolean writeLog(String logFile, String logData) {
		try {
			File file = new File(logFile);
			file.getParentFile().mkdirs();
			if (!file.exists()) {
				file.createNewFile();
			}
			BufferedWriter bw = new BufferedWriter(new FileWriter(file, true)); // append the result

			bw.write(logData);
			bw.newLine();
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	/**
	 * a general purpose dialog box used to display all kinds of logs e.g.
	 * - Tower log
	 * - Collective tower log
	 * - Global log
	 * - Wave log
	 * 
	 * @param dialogTitle text containing one of the above listed titles
	 * @param logText string containing all the log info to be displayed
	 */
	public static void showLog(String dialogTitle, String logText){
		JTextArea textArea = new JTextArea(logText);
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);  
		textArea.setLineWrap(true);  
		textArea.setWrapStyleWord(true); 
		//Font font = new Font("logFont", Font.HANGING_BASELINE , 18);
		//textArea.setFont(font);
		//textArea.setColumns(10);
		scrollPane.setPreferredSize( new Dimension( 1000, 500 ) );
		JOptionPane.showMessageDialog(null, scrollPane, dialogTitle, JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * a general dialog box displays message containing a passed text.
	 * @param text message to be displayed in the dialog box
	 */
	public static void showDialog(String text){
		JOptionPane.showMessageDialog(null, text, "Message", JOptionPane.INFORMATION_MESSAGE);	
	}

	/**
	 * a general dialog box displays message containing a passed text.
	 * @param title display in the dialog box
	 * @param text message to be displayed in the dialog box
	 */
	public static void showDialog(String title, String text){
		JOptionPane.showMessageDialog(null, text, title, JOptionPane.INFORMATION_MESSAGE);	
	}


	/**
	 * displays history, edit date, creation date and top 5 scorers in a dialog
	 * @param map map object containing all the information to be displayed
	 */
	public static void showMapLog(MapModel map){

		if(map==null){
			showDialog("Please load a map first!");
			return;
		}

		String text = "";

		text += "CREATION TIME :\n" + map.getCreationTime() + "\n\n"
				+ "LAST EDITED :\n" + getEditHistory(map.getEditHistory()) + "\n\n"
				+ "TOP 5 SCORES :\n"  + getTopFiveScores(map.getPlayHistory()) + "\n\n\n"
				+ "GAMEPLAY HISTORY :\n" + getPlayHistory(map.getPlayHistory());


		JTextArea textArea = new JTextArea(text);
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);  
		textArea.setLineWrap(true);  
		textArea.setWrapStyleWord(true); 
		//Font font = new Font("logFont", Font.HANGING_BASELINE , 18);
		//textArea.setFont(font);
		//textArea.setColumns(10);
		scrollPane.setPreferredSize( new Dimension( 400, 600 ) );
		JOptionPane.showMessageDialog(null, scrollPane, "Map Statistics", JOptionPane.INFORMATION_MESSAGE);
	}


	/**
	 * returns all the game events associated with the current map file
	 * @param playHistory ArrayList object containing all the game play events
	 * @return history converted in a single string object to be displayed in a dialog
	 */
	public static String getPlayHistory(ArrayList<String> playHistory) {
		if(playHistory!=null && playHistory.size()>0){
			String history = "";
			int i = 0;
			for(String text : playHistory){
				history += ++i + " - " + text + "\n";
			}
			return history;
		}else{
			return "No history found for this map!";
		}

	}
	
	public static String getEditHistory(ArrayList<String> EditHistory) {
		if(EditHistory!=null && EditHistory.size()>0){
			String history = "";
			int i = 0;
			for(String text : EditHistory){
				history += ++i + " - " + text + "\n";
			}
			return history;
		}else{
			return "No edits found for this map!";
		}

	}

	/**
	 * returns the top 5 scores of a map.
	 * gets all the history data as a parameter and extracts top five scorers
	 * with time and date of the game played
	 * @param playHistory ArrayList object containing the history of map'f file
	 * @return returns only top 5 scorers sorted in an asceding order
	 */
	public static String getTopFiveScores(ArrayList<String> playHistory) {
		if(playHistory!=null && playHistory.size()>0){
			ArrayList<Double> tempScores = new ArrayList<Double>();
			for(int i = 0; i<playHistory.size(); i++){
				if(playHistory.get(i).contains("::")){
					String score = playHistory.get(i).split("::")[1] + "." + i;
					tempScores.add(Double.parseDouble(score.trim()));
				}
			}
			Collections.sort(tempScores);
			Collections.reverse(tempScores);
			String topFiveScores = "";
			for(int i = 0; i<tempScores.size(); i++){
				if(i>=5){
					break;
				}

				String score = Double.toString(tempScores.get(i));
				if(score.contains(".")){
					score = score.split("\\.")[1];
					topFiveScores+= (i+1) + " - " + playHistory.get(Integer.parseInt(score)) + "\n";
				}
			}
			return topFiveScores;
		}else{
			return "No history found for this map!";
		}

	}


	/**
	 * updates map file whenever a wave is finished.
	 * Updates data about :
	 * - Top 5 scores 
	 * - Play history with time and date
	 * - Map edit time
	 * 
	 * and writes it to the map file using fileOutputStream 
	 * @param mapModel object of MapModel class containing all the updated information.
	 * @return true if the changes were saved successfully; false otherwise
	 */
	public static boolean updateMapFile(MapModel mapModel){
		try {
			int[][] map = mapModel.getMapArray();
			for(int i = 0; i < map.length; i++) {
				for(int j = 0; j < map[i].length; j++) {
					if(map[i][j] <0 ) {
						map[i][j] = 0;
					}
				}
			}
			
			File file = mapModel.getFilePath();
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos); 
			oos.writeObject(mapModel);
			oos.close();
			fos.close();
		}catch(IOException ioe){
			ioe.printStackTrace();
			return false;
		}
		return true;
	}



}
