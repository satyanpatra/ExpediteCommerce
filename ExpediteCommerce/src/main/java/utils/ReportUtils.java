package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportUtils {
    private static PrintWriter writer;
    private static String reportFilePath;

    public static void startReport() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = dateFormat.format(new Date());
        reportFilePath = "broken_links_report_" + timestamp + ".txt";
        try {
            writer = new PrintWriter(new FileWriter(reportFilePath));
            writer.println("Broken Links Report - " + timestamp);
            writer.println("----------------------------------------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void logBrokenLink(String url, int statusCode) {
        if (writer != null) {
            writer.println("Broken Link: " + url + " - Status Code: " + statusCode);
        }
    }

    public static void endReport() {
        if (writer != null) {
            writer.println("----------------------------------------");
            writer.println("Report End");
            writer.close();
            System.out.println("Report generated: " + reportFilePath);
        }
    }
}
