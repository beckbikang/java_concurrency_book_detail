package Java.logic.part6.proxy;


public class CGLibContainerDemo {
	
	public static void main(String[] args) {
		
		ServiceA a = CGLibContainer.getInstance(ServiceA.class);
		a.callB();

		
	}
}
