package com.search;

import com.search.exceptions.ParsingException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class AirportController {
    FileParser fp=new FileParser();
    FilterService fs=new FilterService();
    Scanner scanner = new Scanner(System.in);
    Map<String, Long> data;
    ArrayList<Object> keys;
    public  void mainLoop(String[] args) throws ParsingException, IOException {
        try {
            data.putAll(fp.setData());
        }
        catch (ParsingException fileParserException){
            System.out.println(fileParserException.getMessage());
            return;
        }
        CommandLineView.enterName();
        String request = scanner.nextLine();
        keys = new ArrayList<>(fs.getAirportByName(request,data));
        long time = System.currentTimeMillis();
        long rowsCount = 0;
        while (request.compareTo("!quit") != 0) {

            if (keys.size() < 1) {
                CommandLineView.displayNoFound();
                CommandLineView.enterName();
                request = scanner.nextLine();
                continue;
            }
            Map<String, Long> sortedMap = new TreeMap<>(data);
            sortedMap.entrySet().forEach(System.out::println);
            time = System.currentTimeMillis() - time;

            for (Object i : keys) {

                        CommandLineView.displayAnswer(new Object[]{fp.setData(i).,{});
                        rowsCount++;

                }

            }
            CommandLineView.enterSearchInfo(time, rowsCount);
            CommandLineView.enterName();
            request = scanner.nextLine();
        }
    }

