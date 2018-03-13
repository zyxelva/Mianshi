/**  

* <p>Title: ddd.java</p>  

* <p>Description: </p>  

* <p>Copyright: Copyright (c) 2017</p>  

* <p>Company: zyx@taeyeon.cn</p>  

* @author KEN  

* @date 2018年3月13日  

* @version 1.0  

*/  

package com.zyx.interfaceCallBack.CalculatorCallbackTest;

/**
 * @author KEN
 *
 */
public class SuperCalculator
{
    public void add(int a, int b, ICallBack  customer)
    {
        int result = a + b;
        customer.fillBlank(a, b, result);
    }
}
