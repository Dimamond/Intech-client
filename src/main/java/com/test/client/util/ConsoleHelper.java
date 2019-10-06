package com.test.client.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


    public static void print(String s){
        System.out.print(s);

    }

    public static void println(String s){
        System.out.println(s);

    }

    public static String read() throws IOException {
        return reader.readLine();
    }

    public static int readInt()throws IOException {
        return Integer.parseInt(read());
    }


}
