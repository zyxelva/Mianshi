/**
 * 
 * <p>
 * Title: A.java
 * </p>
 * 
 * <p>
 * Description:
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2017
 * </p>
 * 
 * <p>
 * Company: zyx@taeyeon.cn
 * </p>
 * 
 * @author KEN
 * 
 * @date 2018年3月12日
 * 
 * @version 1.0
 * 
 */

package com.zyx.interfaceCallBack.CalculatorCallbackTest;

/**
 * @author KEN
 *
 */
public class Student implements ICallBack {


    private String name;

    public Student(){}
    
    public Student(String name){
        this.name=name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void callHelp(int a, int b) {
        new SuperCalculator().add(a, b, this);
    }

    @Override
    public void fillBlank(int a, int b, int result) {
        System.out.println(name + "求助小红计算+++++++++++++:" + a + " + " + b + " = " + result);
    }

    public static void main(String[] args) {

    }

}
