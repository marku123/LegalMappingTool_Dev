package uploader;

import java.io.File;
import java.net.InetAddress;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import logs.Logging;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

public class UploadUtilities {

	public static String uploadFile(HttpServletRequest request) {

		String responseValue = "";
		SimpleDateFormat sdf = new SimpleDateFormat("HH.mm.ss");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String CountryName = "";
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);

		try {
			List<FileItem> items = upload.parseRequest(request);
			// List fileItems = upload.parseRequest(request);

			Iterator<FileItem> iter = items.iterator();
			// Iterator i = fileItems.iterator();

			String filePath = "";
			String filePathBackup = "";
			File file;
			File fileBackup;
			String fileName;
			String timeStampFileName = "";

			while (iter.hasNext()) {
				FileItem item = iter.next();
				
				if (item.isFormField()) {
				//Get the name of the country from the Ajax request.
					
					CountryName = item.getString();
					CountryName = CountryName.replaceAll("\\s+", "");
					Logging.LogStringInFile("Here is the country name pulled from FormData: " + CountryName);

					
				} else {

					fileName = item.getName();
					timeStampFileName = sdf.format(timestamp) + "_" + fileName;
					String timeStampFileNameRemoveSpecial = timeStampFileName.replaceAll("[^\\x00-\\x7F]", "");

					if (InetAddress.getLocalHost().getHostName().matches(".*jpcloudusa008.*")) {

						// File path for JavaPipe.

						// Store file on the JavaPipe web server in the Legal
						// Mapping Tool directory.
						filePath = "/chroot/home/ukram123/public_html/webapps/LegalMappingTool_Dev/files/" + CountryName + "/" + timeStampFileNameRemoveSpecial;
						file = new File(filePath);
						item.write(file);


						filePathBackup = "/chroot/home/ukram123/public_html/legaltoolbackupfiles_dev/" + CountryName + "/" ;
						fileBackup = new File(filePathBackup);
						FileUtils.copyFileToDirectory(file, fileBackup);

						responseValue = "files/" + CountryName + "/" + timeStampFileName;

					} else if (InetAddress.getLocalHost().getHostName().matches(".*xjdz3.*")) {

						// File path for Daily Razor.

						Logging.LogStringInFile("Here is the country name for creating the upload links: " + CountryName);

						
						filePath = "/home/ukramcom/tomcat/webapps/ukram123.com/LegalMappingTool_Dev/files/" + CountryName + "/" + timeStampFileNameRemoveSpecial;
						file = new File(filePath);
						item.write(file);

						filePathBackup = "/home/ukramcom/public_html/legaltoolbackupfiles_dev/" +  CountryName + "/";
						fileBackup = new File(filePathBackup);
						FileUtils.copyFileToDirectory(file, fileBackup);

						responseValue = "files/" + CountryName + "/" + timeStampFileName;

					} else {

						// File path for local machine.
						filePath = System.getProperty("user.dir") + "\\files\\" + CountryName + "\\" + timeStampFileNameRemoveSpecial;
						file = new File(filePath);
						item.write(file);

						filePathBackup = System.getProperty("user.dir") + "\\filesbackup\\" + CountryName + "\\";
						fileBackup = new File(filePathBackup);
						FileUtils.copyFileToDirectory(file, fileBackup);

						responseValue = "file:///C:\\J2EESetup\\eclipse\\files\\" + CountryName + "\\" + timeStampFileName;

					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return (responseValue);
	}

	public static void initializeFiles(String countryName) {

		try {

			countryName = countryName.replaceAll("\\s+", "");
			File fileTomcat = null;
			File fileBackup = null;

			if (InetAddress.getLocalHost().getHostName().matches(".*jpcloudusa008.*")) {

				// File path for JavaPipe.
				fileTomcat = new File("/chroot/home/ukram123/public_html/webapps/LegalMappingTool_Dev/files/" + countryName);
				fileBackup = new File("/chroot/home/ukram123/public_html/legaltoolbackupfiles_dev/" + countryName);

			} else if (InetAddress.getLocalHost().getHostName().matches(".*xjdz3.*")) {

				// File path for DailyRazor.
				fileTomcat = new File("/home/ukramcom/tomcat/webapps/ukram123.com/LegalMappingTool_Dev/files/" + countryName);
				fileBackup = new File("/home/ukramcom/public_html/legaltoolbackupfiles_dev/" + countryName);

			} else {
				// File path for local machine.

				fileTomcat = new File(System.getProperty("user.dir") + "\\files\\" + countryName);
				fileBackup = new File(System.getProperty("user.dir") + "\\filesbackup\\" + countryName);

			}

			// Test to see if the Tomcat directory exists. If not, create
			// it.
			if (!(fileTomcat.exists() && fileTomcat.isDirectory())) {
				fileTomcat.mkdir();
			}

			// Test to see if the backup directory exists. If not, create
			// it.
			if (!(fileBackup.exists() && fileBackup.isDirectory())) {
				fileBackup.mkdir();
			}

			// Copy the files from the back up directory to the Tomcat
			// directory if they do not exist.
			if (!(fileTomcat.list().length > 0) && (fileBackup.list().length > 0)) {
				FileUtils.copyDirectory(fileBackup, fileTomcat);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
