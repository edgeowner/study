package com.bage.study.java.multhread;

/**
 * 多线程交替顺序执行实现
 * @author bage
 *
 */
public class ThreadSeqExchange {

	public static final int count = 1;
	
	public static void main(String[] args) throws Exception {
		
		Print print = new Print();
		Thread thread1 = new ThreadSeqEx1(print);
		Thread thread2 = new ThreadSeqEx2(print);

		// 实现方式
		thread1.start();
		thread2.start();
	}
	
}


class ThreadSeqEx1 extends Thread{
	Print print;
	public ThreadSeqEx1(Print print) {
		this.print = print;
	}
	@Override
	public void run() {
		print.printA();
	}
}

class ThreadSeqEx2 extends Thread{
	Print print;
	public ThreadSeqEx2(Print print) {
		this.print = print;
	}
	@Override
	public void run() {
		print.printB();
	}
}


class Print{
	
	private volatile int orderNum = 1;  

	public synchronized  void printA() {
		for (int i = 0; i < 10; i++) {
			while(orderNum != 1) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("A");
			orderNum = 2;
			notifyAll();  
		}
	}
	public synchronized  void printB() {
		for (int i = 0; i < 10; i++) {
			while(orderNum != 2) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("B");
			orderNum = 1;
			notifyAll();  
		}
	}
}

