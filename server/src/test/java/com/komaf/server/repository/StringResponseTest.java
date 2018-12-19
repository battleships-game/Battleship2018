package com.komaf.server.repository;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringResponseTest {

    @Test
    public void testStringResponse(){
        StringResponse stringResponse = new StringResponse("jeden");
        assertEquals("jeden", stringResponse.getResponse());
    }
}
