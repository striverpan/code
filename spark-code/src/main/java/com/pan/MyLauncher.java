package com.pan;


import org.apache.spark.launcher.SparkLauncher;

import java.util.HashMap;

public class MyLauncher {

    public static void main(String[] args) throws Exception {

        HashMap<String, String> map = new HashMap<String, String>();

        map.put("HADOOP_CONF_DIR", "/opt/cloudera/parcels/CDH-5.11.2-1.cdh5.11.2.p0.4/etc/hadoop/conf.dist");

        map.put("YARN_CONF_DIR", "/opt/cloudera/parcels/CDH-5.11.2-1.cdh5.11.2.p0.4/etc/hadoop/conf.dist");

        map.put("SPARK_CONF_DIR", "/opt/cloudera/parcels/CDH-5.11.2-1.cdh5.11.2.p0.4/etc/spark/conf.dist");

        new SparkLauncher(map).setAppResource("/home/pan/spark-code-1.0-SNAPSHOT.jar").setMainClass("com.pan.JavaLogQuery").setMaster("yarn-client").setConf(SparkLauncher.DRIVER_MEMORY, "2g").setVerbose(true).startApplication();

        Thread.sleep(100000);

    }

}