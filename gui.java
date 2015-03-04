import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by David on 3/3/2015.
 * This creates the gui menu which runs the program and solution
 * *On a personal note this part of the program was generated by a gui builder called GuiGenie
 * which mostly just helped with the layout of the gui. All the action functions were implemented by me.
 */

public class gui extends JPanel {

    private int rowNumber;
    private int colNumber;
    private boolean hasAgents;
    private gui3 gi;
    private textGui txtGui;
    private boolean defaultObstacles;   //is accepting default obstacles
    private boolean defaultAgents;      //is accepting default agents
    private boolean agentsPresent;      //is agents present
    private boolean allWalk;            //is the all walk button set
    private boolean blindWalk;          //is the blind walk button set
    private boolean depthWalk;          //is the depth walk button set
    private boolean breadthWalk;        //is the breadth walk button set
    private boolean hillClimbWalk;      //is the hill-climbing walk button set
    private boolean randomRestartWalk;  //is the random restart button set
    private boolean active;             //has the run button been pressed
    private boolean rowNum;             //has the row number been entered
    private boolean colNum;             //has the column number been entered
    private Main main;


    public gui(Main temp) {

    	main = temp;
        rowNumber = 0;
        colNumber = 0;
        hasAgents = false;
        defaultObstacles = true;   //is accepting default obstacles
        defaultAgents = true;      //is accepting default agents
        agentsPresent = true;      //is agents present
        allWalk = false;            //is the all walk button set
        blindWalk = false;          //is the blind walk button set
        depthWalk = false;          //is the depth walk button set
        breadthWalk = false;        //is the breadth walk button set
        hillClimbWalk = false;      //is the hill-climbing walk button set
        randomRestartWalk = false;  //is the random restart button set
        active = true;             //has the run button been pressed
        rowNum = false;             //has the row number been entered
        colNum = false;             //has the column number been entered
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        addComponents();
    }

    public void addComponents() {
        gi = new gui3();
        txtGui = new textGui();
        this.add(gi);
        this.add(txtGui);
    }

