package com.example.paymentproject.utils;

import java.util.logging.Filter;
import java.util.logging.LogRecord;

public class FilterImpl implements Filter {

    @Override
    public boolean isLoggable(LogRecord record) {
        return false;
    }
}
