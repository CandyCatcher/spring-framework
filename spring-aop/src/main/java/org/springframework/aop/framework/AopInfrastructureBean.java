/*
 * Copyright 2002-2007 the original author or authors.
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

package org.springframework.aop.framework;

/**
 * Marker interface that indicates a bean that is part of Spring's
 * AOP infrastructure. In particular, this implies that any such bean
 * is not subject to auto-proxying, even if a pointcut would match.
 *
 * @author Juergen Hoeller
 * @since 2.0.3
 * @see org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator
 * @see org.springframework.aop.scope.ScopedProxyFactoryBean
 */
/*
 免被AOP代理的标记接口.若Bean实现了此接口，表明它是一个Spring AOP的基础类，
 那么这个类是不会被AOP给代理的，即便它匹配pointcut表达式
 一般没有方法的接口，作用就是标记的
 实现了该接口的bean即使实现了pointcut表达式也是不会通过SpringAOP表达式处理的、
 这些bean会被当做SpringAOP的基础服务类来处理
 */
public interface AopInfrastructureBean {

}
