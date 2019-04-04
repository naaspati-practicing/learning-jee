package com.actionbazaar.ejb.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.sql.DataSource;

import com.actionbazaar.ejb.api.BidderAccountBuilder;
import com.actionbazaar.jaxb.Bidder;
import com.actionbazaar.jaxb.BillingInfo;
import com.actionbazaar.jaxb.BiographicalInfo;
import com.actionbazaar.jaxb.LoginInfo;

@Stateful
public class BidderAccountBuilderBean implements BidderAccountBuilder {
	private LoginInfo loginInfo;
	private BiographicalInfo biographicalInfo;
	private BillingInfo billingInfo;
	
	@Resource(mappedName="jdbc/postgresDS")
	private DataSource ds;

	@Override
	public void setLoginInfo(LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
	}
	
	private void checkFlow(Object requireNonNull, String varName) {
		if(requireNonNull == null)
			throw new WorkflowOrderViolationException(varName+" expected to be set first");
	}

	@Override
	public void setBiographicalInfo(BiographicalInfo biographicalInfo) {
		checkFlow(loginInfo, "loginInfo");
		this.biographicalInfo = biographicalInfo;
	}

	@Override
	public void setBillingInfo(BillingInfo billingInfo) {
		checkFlow(biographicalInfo, "biographicalInfo");
		this.billingInfo = billingInfo;
	}
	
	@Override
	@Remove
	public void cancel() {
		this.loginInfo = null;
		this.biographicalInfo = null;
		this.billingInfo = null;
	}
	
	@Override
	@Remove
	public Bidder build() {
		checkFlow(loginInfo, "loginInfo");
		checkFlow(biographicalInfo, "biographicalInfo");
		checkFlow(billingInfo, "billingInfo");
		
		try(Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement("INSERT INTO BIDDERS (username, first_name, last_name, credit_card_type) VALUES(?,?,?,?) returning id")) {
			
			ps.setString(1, loginInfo.getUsername());
			ps.setString(2, biographicalInfo.getFirstName());
			ps.setString(3, biographicalInfo.getLastName());
			ps.setString(4, billingInfo.getCreditCardType());

			int id = ps.executeQuery().getInt(1);
			
			Bidder bidder = new Bidder();
			bidder.setId(id);
			bidder.setFirstName(biographicalInfo.getFirstName());
			bidder.setLastName(biographicalInfo.getLastName());
			
			this.loginInfo = null;
			this.biographicalInfo = null;
			this.billingInfo = null;
			
			return bidder;
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
}
