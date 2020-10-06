package server.ServerCommands;

import server.ServerNet.Answer;
import server.ServerNet.Request;

public interface Execute {
    public abstract void execute(Request request, Answer answer);
}
