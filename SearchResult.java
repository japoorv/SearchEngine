import java.util.*;
import java.io.*;
import java.lang.*;
public class SearchResult implements Comparable<SearchResult>	
{
	PageEntry p;double relevance;
	public SearchResult(PageEntry p1,double r)
	{
		p=p1;
		relevance=r;
	}
	public PageEntry getPageEntry()	
	{
		return p;

	}
	public double getRelevance()
	{
		return relevance;
	}
	public int compareTo(SearchResult otherObject)
	{
		if (relevance==otherObject.relevance)return 0;
		else if (relevance>otherObject.relevance)return -1;
		else return 1;

	}

}