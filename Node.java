import java.util.*;
import java.io.*;
import java.lang.*;
public class Node<T>
{
	T data;
	Node<T> next;
	Node()
	{
		
	}
	Node(T o)
	{
		this.data=o;
		this.next=null;
	}
	Node (Node<T> a)
	{
		if (a==null)return;
		this.data=a.data;
		this.next=a.next;
	}
}