
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer log = new LogAnalyzer();
        log.readFile("short-test_log");
        log.printAll();
    }
    
    public void testUniqIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        int uniqueIPs = la.countUniqueIPs();
        System.out.println("There are " + uniqueIPs + " IPs");
    }
    
    public void testPrintAllHigherThanNum() {
        LogAnalyzer log = new LogAnalyzer();
        log.readFile("short-test_log");
        log.printAllHigherThanNum(200);
    }
    
    public void testUniqueIPVisitsOnDay(){
        LogAnalyzer log = new LogAnalyzer();
        log.readFile("weblog-short_log");
        ArrayList<String> array = log.uniqueIPVisitsOnDay("Sep 14");
        
        System.out.println("IP: " + array);
        
        System.out.println("Counts: " + array.size());
    }
    
    public void testCountUniqueIPsInRange() {
        LogAnalyzer log = new LogAnalyzer();
        log.readFile("weblog-short_log");
        int count = log.countUniqueIPsInRange(200,299);
        System.out.println("Count Unique IPs In Range: " + count);
    }
}
