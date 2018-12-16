import java.util.*;
import java.io.*;
import java.lang.*;
public class MyHashTable
{
	int Max_size=1001;
	Mysetw [] hash=new Mysetw [1001]	;
	MyHashTable()
	{
		for (int c=0;c<1001;c++)
			hash[c]=new Mysetw();
	}
	public int getHashTable(String x)
	{
	
		int total=0;
		for (int c=0;c<x.length();c++)
			total+=(x.charAt(c)-'a')+c*c;
		return (Max_size*10+total)%Max_size;
	

	}

	void addPositionsForWord(WordEntry w)
	{
			int indi=getHashTable(w.ori);
			Node<WordEntry> temp=hash[indi].front;

			int fl=0;
			while(temp!=null)
			{
				if (temp.data.ori.equals(w.ori)){temp.data.addPositions(w.pos);fl=1;break;}
				else temp=temp.next;
			}		
			if (fl==0)hash[indi].Insert(w);

	}
}