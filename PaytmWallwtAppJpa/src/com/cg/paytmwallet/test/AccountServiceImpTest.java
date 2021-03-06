package com.cg.paytmwallet.test;


import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import com.cg.paytmwallet.bean.AccountApp1;
import com.cg.paytmwallet.exception.AccountException;
import com.cg.paytmwallet.service.AccountService;
import com.cg.paytmwallet.service.AccountServiceImp;

public class AccountServiceImpTest {

	AccountService accService=new AccountServiceImp();
	
	@Test
	public void testCreateAccountMobileNum()  {
		AccountApp1 acc=new AccountApp1();
		acc.setBalance(2500.0);
		acc.setEmailid("vineesha@gmail.com");
		acc.setMobileNo("999999");
		acc.setName("vineesha");
		
		try {
			accService.createAccount(acc);
		
		} catch (AccountException e) {
			
			assertEquals("Mobile Number should contain 10 digits",e.getMessage());
		}
		
	}
	@Test
	public void testCreateAccountName() {
		AccountApp1 acc=new AccountApp1();
		acc.setBalance(2500.0);
		acc.setEmailid("vineesha@gmail.com");
		acc.setMobileNo("9999999999");
		acc.setName("vinee123");
		try {
			accService.createAccount(acc);
		} catch (AccountException e) {
			assertEquals("Name should contain only alphabets",e.getMessage());
		}
	}
	@Test
	public void testCreateAccountMailId() {
		AccountApp1 acc=new AccountApp1();
		acc.setBalance(2000.0);
		acc.setEmailid("vineesha123@gmail");
		acc.setMobileNo("9380015060");
		acc.setName("vineesha");
		try {
			accService.createAccount(acc);
		} catch (AccountException e) {
			assertEquals("Invalid mailId",e.getMessage());
		}
	}
	@Test
	public void testCreateAccountAmount() {
		AccountApp1 acc=new AccountApp1();
		acc.setBalance(-2000.0);
		acc.setEmailid("vineesha@gmail.com");
		acc.setMobileNo("9380015060");
		acc.setName("vineesha");
		try {
			accService.createAccount(acc);
		} catch (AccountException e) {
			assertEquals("Enter Valid Amount should be greater than zero",e.getMessage());
		}
	}
	
	@Test
	public void testCreateAccount() {
		AccountApp1 acc=new AccountApp1();
		acc.setBalance(10000.0);
		acc.setEmailid("jyothi@gmail.com");
		acc.setMobileNo("99999998800");
		acc.setName("jyothi");
		try {
			String mobileNo = accService.createAccount(acc);
			assertNotNull(mobileNo);
		} catch (AccountException e) {
			System.out.println(e.getMessage());
		}
		
	}
	@Test
	public void testShowBalanceMobileNum()  {
		try {
			accService.showBalance("98765432");
		} catch (AccountException e) {
			assertEquals("Mobile Number should contain 10 digits",e.getMessage());
		}
	}
	
	@Test
	public void testShowBalance()  {
		AccountApp1 acc=new AccountApp1();
	try {
			double amount=accService.showBalance("9380014060");
			assertEquals(2000.0, amount,0.00);
		} catch (AccountException e) {
			assertEquals("Account with mobileno 1234567890 does not exist",e.getMessage());
			}
	
	}
	@Test
	public void testDepositMobileNum()  {
		try {
			accService.deposit("98765432",50.0);
		} catch (AccountException e) {
			assertEquals("Mobile Number should contain 10 digits",e.getMessage());
		}
	}
	@Test
	public void testDepositAmount() {
		try {
			accService.deposit("9876543210",-1.0);
		} catch (AccountException e) {
			assertEquals("Enter Valid Amount should be greater than zero",e.getMessage());
		}
	}
	
	@Test
	public void testDeposit() {
		AccountApp1 acc=new AccountApp1();
		acc.setMobileNo("9999999998");
		try {
			double ac= accService.deposit(acc.getMobileNo(),500);
			assertEquals(7000.0, ac,0.00);
		} catch (AccountException e) {
			System.out.println(e.getMessage());
		}
	}
	@Test
	public void testWithdrawMobileNum()  {
		try {
			accService.withdraw("98765432",50.0);
		} catch (AccountException e) {
			assertEquals("Mobile Number should contain 10 digits",e.getMessage());
		}
	}
	@Test
	public void testWithdrawAmount()  {
		try {
			accService.withdraw("9876543210",-1.0);
		} catch (AccountException e) {
			assertEquals("Enter Valid Amount should be greater than zero",e.getMessage());
		}
	}
	
	
	@Test
	public void testWithdrawMoreAmount() {
		try {
			accService.withdraw("9999999995",10000);
		} catch (AccountException e) {
			assertEquals("Enter amount less than existing amount",e.getMessage());
		}
	}
	@Test
	public void testWithdraw()  {
		AccountApp1 acc=new AccountApp1();
		acc.setMobileNo("9999999997");
		try {
			double acc1 = accService.withdraw(acc.getMobileNo(),1000);
			assertEquals(12000.0, acc1,0.00);
		} catch (AccountException e) {
			System.out.println(e.getMessage());
			
		}
		}
	@Test
	public void testFundTransferMobileNo1()  {
		try {
			accService.fundTransfer("98765432","9876543210",50.0);
		} catch (AccountException e) {
			assertEquals("Mobile Number should contain 10 digits",e.getMessage());
		}
	}
	@Test
	public void testFundTransferMobileNo2()  {
		try {
			accService.fundTransfer("9876543210","98765432",50.0);
		} catch (AccountException e) {
			assertEquals("Mobile Number should contain 10 digits",e.getMessage());
		}
	}
	@Test
	public void testFundTransferAmount()
	{
		try {
			accService.fundTransfer("9876543210","8765432190",-1);
		} catch (AccountException e) {
			assertEquals("Enter Valid Amount should be greater than zero",e.getMessage());
		}
	}
	
	@Test
	public void testFundTransferMoreAmount() {
		try {
			accService.fundTransfer("9999999993","9999999992",10000);
		} catch (AccountException e) {
			System.out.println(e.getMessage());
			assertEquals("Enter amount less than existing amount",e.getMessage());
		}
	}
	@Test
	public void testFundTransfer()  
	{
		AccountApp1 acc=new AccountApp1();
		AccountApp1 acc1=new AccountApp1();
		try {
			boolean b = accService.fundTransfer("9999999999","9999999996",100);
			assertNotNull(b);
		} catch (AccountException e) {
			System.out.println(e.getMessage());
		}
		}
	@Test
	public void testPrintTransactionMobileNo()  {
		try {
			accService.printTransactions("98765432");
		} catch (AccountException e) {
			assertEquals("Mobile Number should contain 10 digits",e.getMessage());
		}
	}
	
	@Test
	public void testPrintTransactions()  {
		AccountApp1 acc=new AccountApp1();
		try {
			AccountApp1 acc1 = accService.printTransactions("9999999999");
			assertNotNull(acc1);
		} catch (AccountException e) {
			System.out.println(e.getMessage());
			
		}
		}

	

}
