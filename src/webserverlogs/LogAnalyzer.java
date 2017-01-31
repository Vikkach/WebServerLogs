/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package webserverlogs;

import edu.duke.FileResource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author New
 */
public class LogAnalyzer {
    
    private static ArrayList<LogEntry> records;
    
    public LogAnalyzer() {
        this.records = new ArrayList<>();
    }
    
    public void readFile(String filename) {
        FileResource fr = new FileResource(filename);
        for (String line : fr.lines()) {
            LogEntry logEntry = WebLogParser.parseEntry(line);
            records.add(logEntry);
        }
    }
    
    public int countUniqueIP() {
        ArrayList<String> uniqueIPs = new ArrayList<>();
        uniqueIPs.clear();
        for (LogEntry le : records) {
            String ipAddr = le.getIpAddress();
            if (!uniqueIPs.contains(ipAddr)) {
                uniqueIPs.add(ipAddr);
            }
        }
        return uniqueIPs.size();
    }
    
    public static void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }
    
    public static void printAllHigherThanNum(int num) {
        for (LogEntry le : records) {
            int statusCode = le.getStatusCode();
            if (statusCode >= num) {
                System.out.println(le);
            }
        }
    }
    
    public static ArrayList<String> uniqueIPVisitsOnDay(String someday) {
        ArrayList<String> uniqueIPVisitsOnDay = new ArrayList<>();
        uniqueIPVisitsOnDay.clear();
        for (LogEntry le : records) {
            String ipAddr = le.getIpAddress();
            String date = le.getAccessTime().toString();
            if (date.contains(someday) && !uniqueIPVisitsOnDay.contains(ipAddr)) {
                uniqueIPVisitsOnDay.add(ipAddr);
                System.out.println(le);
            }
        }
        return uniqueIPVisitsOnDay;
    }
    
    public static int countUniqueIPsInRange(int low, int high) {
        ArrayList<String> uniqueIPs = new ArrayList<>();
        for (LogEntry le : records) {
            int statusCode = le.getStatusCode();
            String ipAddr = le.getIpAddress();
            if (statusCode >= low && statusCode <= high && !uniqueIPs.contains(ipAddr)) {
                uniqueIPs.add(ipAddr);
            }
        }
        return uniqueIPs.size();
    }
    
    public HashMap<String, Integer> countVisitsPerIP() {
        HashMap<String, Integer> counts = new HashMap<>();
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
    
    public int mostNumberVisitsByIP(HashMap<String, Integer> counts) {
        int maxNumberOfVisits = 0;
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            if (maxNumberOfVisits < entry.getValue()) {
                maxNumberOfVisits = entry.getValue();
            }
        }
        return maxNumberOfVisits;
    }
    
    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts) {
        ArrayList<String> ipAddrs = new ArrayList<>();
        int maxNumberOfVisits = mostNumberVisitsByIP(counts);
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            if (!ipAddrs.contains(entry.getKey()) && entry.getValue() == maxNumberOfVisits) {
                ipAddrs.add(entry.getKey());
            }
        }
        return ipAddrs;
    }
    
    public HashMap<String, ArrayList<String>> iPsForDays() {
        HashMap<String, ArrayList<String>> daysMap = new HashMap<>();
        for (LogEntry le : records) {
            Date date = le.getAccessTime();
            String month = date.toString().substring(4, 10);
            ArrayList<String> ipAddrs = new ArrayList<>();
            if (!daysMap.containsKey(month)) {
                ipAddrs.clear();
                ipAddrs.add(le.getIpAddress());
                daysMap.put(month, ipAddrs);                
            } else if (daysMap.containsKey(month)) {
                ipAddrs = daysMap.get(month);
                ipAddrs.add(le.getIpAddress());
                daysMap.put(month, ipAddrs);
            }
        }
        return daysMap;
    }
    
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> daysMap) {
        String mostVisitedDay = null;
        int maxIPs = 0;
        for (Map.Entry<String, ArrayList<String>> entry : daysMap.entrySet()) {
            if (maxIPs < entry.getValue().size()) {
                maxIPs = entry.getValue().size();
                mostVisitedDay = entry.getKey();
            }
        }
        return mostVisitedDay;
    }
    
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> daysMap, String day) {
        ArrayList<String> ipMostVisits = new ArrayList<>();
        if (daysMap.containsKey(day)) {
            ArrayList<String> ips = daysMap.get(day);
            HashMap<String, Integer> countIPs = new HashMap<>();
            for (int i = 0; i < ips.size(); i++) {
                String ip = ips.get(i);
                if (!countIPs.containsKey(ip)) {
                    countIPs.put(ip, 1);
                } else {
                    countIPs.put(ip, countIPs.get(ip) + 1);
                }
            }
            int maxVisits = 0;
            for (Map.Entry<String, Integer> entry : countIPs.entrySet()) {
                if (maxVisits < entry.getValue()) {
                    maxVisits = entry.getValue();
                }
            }
            for (Map.Entry<String, Integer> entry : countIPs.entrySet()) {
                if (maxVisits == entry.getValue()) {
                    ipMostVisits.add(entry.getKey());
                }
            }
        }
        return ipMostVisits;
    }
    
}
