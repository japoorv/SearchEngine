import java.util.*;
import java.io.*;
import java.lang.*;

public class Mysetw  // Helper Class for implementing the Set data structure not the real deal.
{
	
	Node<WordEntry> front=null;int size=0;
	public Boolean IsEmpty()
	{
	
		if (size!=0)return false; else return true;
	}

	public Boolean IsMember (WordEntry o)
	{
		Node<WordEntry> temp=new Node<WordEntry>(this.front);
		while(temp!=null)
		{
			if (temp.data!=null&&temp.data.equals(o))return true;
			temp=temp.next;

		}
		return false;
	}

	/*public Boolean IsMember (int o,MobilePhone xx)
	{
		Node temp=front;
		while(temp!=null)
		{
			if (((MobilePhone)temp.data).id==o)return true;
			temp=temp.next;

		}
		return false;
	}*/

	public void addElement(WordEntry o)
	{
		 
		 	Node<WordEntry> a=new Node<WordEntry>(o);
		 	a.next=front;
		 	this.front=new Node<WordEntry>(a);
		 	size++;
		 	
	}
	public void Delete(WordEntry o)
	{
		Node<WordEntry> temp=front;
		if (size==0){System.out.println("Error ! Object not in list");return;}
		if (temp.data==o){front=front.next;size--;}
		else 
		while(temp!=null)
		{
			try
			{
				if (temp.data==o){temp.next=temp.next.next;size--;break;}
				else {temp=temp.next;}
			}
			catch (Exception en){System.out.println("Error ! Object not in list");break;}
		}
		

	}
	public void Insert(WordEntry o)
	{
		 
		 	Node<WordEntry> a=new Node<WordEntry>(o);
		 	a.next=front;
		 	this.front=new Node<WordEntry>(a);
		 	size++;
		 	
	}

	public Mysetw union(Mysetw a)
	{
		Mysetw
		 x=new Mysetw();
		Node<WordEntry> temp=this.front;
		if (this.IsEmpty())return a;
		else if (a.IsEmpty())return this;
		while(temp!=null)
		{
			if (!x.IsMember(temp.data))x.Insert(temp.data);
			temp=temp.next;
		}
		temp=a.front;
		while(temp!=null)
		{
			if (!x.IsMember(temp.data))x.Insert(temp.data);
			temp=temp.next;
		}
		return x;
	}

	public Mysetw Intersection(Mysetw a)
	{
		Mysetw x=new Mysetw ();
		Node<WordEntry> temp=a.front;
		
		while(temp!=null)
		{
			if (this.IsMember(temp.data))x.Insert(temp.data);
			temp=temp.next;
		}


return x;
	}


}