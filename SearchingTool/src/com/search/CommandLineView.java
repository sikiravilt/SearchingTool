package com.search;

public class CommandLineView {
    public static void enterName() {
        System.out.println("Введите название аэропорта: ");
    }

    public static void displayAnswer(Object[] answer) {
        System.out.println("\"" +answer[0].toString() + "\"" + '['
                + answer[1].toString() + ']');
    }

    public static void displayNoFound() {
        System.out.println("Строки не были найдены.\n");
    }

    public static void enterSearchInfo(long time, long rowsCount) {
        System.out.println(String.format("Количество найденных строк: %d. Время, затраченное на поиск: %d мс\n",
                rowsCount, time));
    }
}
