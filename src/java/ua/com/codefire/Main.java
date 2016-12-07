package ua.com.codefire;

import ua.com.codefire.net.Spider;

public class Main {

    public static void main(String[] args) {

        Spider spider  = new Spider();

        spider.scan("http://maup.com.ua");
    }
}
