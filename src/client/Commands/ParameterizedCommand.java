package client.Commands;

import java.util.Iterator;
import java.util.Stack;
/**
 * the main abstract parent of all parametrized commands
 * @author merdwed
 * @see Command
 * @see StringParametrizedCommand
 * @see LongParametrizedCommand
 * @see DoubleParametrizedCommand
 * @see TicketTypeParametrizedCommand
 * @see TicketParametrizedCommand
 */
public abstract class ParameterizedCommand extends Command{
    //if parameters is null, execution doesn't work, programmer must write condition of null args, but I didn't write it, because I've never caught bugs with this
    /**
     * Stack of parameter classes, which command need. Command pushes Classes to stack in creation
     */
    protected Stack<Class> parameterClassStack=new Stack<Class>();

    /**
     * @return iterator of stack of parameter classes
     * @see ParameterizedCommand#parameterClassStack
     */
    public Iterator<Class> parameterClassIterator(){
        return parameterClassStack.iterator();
    }

    /**
     * method for filling command parameters from outside. One parameter by one calling
     * @exception IllegalArgumentException
     * @param obj current input parameter. Class of the arg must be equal to last needed Class in parameter classes stack
     * @see ParameterizedCommand#parameterClassStack
     */
    public abstract void pushParameter(Object obj);//надо подумать о том чтобы запихнуть это в интерфейс

}
