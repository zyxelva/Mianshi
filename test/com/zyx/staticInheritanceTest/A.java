/**
 * Title: StaticInheritance.java Description: Copyright: Copyright (c) 2017
 * Company: zyx@taeyeon.cn
 * 
 * @author KEN
 * @date 2018年3月13日
 * @version 1.0
 */

package com.zyx.staticInheritanceTest;

/**
 * @author KEN
 */
public class A {

    /**
     * Title: Description: 测试静态属性、静态方法是否可以继承
     * 
     * @param args
     */
    public static String staticStr = "A静态属性";
    public String nonStaticStr = "A非静态属性";

    public static void staticMethod() {
        System.out.println("A静态方法");
    }

    public void nonStaticMethod() {
        System.out.println("A非静态方法");
    }

    public static void main(String[] args) {
    }

}
