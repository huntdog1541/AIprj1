import javax.swing.*;

/**
 * Created by David on 3/3/2015.
 */
public class Main {
    private static gui gi;
    private static Robot robby;
    private static Map map;
    private static Log log;
    private static Graphs gph;

    public static void main(String[] args) {
        System.out.println("Hello World!");
        JFrame frame = new JFrame("AI GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.getContentPane().setSize(800, 400);
        gi = new gui();
        frame.getContentPane().add(gi);
        frame.pack();
        log = new Log(gi);
        map = new Map(5, .20, .10, false, log);
        robby = new Robot(map, log);
        robby.setWalking();
        gph = new Graphs(map);
    }
}
