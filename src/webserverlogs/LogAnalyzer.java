/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package webserverlogs;

import edu.duke.FileResource;
import java.util.ArrayList;

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
    
    public static void printAllHigherThanNum(int num){
         for (LogEntry le : records) {
             int statusCode= le.getStatusCode();
             if(statusCode >= num){
                 System.out.println(le);
             }
         }
    }
    
    public static ArrayList<String> uniqueIPVisitsOnDay(String someday){
        ArrayList<String> uniqueIPVisitsOnDay = new ArrayList<>();
        uniqueIPVisitsOnDay.clear();
        for (LogEntry le : records) {
            String ipAddr = le.getIpAddress();
            String date = le.getAccessTime().toString();
                if (date.contains(someday) && !uniqueIPVisitsOnDay.contains(ipAddr)){
                    uniqueIPVisitsOnDay.add(ipAddr);
                    System.out.println(le);
            }
        }
        return uniqueIPVisitsOnDay;
    }
    
    public static int countUniqueIPsInRange(int low, int high){
        ArrayList<String> uniqueIPs = new ArrayList<>();
        for (LogEntry le : records) {
             int statusCode= le.getStatusCode();
             String ipAddr = le.getIpAddress();
             if(statusCode >= low &&  statusCode <= high && !uniqueIPs.contains(ipAddr)){
                 uniqueIPs.add(ipAddr);
             }
         }
        return uniqueIPs.size();
    }
    

}
