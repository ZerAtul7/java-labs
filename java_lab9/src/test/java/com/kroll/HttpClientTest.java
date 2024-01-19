package com.kroll;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HttpClientTest {

    @Test
    void testHttpGet() {
        String url = "https://jsonplaceholder.typicode.com/todos/1";
        String response = HttpClient.get(url);
        assertNotNull(response);
        assertTrue(response.contains("userId"));
        assertTrue(response.contains("id"));
        assertTrue(response.contains("title"));
        assertTrue(response.contains("completed"));
    }

}
