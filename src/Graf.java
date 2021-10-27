import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JScrollPane;


public class Graf
{
    static JTextField t;
    static JLabel Label1;
    static JLabel Label2;
    static JTextField t1;

	private static void initUI() {

        JFrame f = new JFrame("Algoritmica Grafurilor");

        //sa se inchida aplicatia atunci cand inchid fereastra
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        f.getContentPane().setLayout(new FlowLayout());
        Label1= new JLabel("enter NUmber of Nodes");
        t=new JTextField("",10);
        Label2 = new JLabel("enter Probability of Edge");
        t1=new JTextField("",10);

        Button Generate = new Button("Generate Graph");
        Generate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initPanel(f);
            }
        });

        f.add(Label1);
        f.getContentPane().add(t);
        f.add(Label2);
        f.getContentPane().add(t1);
        f.add(Generate);
        //setez dimensiunea ferestrei
        f.setSize(300, 300);
        //fac fereastra vizibila
        f.setVisible(true);

    }

    public static void initPanel(JFrame f) {
        f.setVisible(false);
        JFrame graph = new JFrame("GraphTool");
        graph.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        graph.add(new MyPanel(Integer.parseInt(t.getText()),Integer.parseInt(t1.getText())));
        graph.setExtendedState(JFrame.MAXIMIZED_BOTH);
        graph.setVisible(true);

    }

	public static void main(String[] args)
	{
		//pornesc firul de executie grafic
		//fie prin implementarea interfetei Runnable, fie printr-un ob al clasei Thread
		SwingUtilities.invokeLater(new Runnable() //new Thread()
		{
            public void run() 
            {
            	initUI(); 
            }
        });
	}	
}
