import java.util.*;
import java.io.*;
import java.lang.*;
public class WordEntry
{
	String ori;
	MyLinkedList<Position>pos=new MyLinkedList<Position>();
	AVLNode root=new AVLNode ();
	ArrayList<Position>sorted=new ArrayList<Position> (1001);
	WordEntry()
	{
		
	}
	WordEntry(String word)
	{		
		ori=word;
	}
	void getT(AVLNode node)
	{
		
		if (node==null||node.data==null)return;
		getT(node.l);
		sorted.add(node.data);
		getT(node.r);
		return;

	}
void prT(AVLNode node)
	{
		
		if (node==null||node.data==null)return;
		prT(node.l);
		prT(node.r);
		return;

	}

	int height(AVLNode  x)
	{
		if (x==null||x.data==null)return 0;
		return Math.max(1+height(x.l),1+height(x.r));
	}
	void doRot(AVLNode node)
	{
		AVLNode temp=node;

		while(temp!=null&&temp.data!=null&&Math.abs(height(temp.l)-height(temp.r))<=1)
			{temp=temp.p;}

		if (temp==null||temp.data==null)return ;

		AVLNode x,y;
		if (height(temp.l)>=height(temp.r))y=temp.l; else y=temp.r;
		if (height(y.l)>=height(y.r))x=y.l; else x=y.r;

		if (x.data.i_r<=y.data.i_r&&y.data.i_r<=temp.data.i_r) // x y temp
		{

			temp.l=y.r;
			
			
						if (temp.p!=null&&temp.p.data!=null&&temp.data.i_r<=temp.p.data.i_r){temp.p.l=y;y.p=temp.p;} else if (temp.p!=null&&temp.p.data!=null){temp.p.r=y;y.p=temp.p;}
								else {y.p=null;root=y;}
					temp.p=y;
					y.r=temp;
					x.p=y;		
		}
		else if (x.data.i_r<=y.data.i_r&&y.data.i_r>=temp.data.i_r) // temp x y
		{
			temp.r=x.l;
			
			y.l=x.r;
			if (temp.p!=null&&temp.p.data!=null&&temp.data.i_r<=temp.p.data.i_r){temp.p.l=x;x.p=temp.p;} else if (temp.p!=null&&temp.p.data!=null){ temp.p.r=x;x.p=temp.p;}
			else {x.p=null;root=x;}
			
			temp.p=x;
			x.l=temp;
			y.p=x;

			
		}
		else if (x.data.i>=y.data.i&&y.data.i>=temp.data.i) //  temp y x
		{
			temp.r=y.l;
			y.l=temp;
			if (temp.p!=null&&temp.p.data!=null&&temp.data.i_r<=temp.p.data.i_r){temp.p.l=y;y.p=temp;} else if(temp.p!=null&&temp.p.data!=null) {temp.p.r=y;y.p=temp;}
			else {y.p=null;root=y;}

			temp.p=y;
			x.p=y;

		}
		else  // y x temp 
		{
			temp.l=x.r;
			x.r=temp;
			y.r=x.l;
			x.l=x;
			if (temp.p!=null&&temp.p.data!=null&&temp.data.i_r<=temp.p.data.i_r){temp.p.l=x;x.p=temp;} else if (temp.p!=null&&temp.p.data!=null){temp.p.r=x;x.p=temp;}
			else {x.p=null;root=x;}
			temp.p=x;
			y.p=x;

		}

		return ;
	}
	void insertAVL(AVLNode node,AVLNode prev,Position position)
	{
		
		if (node==null||node.data==null){if (node==null)node=new AVLNode();node.data=position;node.p=prev;
			if (prev!=null&&prev.data!=null&&node.data.i_r<=prev.data.i_r)prev.l=node; 
			else if (prev!=null&&prev.data!=null)prev.r=node;return ;}
		
		else if (node.data.i_r>=position.i_r)insertAVL(node.l,node,position); else insertAVL(node.r,node,position);
		return ;
	}

	void addPosition(Position position)
	{
		

			insertAVL(root,null,position);
			
		pos.Insert(position);
	}

	void addPositions(MyLinkedList<Position> positions)
	{
		Node<Position> temp=positions.front;
		while(temp!=null)
		{
			if (temp.data!=null){addPosition(temp.data);}
			temp=temp.next;
		}

	}
	MyLinkedList<Position> getAllPositionsForThisWord()
	{
		return pos;
	}
	
}