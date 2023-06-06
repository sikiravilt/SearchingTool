package com.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FilterService {
    static String[] logicalOperators = {"&", "||"};
    static String[] equalityOperators = {">", "<", "=", "<>"};

    FileParser fileParser = new FileParser();

    public ArrayList<Long> getAirportByName(String givenName, Map<String, Long> names) throws IOException {
        ArrayList<Long> correctNames = new ArrayList<>();
        for (Map.Entry<String, Long>entry : names.entrySet()) {
            if (entry.getKey().contains(givenName.toLowerCase())) {
               // System.out.println("The key for value " + givenName + " is " + entry.getKey());
                correctNames.add(entry.getValue());
                //System.out.println(correctNames);
            }
        }
        return correctNames;
    }

        public static FilterData parseSingleFilter(String filterString) {
            Pattern columnRegex = Pattern.compile("(?<=column\\[)(.*)(?=])");
            Matcher columnMatcher = columnRegex.matcher(filterString);
            columnMatcher.find();
            int columnIndex = Integer.parseInt(columnMatcher.group(1));
            String operator = "";
            for (String equalityOperator : equalityOperators) {
                if (filterString.contains(equalityOperator)) {
                    operator = equalityOperator;
                    break;
                }
            }
            String value = filterString.split(operator)[1].replaceAll("['’\"]+", "");

            return new FilterData(columnIndex, operator, value);
        }

        public  boolean executeFilter(FilterData filter, ArrayList<Long> names ) throws IOException {
       String realValue = fileParser.getNeededColumn(filter.index, names);

            switch(filter.operation){
                case "<": if (realValue.compareTo(filter.value)<0)
                    return true;
                    case "<>":
                    return !realValue.equals(filter.value);
                case "=":if (realValue.compareTo(filter.value)==0)
                    return true;
                case ">": if (realValue.compareTo(filter.value)>0)
                    return true;
                default:
                    return false;
            }
        }

        public void parse(String filters, ArrayList<Long> names) throws IOException {
            ArrayList<String> sas = new ArrayList<String>();
            sas.add(filters);
            for (String logicalOperator : logicalOperators) {
                ArrayList<String> newSas = new ArrayList<String>();
                for (String s : sas) {
                    String[] splitS = s.split(logicalOperator);
                    for (String split : splitS) {
                        newSas.add(split.trim());
                    }
                }
                sas = newSas;
            }

            ArrayList<FilterData> equalityStruct = new ArrayList<FilterData>();
            for (String s : sas) {
                equalityStruct.add(parseSingleFilter(s));
            }

            for (FilterData filter : equalityStruct) {
                executeFilter(filter,names);
            }
        }


    }

