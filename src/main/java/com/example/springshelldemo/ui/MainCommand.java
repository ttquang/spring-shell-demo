package com.example.springshelldemo.ui;

import com.example.springshelldemo.core.exception.StepRuntimeException;
import com.example.springshelldemo.core.model.DummyExecutionEnvironment;
import com.example.springshelldemo.core.model.TestSuite;
import com.example.springshelldemo.service.ExcelImportService;
import com.example.springshelldemo.service.TestSuitePoolService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.io.File;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.logging.Logger;

import static java.lang.String.format;

@ShellComponent
public class MainCommand {
    Logger log = Logger.getLogger(MainCommand.class.getName());

    @Autowired
    private ExcelImportService importService;

    @Autowired
    private TestSuitePoolService poolService;

    @ShellMethod(value = "load all TestSuite")
    public void load() {
        File testSuiteDir = new File(System.getProperty("user.dir") + "\\testsuite");
        try {
            for (File file : testSuiteDir.listFiles()) {
                TestSuite testSuite = importService.processTestSuit(file);
                poolService.addTestSuite(testSuite);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @ShellMethod(value = "list all TestSuite")
    public void list() {
        List<TestSuite> testSuites = poolService.getAll();
        for (int i = 0; i < testSuites.size(); i++) {
            TestSuite testSuite = testSuites.get(i);
            log.info(format("[%d] %s", i, testSuite));
        }
    }

//    @ShellMethod(value = "run testsuite")
//    public void run(@ShellOption(value = "-i") int index) {
//        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\webdriver\\chromedriver.exe");
//        WebDriver webDriver = null;
//        try {
//            TestSuite testSuite = poolService.get(index);
//            webDriver = new ChromeDriver();
//            webDriver.manage().timeouts().pageLoadTimeout(Duration.of(30, ChronoUnit.MINUTES));
//            DummyExecutionEnvironment environment = new DummyExecutionEnvironment(webDriver);
//            testSuite.runWith(environment);
//        } catch (StepRuntimeException ex) {
//            ex.printStackTrace();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        } finally {
//            webDriver.quit();
//        }
//    }

    @ShellMethod(key = "run", value = "run testsuite")
    public void runAll() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\webdriver\\chromedriver.exe");
        System.setProperty("webdriver.chrome.silentOutput", "true");
        List<TestSuite> testSuites = poolService.getAll();
        WebDriver webDriver = null;
        try {
            webDriver = new ChromeDriver();
            webDriver.manage().timeouts().pageLoadTimeout(Duration.of(30, ChronoUnit.MINUTES));
            DummyExecutionEnvironment environment = new DummyExecutionEnvironment(webDriver);
            for (int i = 0; i < testSuites.size(); i++) {
                TestSuite testSuite = testSuites.get(i);
                testSuite.runWith(environment);
            }
        } catch (StepRuntimeException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            webDriver.quit();
        }
    }
}
