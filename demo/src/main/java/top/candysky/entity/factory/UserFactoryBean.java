package top.candysky.entity.factory;

import org.springframework.beans.factory.FactoryBean;
import top.candysky.entity.User;

public class UserFactoryBean implements FactoryBean<User> {
	@Override
	public User getObject() throws Exception {
		return new User();
	}

	@Override
	public Class<?> getObjectType() {
		return User.class;
	}
}
