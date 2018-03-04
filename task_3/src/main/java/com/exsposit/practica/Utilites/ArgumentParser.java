package com.exsposit.practica.Utilites;

import com.exsposit.practica.Loader.Loader;
import com.exsposit.practica.Parsers.CsvParser;
import com.exsposit.practica.Parsers.JsonParser;
import com.exsposit.practica.Parsers.XmlParser;
import org.json.simple.parser.ParseException;


import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArgumentParser {

    private String file = null;
    private String path = null;
    private String threads = null;

    private String fileFormat = null;


    public ArgumentParser(String[] args) throws IOException, InterruptedException {

        if (args.length != 6) {
            System.err.println("Please, write number of the arguments correctly");
            System.exit(0);
        }

        for (int i = 0; i < args.length; i++) {

            if (args[i].equals("-f")) {
                fileFormat = getFileFormat(args[i+1]);

                if(!isFileNameCorrect(args[i + 1])) {
                    file = args[++i];
                } else {
                    System.err.println("Check file's name to symbols '^, ?, etc.'");
                    System.exit(0);
                }
            } else if (args[i].equals("-p")) {
                path = args[++i];
            } else if (args[i].equals("-t")) {
                threads = args[++i];
            }
        }


        if(fileFormat.equals(".csv")) {
            new Loader(path, new CsvParser(file).parse(), threads).download();
        }

        if(fileFormat.equals(".json")) {
            new Loader(path, new JsonParser(file).parse(), threads).download();
        }

        if(fileFormat.equals(".xml")) {
            new Loader(path, new XmlParser(file).parse(), threads).download();
        }

    }

    private boolean isFileNameCorrect(String name) {
        Pattern pattern = Pattern.compile("(.+)?[><\\|\\?*/:\\\\\"](.+)?");
        Matcher matcher = pattern.matcher(name);
        return !matcher.find();
    }

    private String getFileFormat(String args) {
        int index = args.indexOf('.');
        return index == -1 ? null : args.substring(index);
    }

    protected void finalize() throws IOException {
        file = null;
        path = null;
        fileFormat = null;

    }

}

