package com.kroll;

import org.junit.jupiter.api.Test;

import java.util.logging.Level;
import java.util.logging.LogRecord;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomLogFormatterTest {

    @Test
    void testLogFormat() {
        CustomLogFormatter formatter = new CustomLogFormatter();
        LogRecord record = new LogRecord(Level.INFO, "Test message");

        String formattedLog = formatter.format(record);

        assertEquals(true, formattedLog.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} INFO: Test message\\n"));
    }
}
