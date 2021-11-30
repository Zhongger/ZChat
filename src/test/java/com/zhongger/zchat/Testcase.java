package com.zhongger.zchat;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.*;

public class Testcase {

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException, IOException, ClassNotFoundException {
       String b=new String("123");
        String a="123";
        System.out.println(b==a);

    }
}