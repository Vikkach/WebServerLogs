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
    public void testLogEntry(){
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le.getLogInfo());
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
}
