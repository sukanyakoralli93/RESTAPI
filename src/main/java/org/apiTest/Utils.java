package org.apiTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Utils {
    public static String extractDate(String timestampString){
        String extractedDate=null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
            Date date = dateFormat.parse(timestampString);

            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
            extractedDate = dateFormatter.format(date);

            System.out.println("Extracted Date: " + extractedDate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return extractedDate;
    }

    public static String getTodaysDate(){
        LocalDate currentDate = LocalDate.now();

        // Define the desired format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Format the current date
        String formattedDate = currentDate.format(formatter);
        System.out.println(formattedDate);
        return formattedDate;

    }

    public static String readJsonFile(String filePath)
    {
        StringBuilder jsonStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                jsonStringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonStringBuilder.toString();
    }

}



