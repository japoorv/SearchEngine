import java.util.*;
import java.io.*;
import java.lang.*;
public class Position
{
	PageEntry p;int i;int i_r=0;
	Position(PageEntry p1,int wordIndex)
	{
		p=p1;
		i=wordIndex;
		i_r=0;
	}
	Position(PageEntry p1,int wordIndex,int a_i)
	{
		p=p1;
		i=wordIndex;
		i_r=a_i;
	}
	PageEntry getPageEntry()
	{
		return p;
	}
	int getWordEntry()
	{
		return i;
	}
}