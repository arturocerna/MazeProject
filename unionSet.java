
public class unionSet {
 private int[] parent;
 private int[] rank;
 private int size;
 	// constructor to set up the unionSet
 	public unionSet(int n)
 	{
 		parent = new int[n];
 		rank = new int[n];
 		size = n;
 		for (int i = 0; i < parent.length; i++)
 		{
 			parent[i] = i;
 		}
 	}
 	// union wrapper function
  	public void union(int x, int y)
  	{
  		if (x < parent.length & y < parent.length)
  		{
  			link(find(x), find(y));  
  		}
  	}
  	// link function, unions a set
  	private void link(int x, int y) 
  	{
  		if (x==y) return;
  		if(rank[x] > rank[y])
  		{
  			parent[y] = x;
  			size--;
  		} else 
  		{
  			parent[x] = y;
  			if(rank[x] == rank[y])
  				rank[y]++;
  			size--;
  		}
  	}
  	// public find, bounds checking in here
	public int find(int x) 
	{
		int result = -1;
		if (x < parent.length)
		{
			result = pfind(x);
		}
		return result;
	}
	// private find
	private int pfind(int x)
	{
		if(x != parent[x])
			parent[x] = find(parent[x]);
		return parent[x];
	}
	// returns boolean of whether there is only one set
	public boolean isOneSet()
	{
		return size == 1;
	}
}
