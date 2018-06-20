/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

/**
 *
 * @author USER
 */
public class JavaApplication2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
         
        threadDemo thread1=new threadDemo();
        
        System.out.println(" 生产者/消费者问题\n ");
        thread1.test9();
        
       
        
        
        System.out.println("获取当前线程名称 \n ");
        thread1.test2();
        
      // System.out.println("  状态监测\n ");
       // thread1.test3();
        
        System.out.println("线程优先级设置 \n ");
        thread1.test4();
        
        
        
       System.out.println(" 获取线程id\n ");
        thread1.test6();
        
       
        
        
        
        
        
        //System.out.println(" 获取线程状态\n ");
       // thread1.test10();
        
       System.out.println(" 获取所有线程 \n ");
       thread1.test11();
        
        System.out.println("  查看线程优先级\n ");
        thread1.test12();
        
        System.out.println(" 线程挂起\n ");
        thread1.test7();
        
      // System.out.println(" 死锁及解决方法\n ");
      //  thread1.test5();
        
       System.out.println("中断线程 \n ");
        thread1.test13();
        
        
     
        
        System.out.println(" 查看线程是否存活\n ");
        thread1.test1();
        
       // System.out.println("终止线程 \n ");
       // thread1.test8();
        
    }
    
}
