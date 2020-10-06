package server.Threading;

import java.io.IOException;

import server.ServerNet.Request;

public class ProssesRunnableTask implements Runnable {
    Request request;

    public ProssesRunnableTask(Request request) {
        this.request = request;
    }

    @Override
    public void run() {
        
        try {
            request.prossesAccount();
            System.out.println("Prosses runs " + request.getAccount().getLogin());
            request.prossesCommand();
            request.prossesArg();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ThreadPools.executeService.submit(new LoggerForkJoinTask(request));
        System.out.println("Prosses ends " + request.getAccount().getLogin());
    }
}
