package server.Threading;

import server.ServerNet.Answer;
import server.ServerNet.Request;
import server.ServerNet.ServerCommandFactory;

import java.io.IOException;
import java.util.concurrent.RecursiveTask;

public class ExecuteForkJoinTask extends RecursiveTask<Answer> {
    Request request;
    Answer answer;

    public ExecuteForkJoinTask(Request request) {
        this.request = request;
        answer = new Answer();
    }

    @Override
    public Answer compute() {
        synchronized(request) {System.out.println("execute runs " + request.getAccount().getLogin());};
        try {
            synchronized(request) {
                synchronized (ServerCommandFactory.class){ServerCommandFactory.createCommand(request.getCommandType()).execute(request, answer);}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        synchronized(request) {System.out.println("execute ends " + request.getAccount().getLogin());};
        return answer;
        
    }
}
