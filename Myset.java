import java.util.*;
import java.io.*;
import java.lang.*;

public class Myset <T> // Helper Class for implementing the Set data structure not the real deal.
{
	
	Node<T> front=null;int size=0;
	public Boolean IsEmpty()
	{
	
		if (size!=0)return false; else return true;
	}
public Boolean IsMemberpage (String o)
	{
		Node<PageEntry> temp=(Node<PageEntry>)(this.front);
		while(temp!=null)
		{
			if (temp.data!=null&&(temp.data).pageName.equals(o))return true;
			temp=temp.next;

		}
		return false;
	}

	public Boolean IsMember (T o)
	{
		Node<T> temp=new Node<T>(this.front);
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

	public void addElement(T o)
	{
		 
		 	Node<T> a=new Node<T>(o);
		 	a.next=front;
		 	this.front=new Node<T>(a);
		 	size++;
		 	
	}
	public void Delete(T o)
	{
		Node<T> temp=front;
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
	public void Insert(T o)
	{
		 
		 	Node<T> a=new Node<T>(o);
		 	a.next=front;
		 	this.front=new Node<T>(a);
		 	size++;
		 	
	}

	public Myset<PageEntry> union(Myset<PageEntry> a)
	{
		Myset<PageEntry> x=new Myset<PageEntry>();
		Node<PageEntry> temp=(Node<PageEntry>)this.front;
		if (this.IsEmpty())return a;
		else if (a.IsEmpty())return ((Myset<PageEntry>)this);
		while(temp!=null)
		{
			if (!x.IsMemberpage(temp.data.pageName))x.Insert(temp.data);
			temp=temp.next;
		}
		temp=a.front;
		while(temp!=null)
		{
			if (!x.IsMemberpage(temp.data.pageName))x.Insert(temp.data);
			temp=temp.next;
		}
		return x;
	}

	public Myset<PageEntry> Intersection(Myset<PageEntry> a)
	{
		Myset<PageEntry> x=new Myset<PageEntry> ();
		Node<PageEntry> temp=a.front;

		while(temp!=null)
		{
			if (this.IsMemberpage(temp.data.pageName))x.Insert(temp.data);

			temp=temp.next;
		}


return x;
	}


}