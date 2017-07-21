package uploader;

import java.io.File;
import java.net.InetAddress;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

public class UploadUtilities {
	
	public static String uploadFile(HttpServletRequest request) {

		String responseValue = "";
		SimpleDateFormat sdf = new SimpleDateFormat("HH.mm.ss");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);

		try {
			List fileItems = upload.parseRequest(request);
			Iterator i = fileItems.iterator();
			String filePath = "";
			String filePathBackup = "";
			File file;
			File fileBackup;
			FileItem fi;
			String fileName;
			String timeStampFileName = "";

			while (i.hasNext()) {

				fi = (FileItem) i.next();
				fileName = fi.getName();
				timeStampFileName = sdf.format(timestamp) + "_" + fileName;

				if(InetAddress.getLocalHost().getHostName().matches(".*jpcloudusa008.*")) {

					// File path for JavaPipe.

					// Store file on the JavaPipe web server in the Legal Mapping
					// Tool directory.
					filePath = "/chroot/home/ukram123/public_html/webapps/LegalMappingTool_Dev/files/" + timeStampFileName;
					file = new File(filePath);
					fi.write(file);

					filePathBackup = "/chroot/home/ukram123/public_html/legaltoolbackupfiles/";
					fileBackup = new File(filePathBackup);
				    FileUtils.copyFileToDirectory(file, fileBackup);
					
					responseValue = "files/" + timeStampFileName;
					
				} else if(InetAddress.getLocalHost().getHostName().matches(".*xjdz3.*")) {

					// File path for Daily Razor.

					filePath = "/home/ukramcom/tomcat/webapps/ukram123.com/LegalMappingTool_Dev/files/" + timeStampFileName;
					file = new File(filePath);
					fi.write(file);

					filePathBackup = "/home/ukramcom/public_html/legaltoolbackupfiles_dev/";
					fileBackup = new File(filePathBackup);
				    FileUtils.copyFileToDirectory(file, fileBackup);
					
					responseValue = "files/" + timeStampFileName;
					
				}else {
					// File path for local machine.
					filePath = System.getProperty("user.dir") + "\\files\\" + timeStampFileName;
					file = new File(filePath);
					fi.write(file);

					filePathBackup = System.getProperty("user.dir") + "\\filesbackup\\";
					fileBackup = new File(filePathBackup);
				    FileUtils.copyFileToDirectory(file, fileBackup);
					
					responseValue = "file:///C:\\J2EESetup\\eclipse\\files\\" + timeStampFileName;

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return(responseValue);
	}
	
	
	
	public static void initializeFiles() {

		try {

			String filePath = "";
			File file;
		
			if (InetAddress.getLocalHost().getHostName().matches(".*jpcloudusa008.*")) {

				// File path for JavaPipe.

				filePath = "/chroot/home/ukram123/public_html/webapps/LegalMappingTool_Dev/files/";
				file = new File(filePath);
	
				if(!(file.list().length>0)){
							
					File fileBackup = new File( "/chroot/home/ukram123/public_html/legaltoolbackupfiles/");
				    FileUtils.copyDirectory(fileBackup, file);
				}
			} else if(InetAddress.getLocalHost().getHostName().matches(".*xjdz3.*")) {

				// File path for DailyRazor.

				filePath = "/home/ukramcom/tomcat/webapps/ukram123.com/LegalMappingTool_Dev/files/";
				file = new File(filePath);
	
				if(!(file.list().length>0)){
							
					File fileBackup = new File( "/home/ukramcom/public_html/legaltoolbackupfiles_dev/");
				    FileUtils.copyDirectory(fileBackup, file);
				}
				
			} else {
				// File path for local machine.

				filePath = System.getProperty("user.dir") + "\\files\\";
				file = new File(filePath);
	
				if(!(file.list().length>0)){
					
					File fileBackup = new File(System.getProperty("user.dir") + "\\filesbackup\\");
				    FileUtils.copyDirectory(fileBackup, file);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}	
	
}
