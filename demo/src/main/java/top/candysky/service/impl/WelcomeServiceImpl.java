package top.candysky.service.impl;

import org.springframework.stereotype.Service;
import top.candysky.service.WelcomeService;

@Service()
public class WelcomeServiceImpl implements WelcomeService {
	@Override
	public String sayHello(String name) {
		System.out.println(name);
		return "success";
	}
}
