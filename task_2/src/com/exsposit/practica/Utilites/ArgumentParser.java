package com.exsposit.practica.Utilites;

import com.exsposit.practica.Loader.Loader;
import com.exsposit.practica.Parsers.CsvParser;
import com.exsposit.practica.Parsers.JsonParser;
import com.exsposit.practica.Parsers.XmlParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArgumentParser {

    private String file = null;
    private String path = null;
    private String fileFormat = null;

    public ArgumentParser(String[] args) throws IOException{

        if (args.length != 4) {
            System.err.println("Please, write number the arguments correctly");
            System.exit(0);
        }

        for (int i = 0; i < args.length; i++) {

            if (args[i].equals("-f")) {
                fileFormat = getFileFormat(args[i+1]);

                if(isFileNameCorrect(args[i+1]) == false) {
                    file = args[++i];
                }
            } else if (args[i].equals("-p")) {
                path = args[++i];
            }
        }


        if(fileFormat.equals(".csv")) {
            new Loader(path, new CsvParser(file).parse()).download();
        }

        if(fileFormat.equals(".json")) {
            new Loader(path, new JsonParser(file).parse()).download();
        }

        if(fileFormat.equals(".xml")) {
            new Loader(path, new XmlParser(file).parse()).download();
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

}

