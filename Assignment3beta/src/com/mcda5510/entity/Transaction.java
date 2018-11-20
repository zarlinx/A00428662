package com.mcda5510.entity;

public class Transaction {
	
	java.util.Date dt = new java.util.Date();
	java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private int trxnID;
    private String nameOnCard;
    private String cardNumber;
    private double unitPrice;
    private int quantity;
    private double totalPrice;
    private String expDate;
    String createdOn = sdf.format(dt);
    private String createdBy = System.getProperty("user.name");
    private String creditCardType;

    public int getID() {
        return trxnID;
}
public void setID(int trxnID) {
        this.trxnID = trxnID;
}
public String getNameOnCard() {
        return nameOnCard;
}
public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
}
public String getCardNumber() {
        return cardNumber;
}
public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
}
public double getUnitPrice() {
        return unitPrice;
}
public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
}
public int getQuantity() {
        return quantity;
}
public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
public double getTotalPrice() {
        return unitPrice*quantity;
}
public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
}
public String getExpDate() {
        return expDate;
}
public void setExpDate(String expDate) {
        this.expDate = expDate;
}
public String getCreatedOn() {
        return createdOn;
}

public String getCreatedBy() {
        return createdBy;
}

public String getCreditCardType() {
        return creditCardType;
}
public void setCreditCardType(String creditCardType) {
        this.creditCardType = creditCardType;
}
public String toString(){
	
	String results = new String();
	
	results = "[ID: " + trxnID + ", NameOnCard: " + nameOnCard +", CardNumber: " + cardNumber+
			 ", UnitPrice: " + unitPrice +", Quantity: " + quantity+", TotalPrice: " + totalPrice+
			 ", ExpDate: " + expDate +", CreatedOn: " + createdOn+", CreatedBy: " + createdBy+
			 ", CreditCardType: "+ creditCardType+"]";
	return results;

}
}