/*
 * Copyright 2002-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.beans.factory.support;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;

/**
 * GenericBeanDefinition is a one-stop shop for standard bean definition purposes.
 * Like any bean definition, it allows for specifying a class plus optionally
 * constructor argument values and property values. Additionally, deriving from a
 * parent bean definition can be flexibly configured through the "parentName" property.
 *
 * <p>In general, use this {@code GenericBeanDefinition} class for the purpose of
 * registering user-visible bean definitions (which a post-processor might operate on,
 * potentially even reconfiguring the parent name). Use {@code RootBeanDefinition} /
 * {@code ChildBeanDefinition} where parent/child relationships happen to be pre-determined.
 *
 * generic：通用的
 *
 * 是一站式的标准bean definition，除了具有指定类、可选的构造参数值和属性参数这些其它bean definition一样的特性外，
 *
 * 它还具有通过parenetName属性来灵活设置parent bean definition。
 * 这句话的意思是指，当在运行时设置parent BeanDefinition时，rootBeanDefinition会抛出异常。
 * 但是genericBeanDefinition不会抛出异常
 *
 * 通常， GenericBeanDefinition用来注册用户可见的bean definition
 * (可见的bean definition意味着可以在该类bean definition上定义post-processor来对bean进行操作，甚至为配置parent name做扩展准备)。
 *
 * RootBeanDefinition/ChildBeanDefinition 用来预定义具有parent/child关系的bean definition。
 * @author Juergen Hoeller
 * @since 2.5
 * @see #setParentName
 * @see RootBeanDefinition
 * @see ChildBeanDefinition
 */
@SuppressWarnings("serial")
public class GenericBeanDefinition extends AbstractBeanDefinition {

	// 这是GenericBeanDefinition和其它bean definition的不同之处，可以灵活设置parent bean definition
	@Nullable
	private String parentName;

	/* -----------------------------------------------------构造方法----------------------------------------------------- */

	/*
	GenericBeanDefinition的构造方法和ChildBeanDefinition相比没有更多的灵活性
	GenericBeanDefinition 是用于标准 bean definition 的一站式服务
	 */

	/**
	 * Create a new GenericBeanDefinition, to be configured through its bean
	 * properties and configuration methods.
	 * @see #setBeanClass
	 * @see #setScope
	 * @see #setConstructorArgumentValues
	 * @see #setPropertyValues
	 */
	public GenericBeanDefinition() {
		super();
	}

	/**
	 * Create a new GenericBeanDefinition as deep copy of the given
	 * bean definition.
	 * 通过复制一个给定的beanDefiniton创建GenericBeanDefinition
	 * @param original the original bean definition to copy from
	 */
	public GenericBeanDefinition(BeanDefinition original) {
		super(original);
	}

	/* -----------------------------------------------------构造方法----------------------------------------------------- */

	/**
	 * 实现的是BeanDefinition接口中的方法
	 * 在这里设置parentName
	 * @param parentName
	 */
	@Override
	public void setParentName(@Nullable String parentName) {
		this.parentName = parentName;
	}

	@Override
	@Nullable
	public String getParentName() {
		return this.parentName;
	}


	/**
	 * 继承的AbstractBeanDefinition
	 * @return 克隆的自身？
	 */
	@Override
	public AbstractBeanDefinition cloneBeanDefinition() {
		return new GenericBeanDefinition(this);
	}

	@Override
	public boolean equals(@Nullable Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof GenericBeanDefinition)) {
			return false;
		}
		GenericBeanDefinition that = (GenericBeanDefinition) other;
		return (ObjectUtils.nullSafeEquals(this.parentName, that.parentName) && super.equals(other));
	}

	/**
	 * 返回的是自身以及父bean Definition
	 * @return
	 */
	@Override
	public String toString() {
		if (this.parentName != null) {
			return "Generic bean with parent '" + this.parentName + "': " + super.toString();
		}
		return "Generic bean: " + super.toString();
	}

}
