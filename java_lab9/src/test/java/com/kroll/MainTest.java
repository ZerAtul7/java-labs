package com.kroll;

import org.json.JSONArray;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    void getDataFromApi_validUrl_returnsJsonArray() {
        String apiUrl = "https://api.escuelajs.co/api/v1/products";
        JSONArray data = Main.getDataFromApi(apiUrl);
        assertNotNull(data);
        assertTrue(data.length() > 0);
    }
    @Test
    void getDataFromApi_invalidUrl_returnsNull() {
        String apiUrl = "https://api.escuelajs.co/api/v1/invalid_endpoint";
        JSONArray data = Main.getDataFromApi(apiUrl);
        assertNull(data);
    }

}
