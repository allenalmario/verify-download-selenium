import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.time.Instant;

public class FileUtil {
    public static boolean isFileDownload(String expectedFileName, String fileExtension, int timeOut) throws IOException {
        //download folder path
        String folderName = System.getProperty("user.dir")+ File.separator+"Downloads";

        // to iterate through files in downloads folder
        File[] listOfFiles;

        String fileName;

        //by default, consider file is not downloaded
        boolean fileDownloaded = false;

        long startTime = Instant.now().toEpochMilli();

        long waitTime = startTime + timeOut;

        while(Instant.now().toEpochMilli() < waitTime) {
            //get all downloads of the downloads folder
            listOfFiles = new File(folderName).listFiles();

            for (File file:listOfFiles) {
                fileName = file.getName().toLowerCase();
                if(fileName.contains(expectedFileName.toLowerCase()) && fileName.contains(fileExtension.toLowerCase()) && !fileName.contains("crdownload") && file.lastModified() > startTime) {
                    fileDownloaded = true;
                    FileUtils.cleanDirectory(new File(folderName));
                    break;
                }
            }
            if(fileDownloaded) {
                break;
            }
        }
        return fileDownloaded;

    }
}
