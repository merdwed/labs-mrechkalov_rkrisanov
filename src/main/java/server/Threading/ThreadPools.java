package server.Threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

public class ThreadPools {
    public static ExecutorService prossesService=Executors.newFixedThreadPool(5);
    public static ForkJoinPool executeService=new ForkJoinPool();//можно было бы через Executors, но я решил оставить так
    public static ExecutorService answerServise=Executors.newCachedThreadPool();

}
