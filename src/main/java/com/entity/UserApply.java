package com.entity;

public class UserApply {
    
	private int id;
	private String name;
	private String nrc;
	private String paypal_id;
	private String paypal_mail;
	private String postal;
	private String city;
	private String address;
	private String country;
	@Override
	public String toString() {
		return "UserApply [ id=" + id + ", name=" + name + ", nrc=" + nrc + ", paypal_id=" + paypal_id + ", paypal_mail=" + paypal_mail + ","
				+ ",postal=" + postal + ", city= " +city+ ", address=" + address + ", country= " + country +"]";
	}
	public UserApply() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public int getID() {
		return id;
	}
	public void setID(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNRC() {
		return nrc;
	}
	public void setNRC(String nrc) {
		this.nrc = nrc;
	}
	public String getPAYPAL_ID() {
		return paypal_id;
	}
	public void setPAYPAL_ID(String paypal_id) {
		this.paypal_id = paypal_id;
	}
	public String getPAYPAL_MAIL() {
		return paypal_mail;
	}
	public void setPAYPAL_MAIL(String paypal_mail) {
		this.paypal_mail = paypal_mail;
	}
	public String getPostal() {
		return postal;
	}
	public void setPostal(String postal) {
		this.postal = postal;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	

}
