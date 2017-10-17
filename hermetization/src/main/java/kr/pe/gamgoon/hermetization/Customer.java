package kr.pe.gamgoon.hermetization;

import java.util.ArrayList;
import java.util.Date;

public class Customer {
	public long id;
	public String name;
	public Date createionDate;
	public Date activationDate;
	public ArrayList<ContactPerson> contactPeople = new ArrayList<>();
}
