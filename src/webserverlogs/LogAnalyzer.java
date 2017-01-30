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
        for (LogEntry le : records) {
            String ipAddr = le.getIpAddress();
            System.out.println(le.toString().indexOf(ipAddr));
            if (uniqueIPVisitsOnDay.contains(ipAddr)){
                String date = le.getAccessTime().toString();
                if (date.indexOf(someday) != -1){
                    uniqueIPVisitsOnDay.add(le.toString());
                    System.out.println(le);
                }
            }
        }
        return uniqueIPVisitsOnDay;
    }
    

}
