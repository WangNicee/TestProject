package com.company;

import java.util.LinkedList;

public class Account {
    //。账号
    private String actno;
    //余额
    private double balance;

    public String getActno() {
        return actno;
    }

    public void setActno(String actno) {
        this.actno = actno;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Account() {
    }

    public Account(String actno, double balance) {
        this.actno = actno;
        this.balance = balance;
    }
    //取款的方法
    //synchronized也可以写在实例方法上，，表示整个方法都要同步化(效率不高),这样默认锁的是this，这样写不灵活
    public /*synchronized*/ void withdraw(double money){
        /*
        线程同步机制的语法是:
        synchronized(){
            //线程同步代码快
        }
        synchronized后面小括号传入的内容很关键
        这个数据必须是多线程共享的数据。
        在这个例子中，账户对象是共享的，所以这里填this(this的引用类型属性也行，这里act变量可
        以传进去，但是balance不行，需要的是Object子类，这里应该不允许自动装包，因为装包后的变量是新对象了)

        在java语言中，任何一个对象都有一把“锁”，其实这把锁就是标记。（只是把它叫做锁）
        100个对象，100把锁。1个对象1把锁

        synchronized ("abc")使用这个也能达到一样的效果，因为"abc"是常量池中的对象，在"abc"上加锁感觉效果
        和在balance上加锁一样，虽然不直接访问"abc"，但是只有获得锁的线程才能执行synchronized中的代码，也限制了多个
        线程对balance的删减
        需要注意的是synchronized ("abc")表示所有操作"abc"的线程都同步，因为常量池中的对象为所有线程所共享
         */
        synchronized ("abc"){
            //获取取款之前的余额
            double before=this.getBalance();
            //取款之后的余额
            double after=before-money;
            try {
                Thread.sleep(5*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //更新余额
            this.setBalance(after);
        }
    }
}
