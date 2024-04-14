package org.example.cinemamanagement.utils;

import java.time.format.DateTimeFormatter;

public class DateTimeHelper {
    public static DateTimeFormatter formatVNTime() {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    }
}
