
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         FileResource fr = new FileResource();
         for (String line : fr.lines()) {
             LogEntry log = WebLogParser.parseEntry(line);
             records.add(log);
         }
     }
     
     public int countUniqueIPs() {
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         
         for (LogEntry le : records) {
             String ipAddr = le.getIpAddress();
             
             if (!uniqueIPs.contains(ipAddr)) {
                 uniqueIPs.add(ipAddr);
                }
         }
         return uniqueIPs.size();
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public void printAllHigherThanNum(int num) {
         for (LogEntry le : records) {
             if (le.getStatusCode() > num) {
                 System.out.println(le);
             }
         }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
         ArrayList<String> ipDates = new ArrayList<String>();
         for (LogEntry le : records) {
             Date d = le.getAccessTime();
             String dstr = d.toString();
             if (dstr.contains(someday)) {
                 String ip = le.getIpAddress();
                 if (!ipDates.contains(ip)){
                     ipDates.add(ip);
                 }
             }
             
         }
         return ipDates;
     }
     
     public int countUniqueIPsInRange(int low, int high) {
         int count = 0;
         for (LogEntry le : records) {
             if (le.getStatusCode() >= low && le.getStatusCode() <= high) {
                 count++;
             }
         }
         return count;
     }
}
