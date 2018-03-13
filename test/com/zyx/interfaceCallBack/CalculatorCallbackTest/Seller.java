/**
 * Title: Seller.java Description: Copyright: Copyright (c) 2017 Company:
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
public class Seller{

    private String name = null;

    public Seller(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public class doHomeWork implements ICallBack {
        @Override
        public void fillBlank(int a, int b, int result) {
            System.out.println(name + "求助小红算账:" + a + " + " + b + " = " + result + "元");
        }
    }

    public void callHelp(int a, int b) {
        new SuperCalculator().add(a, b, new doHomeWork());
    }
}
