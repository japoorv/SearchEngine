import java.io.*;
import java.lang.*;
import java.util.*;
public class PageIndex
{
	MyLinkedList<WordEntry> a=new MyLinkedList<WordEntry>();
	MyLinkedList<WordEntry> getWordEntries()
	{
		Node<WordEntry> temp=new Node<WordEntry> ();
		while(temp!=null)
		{
			
			temp=temp.next;
		}
		return a;
	}
	void addPositionForWord(String str,Position p)
	{
		Node<WordEntry> temp=new Node<WordEntry> ();
		temp=a.front;
		int fl=0;
		while(temp!=null)
		{
			if (temp.data.ori.equals(str))
				{temp.data.addPosition(p);fl=1;}
			temp=temp.next;
		}
		if (fl==0){WordEntry ss=new WordEntry(str);ss.addPosition(p);a.Insert(ss);}

	}
	Boolean ispresent(String str)
	{
		Node<WordEntry>temp=new Node<WordEntry> ();
		temp=a.front;
		while(temp!=null)
		{
			if (temp.data.ori.equals(str))return true;
			temp=temp.next;
		}
		return false;
	}
	MyLinkedList<Position> retIndex(String str,PageEntry ppp)
	{
		Node<WordEntry>temp=new Node<WordEntry> ();
		MyLinkedList<Position>retval=new MyLinkedList<Position>();
		temp=a.front;
		while(temp!=null)
		{
			if (temp.data.ori.equals(str)){
				for (Node<Position>temp1=temp.data.pos.front;temp1!=null;temp1=temp1.next)
					if (temp1.data.p.equals(ppp))retval.Insert(temp1.data);
				return temp.data.pos;}
			temp=temp.next;
		}
		return null;
	}
	WordEntry retWE(String str)
	{
		Node<WordEntry>temp=new Node<WordEntry> ();
		temp=a.front;
		while(temp!=null)
		{
			if (temp.data.ori.equals(str))return temp.data;
			temp=temp.next;
		}
		return null;
	}


}