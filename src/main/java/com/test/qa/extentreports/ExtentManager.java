package com.test.qa.extentreports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

    public class ExtentManager {
        public static final ExtentReports extentReports = new ExtentReports();

        public synchronized static ExtentReports createExtentReports() {
            String reportPath = System.getProperty("user.dir")+"/TestReport/extent-reports/extent-report.html";
            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
            reporter.config().setReportName("Sample Extent Report");
            extentReports.attachReporter(reporter);
            extentReports.setSystemInfo("Framework", "Web Automation Framework 1");
            extentReports.setSystemInfo("Author", "Sourabh Mathur");
            return extentReports;
        }
}
