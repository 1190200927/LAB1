package P3;

import java.util.HashSet;
import java.util.Set;


public class FriendshipGraph {
	private Set<String> names = new HashSet<String>();
	private int n=0;
	private int N = 10000;
	private int [][] network = new int [N][N] ;
	private int [][] distancework = new int [N][N];
	private int flag=0;
	int i , j , k;
	
	
	public void addVertex(Person newPerson) {
		if (names.contains(newPerson.getName())) {
			//System.out.println("Person " + newPerson.name + " already existed.");
			System.exit(0);
		}
		
		names.add(newPerson.getName());
		newPerson.number=n;
		n++;
	}
	
	public void addEdge(Person a, Person b) {
		if (a == b) {
			System.out.println("They are the same one.");
			System.exit(0);
		}
		network[a.number][b.number]=1;
		network[b.number][a.number]=1;
		distancework[a.number][b.number]=1;
		distancework[b.number][a.number]=1;
		return;
	}

	public int getDistance(Person begin, Person end){
		
		if (flag==0){
			for(k=0;k< n+1;k++)  
			{
				for(i=0;i< n+1;i++)  
				{
					if(i==k)
						distancework[i][i]=0;
					else if(distancework[k][i]!=1)
						distancework[k][i]=-1;
				}
			}
			
			for(k=0;k< n+1;k++)  
			{
				for(i=0;i< n+1;i++)  
				{
					for(j=0;j< n+1;j++)  
					{
						if(distancework[i][j]==-1 && distancework[i][k]!=-1 && distancework[k][j]!=-1)   
						distancework[i][j]=distancework[i][k]+distancework[k][j];
						else if(distancework[i][j]!=-1 && distancework[i][k]!=-1 && distancework[k][j]!=-1)
						{
							if(distancework[i][j]>distancework[i][k]+distancework[k][j])   
								distancework[i][j]=distancework[i][k]+distancework[k][j];
						}
					}
				}
			}	
		flag++;
		}
		return distancework[begin.number][end.number];
	}
	
	
	public static void main(String args[]) {
		FriendshipGraph graph = new FriendshipGraph();
		Person rachel = new Person("Rachel");
		Person ross = new Person("Ross");
		Person ben = new Person("Ben");
		Person kramer = new Person("Kramer");
		graph.addVertex(rachel);
		graph.addVertex(ross);
		graph.addVertex(ben);
		graph.addVertex(kramer);
		graph.addEdge(rachel, ross);
		graph.addEdge(ross, rachel);
		graph.addEdge(ross, ben);
		graph.addEdge(ben, ross);
		

			
		System.out.println(graph.getDistance(rachel, ross));
		System.out.println(graph.getDistance(rachel, ben));
		System.out.println(graph.getDistance(rachel, rachel));
		System.out.println(graph.getDistance(rachel, kramer));
		
		
	}
}

