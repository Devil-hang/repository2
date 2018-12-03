package com.caibei.config;

import org.apache.log4j.PropertyConfigurator;

import java.util.Properties;

/*
log4j配置。日志配置文件
 */
public class Log {

    public static void initLog(String fileName) {
        // 获取到模块名字
        String founctionName = fileName.substring(0,fileName.indexOf("_"));
        // 声明日志文件存储路径以及文件名、格式
        String logFilePath = "./result/logs/" + founctionName + "/" + fileName + ".log";
        Properties prop = new Properties();
        // 配置日志输出的格式
        prop.setProperty("log4j.rootLogger", "info, toConsole, toFile");//配置根logger log4j.rootLogger = [ level ] , appenderName（日志输出到的地方，可以是多个）, appenderName, …
        prop.setProperty("log4j.appender.file.encoding", "UTF-8");
        prop.setProperty("log4j.appender.toConsole", "org.apache.log4j.ConsoleAppender");
        prop.setProperty("log4j.appender.toConsole.Target", "System.out");
        prop.setProperty("log4j.appender.toConsole.layout", "org.apache.log4j.PatternLayout ");
        prop.setProperty("log4j.appender.toConsole.layout.ConversionPattern", "[%d{yyyy-MM-dd HH:mm:ss}] [%p] %m%n");
        prop.setProperty("log4j.appender.toFile", "org.apache.log4j.DailyRollingFileAppender");
        prop.setProperty("log4j.appender.toFile.file", logFilePath);
        prop.setProperty("log4j.appender.toFile.append", "false");
        prop.setProperty("log4j.appender.toFile.Threshold", "info");
        prop.setProperty("log4j.appender.toFile.layout", "org.apache.log4j.PatternLayout");
        prop.setProperty("log4j.appender.toFile.layout.ConversionPattern", "[%d{yyyy-MM-dd HH:mm:ss}] [%p] %m%n");
        // 使配置生效
        PropertyConfigurator.configure(prop);

    }

}