    public void addText(String temp)
    {
        txtGui.addText(temp);
    }

	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}

	public int getColNumber() {
		return colNumber;
	}

	public void setColNumber(int colNumber) {
		this.colNumber = colNumber;
	}

	public boolean isHasAgents() {
		return hasAgents;
	}

	public void setHasAgents(boolean hasAgents) {
		this.hasAgents = hasAgents;
	}

	public boolean isDefaultObstacles() {
		return defaultObstacles;
	}

	public void setDefaultObstacles(boolean defaultObstacles) {
		this.defaultObstacles = defaultObstacles;
	}

	public boolean isDefaultAgents() {
		return defaultAgents;
	}

	public void setDefaultAgents(boolean defaultAgents) {
		this.defaultAgents = defaultAgents;
	}

	public boolean isAgentsPresent() {
		return agentsPresent;
	}

	public void setAgentsPresent(boolean agentsPresent) {
		this.agentsPresent = agentsPresent;
	}

	public boolean isAllWalk() {
		return allWalk;
	}

	public void setAllWalk(boolean allWalk) {
		this.allWalk = allWalk;
	}

	public boolean isBlindWalk() {
		return blindWalk;
	}

	public void setBlindWalk(boolean blindWalk) {
		this.blindWalk = blindWalk;
	}

	public boolean isDepthWalk() {
		return depthWalk;
	}

	public void setDepthWalk(boolean depthWalk) {
		this.depthWalk = depthWalk;
	}

	public boolean isBreadthWalk() {
		return breadthWalk;
	}

	public void setBreadthWalk(boolean breadthWalk) {
		this.breadthWalk = breadthWalk;
	}

	public boolean isHillClimbWalk() {
		return hillClimbWalk;
	}

	public void setHillClimbWalk(boolean hillClimbWalk) {
		this.hillClimbWalk = hillClimbWalk;
	}

	public boolean isRandomRestartWalk() {
		return randomRestartWalk;
	}

	public void setRandomRestartWalk(boolean randomRestartWalk) {
		this.randomRestartWalk = randomRestartWalk;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isRowNum() {
		return rowNum;
	}

	public void setRowNum(boolean rowNum) {
		this.rowNum = rowNum;
	}

	public boolean isColNum() {
		return colNum;
	}

	public void setColNum(boolean colNum) {
		this.colNum = colNum;
	}

    public double getNumberOfObstacles()
    {
        return gi.getNumObst();
    }

    public double getNumberOfAgents()
    {
        return gi.getNumAgents();
    }

    public int getRowNumber()
    {
        String ans = gi.getRowsAns();
        int temp;
        if(ans.isEmpty())
            temp = 5;
        else
            temp = Integer.parseInt(ans);
        return temp;
    }

    public int getColumnNumber()
    {
        String ans = gi.getColumnAns();
        int temp;
        if(ans.isEmpty())
            temp = 5;
        else
            temp = Integer.parseInt(ans);
        return temp;
    }

    public void setEnabledComponents(boolean ans){
        if(ans)
        {
            active = true;
            gi.checkActive();
        }
    }

    public void disableOtherWalks(boolean ans)
    {
        if(ans)
        {
            gi.disableBlind(true);
            gi.disableDepth(true);
            gi.disableBreadth(true);
            gi.disableHillClimb(true);
            gi.disableRandomRestart(true);
        }
        else
        {
            gi.disableBlind(false);
            gi.disableDepth(false);
            gi.disableBreadth(false);
            gi.disableHillClimb(false);
            gi.disableRandomRestart(false);
        }
    }


	public class gui3 extends JPanel {
        private JLabel jcomp1;
        private JLabel jcomp2;
        private JTextField rowsAns;
        private JTextField columnAns;
        private JToggleButton jcomp5;
        private JRadioButton jcomp6;
        private JRadioButton jcomp7;
        private JRadioButton jcomp8;
        private JRadioButton jcomp9;
        private JRadioButton jcomp10;
        private JRadioButton jcomp11;
        private JButton jcomp12;
        private JLabel jcomp13;
        private JLabel jcomp14;
        private JTextField numAgents;
        private JTextField numObst;
        private JRadioButton jcomp17;
        private JRadioButton jcomp18;
        private JLabel jcomp19;
        private JLabel jcomp20;
        private JLabel jcomp21;
        private JLabel jcomp22;
        private JLabel jcomp23;
        private JPanel agentColor;
        private JPanel obstacleColor;
        private JPanel treasureColor;
        private JPanel entryColor;

        public gui3() {
            //construct components
            this.setBorder(BorderFactory.createTitledBorder("Values"));
            jcomp1 = new JLabel("How many rows: ");
            jcomp2 = new JLabel("How many columns:");
            rowsAns = new JTextField(5);
            columnAns = new JTextField(5);
            jcomp5 = new JToggleButton("Agents", false);
            jcomp6 = new JRadioButton("Blind Walk", blindWalk);
            jcomp7 = new JRadioButton("Depth Walk", depthWalk);
            jcomp8 = new JRadioButton("Breadth Walk", breadthWalk);
            jcomp9 = new JRadioButton("Hill-Climbing Walk", hillClimbWalk);
            jcomp10 = new JRadioButton("Random Restart Hill-Climbing", randomRestartWalk);
            jcomp11 = new JRadioButton("All Walks", allWalk);
            jcomp12 = new JButton("Run");
            jcomp13 = new JLabel("Number of Obstacles:");
            jcomp14 = new JLabel("Number of Agents:");
            numAgents = new JTextField (5);
            numObst = new JTextField (5);
            jcomp17 = new JRadioButton("default number of Obstacles");
            jcomp17.setSelected(true);
            jcomp18 = new JRadioButton("default number of Agents");
            jcomp18.setSelected(true);
            jcomp19 = new JLabel("Color Key");
            jcomp20 = new JLabel("Agents");
            jcomp21 = new JLabel("Entry");
            jcomp22 = new JLabel("Treasure");
            jcomp23 = new JLabel("Obstacle");
            agentColor = new JPanel();
            agentColor.setBackground(Color.RED);
            obstacleColor = new JPanel();
            obstacleColor.setBackground(Color.GREEN);
            treasureColor = new JPanel();
            treasureColor.setBackground(Color.YELLOW);
            entryColor = new JPanel();
            entryColor.setBackground(Color.BLUE);

            //set components properties
            jcomp1.setToolTipText("Enter in the number of rows");
            jcomp2.setToolTipText("For Now only enter in the number of rows");
            jcomp5.setToolTipText("Click here if you want there to be Agents");
            jcomp6.setToolTipText("Click here for this algorithm");
            jcomp7.setToolTipText("Click here for this algorithm");
            jcomp8.setToolTipText("Click here for this algorithm");
            jcomp9.setToolTipText("Click here for this algorithm");
            jcomp10.setToolTipText("Click here for this algorithm");
            jcomp11.setToolTipText("Click here for this algorithm");
            jcomp12.setToolTipText("Click here to run the simulation");
            jcomp13.setToolTipText("Enter the number of obstacles");
            jcomp14.setToolTipText("Enter the number of agents");
            numObst.setToolTipText("Enter the number of obstacles");
            jcomp17.setToolTipText("Click here to accept the number of obstacles");
            jcomp18.setToolTipText("Click here to accept the default number of agents");

            //adjust size and set layout
            setPreferredSize(new Dimension(784, 454));
            setLayout(null);

            columnAns.setEnabled(false);
            if(!defaultObstacles)
                numObst.setEnabled(false);

            if(!defaultAgents)
                numAgents.setEnabled(false);
            checkActive();
            jcomp12.addActionListener(new RunButtonListner());
            jcomp11.addActionListener(new AllWalkRadio());
            jcomp6.addActionListener(new BlindWalkRadio());
            jcomp7.addActionListener(new DepthWalkRadio());
            jcomp8.addActionListener(new BreadthWalkRadio());
            jcomp9.addActionListener(new HillClimbWalkRadio());
            jcomp10.addActionListener(new RandomRestartWalkRadio());
            jcomp17.addActionListener(new defaultObstacleRadio());
            jcomp18.addActionListener(new defaultAgentsRadio());

            checkNumObst();
            checkNumAgents();

            //add components
            add(jcomp1);
            add(jcomp2);
            add(rowsAns);
            add(columnAns);
            add(jcomp5);
            add(jcomp6);
            add(jcomp7);
            add(jcomp8);
            add(jcomp9);
            add(jcomp10);
            add(jcomp11);
            add(jcomp12);
            add(jcomp13);
            add(jcomp14);
            add(numAgents);
            add(numObst);
            add(jcomp17);
            add(jcomp18);
            add(jcomp19);
            add(jcomp20);
            add(jcomp21);
            add(jcomp22);
            add(jcomp23);
            add(agentColor);
            add(obstacleColor);
            add(treasureColor);
            add(entryColor);


            //set component bounds (only needed by Absolute Positioning)
            jcomp1.setBounds(40, 40, 97, 28);
            jcomp2.setBounds(40, 75, 117, 25);
            rowsAns.setBounds(160, 40, 250, 25);
            columnAns.setBounds(160, 75, 250, 25);
            jcomp5.setBounds(40, 130, 100, 25);
            jcomp6.setBounds(435, 125, 100, 25);
            jcomp7.setBounds(435, 155, 100, 25);
            jcomp8.setBounds(435, 185, 100, 25);
            jcomp9.setBounds(435, 215, 134, 25);
            jcomp10.setBounds(435, 245, 194, 25);
            jcomp11.setBounds(435, 275, 100, 25);
            jcomp12.setBounds(40, 395, 100, 25);
            jcomp13.setBounds(455, 75, 131, 25);
            jcomp14.setBounds(170, 155, 110, 25);
            numAgents.setBounds (280, 155, 100, 25);
            numObst.setBounds (580, 75, 100, 25);
            jcomp17.setBounds(450, 40, 250, 25);
            jcomp18.setBounds(165, 130, 200, 25);
            jcomp19.setBounds(165, 285, 100, 25);
            jcomp20.setBounds(165, 320, 100, 25);
            agentColor.setBounds(165, 320, 100, 50);
            jcomp21.setBounds(280, 320, 100, 25);
            entryColor.setBounds(280, 320, 100, 50);
            jcomp22.setBounds(395, 320, 100, 25);
            treasureColor.setBounds(395, 320, 100, 50);
            jcomp23.setBounds(525, 320, 100, 25);
            obstacleColor.setBounds(525, 320, 100, 50);


        }

        public boolean checkRadioSelected()
        {
            boolean ans = false;
            if(jcomp6.isSelected())
                ans = true;
            if(jcomp7.isSelected())
                ans = true;
            if(jcomp8.isSelected())
                ans = true;
            if(jcomp9.isSelected())
                ans = true;
            if(jcomp10.isSelected())
                ans = true;
            if(jcomp11.isSelected())
                ans = true;
            return ans;
        }

        public void checkNumObst()
        {
            if(jcomp17.isSelected())
                numObst.setEnabled(false);
            else
                numObst.setEnabled(true);
        }

        public void checkNumAgents()
        {
            if(jcomp18.isSelected())
                numAgents.setEnabled(false);
            else
                numAgents.setEnabled(true);
        }

		public String getRowsAns() {
			return rowsAns.getText();
		}

		public void setRowsAns(String temp) {
			this.rowsAns.setText(temp);;
		}

		public String getColumnAns() {
			return columnAns.getText();
		}

		public void setColumnAns(String temp) {
			this.columnAns.setText(temp);;
		}

		public double getNumAgents() {
            String ans = numAgents.getText();
            double num = 0;
            if(ans.isEmpty())
                num = .10;
            else
                num = Integer.parseInt(ans);
			return num;
		}

		public void setNumAgents(String temp) {
			this.numAgents.setText(temp);;
		}

		public double getNumObst() {
            String ans = numObst.getText();
            double num = 0;
            if(ans.isEmpty())
                num = .20;
            else
                num = Double.parseDouble(ans);
            return num;
		}

		public void setNumObst(String temp) {
			this.numObst.setText(temp);
		}

        public void greyRowAns(boolean val)
        {
            if(val)
                this.rowsAns.setEnabled(false);
        }

        public void disableBlind(boolean ans)
        {
            if(ans)
            {
                jcomp6.setEnabled(false);
                jcomp6.setSelected(false);
            }
            else
                jcomp6.setEnabled(true);
        }

        public void disableDepth(boolean ans)
        {
            if(ans)
            {
                jcomp7.setEnabled(false);
                jcomp7.setSelected(false);
            }
            else
                jcomp7.setEnabled(true);
        }

        public void disableBreadth(boolean ans)
        {
            if(ans)
            {
                jcomp8.setEnabled(false);
                jcomp8.setSelected(false);
            }
            else
                jcomp8.setEnabled(true);
        }

        public void disableHillClimb(boolean ans)
        {
            if(ans)
            {
                jcomp9.setEnabled(false);
                jcomp9.setSelected(false);
            }
            else
                jcomp9.setEnabled(true);
        }

        public void disableRandomRestart(boolean ans)
        {
            if(ans)
            {
                jcomp10.setEnabled(false);
                jcomp10.setSelected(false);
            }
            else
                jcomp10.setEnabled(true);

        }

        public void checkActive()
        {
            System.out.println("check active: " + active);
            if(!active)
            {
                System.out.println("Active is " + active);
                rowsAns.setEnabled(false);
                //columnAns.setEnabled(false);
                jcomp5.setEnabled(false);
                jcomp6.setEnabled(false);
                jcomp7.setEnabled(false);
                jcomp8.setEnabled(false);
                jcomp9.setEnabled(false);
                jcomp10.setEnabled(false);
                jcomp11.setEnabled(false);
                jcomp12.setEnabled(false);
                numAgents.setEnabled(false);
                numObst.setEnabled(false);
                jcomp17.setEnabled(false);
                jcomp18.setEnabled(false);
                jcomp14.setEnabled(false);
                jcomp13.setEnabled(false);
                jcomp1.setEnabled(false);
                jcomp2.setEnabled(false);
            }
            else
            {
                    System.out.println("Active is " + active);
                    rowsAns.setEnabled(true);
                    //columnAns.setEnabled(true);
                    jcomp5.setEnabled(true);
                    jcomp6.setEnabled(true);
                    jcomp7.setEnabled(true);
                    jcomp8.setEnabled(true);
                    jcomp9.setEnabled(true);
                    jcomp10.setEnabled(true);
                    jcomp11.setEnabled(true);
                    jcomp12.setEnabled(true);
                    checkNumObst();
                    checkNumAgents();
                    jcomp17.setEnabled(true);
                    jcomp18.setEnabled(true);
                    jcomp14.setEnabled(true);
                    jcomp13.setEnabled(true);
                    jcomp1.setEnabled(true);
                    jcomp2.setEnabled(true);
            }
        }
    }

    public class textGui extends JPanel {
        private JTextArea jcomp1;
        private JLabel jcomp2;

        public textGui() {
            //construct components
            this.setBorder(BorderFactory.createTitledBorder("Results"));
            jcomp1 = new JTextArea (5, 5);
            JScrollPane jsp = new JScrollPane(jcomp1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

            //adjust size and set layout
            setPreferredSize (new Dimension (784, 454));
            setLayout (null);
            jcomp1.setEditable(false);
            //add components
            add(jsp);

            //set component bounds (only needed by Absolute Positioning)
            jsp.setBounds (2, 20, 779, 432);
            //testText();
        }

        public void addText(String temp)
        {
            jcomp1.append(temp);
        }

        public void testText()
        {
            int i;
            for(i = 0; i < 1000; i++)
                jcomp1.append("a\n");
        }
    }
    
    public class RunButtonListner implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            active = false;
            gi.checkActive();
            System.out.println("Clicked Run button");
            if(gi.checkRadioSelected())
                main.startWalking();
            else
                addText("No algorithm selected\n");
            setEnabledComponents(true);
        }
    }

    public class AllWalkRadio implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            allWalk = !allWalk;
            if(allWalk)
            {
                disableOtherWalks(true);
            }
            else
                disableOtherWalks(false);
        }
    }

    public class BlindWalkRadio implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            blindWalk = !blindWalk;
        }
    }

    public class DepthWalkRadio implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            depthWalk = !depthWalk;
        }
    }

    public class BreadthWalkRadio implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            breadthWalk = !breadthWalk;
        }
    }

    public class HillClimbWalkRadio implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            hillClimbWalk = !hillClimbWalk;
        }
    }
    public class RandomRestartWalkRadio implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            randomRestartWalk = !randomRestartWalk;
        }
    }

    public class defaultObstacleRadio implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            gi.checkNumObst();
        }
    }

    public class defaultAgentsRadio implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            gi.checkNumAgents();
        }
    }

}