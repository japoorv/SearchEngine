import java.util.*;
import java.lang.*;
import java.io.*;
public class AVLNode
{
	Position data;
	AVLNode l,r,p;
	int height;
	AVLNode()
	{
		data=null;
		l=r=p=null;
		
		height=0;
	}
	AVLNode(Position o)
	{
		this.data=o;
		l=r=p=null;
		height=0;
	}
	void setData(Position o)
	{
		data=o;
	}
}
