package com.exsposit.practica.Beans;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

public interface IParse {
    public List<File> parse() throws IOException;
}
