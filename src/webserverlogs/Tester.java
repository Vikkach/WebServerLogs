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

    public void testUniqueIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log.txt");
        int uniqueIPs = la.countUniqueIP();
        System.out.println("There are " + uniqueIPs + " IPs");
    }

    public void testPrintAllHigherThanNum(int num) {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log.txt");
        LogAnalyzer.printAllHigherThanNum(num);
    }

    public void testUniqueIPVisitsOnDay(String someday) {
        LogAnalyzer log = new LogAnalyzer();
        log.readFile("weblog-short_log.txt");
        LogAnalyzer.uniqueIPVisitsOnDay(someday);
    }

    public void testCountUniqueIPsInRange(int low, int high) {
        LogAnalyzer log = new LogAnalyzer();
        log.readFile("short-test_log.txt");
        System.out.println(LogAnalyzer.countUniqueIPsInRange(low, high));
    }

    public void testCountVisitsPerIP() {
        LogAnalyzer log = new LogAnalyzer();
        log.readFile("short-test_log.txt");
        HashMap<String, Integer> counts = log.countVisitsPerIP();
        System.out.println(counts);
    }

    public void testMostNumberVisitsByIP() {
        LogAnalyzer log = new LogAnalyzer();
        log.readFile("weblog3-short_log.txt");
        HashMap<String, Integer> counts = log.countVisitsPerIP();
        int maxNumberOfVisits = log.mostNumberVisitsByIP(counts);
        System.out.println(maxNumberOfVisits);
    }

    public void testIPsMostVisits() {
        LogAnalyzer log = new LogAnalyzer();
        log.readFile("weblog3-short_log.txt");
        HashMap<String, Integer> counts = log.countVisitsPerIP();
        ArrayList<String> ipAddrs = log.iPsMostVisits(counts);
        System.out.println(ipAddrs);
    }
    
    public void testIPsForDays() {
        LogAnalyzer log = new LogAnalyzer();
        log.readFile("weblog3-short_log.txt");
        System.out.println(log.iPsForDays());
    }
    
    public void testDayWithMostIPVisits(){
        LogAnalyzer log = new LogAnalyzer();
        log.readFile("weblog3-short_log.txt");
        HashMap<String, ArrayList<String>> daysMap = log.iPsForDays();
        System.out.println(log.dayWithMostIPVisits(daysMap));
    }
    
    public void testIPsWithMostVisitsOnDay(){
        LogAnalyzer log = new LogAnalyzer();
        log.readFile("weblog3-short_log.txt");
        HashMap<String, ArrayList<String>> daysMap = log.iPsForDays();
        System.out.println(log.iPsWithMostVisitsOnDay(daysMap, "Sep 30"));
    }

}
