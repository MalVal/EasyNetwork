package com.EasyNetwork;

import com.EasyNetwork.udp.service.UnreliableRequestService;
import com.EasyNetwork.udp.socket.UnreliableSocket;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        UnreliableRequestService service1 = new UnreliableRequestService(new UnreliableSocket("127.0.0.1", 12345, 54321));
        UnreliableRequestService service2 = new UnreliableRequestService(new UnreliableSocket("127.0.0.1", 54321, 12345));

        service1.sendObject("Hello World from service1");
        System.out.println(service2.receiveObject());
        service2.sendObject("Hello World from service2");
        System.out.println(service1.receiveObject());

        assertTrue( true );
    }
}
