package com.exsposit.practica.Beans;

import com.exsposit.practica.Beans.File;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

public interface IParse {
    List<File> parse() throws IOException, ParseException;
}
