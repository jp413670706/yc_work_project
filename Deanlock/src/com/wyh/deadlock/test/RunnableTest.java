package com.wyh.deadlock.test;

public class RunnableTest implements Runnable {
private int flag = 1;
private Object obj1 = new Object(), obj2 = new Object();

public void run() {
    System.out.println("flag=" + flag);
    if (flag == 1) {
        synchronized (obj1) {
            System.out.println("���Ѿ�����obj1����Ϣ0.5�������obj2ȥ��");   
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (obj2) {
                System.out.println("1");
            }
        }
    }
    if (flag == 0) {
        synchronized (obj2) {
            System.out.println("���Ѿ�����obj2����Ϣ0.5�������obj1ȥ��");   
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (obj1) {
                System.out.println("0");
            }
        }
    }
}

public static void main(String[] args) {
    RunnableTest run01 = new RunnableTest();
    RunnableTest run02 = new RunnableTest();
    run01.flag = 1;
    run02.flag = 0;
    Thread thread01 = new Thread(run01);
    Thread thread02 = new Thread(run02);
    System.out.println("�߳̿�ʼඣ�");   
    thread01.start();
    thread02.start();
}
}