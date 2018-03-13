/**  
* Title: B.java
* Description: 
* Copyright: Copyright (c) 2017  
* Company: zyx@taeyeon.cn
* @author KEN  
* @date 2018年3月13日  
* @version 1.0  
*/  

package com.zyx.staticInheritanceTest;

/**
 * @author KEN
 */

public class B extends A{//子类B
    public static String staticStr = "B改写后的静态属性";
    public String nonStaticStr = "B改写后的非静态属性";
    public static void staticMethod(){
        System.out.println("B改写后的静态方法");
    }
}
