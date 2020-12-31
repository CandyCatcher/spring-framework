package top.candysky.controller;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import top.candysky.service.WelcomeService;

@Controller
public class WelcomeController implements ApplicationContextAware, BeanNameAware {

	private String myName;
	private ApplicationContext myContainer;

	@Autowired
	private WelcomeService welcomeService;

	public void handleRequest() {
		welcomeService.sayHello("request success");
		System.out.println("my name is" + myName);
		String[] beanDefinitionNames = myContainer.getBeanDefinitionNames();
		for (String beanDefinitionName : beanDefinitionNames) {
			System.out.println("召唤容器获得的beanDefinitionName" + beanDefinitionName);
		}
	}

	@Override
	public void setBeanName(String name) {
		this.myName = name;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.myContainer = applicationContext;
	}
}
