package com.search;

import com.search.exceptions.ParsingException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FileParser {
    private final String fileName = "src/resources/airports.csv";

    public Map<String, Long> setData() throws ParsingException {
        Map<String, Long> data = new HashMap<>();
        try{
        RandomAccessFile file = new RandomAccessFile(fileName, "r");
        long pointer = file.getFilePointer();
        String line;
        while ((line = file.readLine()) != null) {
            data.put(getSecondColumn(line).toLowerCase(),pointer);
            pointer = file.getFilePointer();

        }
        file.close();}
        catch (Exception exception) {
            throw new ParsingException("Ошибка при чтении файла airports.csv!");
        }
        return data;
    }

    public String getSecondColumn(String data) {
        String[] parameters = data.split(",");
        String parameter = parameters[1];
        return parameter;
    }

    public String getNeededColumn( int n, ArrayList<Long> keys) throws IOException {
        RandomAccessFile file = new RandomAccessFile(fileName, "r");
        String line = file.readLine();
        int k=0;
        String parameter = "";
       // ArrayList<String> column= new ArrayList<>();
        while (line!=null){
            file.seek(keys.get(k));
            k++;
            String[] parameters = line.split(",");
            parameter = parameters[n];
           // column.add(parameter);
        }
        return parameter;
    }

    public String getFullLine(long lineNumber) throws ParsingException {
        String line;
        try{
        RandomAccessFile file = new RandomAccessFile(fileName, "r");
        file.seek(lineNumber);
        line = file.readLine();
        file.close();
    }
        catch (Exception exception) {
        throw new ParsingException("Ошибка при чтении файла!");
    }
        return line;
    }
}
