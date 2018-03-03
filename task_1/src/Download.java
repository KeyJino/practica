import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Download {

    private String url;
    private String path;
    private String fileName;

    Download(String url, String path, String fileName){
        this.url = url;
        this.path = path;
        this.fileName = fileName;
    }

    public void startDownload(){
        try {

            downloadUsingStream(url, path + "/" + fileName);
            System.out.println("Download is successfully!");
            System.out.println("Url : " + url);
            System.out.println("Path : " + path);
            System.out.println("File name : " + fileName);

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    private static void downloadUsingStream(String urlStr, String file) throws IOException {
        try {
            URL url = new URL(urlStr);
            BufferedInputStream bis = new BufferedInputStream(url.openStream());
            FileOutputStream fis = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int count = 0;
            while ((count = bis.read(buffer, 0, 1024)) != -1) {
                fis.write(buffer, 0, count);
            }
            fis.close();
            bis.close();
        } catch (FileNotFoundException e) {
            System.err.println("1)File not found \n2)Access denied \ncheck please and repeat");
            System.exit(0);
        }
    }
}
