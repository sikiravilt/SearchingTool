package com.search;

import com.search.exceptions.ParsingException;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, ParsingException {
	FileParser fp=new FileParser();
    String filters = "column[1]>10 & column[5]=’KEF’ || column[3]=’Keflavik’";
	FilterService fs=new FilterService();
      System.out.println(fp.setData());
        System.out.println(fp.getFullLine(1032297));
//        System.out.println(fs.getAirportByName("Bo",fp.setData()));
//       fs.parse(filters,fs.getAirportByName("Ke",fp.setData()));
    }
}
