package com.fayayo.study.threadPool;

import java.util.concurrent.*;

/**
 * @author dalizu on 2018/8/10.
 * @version v1.0
 * @desc 自定义线程池
 */
public class ThreadPoolManager {

    private ThreadPoolExecutor threadPoolExecutor;
    private int coreThread = 10;
    private int maxThread = 50;
    private int maxTimeRecycle = 60;

    public ThreadPoolManager() {
        //log.info("{}初始化RpcJobThreadPool......",Constants.LOG_PREFIX);
        //无界 也可以指定大小的队列
        final LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(maxThread);
        threadPoolExecutor = new ThreadPoolExecutor(coreThread, maxThread, maxTimeRecycle, TimeUnit.SECONDS,
                queue,
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread t = new Thread(r);
                        t.setName("demo-thread");
                        if(t.isDaemon()) {
                            t.setDaemon(false);
                        }
                        if(Thread.NORM_PRIORITY != t.getPriority()) {
                            t.setPriority(Thread.NORM_PRIORITY);
                        }
                        return t;
                    }
                });
        //设置策略
        /*threadPoolExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());//丢弃任务，但是不抛出异常
        threadPoolExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());//丢弃队列最前面的任务,执行后面加入的任务
        threadPoolExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());//默认情况  丢弃任务，异常抛出
        threadPoolExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());//由调用线程执行*/
        //自定义策略
        threadPoolExecutor.setRejectedExecutionHandler(new BlockRejectedExecutionHandler());
        //监控线程
        Thread daemonThread = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    if (queue.size() > maxThread) {
                        //log.info("{}current thread task size:{} exceed thread pool define size:{}",Constants.LOG_PREFIX,queue.size(),maxThread);
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        daemonThread.setDaemon(true);
        daemonThread.start();
    }

     /**
       *@描述 拒绝策略
     */
    private class BlockRejectedExecutionHandler implements RejectedExecutionHandler {
        public void rejectedExecution(Runnable runnable, ThreadPoolExecutor executor) {
            try {
                //log.info("current thread task exceed thread pool define size : {}", maxThread);
                // 核心改造点，由blockingqueue的offer改成put阻塞方法
                executor.getQueue().put(runnable);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void execute(Runnable command) {
        threadPoolExecutor.execute(command);
    }

    public Future<?> submit(Callable<?> command) {
        return threadPoolExecutor.submit(command);
    }

    public void shutdown() {
        threadPoolExecutor.shutdown();
    }

    public int getActiveCount() {
        return threadPoolExecutor.getActiveCount();
    }

}
