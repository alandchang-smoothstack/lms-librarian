package com.smoothstack.lms.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerWrapper {
	static public Logger log = LogManager.getLogger(LoggerWrapper.class.getName());
}