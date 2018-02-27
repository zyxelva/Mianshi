package com.zyx;

public class DrawThread extends Thread
{
	private Account2 account;
	private double drawAmount;
	public DrawThread(String name, Account2 account, double drawAmount)
	{
		super(name);
		this.account=account;
		this.drawAmount=drawAmount;
	}

	@Override
	public void run()
	{
		for(int i=0;i<100;i++){
			account.draw(drawAmount);
		}
//		synchronized (account)
//		{
//			if (account.getBalance() >= drawAmount)
//			{
//				System.out.println(getName() + " 取钱成功！吐出钞票：" + drawAmount);
//
//				try
//				{
//					Thread.sleep(1);
//				}
//				catch (InterruptedException ex)
//				{
//					ex.printStackTrace();
//				}
//
//				account.setBalance(account.getBalance() - drawAmount);
//				System.out.println("\t 账户余额：" + account.getBalance());
//			}
//			else
//			{
//				System.out.println(getName() + " 取钱失败！ 余额不足！");
//			}
//		}
	}
}
