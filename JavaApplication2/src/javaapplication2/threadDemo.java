/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import static java.lang.Thread.sleep;
import java.util.Date;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
/**
 *
 * @author USER
 */
public class threadDemo {
    void test1(){
    TwoThreadAlive tt = new TwoThreadAlive();
      tt.setName("Thread");
      System.out.println("before start(), tt.isAlive()=" + tt.isAlive());
      tt.start();
      System.out.println("just after start(), tt.isAlive()=" + tt.isAlive());
      for (int i = 0; i < 10; i++) {
         tt.printMsg();
      }
      System.out.println("The end of main(), tt.isAlive()=" + tt.isAlive());
    
    }//1
    
    void test2(){
    
    TwoThreadGetName tt = new TwoThreadGetName();
      tt.start();
      for (int i = 0; i < 10; i++) {
         tt.printMsg();
      }
    
    }//2
    
    static void showThreadStatus(Thread thrd) {
      System.out.println(thrd.getName() + "Alive:=" + thrd.isAlive() + " State:=" + thrd.getState());
   }
    
    void test3(){
        
try{
    MyThread1 thrd = new MyThread1();
      thrd.setName("MyThread #1");
      showThreadStatus(thrd);
      thrd.start();
      Thread.sleep(50);
      showThreadStatus(thrd);
      thrd.waiting = false;
      Thread.sleep(50); 
      showThreadStatus(thrd);
      thrd.notice();
      Thread.sleep(50);
      showThreadStatus(thrd);
      while(thrd.isAlive()) 
      System.out.println("alive");
      showThreadStatus(thrd);
          
 }catch(Exception e){
          System.out.println("Exception thrown  :" + e);
}
    
    }//3
    
    void test4(){
    
      new SimplePriorities(Thread.MAX_PRIORITY);
      for(int i = 0; i < 5; i++)
      new SimplePriorities(Thread.MIN_PRIORITY);
    
    }//4
    
    /**
     * 
     * 为了解决这个问题，我们不使用显示的去锁，我们用信号量去控制。
信号量可以控制资源能被多少线程访问，这里我们指定只能被一个线程访问，
* 就做到了类似锁住。而信号量可以指定去获取的超时时间，
* 我们可以根据这个超时时间，去做一个额外处理。
对于无法成功获取的情况，一般就是重复尝试，
* 或指定尝试的次数，也可以马上退出。
     */
    void test5(){
      UnLockTest a1=new UnLockTest();
      a1.run();
    }//5
    
    void test6(){
     ThreadID tid = new ThreadID();
    Main2 shared = new Main2(tid);
 
    try {
      Thread threadA = new Thread(shared, "threadA");
      threadA.start();
 
      Thread.sleep(500);
 
      Thread threadB = new Thread(shared, "threadB");
      threadB.start();
 
      Thread.sleep(500);
 
      Thread threadC = new Thread(shared, "threadC");
      threadC.start();
    } catch (InterruptedException x) {
    }
    
    }//6
    
    void test7(){
    
        try{
          for (int i = 0; i < 5; i++)
          new SleepingThread().join();
          System.out.println("线程已被挂起");
        
        }catch(Exception e){
          System.out.println("Exception thrown  :" + e);
        }
    
    
    }//7
    
    void test8(){
     

try{
        Thread thread = new ThreadInterrupt(); 
        thread.start(); 
        System.out.println("在50秒之内按任意键中断线程!"); 
        System.in.read(); 
        thread.interrupt(); 
        thread.join(); 
        System.out.println("线程已经退出!");
 }catch(Exception e){
          System.out.println("Exception thrown  :" + e);
}
    
    }//8
    
    void test9(){
    ProducerConsumerTest a2=new ProducerConsumerTest();
    a2.ProducerConsumerTest_main();
    
    }//9
    
    void test10(){
    

try{
          Main3 s0=new Main3();
          s0.maintest();
 }catch(Exception e){
          System.out.println("Exception thrown  :" + e);
}
    
    }//10
    
    void test11(){
        
    Main5 t1 = new Main5();
      t1.setName("thread1");
      t1.start();
      ThreadGroup currentGroup = 
      Thread.currentThread().getThreadGroup();
      int noThreads = currentGroup.activeCount();
      Thread[] lstThreads = new Thread[noThreads];
      currentGroup.enumerate(lstThreads);
      for (int i = 0; i < noThreads; i++)
      System.out.println("线程号：" + i + " = " + lstThreads[i].getName());
    
    }//11
    
