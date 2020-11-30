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

package org.springframework.aop;

import org.springframework.lang.Nullable;

/**
 * A {@code TargetSource} is used to obtain the current "target" of
 * an AOP invocation, which will be invoked via reflection if no around
 * advice chooses to end the interceptor chain itself.
 *
 * <p>If a {@code TargetSource} is "static", it will always return
 * the same target, allowing optimizations in the AOP framework. Dynamic
 * target sources can support pooling, hot swapping, etc.
 *
 * <p>Application developers don't usually need to work with
 * {@code TargetSources} directly: this is an AOP framework interface.
 *
 * @author Rod Johnson
 * @author Juergen Hoeller
 *
 * springAOP不是直接代理被代理对象的Class对象实例
 * 而是需要通过被代理对象class实例的TargetSource间接代理class对象实例
 * 这是因为通常情况下，一个proxy只能代理一个Target，每次方法调用的目标也只能固定为target
 * 但是如果让proxy代理targetsource，可以让每次方法调用的目标不同或者相同，这都取决于
 * 方法的实现，这种机制使得方法调用非常灵活，可以扩展出很多功能。比如通过实现getTarget（）方法
 * 从目标对象池里去获取被代理实例并返回，也就是做池化处理。也可以做一个对运行时的被代理对象进行热替换操作等等
 */
public interface TargetSource extends TargetClassAware {

	/**
	 * Return the type of targets returned by this {@link TargetSource}.
	 * <p>Can return {@code null}, although certain usages of a {@code TargetSource}
	 * might just work with a predetermined target class.
	 * @return the type of targets returned by this {@link TargetSource}
	 * 返回被代理对象的目标类型
	 */
	@Override
	@Nullable
	Class<?> getTargetClass();

	/**
	 * Will all calls to {@link #getTarget()} return the same object?
	 * <p>In that case, there will be no need to invoke {@link #releaseTarget(Object)},
	 * and the AOP framework can cache the return value of {@link #getTarget()}.
	 * @return {@code true} if the target is immutable
	 * @see #getTarget
	 * 返回目标源是否是静态的
	 * 当前目标源是否是静态的。
	 * 如果为false，则每次方法调用结束后会调用releaseTarget()释放目标对象.
	 * 如果为true，则目标对象不可变，也就没必要释放了。
	 */
	boolean isStatic();

	/**
	 * Return a target instance. Invoked immediately before the
	 * AOP framework calls the "target" of an AOP method invocation.
	 * @return the target object which contains the joinpoint,
	 * or {@code null} if there is no actual target instance
	 * @throws Exception if the target object can't be resolved
	 * 获取一个目标对象。
	 * 在每次MethodInvocation方法调用执行之前获取。
	 */
	@Nullable
	Object getTarget() throws Exception;

	/**
	 * Release the given target object obtained from the
	 * {@link #getTarget()} method, if any.
	 * @param target object obtained from a call to {@link #getTarget()}
	 * @throws Exception if the object can't be released
	 * 释放指定的目标对象。
	 */
	void releaseTarget(Object target) throws Exception;

}
