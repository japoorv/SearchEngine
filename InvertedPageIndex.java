import java.util.*;
import java.io.*;
import java.lang.*;
public class InvertedPageIndex
{
	MyHashTable words=new MyHashTable();


	void addPage(PageEntry p)
	{
		Node<WordEntry> temp=p.index.a.front;
		while(temp!=null)
		{
			words.addPositionsForWord(temp.data);
			temp=temp.next;
		}
	}
	Myset<PageEntry> getPagesWhichContainWord(String str)
	{

		Myset<PageEntry>x=new Myset<PageEntry>();
		int indi=words.getHashTable(str);
		Node<WordEntry>temp=words.hash[indi].front;
	
		while(temp!=null)
		{
			if (temp.data.ori.equals(str))
			{
				Node<Position> temp1=temp.data.pos.front;
				while(temp1!=null)
				{
					if (!x.IsMemberpage(temp1.data.p.pageName))x.Insert(temp1.data.p);
					temp1=temp1.next;
				}
			}
			temp=temp.next;
		}

		return x;
	}

	Myset<PageEntry>getPagesWhichContainPhrase(String str[],int n)
	{
		//InvertedPageIndex tf=SearchEngine.in_of_pg;

		Myset<PageEntry>retval=getPagesWhichContainWord(str[0]);

		Myset<PageEntry>finale=new Myset<PageEntry> ();
		
		for (int c=1;c<n;c++)
		{
			
		
			retval=retval.Intersection(getPagesWhichContainWord(str[c]));
			
		}

		Node<PageEntry>temp=retval.front;

		while(temp!=null)
		{
			WordEntry xe=temp.data.index.retWE(str[0]);
			xe.getT(xe.root);
			ArrayList<Position> poss=xe.sorted;
	
			for (int c2=0;c2<poss.size();c2++)
			{
			
				int fl=0;
			for (int c=1;c<n;c++)
			{
				
				if (((MyLinkedList<Position>)(temp.data.index.retIndex(str[c],temp.data))).IsMember(poss.get(c2).i_r+c,temp.data))fl++;
			}
			if (fl==n-1){finale.Insert(temp.data);break;}
		}
		temp=temp.next;
		}
	return finale;
	}
}