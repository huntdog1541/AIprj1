/**
 * Created by David on 3/2/2015.
 */
import java.io.File;
import java.io.PrintWriter;
import java.lang.*;

public class Log {

    private String erroMessage;
    private String response;
    private gui display;
    private StringBuilder sb;
    private PrintWriter output;
    private boolean blindWalkOn;
    private boolean BreadthWalkOn;
    private boolean depthWalkOn;
    private boolean hillclimbingWalkOn;
    private boolean randomrestartWalkOn;
    private boolean astarWalkOn;
    private boolean iterativedeepWalkOn;
    private boolean heuristicWalkOn;

    public Log()
    {

    }

    public Log(gui gi)
    {
        display = gi;
        //output = new PrintWriter("output.txt");
        blindWalkOn = false;
        BreadthWalkOn = false;
        depthWalkOn = false;
        hillclimbingWalkOn = false;
        randomrestartWalkOn = false;
        astarWalkOn = false;
        iterativedeepWalkOn = false;
        heuristicWalkOn = false;
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

    public void printAll(String... args)
    {
        sb = new StringBuilder();
        for(String i : args)
        {
            sb.append(i);
        }
        display.addText(sb.toString());
        display.addText("\n");
    }

    public void printIncludeString(String temp, String args[])
    {
        System.out.println(temp);
        printAll(args);
    }

    public void printBoth(String... args)
    {
        sb = new StringBuilder();
        for(String i: args)
        {
            sb.append(i);
        }
        String temp = sb.toString();
        System.out.println(temp);
        display.addText(temp);
        display.addText("\n");
    }


}
