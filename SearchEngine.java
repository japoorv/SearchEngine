import java.util.*;
import java.lang.*;
import java.io.*;
public class SearchEngine
{
	String maps="abcdefghijklmnopqrstuvwxyz1234567890";
	String puncts="{}[]<>=().,;’\"?#!-:"	;
	String connect[]={"a", "an", "the", "they", "these", "this", "for", "is", "are", "was", "of","or", "and", "does", "will", "whose"};
	String sing[]={"stack","structure","application"};
	String plur[]={"stacks","structures","applications"};


		public static InvertedPageIndex in_of_pg=new InvertedPageIndex();
		public static Myset<PageEntry>pages=new Myset<PageEntry>();
	SearchEngine()
	{
		
	}
	String singular(String x)
	{
		for (int c=0;c<plur.length;c++)
			if (plur[c].equals(x))return sing[c];
		return x;
	}
PageEntry name2page(String x)
{
	Node<PageEntry> temp=pages.front;
	while(temp!=null)
	{
		if (temp.data.pageName.equals(x))return temp.data;
		else temp=temp.next;
	}
	return null;
}

	Boolean findis(String fs[],String x,int n)
	{
		for (int c=0;c<n;c++)
			{if (fs[c].equals(x))return true;}
		return false;

	}

	Myset<String> refine(String x)   // getting our  formatted list of words
	{
		x=x.toLowerCase();String ret="";Myset<String>retval=new Myset<String>();
		for (int c=0;c<x.length();c++)
			{if (puncts.indexOf(x.charAt(c))>=0){if (ret.length()>0&&!findis(connect,ret,connect.length))retval.Insert(singular(ret));ret="";}
			else ret=ret+x.charAt(c);}
			if (ret!="")retval.Insert(singular(ret));
		return retval;
	}

	double getInverseDocFreq(String w)
	{
		int N=pages.size;
		int ii=0;
		for (Node<PageEntry> temp=pages.front;temp!=null;temp=temp.next)
		{
			if (temp.data.index.ispresent(w))ii++;
		}
		return 	Math.log(N/Math.log(ii));
	}


