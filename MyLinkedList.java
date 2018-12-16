import java.util.*;
import java.io.*;
import java.lang.*;

public class MyLinkedList<T>
{
	Node<T> front=null;int size=0;
	public Boolean IsEmpty()
	{
	
		if (size!=0)return false; else return true;
	}

	public Boolean IsMember (int o,PageEntry ppp)
	{
		Node<T> temp=new Node<T>(this.front);
		while(temp!=null)
		{
			if (((Position)temp.data).i_r==o&&((Position)temp.data).p.equals(ppp))return true;
			temp=temp.next;

		}
		return false;
	}

	public void Insert(T o)
	{
		 
		 	Node<T> a=new Node<T>(o);
		 	a.next=front;
		 	this.front=a;
		 	size++;
		 	
	}


	public void Delete(T o)
	{
		Node<T> temp=front;
		if (size==0){System.out.println("Error ! T not in list");return;}
		if (temp.data==o){front=front.next;size--;}
		else 
		while(temp!=null)
		{
			try
			{
				if (temp.next.data==o){temp.next=temp.next.next;size--;break;}
				else {temp=temp.next;}
			}
			catch (Exception en){System.out.println("Error ! T not in list");break;}
		}
		

	}

	

}


