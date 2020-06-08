package integration;

import org.junit.AfterClass;
import org.junit.BeforeClass;

abstract class AbstractTest {

    final private static int PORT = 8080;
    final private static String HOST = "localhost";
    final protected static String URL = "http://" + HOST + ":" + PORT;

    @BeforeClass
    public static void setUp() throws Exception {
        System.out.println("prepare DOCKER");
        Process p1 = Runtime.getRuntime().exec("docker container stop tomcat_container");
        p1.waitFor();
        Process p2 = Runtime.getRuntime().exec("docker container rm tomcat_container");
        p2.waitFor();
        Process p3 = Runtime.getRuntime().exec("docker rmi tomcat_deployment");
        p3.waitFor();
        Process p4 = Runtime.getRuntime().exec("docker-compose up");
        Thread.sleep(5000);
        System.out.println("DOCKER up and running");
    }

    @AfterClass
    public static void tearDown() throws Exception {
        System.out.println("stop DOCKER");
        Process p1 = Runtime.getRuntime().exec("docker container stop tomcat_container");
        p1.waitFor();
        Process p2 = Runtime.getRuntime().exec("docker container rm tomcat_container");
        p2.waitFor();
        Process p3 = Runtime.getRuntime().exec("docker rmi tomcat_deployment");
        p3.waitFor();
        System.out.println("DOCKER stopped");
    }
}
