import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.Vector;
import javax.swing.*;

public class MyPanel extends JPanel {
	private int nodeNr = 1;
	private int node_diam = 15;
	private Vector<Node> listaNoduri;
	private Vector<Arc> listaArce;
	Point pointStart = null;
	Point pointEnd = null;
	boolean isDragging = false;


	public MyPanel(int NumberOfNodes,int ProbabilityOfEdge)
	{
		listaNoduri = new Vector<Node>();
		listaArce = new Vector<Arc>();

		System.out.println(NumberOfNodes);
		System.out.println(ProbabilityOfEdge);

		generateRandomNodes(NumberOfNodes,ProbabilityOfEdge);

		// borderul panel-ului
		setBorder(BorderFactory.createLineBorder(Color.black));
	}
	private void generateRandomNodes(Integer NumberOfNodes,Integer ProBabilityOfEdge) {
		int max = NumberOfNodes * 3;
		if (NumberOfNodes < 300) {
			max = NumberOfNodes * 10;
		}
		for (int i = 0; i < NumberOfNodes; i++) {
			Point nou = new Point();
			nou.x = (int) ((Math.random() * (max - 0)) + 0);
			nou.y = (int) ((Math.random() * (max - 0)) + 0);
			while (CheckForNotOverleaping(nou)) {
				nou.x = (int) ((Math.random() * (max - 0)) + 0);
				nou.y = (int) ((Math.random() * (max - 0)) + 0);
			}

			Node node = new Node(nou.x, nou.y, nodeNr);
			System.out.println(node + " " + node.getNumber());
			listaNoduri.add(node);
			nodeNr++;

			repaint();
		}

		int numarMuchii = NumberOfNodes * (NumberOfNodes - 1) / 2;

		for (int i = 0; i < numarMuchii; i++)
		{
			int Probl = (int) ((Math.random() * (100 - 0)) + 0);

			int index = (int) (Math.random() * listaNoduri.size());
			int index1 = (int) (Math.random() * listaNoduri.size());

			while (index1 == index)
			{
				index1 = (int) (Math.random() * listaNoduri.size());
			}

			if (Probl < ProBabilityOfEdge)
			{
				Point Start = new Point();
				Start.x = listaNoduri.elementAt(index).getMiddleX();
				Start.y = listaNoduri.elementAt(index).getMiddleY();
				Point End = new Point();
				End.x = listaNoduri.elementAt(index1).getMiddleX();
				End.y = listaNoduri.elementAt(index1).getMiddleY();

				Arc arc = new Arc(Start, End);

				listaArce.add(arc);
			}
		}
		repaint();

	}
	private Boolean CheckForNotOverleaping(Point New) {

		boolean IsOverlap=false;
		double minDist=10000;

		for (Node n : listaNoduri)
		{
			double Xmiddle = (2*n.getCoordX()+30) /2 ;
			double Ymiddle = (2*n.getCoordY()+30) /2 ;

			double dis=Math. sqrt((New.getX()-Xmiddle)*(New.getX()-Xmiddle) + (New.getY()-Ymiddle)*(New.getY()-Ymiddle));

			if(minDist>dis)
			{
				minDist=dis;
			}

			if(n.getCoordX() == New.getX() && n.getCoordY() == New.getY())
			{
				IsOverlap =true;
			}
		}
		if(minDist<=node_diam+30)
		{
			IsOverlap=true;
		}
		return IsOverlap;

	}
	//metoda care se apeleaza la eliberarea mouse-ului
	private void addNode(int x, int y) {
		Node node = new Node(x, y, nodeNr);
		listaNoduri.add(node);
		nodeNr++;
		repaint();
	}
	
	//se executa atunci cand apelam repaint()
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);//apelez metoda paintComponent din clasa de baza
		//g.drawString("This is my Graph!", 10, 20);
		//deseneaza arcele existente in lista

		for (Arc a : listaArce)
		{
			a.drawArc(g);
		}
		//deseneaza arcul curent; cel care e in curs de desenare
		if (pointStart != null)
		{
			g.setColor(Color.RED);
			g.drawLine(pointStart.x, pointStart.y, pointEnd.x, pointEnd.y);
		}
		//deseneaza lista de noduri
		for(int i=0; i<listaNoduri.size(); i++)
		{
			listaNoduri.elementAt(i).drawNode(g, node_diam);
		}

	}
}
