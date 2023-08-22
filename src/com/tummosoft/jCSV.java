package com.tummosoft;

import anywheresoftware.b4a.BA;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Collectors;

import anywheresoftware.b4a.AbsObjectWrapper;
import anywheresoftware.b4a.BA.Events;
import anywheresoftware.b4a.BA.Hide;
import anywheresoftware.b4a.BA.ShortName;
import java.io.Reader;
import java.nio.charset.Charset;

import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.CSVParserWriter;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.CsvToBeanFilter;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Spliterator;

import java.util.function.Consumer;

@BA.ShortName("jCSV")
@Events(values = {"CSVComplete (Success As Boolean, Crsr As ResultSet)"})
public class jCSV {
    char _separator;
    char _quotecharacter;   
    String eventName = "";
    public void Initialize(String EventName) {       
       eventName = EventName.toLowerCase(BA.cul);
    }
    public anywheresoftware.b4a.objects.collections.List Reader(final BA ba, String CSVPath, Class<?> classname, anywheresoftware.b4a.objects.collections.Map columnMap) throws IOException, ClassNotFoundException {
        
        CSVReader csv = new CSVReader(new FileReader(new File(CSVPath), Charset.forName("UTF-8")));
        HeaderColumnNameTranslateMappingStrategy strategy = new HeaderColumnNameTranslateMappingStrategy();
        
        Map<String, String> columnMappingMap = new HashMap<String,String>();
        
        columnMappingMap.put("col1", "col1");
        columnMappingMap.put("col2", "col2");
        columnMappingMap.put("col3", "col3");
               
        strategy.setColumnMapping(columnMappingMap);        
        strategy.setType(classname.getClass());
        CsvToBean csvToBean = new CsvToBean();                
        csvToBean.setMappingStrategy(strategy);        
        csvToBean.setIgnoreEmptyLines(true);   
        
        csvToBean.setCsvReader(csv);
        
        List<?> list = csvToBean.parse();   
        BA.Log(list.get(0).toString());
        anywheresoftware.b4a.objects.collections.List result = new  anywheresoftware.b4a.objects.collections.List();
        result.Initialize();
        
        return result;
    }
    
    private class filed {
        Object field1;
        Object field2;
        Object field3;
    }
}
 
