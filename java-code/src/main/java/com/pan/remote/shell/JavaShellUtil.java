package com.pan.remote.shell;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class JavaShellUtil {

    public static boolean executeShell(String shStr, String... params) throws IOException {
        String[] shell = null;
        if(params != null){
            shell = new String[3 + params.length];
        }else {
            shell = new String[3];
        }
        StringBuffer sb = new StringBuffer();
        shell[0] = "/bin/sh";
        shell[1] = "-c";
        shell[2] = shStr;
        sb.append("sh ").append(shStr);
        if(params != null){
            for (int i = 0; i<params.length; i++) {
                shell[3+i] = params[i];
                sb.append(" ").append(params[i]);
            }
        }
        Process process;
        process = Runtime.getRuntime().exec(sb.toString());
        InputStreamReader ir = new InputStreamReader(process
                .getInputStream());

        LineNumberReader input = new LineNumberReader(ir);
        String line;
        try {
            process.waitFor();
        }catch (Exception e){
            e.printStackTrace();
        }
        boolean flag = false;
        while ((line = input.readLine()) != null){
            System.out.println(line);
            if(line.indexOf("Successfully") >= 0){
                flag = true;
            }
        }
        try{
            ir.close();
            input.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    public static void main(String[] args) {

        System.out.print("----------start-------");
        boolean flag = false;
        try {
            flag = JavaShellUtil.executeShell("/root/flink/start.sh");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.print("----------flag+"+flag+"-------");
        System.out.print("----------end-------");
    }

}