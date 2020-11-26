package top.candysky.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import top.candysky.service.HelloService;

@Controller
public class HelloController {
	@Autowired
	private HelloService helloService;
	public void handleRequest() {
		helloService.helloService();
		helloService.justThrowException();
	}
}
