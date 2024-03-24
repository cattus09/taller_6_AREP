package edu.escuelaing.arep.app;

import org.junit.Test;
import static org.junit.Assert.*;

public class RoundRobinTest {

    @Test
    public void testGetPort() {
        assertEquals(4568, RoundRobin.getPort());
    }
}