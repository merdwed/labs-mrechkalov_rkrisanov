package server.Threading;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import server.ServerNet.Answer;
import server.ServerNet.Connection;
import server.ServerNet.Request;

import java.util.concurrent.RecursiveAction;


public class LoggerForkJoinTask extends RecursiveAction {
    public static Logger connectionLogger = LogManager.getLogger();
    ExecuteForkJoinTask executeCommand;
    Request request;
    public LoggerForkJoinTask(Request request){
        this.request=request;
        executeCommand=new ExecuteForkJoinTask(request);
    }
    @Override
    public void compute(){
        synchronized(request) {System.out.println("logger runs " + request.getAccount().getLogin());};
        executeCommand.fork();//запустили чтобы выполнялся
        synchronized(connectionLogger) {connectionLogger.info("Request received from " + Connection.getInstance().getRemoteAdd());};
        synchronized(connectionLogger) {connectionLogger.info("Request received from user: "+request.getAccount().getLogin());};
        synchronized(connectionLogger) {connectionLogger.info("Request contains command " + request.getCommandType());};
        Answer answer=executeCommand.join();
        connectionLogger.info("Command " + request.getCommandType() + " executed");
        ThreadPools.answerServise.submit(new AnswerRunnableTask(request, answer));  
        synchronized(request) {System.out.println("logger ends " + request.getAccount().getLogin());};              
    }

}