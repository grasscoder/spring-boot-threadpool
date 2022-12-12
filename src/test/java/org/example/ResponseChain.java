package org.example;

abstract class Handler {
    public Handler next;

    public void setHandler(Handler handler) {
        this.next = handler;
    }

    public abstract void doHandler(User user);
}
class User{
    String name;
}

class AHandler extends Handler {
    @Override
    public void doHandler(User user) {
        System.out.println("AHandler 处理");
        if (this.next != null) {
            this.next.doHandler(user);
        }
    }
}

class BHandler extends Handler {
    @Override
    public void doHandler(User user) {
        System.out.println("BHandler 处理");
        if (this.next != null) {
            this.next.doHandler(user);
        }
    }
}

class CHandler extends Handler {
    @Override
    public void doHandler(User user) {
        System.out.println("CHandler 处理");
        if (this.next != null) {
            this.next.doHandler(user);
        }
    }
}

public class ResponseChain {
    public static void main(String[] args) {
        User user = new User();
        AHandler a = new AHandler();
        BHandler b = new BHandler();
        CHandler c = new CHandler();
        a.setHandler(b);
        b.setHandler(c);
        a.doHandler(user);
    }
}
