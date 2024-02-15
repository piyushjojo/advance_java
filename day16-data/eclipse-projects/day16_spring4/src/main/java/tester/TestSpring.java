package tester;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import config.AppConfig;
import dependent.ATMImpl;

public class TestSpring {

	public static void main(String[] args) {
		// start / bootstrap SC , using xml based meta instrs placed in run time class
		// path
		try (AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(AppConfig.class)) {
			System.out.println("SC up n running .................");
			// get ready to user : atm bean from SC
			// o.s.b.f.BeanFactory : method <T> T getBean(String beanId/name, Class<T>
			// beanCls) throws BeansExc
			ATMImpl atm1=ctx.getBean("my_atm", ATMImpl.class);//1st demand
			//B.L 
			atm1.deposit(1000);
			ATMImpl atm2=ctx.getBean("my_atm", ATMImpl.class);//2nd demand
			System.out.println(atm1==atm2);//true since singleton			
		} // ctx.close() => shutting down SC --chks for singleton beans --- destroy
			// ---spring beans marked for GC
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
