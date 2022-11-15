package com.testinium.Steps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.testinium.methods.Methods;

public class PageBase {
    Methods methods;
    Logger logger = LogManager.getLogger(PageBase.class);

    public PageBase() {
        methods = new Methods();
    }

}
