
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
        log.printAllHigherThanNum(400);
    }
    
    public void testUniqueIPVisitsOnDay(){
        LogAnalyzer log = new LogAnalyzer();
        log.readFile("weblog2_log");
        ArrayList<String> array = log.uniqueIPVisitsOnDay("Sep 24");
        
        System.out.println("IP: " + array);
        
        System.out.println("Size of Array: " + array.size());
    }
    
    public void testCountUniqueIPsInRange() {
        LogAnalyzer log = new LogAnalyzer();
        log.readFile("weblog-short_log");
        int count = log.countUniqueIPsInRange(200,299);
        System.out.println("Count Unique IPs In Range: " + count);
    }
    
    public void testCounts() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        System.out.println(counts);
    }
    
    public void testMostNumberVisitsByIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        int max = la.mostNumberVisitsByIP(counts);
        System.out.println("Most Number Visits By IP: " + max);
    }
    
    public void testIPsMostVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        System.out.println(la.iPsMostVisits(counts));
    }
    
    public void testIPsForDays() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log");
        System.out.println(la.iPsForDays());
    }
    
    public void testDayWithMostIPVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log");
        HashMap<String, ArrayList<String>> counts = la.iPsForDays();
        System.out.println("Most Date Visited: " + la.dayWithMostIPVisits(counts));
    }
    
    public void testIPsWithMostVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log");
        String date = "Sep 30";
        HashMap<String, ArrayList<String>> datesIPs = la.iPsForDays();
        ArrayList<String> results = la.iPsWithMostVisitsOnDay(datesIPs, date);
        System.out.println(results);
    }
}
