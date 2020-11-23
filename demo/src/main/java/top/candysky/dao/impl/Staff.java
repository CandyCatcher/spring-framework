package top.candysky.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope(value = "protoype")
public class Staff {
	private Company company;

	@Autowired
	public Staff(Company company) {
		this.company = company;
	}
}
