import java.util.*;
import java.io.*;
import java.lang.*;
import java.lang.Math.*;
public class PageEntry
{
	String maps="abcdefghijklmnopqrstuvwxyz1234567890";
	String puncts="{}[]<>=().,;’\"?#!-:"	;
	String connect[]={"a", "an", "the", "they", "these", "this", "for", "is", "are", "was", "of","or", "and", "does", "will", "whose"};
	String sing[]={"stack","structure","application"};
	String plur[]={"stacks","structures","applications"};
	String pageName;
	PageIndex index;
	Myset<String> inverse(Myset<String> a)
	{
		Myset<String>retval=new Myset<String>();
		for (Node<String> temp=a.front;temp!=null;temp=temp.next)retval.Insert(temp.data);
	return retval;	
	}
	Myset<String> refine(String x)   // getting our  formatted list of words
	{
		x=x.toLowerCase();String ret="";Myset<String>retval=new Myset<String>();
		for (int c=0;c<x.length();c++)
			{if (puncts.indexOf(x.charAt(c))>=0){if (ret.length()>0&&!findis(connect,ret,connect.length))retval.Insert(singular(ret));ret="";}
			else ret=ret+x.charAt(c);}
			if (ret!="")retval.Insert(singular(ret));
		return inverse(retval);
	}
Boolean findis(String fs[],String x,int n)
	{
		for (int c=0;c<n;c++)
			{if (fs[c].equals(x))return true;}
		return false;

	}
	String singular(String x)
	{
		for (int c=0;c<plur.length;c++)
			if (plur[c].equals(x))return sing[c];
		return x;
	}
	PageEntry (String a)
	{
		String f=a;
pageName=f;
		this.index=new PageIndex();
FileInputStream fin=null;
				try{fin=new FileInputStream(f);}catch(Exception en){System.out.println("No such file exists");return ;}

				Scanner s1=new Scanner(fin);
				int indi=1;int iop=1;
				while(s1.hasNext())
				{

				
					f=s1.next();
					Myset<String> fs=refine(f);
					for (Node<String> temp1=fs.front;temp1!=null;temp1=temp1.next)
					{
					
					Position temp=new Position(this,indi,iop);
				
						if (!findis(connect,temp1.data,connect.length)){iop++;this.index.addPositionForWord(temp1.data,temp);}
					indi++;
				}
				}
		
	}
	public PageIndex getPageIndex()
	{
		return index;
	}
	public int frequ(String word)
	{
		Node<WordEntry>temp=new Node<WordEntry>();
		int freq=0;
		temp=this.index.a.front;
		while(temp!=null)
		{
			if (temp.data.ori.equals(word)){Node<Position>temp1=temp.data.pos.front;for (;temp1!=null;temp1=temp1.next)if (temp1.data.p==this)freq++;}
		
			temp=temp.next;
		}

		//System.out.println(this.pageName+" "+freq);
	return freq;

	}
public int total()
{
	Node<WordEntry>temp=new Node<WordEntry>();
int total=0;
		temp=index.a.front;
		while(temp!=null)
		{
			
			{Node<Position>temp1=temp.data.pos.front;for (;temp1!=null;temp1=temp1.next)if (temp1.data.p==this)total++;}

			temp=temp.next;
		}

		//System.out.println(this.pageName+" "+freq);
		return total;

}
	int howmany(String str[],int n)
	{
		
			int hows=0;
			MyLinkedList<Position> poss=this.index.retIndex(str[0],this);

			for (Node <Position> temp1=poss.front;temp1!=null;temp1=temp1.next)
			{
				if (temp1.data.p!=this)continue;

				int fl=0;
			for (int c=1;c<n;c++)
			{
				
				if (((MyLinkedList<Position>)(this.index.retIndex(str[c],this))).IsMember(((Position)temp1.data).i_r+c,this))fl++;
			}
			if (fl==n-1){hows++;}
		}
		return hows;
		

	}
	
double getRelevanceOfPhrase(String str[],int n)
{
	int m=howmany(str,n);
	int Wp=total();
	int k=n;
	int N=SearchEngine.pages.size;
	double nwp=SearchEngine.in_of_pg.getPagesWhichContainPhrase(str,n).size;
	double returnval=(m/(Wp-(k-1.0)*m))*Math.log(N/nwp); 
	//System.out.println("m is "+ m + "Wp "+Wp + "k is "+k +"N is "+ N+"nwp "+nwp+" "+returnval+" "+this.pageName);
	
	return returnval;


		
}
	double getInverseDocFreq(String w)
	{
		int N=SearchEngine.pages.size;

		int ii=0;
		for (Node<PageEntry> temp=SearchEngine.pages.front;temp!=null;temp=temp.next)
		{
			if (temp.data.index.ispresent(w))ii++;
		}
		double nwp=ii; // update
		//System.out.println(N+" "+w);
		return 	Math.log(N/nwp);
	}

	public double getRelevanceOfPage(String str[],int n,Boolean doThese) // n is the length of the phrase
	{
		if (doThese) // str is a phrase
		{
			double res=0;
			for (int c=0;c<n;c++)res+=getTermFrequency(str[c])*getInverseDocFreq(str[c]);
				return res;
		}
		else 
		{
			double res=0;
			for (int c=0;c<n;c++)
				{;
			if (getTermFrequency(str[c])>0)res+=getTermFrequency(str[c])*getInverseDocFreq(str[c]);}
				return res;
		
		}

	}
	public double getTermFrequency(String word)
	{
		double freq=frequ(word);
		double total=total();

		//System.out.println(this.pageName+" "+freq);
		if (total==0||freq==0)return 0; else return freq/total;


	}
}