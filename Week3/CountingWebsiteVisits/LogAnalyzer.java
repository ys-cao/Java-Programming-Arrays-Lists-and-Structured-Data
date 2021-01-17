
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
         ArrayList<String> ips = new ArrayList<String>();
         for (LogEntry le : records) {
             if (le.getStatusCode() >= low && le.getStatusCode() <= high) {
                 if (!ips.contains(le.getIpAddress())){
                     ips.add(le.getIpAddress());
                 }
             }
         }
         return ips.size();
     }
     
     public HashMap<String, Integer> countVisitsPerIP() {
        HashMap<String, Integer> counts = new HashMap<String, Integer>();
        for (LogEntry le : records) {
            String ip = le.getIpAddress();
            
            if (!counts.containsKey(ip)) {
                counts.put(ip, 1);
            } else {
                counts.put(ip, counts.get(ip) + 1);
            }
        }
        return counts;
     }
     
     public int mostNumberVisitsByIP(HashMap<String, Integer> ipCounts) {
         int max = 0;
         for (String s : ipCounts.keySet()) {
             int currCount = ipCounts.get(s);
             if (currCount > max) {
                 max = currCount;
                }
            }
         return max;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> ipCounts) {
         ArrayList<String> iPsMostVisits = new ArrayList<String>();
         int max = mostNumberVisitsByIP(ipCounts);
         for (String s : ipCounts.keySet()) {
             int currCount = ipCounts.get(s);
             if (currCount == max) {
                 iPsMostVisits.add(s);
                }
            }
         return iPsMostVisits;
     }
     
     public HashMap<String, ArrayList<String>> iPsForDays() {
        HashMap<String, ArrayList<String>> iPsForDays = new HashMap<String, ArrayList<String>>();
        for (LogEntry le : records) {
            Date d = le.getAccessTime();
            String day = d.toString();
            String dstr = day.substring(4, 10);
            if (!iPsForDays.containsKey(dstr)) {
                ArrayList<String> ips = new ArrayList<String>();
                ips.add(le.getIpAddress());
                iPsForDays.put(dstr, ips); 
            } else {
                ArrayList<String> currArray = iPsForDays.get(dstr);
                currArray.add(le.getIpAddress());
                iPsForDays.put(dstr, currArray);
                
                /*if (!currArray.contains(le.getIpAddress())) {
                currArray.add(le.getIpAddress());
                iPsForDays.put(dstr, currArray);
                }*/
            }
        }
        return iPsForDays;
    }
    
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> ip) {
        String dateMostVisited = "";
        int max = 0;
        for (String s : ip.keySet()) {
            ArrayList<String> ips = ip.get(s);
            if (max < ips.size()) {
                max = ips.size();
                dateMostVisited = s;
            }
        }
        return dateMostVisited;
    }
    
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> ip, String day) {
        ArrayList<String> ips = ip.get(day);
        HashMap<String, Integer> ipCounts = new HashMap<String, Integer>();
        for (String s : ips) {
            if (!ipCounts.containsKey(s)) {
                ipCounts.put(s, 1);
            } else {
                ipCounts.put(s, ipCounts.get(s) + 1);
            }
        }
        return iPsMostVisits(ipCounts);
    }
}
