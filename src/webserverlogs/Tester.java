/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package webserverlogs;

import java.util.*;

/**
 *
 * @author New
 */
public class Tester {

    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer log = new LogAnalyzer();
        log.readFile("short-test_log.txt");
        LogAnalyzer.printAll();
    }
    
    public void testUniqueIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log.txt");
        int uniqueIPs = la.countUniqueIP();
        System.out.println("There are " + uniqueIPs + " IPs");
    }

}
