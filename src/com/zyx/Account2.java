package com.zyx;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Account2
{

	private String accountNo;
	private double balance;
	private boolean flag = false;

	public Account2(String accountNo, double balance)
	{
		this.accountNo = accountNo;
		this.balance = balance;
	}

	public String getAccountNo()
	{
		return accountNo;
	}

	public void setAccountNo(String accountNo)
	{
		this.accountNo = accountNo;
	}

	public double getBalance()
	{
		return balance;
	}

	public void setBalance(double balance)
	{
		this.balance = balance;
	}

	@Override
	public int hashCode()
	{
		return accountNo.hashCode();
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj != null && obj.getClass() == Account.class)
		{
			Account target = (Account) obj;
			return target.getAccountNo().equals(accountNo);
		}
		return false;
	}

	public synchronized void draw(double drawAmount)
	{
		// if (this.getBalance() >= drawAmount)
		// {

		try
		{
			if (!flag)
			{
				wait();
			}
			else
			{
				System.out.println(Thread.currentThread().getName() + " 取钱成功！吐出钞票：" + drawAmount);
				this.balance -= drawAmount;
				System.out.println("账户余额: " + balance);
				flag = false;
				notifyAll();
			}

		}
		catch (InterruptedException ex)
		{
			ex.printStackTrace();
		}

		// this.setBalance(this.getBalance() - drawAmount);
		// System.out.println("\t 账户余额：" + this.getBalance());
		// }
		// else
		// {
		// System.out.println(Thread.currentThread().getName() + " 取钱失败！
		// 余额不足！");
		// }
	}

	public synchronized void deposit(double depositAmount)
	{
		try
		{
			if (flag)
			{
				wait();
			}
			else
			{
				System.out.println(Thread.currentThread().getName() + " 存款：" + depositAmount);
				balance += depositAmount;
				System.out.println("账户余额: " + balance);
				flag = true;
				notifyAll();
			}
		}
		catch (InterruptedException ex)
		{
			ex.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		String str = "15622108888 hahhh 13777262296 hh 15827742408";
		Pattern p = Pattern.compile("((13\\d)|(15\\d))\\d{8}");
		Matcher m = p.matcher(str);
		while (m.find())
		{
			System.out.println(m.group());
		}
		// SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	}

}
