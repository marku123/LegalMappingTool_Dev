package logs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Logging {

	
	public static void LogStringInFile(String StringToLog) {
		try {

			String filePath = "";
			String fileName;
			String fileWithPath;
			File file;
			Date date= new Date();
			SimpleDateFormat filenameFormat = new SimpleDateFormat("yyyy-MM-dd");
			fileName = "Log_" + filenameFormat.format(new Timestamp(date.getTime())) + ".txt"; 

			if (InetAddress.getLocalHost().getHostName().matches(".*xjdz3.*")) {
				// File path for DailyRazor.

				filePath = "/home/ukramcom/public_html/legaltoollogs_dev/";
				
			} else if(InetAddress.getLocalHost().getHostName().matches(".*jpcloudusa008.*")) {
				// File path for JavaPipe.

				filePath = "/chroot/home/ukram123/public_html/legaltoollogs_dev/";


			} else {
				// File path for my Desktop.

				filePath = "C:\\J2EESetup\\eclipse\\logs\\";

			}

			fileWithPath = filePath + fileName;
			file = new File(fileWithPath);
			
			if(!file.exists()) {
     			file.createNewFile();
			} 
			
			FileWriter fw = new FileWriter(fileWithPath,true);
			fw.write(StringToLog + "\n\n");
			fw.close();

			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
