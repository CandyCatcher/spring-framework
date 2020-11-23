package top.candysky.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope(value = "protype")
public class Company {
	private Staff staff;

	@Autowired
	public Company(Staff staff) {
		this.staff = staff;
	}
}
