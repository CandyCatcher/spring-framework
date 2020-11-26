package top.candysky.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import top.candysky.introduction.LittleUniverse;

/**
 * 在这里织入通用的逻辑
 *
 */
/*
主流情况下，是基于OOP开发AOP的，所以针对AOP的各种概念实体，最终都需要通过某种方式给集成到系统实现语言所实现的OOP实体组件当中
这就意味着AOP以即生成的形式存在在OOP中
正是因为OOP起着主导的作用，AOP需要在夹缝中生存，让OOP理解AOP的语言，所以实现起来难度比较大
因此也不得不放弃AOP本身的很多灵活性
这也是SpringAOP默认只支持方法级别的链接点，或者有限的类的
这也实现了80%的功能了
实际上成员变量和构造函数都能成为joinPoint的
如果真有此类，spring实现不了的，可以直接使用Aspect

织入：将Aspect模块化的横切关注点集成到OOP里
织入器：完成织入过程的执行者，如Aspect的ajc的编译器，spring默认不带这个编译器
spring会使用类实现
 */
@Aspect
@Component
public class ServiceAspect {
	/**
	 * '@Pointcut'指出往哪里织入
	 * 第一个*表示的是任何返回值类型
	 * ..表示top.candysky.service包下面或者子包中的
	 * 第二个*表示的是所有的类
	 * 第三个*表示的是类里面所有的方法
	 * (..)表示不管方法中不传入或传入任意参数都是织入目标
	 */
	@Pointcut("execution(* top.candysky.service..*.*(..))")
	public void embed() {

	}

	/**
	 * 需要指出注入的时机
	 * 里面是执行的方法
	 * joinPoint指的是切面可以注入逻辑的地方
	 */
	@Before("embed()")
	public void before(JoinPoint joinPoint) {
		System.out.println("开始调用" + joinPoint);
	}

	@After("embed()")
	public void after(JoinPoint joinPoint) {
		System.out.println("调用完成" + joinPoint);

	}

	@Around("embed()")
	public Object around(JoinPoint joinPoint) {
		long startTime = System.currentTimeMillis();
		Object returnValue= null;
		System.out.println("开始计时" + joinPoint);
		try {
			// 调用目标方法执行
			returnValue = ((ProceedingJoinPoint)joinPoint).proceed();
			System.out.println("执行成功，结束计时" + joinPoint);
		} catch (Throwable throwable) {
			throwable.printStackTrace();
			System.out.println("执行失败，结束计时" + joinPoint);
		} finally {
			long endTime = System.currentTimeMillis();
			System.out.println("总耗时" + joinPoint + "[" + (endTime - startTime) +"]ms");
		}
		return returnValue;
	}

	/**
	 * 在方法返回结果的时候进行切入
	 */
	@AfterReturning(pointcut = "embed()", returning = "returnValue")
	public void afterReturning(JoinPoint joinPoint, Object returnValue) {
		System.out.println("无论是空还是有值都返回" + joinPoint + ", 返回值[" + returnValue + "]");
	}

	/**
	 * 方法抛出异常的时候也可以进行切入
	 */
	@AfterThrowing(pointcut = "embed()", throwing = "exception")
	public void afterThrowing(JoinPoint joinPoint, Exception exception) {
		System.out.println("抛出异常通知" + joinPoint + ", 返回值[" + exception + "]");
	}

	/**
	 * Introduction 引入型Advice
	 * 目标类引入新接口，而不需要目标类做任何实现
	 * 使得目标类在使用的过程中转型成新接口对象，调用新接口的方法
	 *
	 * 为父类定义默认的实现
	 * defaultImpl给controller包中所有的类引入一个接口，同时给他们实现接口里面方法的逻辑
	 *
	 * 这样就实现了给controller下的类变身为littleUniverse
	 */
	@DeclareParents(value = "top.candysky.controller.*", defaultImpl = top.candysky.introduction.impl.LittleUniverseImpl.class)
	public LittleUniverse littleUniverse;
}

