package def;

import java.util.*;

public class AlgoIlandsBridges {

	public static void main(String[] args) {
		int V = 7;
		int start_user = 0;
		Grapth g = new Grapth(V);

		g.addEdge(0, 1);
		g.addEdge(0, 3);
		g.addEdge(1, 2);
		g.addEdge(1, 3);
		
		bfs(g, 0);

	}

	static void bfs(Grapth g, int start_user) {
		int[] visited = new int[g.getV()];
		int[] res = new int[g.getV()];
		int ver = 0;
		Arrays.fill(visited, 0);
		Arrays.fill(res, -1);
		int time = 0;
		Queue<Integer> q = new LinkedList<>();
		if (visited[start_user] == 0) {
			q.offer(start_user);
			while (!q.isEmpty()) {
				ver = q.poll();
				visited[ver] = 1;
				for (int vv : g.adj(ver)) {
					if (visited[vv] == 0) {
						q.offer(vv);
					}
				}
			}
		}
		
		System.out.println("Последним увидит мем пользователь " + ver);
	}

}

class Grapth {
	ArrayList<ArrayList<Integer>> ver;
	int val;

	Grapth(int val) {
		this.val = val;
		ver = new ArrayList<ArrayList<Integer>>(val);
		for (int i = 0; i < val; i++) {
			ver.add(new ArrayList<Integer>());
		}
	}

	void addEdge(int a, int b) {
		ver.get(a).add(b);
		ver.get(b).add(a);
	}

	ArrayList<Integer> adj(int v) {
		return ver.get(v);
	}

	public int getV() {
		return val;
	}
}