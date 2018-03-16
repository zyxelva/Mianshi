/**  
* Title: C.java
* Description: 
* Copyright: Copyright (c) 2017  
* Company: zyx@taeyeon.cn
* @author KEN  
* @date 2018年3月13日  
* @version 1.0  
*/  

package com.zyx.polymorphismTest;

/**
 * @author KEN
 */

class B extends A {
    public String show(B obj) {
        return ("B and B");
    }

    public String show(A obj) {
        return ("B and A");
    }
}
