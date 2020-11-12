package top.candysky.postprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;

/**
 * 这里可以对bean进行包装
 */
@Configuration
public class CustomizedBeanPostProcessor implements BeanPostProcessor {
	/**
	 * bean初始化之前调用的
	 * @param bean the new bean instance
	 * @param beanName the name of the bean
	 */
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println(beanName + "调用了postProcessBeforeInitialization");
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println(beanName + "调用了postProcessAfterInitialization");
		return bean;
	}
}
