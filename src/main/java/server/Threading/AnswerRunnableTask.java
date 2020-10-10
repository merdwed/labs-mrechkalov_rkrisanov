package server.Threading;

import java.io.IOException;
import java.sql.SQLException;

import DataClasses.CommandTypeUtils.CommandType;
import server.DataBase.DataBase;
import server.ServerNet.ActiveUsers;
import server.ServerNet.Answer;
import server.ServerNet.Connection;
import server.ServerNet.Request;

public class AnswerRunnableTask implements Runnable {
    Request request;
    Answer answer;
    AnswerRunnableTask(Request request, Answer answer){
        this.request=request;
        this.answer=answer;
    }
    @Override
    public void run(){
        System.out.println("answer runs " + request.getAccount().getLogin());
        try {
            if (DataBase.getInstance().checkAccount(request.getAccount())||request.getCommandType().equals(CommandType.CREATE_ACCOUNT)) {

                answer.prepare(request.getCommandType());
                answer.send();
                ActiveUsers.getInstance().send();
                LoggerForkJoinTask.connectionLogger.info("Answer sent to " + Connection.getInstance().getRemoteAdd());//КОСТЫЛЬ
            }
            else
            {
                answer.setToCurrans("Wrong user\nAccount isn't exist");
                answer.prepare(request.getCommandType());
                answer.send();
            }
        } catch (SQLException|IOException e) {
            e.printStackTrace();
        }
        System.out.println("answer ends " + request.getAccount().getLogin());
    }
    
}
