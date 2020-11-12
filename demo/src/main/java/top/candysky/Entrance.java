package top.candysky;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import top.candysky.controller.WelcomeController;
import top.candysky.entity.User;
import top.candysky.entity.factory.UserFactoryBean;
import top.candysky.service.WelcomeService;


/**
 * 将配置文件读取到内存中，配置信息为Resource对象，解析为BeanDefinition，根据BeanDefinition中的信息将对象实例化，注册到容器中
 */

// TODO @component和@Configuration的相同与不同
@Configuration
@ComponentScan("top.candysky")
public class Entrance {
	public static void main(String[] args) {
		//System.out.println("hello world");
//		String xmlPath = "D:\\spring-framework\\demo\\src\\main\\resources\\spring\\spring-config.xml";
//		FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext(xmlPath);
//		WelcomeService welcomeService = (WelcomeService) context.getBean("welcomeService");
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Entrance.class);
		BeanDefinition welcomeController = context.getBeanDefinition("welcomeController");
		User user = (User) context.getBean("userPoster");
		System.out.println("创建的对象" + user);

		//System.out.println(welcomeController.getDestroyMethodName());
		//System.out.println(welcomeController.toString());

//		User userBean = (User) context.getBean("userFactoryBean");
		// 这样得到的是userFactoryBean调用getObjective得到的user实例
//		System.out.println(userBean);

//		UserFactoryBean userFactoryBean = (UserFactoryBean) context.getBean("&userFactoryBean");
//		System.out.println(userFactoryBean);

		//Object classDemo = new ClassDemo();
		// TODO class类通过classLoader创建
		//ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
		//Class<? extends ClassLoader> aClass = systemClassLoader.getClass();
		//if (classDemo.getClass() instanceof  Class) {
		//	System.out.println("1");
		//} else {
		//	System.out.println("0");
		//}



		//String[] beanDefinitionNames = context.getBeanDefinitionNames();
		//for (String beanDefinitionName : beanDefinitionNames) {
		//
		//	System.out.println(beanDefinitionName);
		//}
		//WelcomeService welcomeService = (WelcomeService) context.getBean("welcomeServiceImpl");
//		welcomeService.sayHello("success");
		//WelcomeController welcomeController = (WelcomeController) context.getBean("welcomeController");
		//welcomeController.handleRequest();

	}
}



