package kr.pe.gamgoon.hermetization;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.util.StringUtils;

public class CustomerService {
	public Customer createCustomer(String name, ArrayList<ContactPerson> contactPeople) {
		if (contactPeople == null) {
			throw new IllegalArgumentException("Contact people list cannot be null");
		}
		if (StringUtils.isEmpty(name)) {
			throw new IllegalArgumentException("Name cannot be empty");
		}
		final Customer customer = new Customer();
//		customer.id = Sequence.nextValue();
		customer.createionDate = new Date();
		customer.name = name;
		customer.contactPeople = contactPeople;
		return customer;
	}
}
