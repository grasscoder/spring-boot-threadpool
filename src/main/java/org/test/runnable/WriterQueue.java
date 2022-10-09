package org.test.runnable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WriterQueue {
    private static final Logger logger= LoggerFactory.getLogger(WriterQueue.class);

    private static final int MAX_QUEUE_SIZE = 10000;
    private static LinkedList<String> queue = new LinkedList<String>();
    private Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    private static WriterQueue manager = new WriterQueue();

    private WriterQueue() {
    }

    public static WriterQueue getQueue() {
        return manager;
    }

    public int size() {
        synchronized (getQueue()) {
            return getQueue().queue.size();
        }
    }

    public void put(String context) {
        lock.lock();
        try {
            while (queue.size() == MAX_QUEUE_SIZE) {
                logger.info(Thread.currentThread().getName() + " warning: data queue is full!");
                notFull.await();
            }
            queue.addFirst(context);
            notEmpty.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public LinkedList<String> takeAll() {
        LinkedList<String> retVal = new LinkedList<String>();
        lock.lock();
        try {
            while (queue.size() == 0) {
                logger.info(Thread.currentThread().getName() + " warning: data queue is empty!");
                notEmpty.await();
            }
            retVal.addAll(queue);
//			for(String str : queue){
//				retVal.add(str);
//			}
            //清空队列
            queue.clear();
            notFull.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return retVal;
    }
}