package com.exsposit.practica.Parsers;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;
import com.exsposit.practica.Beans.File;
import com.exsposit.practica.Beans.IParse;
import com.exsposit.practica.Utilites.Downloader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvParser implements IParse{

    private String file;

    private ColumnPositionMappingStrategy setColumnMapping = new ColumnPositionMappingStrategy();
    private String[] columns = new String[] {"url", "name"};

    CSVReader csvReader = null;

    private List<File> csvFilesList = new ArrayList<File>();

    public CsvParser(String file){
        this.file = file;
    }

    public List<File> parse() throws FileNotFoundException {
        setColumnMapping.setType(File.class);
        setColumnMapping.setColumnMapping(columns);
        CsvToBean csv = new CsvToBean();
        csvReader = new CSVReader(new FileReader(file));
        csvFilesList = csv.parse(setColumnMapping, csvReader);
        return csvFilesList;
    }

    protected void finalize() throws IOException {
        csvReader.close();
        csvFilesList = null;
        columns = null;
        setColumnMapping = null;
    }
}
