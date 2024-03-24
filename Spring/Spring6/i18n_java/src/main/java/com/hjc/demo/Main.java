package com.hjc.demo;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main {
    public static void main(String[] args) {
        System.out.println("en_GB===" + ResourceBundle.getBundle("messages",
                new Locale("en","GB")).getString("test"));
        System.out.println("zh_CN===" + ResourceBundle.getBundle("messages",
                new Locale("zh","CN")).getString("test"));
    }
}
