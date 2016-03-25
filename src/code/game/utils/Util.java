package code.game.utils;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class Util {


	public static final int TOWER_1 = 1;
	public static final int TOWER_2 = 2;
	public static final int TOWER_3 = 3;

	public static final int FILE_LOG_TOWER_1 = TOWER_1;
	public static final int FILE_LOG_TOWER_2 = TOWER_2;
	public static final int FILE_LOG_TOWER_3 = TOWER_3;

	public static final int FILE_LOG_TOWER_COLLECTIVE = 4;
	public static final int FILE_LOG_WAVE = 5;
	public static final int FILE_LOG_GLOBAL = 6;



	public static String getDefaultPath(){
		return System.getProperty("user.dir") + "/";
	}

	public static String getLogsPath(){
		return getDefaultPath()+"logs/";
	}


	public static String getLogFile(int fileNo){

		switch (fileNo) {

		case FILE_LOG_TOWER_1:
			return getLogsPath() + "log_tower_1.txt";
		case FILE_LOG_TOWER_2:
			return getLogsPath() + "log_tower_2.txt";
		case FILE_LOG_TOWER_3:
			return getLogsPath() + "log_tower_3.txt";
		case FILE_LOG_TOWER_COLLECTIVE:
			return getLogsPath() + "log_tower_collective.txt";
		case FILE_LOG_WAVE:
			return getLogsPath() + "log_wave.txt";
		case FILE_LOG_GLOBAL:
			return getLogsPath() + "log_global.txt";

		default:
			break;
		}


		return getDefaultPath()+"logs/";
	}
	
	public static String getLogDialogTitle(int fileNo){

		switch (fileNo) {

		case FILE_LOG_TOWER_1:
			return "Industrial Tower log";
		case FILE_LOG_TOWER_2:
			return "Industrial Tower log";
		case FILE_LOG_TOWER_3:
			return getLogsPath() + "log_tower_3.txt";
		case FILE_LOG_TOWER_COLLECTIVE:
			return getLogsPath() + "log_tower_collective.txt";
		case FILE_LOG_WAVE:
			return getLogsPath() + "log_wave.txt";
		case FILE_LOG_GLOBAL:
			return getLogsPath() + "log_global.txt";

		default:
			break;
		}


		return getDefaultPath()+"logs/";
	}


	public static String getDate(){
		Date date = new Date();
		return date.toString();
	}
	
	public static String addDate(String logText){
		return getDate() + "    " + logText;
	}



	public static void logTower(int towerID, String logText){
		logText = addDate(logText);
		writeLog(getLogFile(towerID), logText);
		logTowerCollective(logText);
		logGlobal(logText);
	}

	public static void logTowerCollective(String logText){
		writeLog(getLogFile(FILE_LOG_TOWER_COLLECTIVE), logText);
	}

	public static void showLogTowerCollective(){
		showLog(readLog(getLogFile(FILE_LOG_TOWER_COLLECTIVE)));
	}

	public static void logWave(String logText){
		writeLog(getLogFile(FILE_LOG_WAVE), logText);
		logGlobal(logText);
	}

	public static void logGlobal(String logText){
		writeLog(getLogFile(FILE_LOG_GLOBAL), logText);
	}

	public static void showLogGlobal(){
		showLog(readLog(getLogFile(FILE_LOG_GLOBAL)));
	}





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

		}finally{
			try {
				bufferReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return logData;

	}


	public static void writeLog(String logFile, String logData) {
		try {
			File file = new File(logFile);
			file.getParentFile().mkdirs();
			if (!file.exists()) {
				file.createNewFile();
			}
			BufferedWriter bw = new BufferedWriter(new FileWriter(file, true)); // append the result

			bw.write(logData);
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void showLog(String log){
		JTextArea textArea = new JTextArea(log);
		System.out.println(log);
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);  
		textArea.setLineWrap(true);  
		textArea.setWrapStyleWord(true); 
		//Font font = new Font("logFont", Font.HANGING_BASELINE , 18);
		//textArea.setFont(font);
		//textArea.setColumns(10);
		scrollPane.setPreferredSize( new Dimension( 1000, 500 ) );
		JOptionPane.showMessageDialog(null, scrollPane, "ggggggglog",  
				JOptionPane.YES_NO_OPTION);
	}

}