    void test12(){
           Main6 s0=new Main6();
          s0.test();
    
    }//12
    
    void test13(){
      Main8 si = new Main8();
      Thread t = new Thread(si);
      t.start();
      try {
         Thread.sleep(2000);
      }
      catch (InterruptedException x) {
      }
      System.out.println("in main() - 中断其他线程");
      t.interrupt();
      System.out.println("in main() - 离开");
      
    }//13
}

class Main8 extends Object 
implements Runnable {
   public void run() {
      try {
         System.out.println("in run() - 将运行 work2() 方法");
         work2();
         System.out.println("in run() - 从 work2() 方法回来");
      }
      catch (InterruptedException x) {
         System.out.println("in run() - 中断 work2() 方法");
         return;
      }
      System.out.println("in run() - 休眠后执行");
      System.out.println("in run() - 正常离开");
   }
   public void work2() throws InterruptedException {
      while (true) {
         if (Thread.currentThread().isInterrupted()) {
            System.out.println("C isInterrupted()=" + Thread.currentThread().isInterrupted());
            Thread.sleep(2000);
            System.out.println("D isInterrupted()=" + Thread.currentThread().isInterrupted());
         }
      }
   }
   public void work() throws InterruptedException {
      while (true) {
         for (int i = 0; i < 100000; i++) {
            int j = i * 2;
         }
         System.out.println("A isInterrupted()=" + Thread.currentThread().isInterrupted());
         if (Thread.interrupted()) {
            System.out.println("B isInterrupted()=" + Thread.currentThread().isInterrupted());
            throw new InterruptedException();
         }
      }
   }
   //public static void main(String[] args) {
     
   //}
}

class Main6 extends Object {
   private static Runnable makeRunnable() {
      Runnable r = new Runnable() {
         public void run() {
            for (int i = 0; i < 5; i++) {
               Thread t = Thread.currentThread();
               System.out.println("in run() - priority="
               + t.getPriority()+ ", name=" + t.getName());
               try {
                  Thread.sleep(2000);
               }
               catch (InterruptedException x) {
               }
            }
         }
      };
      return r;
   }
   public void test() {
      System.out.println("in main() - Thread.currentThread().getPriority()=" + Thread.currentThread().getPriority());
      System.out.println("in main() - Thread.currentThread().getName()="+ Thread.currentThread().getName());
      Thread threadA = new Thread(makeRunnable(), "threadA");
      threadA.start();
      try {
         Thread.sleep(3000);
      }
      catch (InterruptedException x) {
      }
      System.out.println("in main() - threadA.getPriority()="+ threadA.getPriority());
   }
}


class Main5 extends Thread {
   
}


class ThreadInterrupt extends Thread 
{ 
    public void run() 
    { 
        try 
        { 
            sleep(50000);  // 延迟50秒 
            System.out.println("线程...");
        } 
        catch (InterruptedException e) 
        { 
            System.out.println(e.getMessage()); 
        } 
    } 
    
   // public static void main(String[] args) throws Exception 
    //{ 
        
   // } 
    
}


class SleepingThread extends Thread {
   private int countDown = 5;
   private static int threadCount = 0;
   public SleepingThread() {
      super("" + ++threadCount);
      start();
   }
   public String toString() { 
      return "#" + getName() + ": " + countDown;
   }
   public void run() {
      while (true) {
         System.out.println(this);
         if (--countDown == 0)
            return;
         try {
            sleep(100);
         }
         catch (InterruptedException e) {
            throw new RuntimeException(e);
         }
      }
   }
   //public static void main(String[] args) 
   //throws InterruptedException {
    
  // }
}

class Main2 extends Object implements Runnable {
  private ThreadID var;
 
  public Main2(ThreadID v) {
    this.var = v;
  }
 
  public void run() {
    try {
      print("var getThreadID =" + var.getThreadID());
      Thread.sleep(2000);
      print("var getThreadID =" + var.getThreadID());
    } catch (InterruptedException x) {
    }
  }
 
  private static void print(String msg) {
    String name = Thread.currentThread().getName();
    System.out.println(name + ": " + msg);
  }
 
  //public static void main(String[] args) {
   
 // }
  
  
}
 
class ThreadID extends ThreadLocal {
  private int nextID;
 
  public ThreadID() {
    nextID = 10001;
  }
 
