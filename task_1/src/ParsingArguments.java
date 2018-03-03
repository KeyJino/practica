import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ParsingArguments {

    private String url = null;
    private String path = null;
    private String fileName = null;

    ParsingArguments(String[] args){

        if (args.length != 6) {
            System.err.println("Please, write number the arguments correctly");
            System.exit(0);
        }

        for (int i = 0; i < 6; i++) {

            if (args[i].equals("-l")) {
                url = args[++i];

            } else if (args[i].equals("-p")) {
                path = args[++i];

            } else if (args[i].equals("-n")) {
                if(isFileNameCorrect(args[i+1]) && (getFileFormat(args[i+1]).equals(".jpg")
                        || getFileFormat(args[i+1]).equals(".png"))) {
                    fileName = args[++i];
                }
                else {
                    System.err.println("File name isn't correctly.");
                    System.exit(0);
                }
            }
        }

        new Download(url, path, fileName).startDownload();

    }

    private boolean isFileNameCorrect(String name){
        Pattern pattern = Pattern.compile("(.+)?[><\\|\\?*/:\\\\\"](.+)?");
        Matcher matcher = pattern.matcher(name);
        return !matcher.find();
    }

    private String getFileFormat(String args) {
        int index = args.indexOf('.');
        return index == -1? null : args.substring(index);
    }
}
