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
        ArrayList<LogEntry> uniqueIPs = new ArrayList<>();
        for (LogEntry le : records) {
            String ipAddr = le.getIpAddress();
            if (!uniqueIPs.contains(le)) {
                uniqueIPs.add(le);
            }
        }
        return uniqueIPs.size();
    }

    public static void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }

}
