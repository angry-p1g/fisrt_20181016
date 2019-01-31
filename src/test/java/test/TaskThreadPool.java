package test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


public class TaskThreadPool {

    private final static ExecutorService threadPool = Executors.newFixedThreadPool( 10);

    private final ThreadPoolExecutor executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(10); // 创建一个大小为9的固定线程池，可以按照CPU的核数初步判定，如果CPU密集性任务则创建N+1个，如果是IO密集型任务则创建2N+1个，其中N即CPU的核数
    protected void shutdown(){
        // do something
        // 这个方法等待线程池中所有已提交任务执行结束，不接收新任务，然后结束
        executor.shutdown();
        // 这个强制结束所有任务，然后正在等在的任务列表
        // executor.shutdownNow();
    }
    protected void execute(Runnable command){
        // do something
        // 提交任务
        executor.execute(command);
    }
    public void status(){
        StringBuffer sb = new StringBuffer();
        // 当前正在执行任务的线程数
        sb.append(executor.getActiveCount() + "\n");
        // 当前正在等待执行的线程数
        sb.append(executor.getQueue().size() + "\n");
        // 返回已经完成的线程数
        sb.append(executor.getCompletedTaskCount() + "\n");
        System.out.println(sb.toString());
    }


    public static void main(String[] args) {
        for(int i = 0;i < 100 ; i++){
            threadPool.submit(new MyCallable(i));
        }
    }
}

class MyCallable implements Runnable{
    private int i;
    MyCallable(int i){
        this.i = i;
    }

    @Override
    public void run() {
        System.out.println("获取当前线程数: "+Thread.currentThread() +" -----"+i);
    }
}