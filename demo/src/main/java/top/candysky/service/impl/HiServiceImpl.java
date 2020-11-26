package top.candysky.service.impl;

import org.springframework.stereotype.Service;
import top.candysky.service.HiService;

@Service
public class HiServiceImpl implements HiService {
	@Override
	public void sayHi() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Hi service");
	}

	@Override
	public String justSayHi() {
		return "just say hi";
	}
}
