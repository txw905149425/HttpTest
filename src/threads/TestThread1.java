package threads;


/**
 * 线程中调用其他的线程
 * */
public class TestThread1 {
	
     public static void main(String[] args) {
       C c=new C();
 	    c.start();
		
     }
     
}
class A extends Thread{ 
	 @Override
	 public void run() { 
	  System.out.println("A");
	  } 
	}
	class B extends Thread{ 
	 @Override
	 public void run() { 
	  System.out.println("B");
	  } 
	}
	class C extends Thread{ 
	 @Override
	 public void run() { 
	  A a=new A();
	  B b=new B();
	  a.start();
	  b.start();
	  System.out.println("C");
	  } 
	}


