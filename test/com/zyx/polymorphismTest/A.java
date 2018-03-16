/**
 * Title: dddd.java Description: Copyright: Copyright (c) 2017 Company:
 * zyx@taeyeon.cn
 * 
 * @author KEN
 * @date 2018年3月14日
 * @version 1.0
 */

package com.zyx.polymorphismTest;
import org.junit.Test;

/**
 * @author KEN
 */
public class A {
    public String show(D obj) {
        return ("A and D");
    }

    public String show(A obj) {
        return ("A and A");
    }
    
//    public String show(B obj) {
//        return ("A and B");
//    }
    
    @Test
    public  void test() {
        A a1 = new A();  
        A a2 = new B();  
        B b = new B();  
        C c = new C();   
        D d = new D();   
        System.out.println(" ①a1.show(b)="+a1.show(b));    //a1指向的类时A
        System.out.println(" ②a1.show(c)="+a1.show(c));    
        System.out.println(" ③a1.show(d)="+a1.show(d));    
        System.out.println(" ④a2.show(b)="+a2.show(b));    //a2指向的类时B，B类直接超类是A而且重写了show（A OBJ）。所以当传入的参数是B对象的时候，调用B类重写的方法。
        System.out.println(" ⑤a2.show(c)="+a2.show(c));    
        System.out.println(" ⑥a2.show(d)="+a2.show(d));    
        System.out.println(" ⑦b.show(b)  ="+b.show(b));     
        System.out.println(" ⑧b.show(c)  ="+b.show(c));     //b指向的类是B，而C,D类的直接超类是B所以调用的是show（B OBJ）。
        System.out.println(" ⑨b.show(d)="+b.show(d));      //b继承了A的方法
        System.out.println(" ⑨d.show(d)="+d.show(d));
        System.out.println(" ⑨d.show(c)="+d.show(c));
        System.out.println(" ⑨d.show(a1)="+d.show(a1));
        System.out.println(" ⑨d.show(a2)="+d.show(a2));
    }
}