  private synchronized Integer getNewID() {
    Integer id = new Integer(nextID);
    nextID++;
    return id;
  }
 
 
  protected Object initialValue() {
    print("in initialValue()");
    return getNewID();
  }
 
  public int getThreadID() {
    Integer id = (Integer) get();
    return id.intValue();
  }
 
  private static void print(String msg) {
    String name = Thread.currentThread().getName();
    System.out.println(name + ": " + msg);
  }
}

class UnLockTest {
   public static String obj1 = "obj1";
   public static final Semaphore a1 = new Semaphore(1);
   public static String obj2 = "obj2";
   public static final Semaphore a2 = new Semaphore(1);
 
  // public static void main(String[] args) {
   
  // }
   public void run(){
      LockAa la = new LockAa();
      new Thread(la).start();
      LockBb lb = new LockBb();
      new Thread(lb).start();
   }
}
class LockAa implements Runnable {
   public void run() {
      try {
         System.out.println(new Date().toString() + " LockA 开始执行");
         while (true) {
            if (UnLockTest.a1.tryAcquire(1, TimeUnit.SECONDS)) {
               System.out.println(new Date().toString() + " LockA 锁住 obj1");
               if (UnLockTest.a2.tryAcquire(1, TimeUnit.SECONDS)) {
                  System.out.println(new Date().toString() + " LockA 锁住 obj2");
                  Thread.sleep(60 * 1000); // do something
               }else{
                  System.out.println(new Date().toString() + "LockA 锁 obj2 失败");
               }
            }else{
               System.out.println(new Date().toString() + "LockA 锁 obj1 失败");
            }
            UnLockTest.a1.release(); // 释放
            UnLockTest.a2.release();
            Thread.sleep(500); // 马上进行尝试，现实情况下do something是不确定的
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}
class LockBb implements Runnable {
   public void run() {
      try {
         System.out.println(new Date().toString() + " LockB 开始执行");
         while (true) {
            if (UnLockTest.a2.tryAcquire(1, TimeUnit.SECONDS)) {
               System.out.println(new Date().toString() + " LockB 锁住 obj2");
               if (UnLockTest.a1.tryAcquire(1, TimeUnit.SECONDS)) {
                  System.out.println(new Date().toString() + " LockB 锁住 obj1");
                  Thread.sleep(60 * 500); // do something
               }else{
                  System.out.println(new Date().toString() + "LockB 锁 obj1 失败");
               }
            }else{
               System.out.println(new Date().toString() + "LockB 锁 obj2 失败");
            }
            UnLockTest.a1.release(); // 释放
            UnLockTest.a2.release();
            Thread.sleep(10 * 500); // 这里只是为了演示，所以tryAcquire只用1秒，而且B要给A让出能执行的时间，否则两个永远是死锁
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}


class SimplePriorities extends Thread {
   private int countDown = 5;
   private volatile double d = 0; 
   public SimplePriorities(int priority) {
      setPriority(priority);
      start();
   }
   public String toString() {
      return super.toString() + ": " + countDown;
   }
   public void run() {
      while(true) {
         for(int i = 1; i < 100000; i++)
         d = d + (Math.PI + Math.E) / (double)i;
         System.out.println(this);
         if(--countDown == 0) return;
      }
   }
   
   /**
   public static void main(String[] args) {
      new SimplePriorities(Thread.MAX_PRIORITY);
      for(int i = 0; i < 5; i++)
      new SimplePriorities(Thread.MIN_PRIORITY);
   }
   */
   
}



class MyThread1 extends Thread{
   boolean waiting= true;
   boolean ready= false;
   MyThread1() {
   }
   public void run() {
      String thrdName = Thread.currentThread().getName();
      System.out.println(thrdName + " starting.");
      while(waiting) 
      System.out.println("waiting:"+waiting); 
      System.out.println("waiting...");
      startWait(); 
      try {
         Thread.sleep(1000);
      }
      catch(Exception exc) {
         System.out.println(thrdName + " interrupted.");
      }
      System.out.println(thrdName + " terminating.");
   }
   synchronized void startWait() {
      try {
         while(!ready) wait();
      }
      catch(InterruptedException exc) {
         System.out.println("wait() interrupted");
      }
   }
   synchronized void notice() {
      ready = true;
      notify();
   }
}

class TwoThreadGetName extends Thread {
   public void run() {
      for (int i = 0; i < 10; i++) {
         printMsg();
      }
   }
   public void printMsg() {
      Thread t = Thread.currentThread();
      String name = t.getName();
      System.out.println("name=" + name);
   } 
   /**
   public static void main(String[] args) {
      TwoThreadGetName tt = new TwoThreadGetName();
      tt.start();
      for (int i = 0; i < 10; i++) {
         tt.printMsg();
      }
   }
   
   */
   
}

class TwoThreadAlive extends Thread {
   public void run() {
      for (int i = 0; i < 10; i++) {
         printMsg();
      }
   }
 
   public void printMsg() {
      Thread t = Thread.currentThread();
      String name = t.getName();
      System.out.println("name=" + name);
   }
 /**
   public static void main(String[] args) {
      TwoThreadAlive tt = new TwoThreadAlive();
      tt.setName("Thread");
      System.out.println("before start(), tt.isAlive()=" + tt.isAlive());
      tt.start();
      System.out.println("just after start(), tt.isAlive()=" + tt.isAlive());
      for (int i = 0; i < 10; i++) {
         tt.printMsg();
      }
      System.out.println("The end of main(), tt.isAlive()=" + tt.isAlive());
   }
   
   */ 
   
}

/*
 author by runoob.com
 ProducerConsumerTest.java
 */

class ProducerConsumerTest {
   public void ProducerConsumerTest_main() {
      CubbyHole c = new CubbyHole();
      Producer p1 = new Producer(c, 1);
      Consumer c1 = new Consumer(c, 1);
      p1.start(); 
      c1.start();
   }
}
class CubbyHole {
   private int contents;
   private boolean available = false;
   public synchronized int get() {
      while (available == false) {
         try {
            wait();
         }
         catch (InterruptedException e) {
         }
      }
      available = false;
      notifyAll();
      return contents;
   }
   public synchronized void put(int value) {
      while (available == true) {
         try {
            wait();
         }
         catch (InterruptedException e) { 
         } 
      }
      contents = value;
      available = true;
      notifyAll();
   }
}

class Consumer extends Thread {
   private CubbyHole cubbyhole;
   private int number;
   public Consumer(CubbyHole c, int number) {
      cubbyhole = c;
      this.number = number;
   }
   public void run() {
      int value = 0;
         for (int i = 0; i < 10; i++) {
            value = cubbyhole.get();
            System.out.println("消费者 #" + this.number+ " got: " + value);
         }
    }
}

class Producer extends Thread {
   private CubbyHole cubbyhole;
   private int number;

   public Producer(CubbyHole c, int number) {
      cubbyhole = c;
      this.number = number;
   }

   public void run() {
      for (int i = 0; i < 10; i++) {
         cubbyhole.put(i);
         System.out.println("生产者 #" + this.number + " put: " + i);
         try {
            sleep((int)(Math.random() * 100));
         } catch (InterruptedException e) { }
      }
   }
}


class MyThread5 extends Thread{
   boolean waiting= true;
   boolean ready= false;
   MyThread5() {
   }
   public void run() {
      String thrdName = Thread.currentThread().getName();
      System.out.println(thrdName + " 启动");
      while(waiting)
      System.out.println("等待："+waiting);
      System.out.println("等待...");
      startWait();
      try {
         Thread.sleep(1000);
      }
      catch(Exception exc) {
         System.out.println(thrdName + " 中断。");
      }
      System.out.println(thrdName + " 结束。");
   }
   synchronized void startWait() {
      try {
         while(!ready) wait();
      }
      catch(InterruptedException exc) {
         System.out.println("wait() 中断。");
      }
   }
   synchronized void notice() {
      ready = true;
      notify();
   }
}
class Main3 {
   public void maintest() 
   throws Exception{
      MyThread5 thrd = new MyThread5();
      thrd.setName("MyThread #1");
      showThreadStatus(thrd);
      thrd.start();
      Thread.sleep(50);
      showThreadStatus(thrd);
      thrd.waiting = false;
      Thread.sleep(50);
      showThreadStatus(thrd);
      thrd.notice();
      Thread.sleep(50);
      showThreadStatus(thrd);
      while(thrd.isAlive()) {
          System.out.println("alive");
      }
      showThreadStatus(thrd);
   }
   static void showThreadStatus(Thread thrd) {
      System.out.println(thrd.getName()+" 存活:" +thrd.isAlive()+" 状态:" + thrd.getState() );
   }
}
