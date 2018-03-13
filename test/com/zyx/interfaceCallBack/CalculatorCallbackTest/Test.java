/**
 * Title: Test.java 
 * Description: Copyright: Copyright (c) 2017 Company:
 * zyx@taeyeon.cn
 * 
 * @author KEN
 * @date 2018年3月13日
 * @version 1.0
 */

package com.zyx.interfaceCallBack.CalculatorCallbackTest;

/**
 * @author KEN
 */
public class Test {
    public static void main(String[] args) {
        int a = 56;
        int b = 31;
        int c = 26497;
        int d = 11256;
        Student s1 = new Student("小明");
        Seller s2 = new Seller("老婆婆");
        Seller2 s3 = new Seller2("老婆婆2");
        
        s1.callHelp(a, b);
        s2.callHelp(c, d);
        s3.callHelp(a, c);
    }
}
