package Java.logic.part6.proxy;

import Java.logic.part6.annotation.SimpleInject;

public class ServiceA {

	@SimpleInject
	ServiceB b;
	
	public void callB(){
		b.action();
	}

	public ServiceB getB() {
		return b;
	}
	
	
}
