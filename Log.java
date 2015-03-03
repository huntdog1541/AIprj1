/**
 * Created by David on 3/2/2015.
 */
import java.lang.*;

public class Log {

    private String erroMessage;
    private String response;
    private gui display;
    private StringBuilder sb;

    public Log()
    {

    }

    public Log(gui gi)
    {
        display = gi;
    }

    public void printResponse(String msg)
    {
        display.addText("\n");
        display.addText(msg);
        display.addText("\n");
    }

    public void buildString(String temp)
    {
        sb = new StringBuilder(temp);
    }

    public void addString(String temp)
    {
        sb.append(temp);
    }

    public void printString()
    {
        response = sb.toString();
        display.addText("\n");
        display.addText(response);
        display.addText("\n");
    }

}
