package model;

public class Client extends Person{
	
	String memberID;
	Amount balance;
	
	final int MEMBER_ID = 465;
	final Amount BALANCE = new Amount (50.00);
	
	public Client(String name, String memberID, Amount balance) {
		super(name);
		this.memberID = memberID;
		this.balance = balance;
	}

	protected String getMemberID() {
		return memberID;
	}

	protected void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	protected Amount getBalance() {
		return balance;
	}

	protected void setBalance(Amount balance) {
		this.balance = balance;
	}

	
}
