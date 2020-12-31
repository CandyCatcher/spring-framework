package top.candysky;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import top.candysky.controller.HelloController;
import top.candysky.controller.HiController;
import top.candysky.controller.WelcomeController;
import top.candysky.dao.impl.BoyFriend;
import top.candysky.dao.impl.Company;
import top.candysky.dao.impl.Staff;
import top.candysky.entity.User;
import top.candysky.entity.factory.UserFactoryBean;
import top.candysky.introduction.LittleUniverse;
import top.candysky.service.HelloService;
import top.candysky.service.HiService;
import top.candysky.service.WelcomeService;


/**
 * 将配置文件读取到内存中，配置信息为Resource对象，解析为BeanDefinition，根据BeanDefinition中的信息将对象实例化，注册到容器中
 * Spring是默认不开启AOP相关的注解逻辑的
 */

// TODO @component和@Configuration的相同与不同
@Configuration
@EnableAspectJAutoProxy
@ComponentScan("top.candysky")
public class Entrance {
	public static void main(String[] args) {
		//System.out.println("hello world");
		//String xmlPath = "D:\\spring-framework\\demo\\src\\main\\resources\\spring\\spring-config.xml";
		//FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext(xmlPath);

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Entrance.class);
		HiService hiService = (HiService) context.getBean("hiServiceImpl");
		hiService.sayHi();
		System.out.println("-----------------------------执行HelloService----------------------------");
		HelloService helloService = (HelloService) context.getBean("helloServiceImpl");
		helloService.helloService();
		/*
		容器在初始化的时候不是创建了bean实例了吗？为什么这里要显示调用呢？
		AbstractApplicationContext的refresh方法初始化的是非延迟加载的单例
		可以去DefaultListableBeanFactory的preInstantiateSingletons方法去看
		可以看到如果Bean不是抽象的（可以实例化的），是单例的，不是懒加载的，则开始创建单例对象通过调用getBean(beanName)方法初始化
		if (!bd.isAbstract() && bd.isSingleton() && !bd.isLazyInit())
		 */
		//执行的话，会报错，说明spring不支持构造器循环依赖的
		//Company company = (Company) context.getBean("company");
		//执行的话，会报错，说明spring不支持protoype循环依赖的
		//BoyFriend boyFriend = (BoyFriend) context.getBean("boyFriend");
		/*
		解决循环依赖的关键在于三级缓存
		三级缓存除了解决循环依赖，还解决了保持单例唯一性的问题。因为从缓存中取出的bean实例要保证是唯一的
		所以三级缓存支持不了protoype，因为protoype不是唯一的。所以protoype的bean没有使用三级缓存
		而是将简单的名字放到缓存。正因为没有三级缓存的支持，才导致protoype不支持循环依赖。因为没有实力缓存


		另外，单例的构造器循环依赖也是不支持的
		AbstractAutowiredCapableBeanFactory的autowireConstructor方法
		 */

		//BeanDefinition welcomeController = context.getBeanDefinition("welcomeController");
		//User user = (User) context.getBean("userPoster");
		//System.out.println("创建的对象" + user);

		//System.out.println(welcomeController.getDestroyMethodName());
		//System.out.println(welcomeController.toString());

		//User userBean = (User) context.getBean("userPoster");
		// 这样得到的是userFactoryBean调用getObjective得到的user实例
		//System.out.println("CustomizedBeanDefinitionRegistryPostProcessor创建的对象" + userBean);

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
		//welcomeService.sayHello("success");
		//WelcomeController welcomeController = (WelcomeController) context.getBean("welcomeController");
		//welcomeController.handleRequest();

		//System.out.println("============================AOP登场================================");
		//HelloController helloController = (HelloController) context.getBean("helloController");
		//HiController hiController = (HiController) context.getBean("hiController");
		//hiController.handleRequest();
		//helloController.handleRequest();
		//((LittleUniverse)hiController).burningUp();

	}
}



