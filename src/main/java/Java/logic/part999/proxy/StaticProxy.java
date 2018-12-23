package Java.logic.part999.proxy;


public class StaticProxy {

    public static void main(String[] args){
        //worker第一种
        WorkerProxy workerProxy = new WorkerProxy(
                new MyWorker()
        );
        workerProxy.run();

        //worker第二种
        WorkerProxy workerProxy2 = new WorkerProxy(
                new MyWorker2()
        );
        workerProxy2.run();
    }
}

interface Worker{
    void run();
}

class MyWorker implements Worker{
    @Override
    public void run() {
        System.out.println("MyWorker running");
    }
}

class MyWorker2 implements Worker{
    @Override
    public void run() {
        System.out.println("MyWorker2 running");
    }
}

class WorkerProxy implements Worker{
    private Worker worker;
    public WorkerProxy(Worker worker){
        this.worker = worker;
    }

    @Override
    public void run() {
        worker.run();
    }
}
