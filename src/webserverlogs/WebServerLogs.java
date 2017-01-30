/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package webserverlogs;

/**
 *
 * @author New
 */
public class WebServerLogs {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Tester test = new Tester();
        test.testCountUniqueIPsInRange(300,399);
    }
}
