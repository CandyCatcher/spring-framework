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

package org.springframework.context;

import java.util.EventObject;

/**
 * Class to be extended by all application events. Abstract as it
 * doesn't make sense for generic events to be published directly.
 *
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @see org.springframework.context.ApplicationListener
 * @see org.springframework.context.event.EventListener
 */

/**
 * spring的事件驱动模型
 *
 * 事件驱动模型的三大部分：
 *   事件：ApplicationEvent抽象类，继承jdk的EventObject
 *		通过里面的source成员变量获得到事件源
 * 事件监听器：ApplicationListener
 * 事件发布器：Publisher以及Multicaster
 */
public abstract class ApplicationEvent extends EventObject {

	/** use serialVersionUID from Spring 1.2 for interoperability. */
	private static final long serialVersionUID = 7099057708183571937L;

	/** System time when the event happened. */
	private final long timestamp;


	/**
	 * Create a new {@code ApplicationEvent}.
	 * @param source the object on which the event initially occurred or with
	 * which the event is associated (never {@code null})
	 *
	 * source是事件源，在spring中指的是事件发布者
	 * 在spring4.2之前，事件必须继承自ApplicationEvent，而从spring4.2之后，框架提供了一个叫做payloadApplicationEvent的子类
	 * 这是一个泛型类，所以可以包装任意类型，就不需要强制继承ApplicationEvent了。
	 * 在容器内部帮我们发送任意类型的事件对象时，框架对象会自动包装为PayloadApplicationEvent这个事件对象
	 */
	public ApplicationEvent(Object source) {
		super(source);
		this.timestamp = System.currentTimeMillis();
	}


	/**
	 * Return the system time in milliseconds when the event occurred.
	 */
	public final long getTimestamp() {
		return this.timestamp;
	}

}
