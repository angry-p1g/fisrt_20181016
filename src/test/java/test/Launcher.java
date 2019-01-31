package test;

public class Launcher {
    private static TaskThreadPool taskPool = new TaskThreadPool();

    public static void main(String[] args) {
        // 新建100个任务，Runnable的实现类Task
        Task[] tasks = new Task[100];
        for (int i = 0; i < tasks.length; i++) {
            tasks[i] = new Task("Task " + (i + 1));
            // 提交到线程池运行
            System.out.println("查看设置的线程数是否有限制 "+i);
            taskPool.execute(tasks[i]);
            if (i % 50 == 0) {
                taskPool.status();
            }
        }
    }

    private static class Task implements Runnable {
        private String name;

        public Task(String name) {
            this.name = name;
        }

        public void run() {
            // do something
            System.out.println("我的名字是：" + this.name);
            try{
                Thread.sleep(10000);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

}