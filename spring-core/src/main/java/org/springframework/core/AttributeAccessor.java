/*
 * Copyright 2002-2018 the original author or authors.
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

package org.springframework.core;

import org.springframework.lang.Nullable;

/**
 * 定义了最基本的对任意对象元数据的修改，用在banDefinition里，主要是获取BeanDefinition的属性
 *
 * 元数据：
 *
 * 所谓的元数据是指用来描述数据的数据，更通俗一点就是描述代码间关系，或者代码与其它资源（例如数据库表）之间内在联系得数据。
 * 对Struts来说就是struts-config.xml,对ejb来说就是ejb-jar.xml和厂商自定义的xml文件，对hibernate来说就是hbm文件。
 *
 * 但是现有的所有的以xml或者其它方式存在的元数据文件都有以下一些不便之处：
 * 第一，与被描述的文件分离，不利于一致性维护；
 * 第二，所有的这些文件都是ascii文件，没有显示的类型支持。
 * 基于元数据的广泛应用JDK1.5引入了Annotation的概念来描述元数据。
 *
 * 在Java中元数据以标签的形式存在于Java代码中。
 * 元数据标签的存在并不影响程序代码的编译和执行，它只是被用来生成其它的文件或针在运行时知道被运行代码的描述信息。
 *
 * 那么在这里指的就是配置信息，包括xml文件以及注解（标签）
 *
 * 在Java中元数据以标签的形式存在于Java代码中，元数据标签的存在并不影响程序代码的编译和执行，它只是被用来生成其它的文件或针在运行时知道被运行代码的描述信息。
 *
 * Interface defining a generic contract for attaching and accessing metadata
 * to/from arbitrary objects.
 *
 * @author Rob Harrop
 * @since 2.0
 */
public interface AttributeAccessor {

	/**
	 * Set the attribute defined by {@code name} to the supplied {@code value}.
	 * If {@code value} is {@code null}, the attribute is {@link #removeAttribute removed}.
	 * <p>In general, users should take care to prevent overlaps with other
	 * metadata attributes by using fully-qualified names, perhaps using
	 * class or package names as prefix.
	 * 设置name的属性值为value。如果值为null，则属性将被#removeAttribute方法移除
	 * 在一般情况下，用户应该考虑使用全限定name，以避免被覆盖，可以考虑使用类型或包名做前缀
	 *
	 * @param name the unique attribute key
	 * @param value the attribute value to be attached
	 */
	// TODO @Nullable注解是什么作用
	// TODO 在形参前面加上注解
	void setAttribute(String name, @Nullable Object value);

	/**
	 * Get the value of the attribute identified by {@code name}.
	 * Return {@code null} if the attribute doesn't exist
	 * 获取属性name对应的值，没有则为null
	 * @param name the unique attribute key
	 * @return the current value of the attribute, if any
	 */
	@Nullable
	Object getAttribute(String name);

	/**
	 * Remove the attribute identified by {@code name} and return its value.
	 * Return {@code null} if no attribute under {@code name} is found.
	 * 移除name对应的属性，并返回对应值。如果没有对应的属性，将会返回null
	 * @param name the unique attribute key
	 * @return the last value of the attribute, if any
	 */
	@Nullable
	Object removeAttribute(String name);

	/**
	 * Return {@code true} if the attribute identified by {@code name} exists.
	 * Otherwise return {@code false}.
	 * 判断是否存在对应的name属性
	 * @param name the unique attribute key
	 */
	boolean hasAttribute(String name);

	/**
	 * Return the names of all attributes.
	 * 返回所有的属性
	 */
	String[] attributeNames();

}
