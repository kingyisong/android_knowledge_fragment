package com.kingyisong.android_knowledge_fragment.deal.core;

import android.util.Log;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by kingyisong on 16/7/21.
 * 涉及到业务处理不得不说系统的线程机制
 * 倘若没有线程，则系统线性执行，大大影响执行效率，也不能充分利用系统的运算速度
 * 所以就出现了线程执行任务的机制，任务之间的关系变换，线程之间的关系变化则会衍生出顺序执行、同步访问资源、线程池等概念
 * 需要结合java 并发编程来搞这一块
 */
public class MultipleThread {

    private static final String TAG = "MultipleThread";

    private ArrayBlockingQueue<String> mQue;


    public void test() {
        mQue = new ArrayBlockingQueue<String>(10);
        blockExecute();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                insertQue("1");
                insertQue("2");
                insertQue("3");
            }
        }).start();
    }

    private void blockExecute() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    String poll = mQue.poll();
                    if (poll != null) {
                        Log.d(TAG, "blockExecute" + poll);
                    }

                }
            }
        }).start();

    }


    private void insertQue(String item) {
        try {
            mQue.put(item);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
