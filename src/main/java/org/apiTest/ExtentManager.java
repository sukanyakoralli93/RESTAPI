package org.apiTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.restassured.http.Header;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ExtentManager {
        private static ExtentReports extentReports;
        synchronized static ExtentReports createInstance() {
            String workingDir = System.getProperty("user.dir");
            ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(workingDir +"\\reports\\extent.html");
            extentSparkReporter.config().setReportName("Automation Build");
            extentSparkReporter.config().setDocumentTitle("Test Results");
            extentSparkReporter.config().setTheme(Theme.DARK);
            extentSparkReporter.config().setEncoding("utf-8");
            extentReports = new ExtentReports();
            extentReports.attachReporter(extentSparkReporter);
            return extentReports;
        }
    public static String getReportNameWithTimeStamp() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
        LocalDateTime localDateTime = LocalDateTime.now();
        String formattedTime = dateTimeFormatter.format(localDateTime);
        String reportName = "TestReport" + formattedTime + ".html";
        return reportName;
    }
    public static void logPassDetails(String log) {
        ExtentReportListener.test.get().pass(MarkupHelper.createLabel(log, ExtentColor.GREEN));
    }
    public static void logFailureDetails(String log) {
        ExtentReportListener.test.get().fail(MarkupHelper.createLabel(log, ExtentColor.RED));
    }
    public static void logExceptionDetails(String log) {
        ExtentReportListener.test.get().fail(log);
    }
    public static void logInfoDetails(String log) {
        ExtentReportListener.test.get().info(MarkupHelper.createLabel(log, ExtentColor.GREY));
    }
    public static void logWarningDetails(String log) {
        ExtentReportListener.test.get().warning(MarkupHelper.createLabel(log, ExtentColor.YELLOW));
    }
    public static void logJson(String json) {
        ExtentReportListener.test.get().info(MarkupHelper.createCodeBlock(json, CodeLanguage.JSON));
    }
    public static void logHeaders(List<Header> headersList) {

        String[][] arrayHeaders = headersList.stream().map(header -> new String[] {header.getName(), header.getValue()})
                .toArray(String[][] :: new);
        ExtentReportListener.test.get().info(MarkupHelper.createTable(arrayHeaders));
    }
    }