	String performAction(String actionMessage)
	{
		Scanner s=new Scanner (actionMessage);
		String x=s.next();String retval="";
		if (x.equals("addPage"))
		{
			
			PageEntry kt=new PageEntry(s.next());
		in_of_pg.addPage(kt);
		pages.Insert(kt);
		
		
	}
	else if (x.equals("queryFindPagesWhichContainWord"))
	{	

		String f;
		f=s.next();
		String origi=f;
		f=singular(f.toLowerCase());


		Node<WordEntry> temp=in_of_pg.words.hash[in_of_pg.words.getHashTable(f)].front;
		while(temp!=null)if (temp.data.ori.equals(f))break; else temp=temp.next;
		Node<Position>temp4=null;
		if (temp!=null)temp4=temp.data.pos.front;
		Myset<PageEntry> retvals=new Myset<PageEntry> ();String xxx[]=new String[1001];
int curr=0;
		while(temp4!=null)
		{
			
			
			if (temp4.data.p.getTermFrequency(f)!=0)
			{
				//System.out.println(temp4.data.p.pageName+" "+temp4.data.p.getTermFrequency(f));
				
				Node<PageEntry> temp1=new Node<PageEntry>();
				temp1=retvals.front;
				
				if (retvals.size==0||(!retvals.IsMemberpage(temp4.data.p.pageName)&&temp1.data.getTermFrequency(f)<temp4.data.p.getTermFrequency(f)&&!findis(xxx,temp4.data.p.pageName,curr))){retvals.Insert(temp4.data.p);xxx[curr]=temp4.data.p.pageName;curr++;}
				
				else 
				{

					while(temp1!=null)
				{
					if (!retvals.IsMemberpage(temp4.data.p.pageName)&&!findis(xxx,temp4.data.p.pageName,curr)&&(temp1.next==null||temp1.next.data.getTermFrequency(f)<temp4.data.p.getTermFrequency(f))&&temp1.data.getTermFrequency(f)>=temp4.data.p.getTermFrequency(f))
					{

						Node<PageEntry> temps=new Node<PageEntry> (temp4.data.p);
						temps.next=temp1.next;
						temp1.next=temps;
						retvals.size++;
						xxx[curr]=temp4.data.p.pageName;
						curr++;
						break;
					}
					else temp1=temp1.next;
					
				}
			}
				
			}
			temp4=temp4.next;

		}
		Node<PageEntry> tem=retvals.front;
		if (retvals.size==0)retval="No webpage contains word "+ origi;
		while(tem!=null)
		{
			if (tem.next!=null)retval+=tem.data.pageName+", ";
			else retval+=tem.data.pageName;
		tem=tem.next;
		}

	}
	else if (x.equals("queryFindPositionsOfWordInAPage"))
	{
		String a1,b1,origi;
		a1=s.next();
		origi=a1;
		a1=singular(a1.toLowerCase());
		b1=s.next();
		PageEntry xs=name2page(b1);Myset<Integer>retvals=new Myset<Integer>();
		if (xs==null){System.out.println("Webpage "+b1+" does not exist.");}
		else {
		Node<WordEntry> temp=xs.index.a.front;
		while(temp!=null)
		{
			if (temp.data.ori.equals(a1))
			{
				Node<Position>temp1=temp.data.pos.front;
				while(temp1!=null)

				{
					if (temp1.data.p==xs&&temp1.next!=null)retval+=temp1.data.i+", ";
					else if (temp1.data.p==xs)retval+=temp1.data.i;
					temp1=temp1.next;
				}
					break;
			}
				temp=temp.next;

		} 
		if (retval==""){retval="Webpage "+b1+" does not contain word "+origi;}
		
	}
}
else if (x.equals("queryFindPagesWhichContainAllWords"))
{
	String str[]=new String[100];
	str[0]=singular(s.next().toLowerCase());
	int counter=1;

	Myset<PageEntry> tt=in_of_pg.getPagesWhichContainWord(str[0]);
	while(s.hasNext())
	{
		str[counter]=singular(s.next().toLowerCase());counter++;
		tt=tt.Intersection(in_of_pg.getPagesWhichContainWord(str[counter-1]));
	}
	
	Myset<PageEntry>tt1=new Myset<PageEntry> ();
	for (Node<PageEntry> temp=tt.front;temp!=null;temp=temp.next)
		if (!tt1.IsMemberpage(temp.data.pageName))tt1.Insert(temp.data);
	


	Myset<SearchResult>iins=new Myset<SearchResult>();
	for (Node<PageEntry> temp=tt1.front;temp!=null;temp=temp.next)
	{
		iins.Insert(new SearchResult(temp.data,temp.data.getRelevanceOfPage(str,counter,true)));
	}
	ArrayList<SearchResult> res=MySort.sortThisList(iins);

	for (int temp=0;temp<res.size();temp++)
		if (temp<=res.size()-2)retval+=res.get(temp).p.pageName+", "; else retval+=res.get(temp).p.pageName;
}
else if (x.equals("queryFindPagesWhichContainAnyOfTheseWords"))
{

	String str[]=new String[100];
	str[0]=singular(s.next().toLowerCase());
	int counter=1;

	Myset<PageEntry> tt=in_of_pg.getPagesWhichContainWord(str[0]);
	while(s.hasNext())
	{
		str[counter]=singular(s.next().toLowerCase());counter++;
		tt=tt.union(in_of_pg.getPagesWhichContainWord(str[counter-1]));
	}
	Myset<SearchResult>iins=new Myset<SearchResult>();
	Myset<PageEntry>tt1=new Myset<PageEntry>();
	for (Node<PageEntry> temp=tt.front;temp!=null;temp=temp.next)
		if (!tt1.IsMemberpage(temp.data.pageName))tt1.Insert(temp.data);

	for (Node<PageEntry> temp=tt1.front;temp!=null;temp=temp.next)
	{
		iins.Insert(new SearchResult(temp.data,temp.data.getRelevanceOfPage(str,counter,false)));
	}
	
	
	ArrayList<SearchResult> res=MySort.sortThisList(iins);

	for (int temp=0;temp<res.size();temp++)
		if (temp<=res.size()-2)retval+=res.get(temp).p.pageName+", "; else retval+=res.get(temp).p.pageName;
}

else if (x.equals("queryFindPagesWhichContainPhrase"))
{
	String str[]=new String[100];
	int coun=0;
	while(s.hasNext())
	{
		String ss=s.next();
		str[coun]=singular(ss.toLowerCase());;
		coun++;
	}
	Myset<PageEntry> tt=in_of_pg.getPagesWhichContainPhrase(str,coun);
	
Myset<PageEntry>tt1=new Myset<PageEntry>();
	for (Node<PageEntry> temp=tt.front;temp!=null;temp=temp.next)
		if (!tt1.IsMemberpage(temp.data.pageName))tt1.Insert(temp.data);
	


	Myset<SearchResult>iins=new Myset<SearchResult>();
	for (Node<PageEntry> temp=tt1.front;temp!=null;temp=temp.next)
	{
		iins.Insert(new SearchResult(temp.data,temp.data.getRelevanceOfPhrase(str,coun)));
	}

	ArrayList<SearchResult> res=MySort.sortThisList(iins);

	for (int temp=0;temp<res.size();temp++)
		if (temp<=res.size()-2)retval+=res.get(temp).p.pageName+ ", "; else retval+=res.get(temp).p.pageName ;
if (retval.equals(""))retval="No webpage contains the given phrase";
}
return retval;
	}
}