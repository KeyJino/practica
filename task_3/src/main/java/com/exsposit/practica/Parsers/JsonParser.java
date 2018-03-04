package com.exsposit.practica.Parsers;

import com.exsposit.practica.Beans.File;
import com.exsposit.practica.Beans.IParse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

import java.util.*;

public class JsonParser implements IParse{

    private String file;

    private List<File> jsonFilesList = new ArrayList<File>();

    public JsonParser(String file) {
        this.file = file;

    }

    public List<File> parse() throws IOException{

        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject) new JSONParser().parse(new FileReader(file));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONArray jsonArray = (JSONArray) jsonObject.get("DownloadFiles");

        Iterator arrayIterator = jsonArray.iterator();

        while (arrayIterator.hasNext()) {
            JSONObject jsonFileAttributes = (JSONObject) arrayIterator.next();
            File file = new File();
            file.setName((String) jsonFileAttributes.get("name"));
            file.setUrl((String) jsonFileAttributes.get("url"));
            jsonFilesList.add(file);
        }

        return jsonFilesList;

    }
}




