import java.util.*;
import java.lang.*;
import java.io.*;
public class MySort
{
	public static ArrayList<SearchResult> sortThisList(Myset<SearchResult> listOfSortableEntries)
	{

		ArrayList<SearchResult>tt=new ArrayList<SearchResult>(1001);
		for (Node<SearchResult> temp=listOfSortableEntries.front;temp!=null;temp=temp.next)tt.add(temp.data);
		
		Collections.sort(tt);
		
		return tt;
	}
}