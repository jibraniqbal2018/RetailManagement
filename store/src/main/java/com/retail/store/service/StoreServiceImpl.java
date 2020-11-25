package com.retail.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.retail.store.billing.BillingProcess;
import com.retail.store.model.Bill;
import com.retail.store.model.Customer;

@Service
public class StoreServiceImpl implements StoreService{
	
	@Value("${employeeDiscount}")
	private float employeeDiscount;
	
	@Value("${affiliatedDiscount}")
	private float affiliatedDiscount;

	@Value("${valuedCustomerDiscount}")
	private float valuedCustomerDiscount;
	
	@Autowired
	private BillingProcess billingProcess;

	@Override
	public Bill generateBill(Customer customer) {
	
        Bill bill;
		switch (customer.getCustomerType()) {

		case EMPLOYEE:
			bill = billingProcess.generateBill(employeeDiscount,customer.getItemList());
			break;
			
		case AFFILIATED:
			bill = billingProcess.generateBill(affiliatedDiscount, customer.getItemList());
			break;
		
		case VALUED_CUSTOMER:
			bill = billingProcess.generateBill(valuedCustomerDiscount, customer.getItemList());
			break;
			
		default:
			bill = billingProcess.generateBill(0.0f, customer.getItemList());
		}
		return bill;
	}

}
