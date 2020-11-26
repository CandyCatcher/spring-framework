package top.candysky.service.impl;

import org.springframework.stereotype.Service;
import top.candysky.service.HelloService;

/**
 * 不修改这些类的代码，借助spring往类里面织入通用的系统方法
 */
@Service
public class HelloServiceImpl implements HelloService {
	@Override
	public void helloService() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Hello Service");
	}

	@Override
	public void justThrowException() {
		throw new RuntimeException("hello exception");
	}
}
