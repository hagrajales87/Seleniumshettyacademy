package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

    public void getReportObject(){
        String path = System.getProperty("user.dir")+("\\reporter\\index.html");
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Test Report");
        reporter.config().setDocumentTitle("SeleniumAcademy");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        //Give the QA Name
        extent.setSystemInfo("Tester", "Héctor Grajales");
    }
}
