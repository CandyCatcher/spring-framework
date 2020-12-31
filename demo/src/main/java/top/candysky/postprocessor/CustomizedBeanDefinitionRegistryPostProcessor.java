package top.candysky.postprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.*;
import org.springframework.context.annotation.Configuration;
import top.candysky.entity.User;

/**
 * 对于这个类来讲，如果标签不给，xml文件不配置，容器感知不到这个类的存在
 * 所以PostProcessor也要变成bean才能和容器进行对话
 */
@Configuration
public class CustomizedBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
	/**
	 * 在这里多注册一个Bean
	 * @param registry the bean definition registry used by the application context
	 * @throws BeansException
	 */
	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		//	获取到User的对象
		Class<?> clazz = User.class;
		BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(clazz);
		GenericBeanDefinition definition = (GenericBeanDefinition) builder.getRawBeanDefinition();
		registry.registerBeanDefinition("userPoster", definition);
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

	}
}
