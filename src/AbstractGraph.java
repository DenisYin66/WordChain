import java.util.*;

public abstract class AbstractGraph<V> implements Graph<V> {
    protected List<V> vertices; //结点列表
    protected List<List<Integer>> neighbors; //存储每个结点的相邻结点

    protected AbstractGraph(int[][] edges,V[] vertices) {
        this.vertices = new ArrayList<V>();
        int verticesLength = vertices.length;
        for(int i=0 ;i<verticesLength ;i++ ) {
            this.vertices.add(vertices[i]);
        }
        createAdjacencyLists(edges, verticesLength);
    }

    protected AbstractGraph(List<Edge> edges,List<V> vertices) {
        this.vertices = vertices;
        createAdjacencyLists(edges,vertices.size());
    }

    protected AbstractGraph(List<Edge> edges,int numOfVertices) {
        this.vertices = new ArrayList<V>();
        for (int i=0; i<numOfVertices ;i++) {
            vertices.add( (V)(new Integer(i)) );
        }
        createAdjacencyLists(edges,numOfVertices);
    }

    protected AbstractGraph(int[][] edges ,int numOfVertices) {
        this.vertices = new ArrayList<V>();
        for(int i=0 ;i<numOfVertices ;i++) {
            vertices.add( (V)(new Integer(i)));
        }
        createAdjacencyLists(edges, numOfVertices);
    }

    private void createAdjacencyLists(int[][] edges ,int numOfVertices) {
        neighbors = new ArrayList< List<Integer> >();
        for(int i=0 ;i<numOfVertices ;i++) {
            neighbors.add(new ArrayList<Integer>());
        }
        for(int i=0 ;i<edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            neighbors.get(u).add(v);
        }
    }

    private void createAdjacencyLists(List<Edge> edges ,int numOfVertices) {
        neighbors = new ArrayList< List<Integer> >();
        for(int i=0 ;i<numOfVertices ;i++) {
            neighbors.add(new ArrayList<Integer>());
        }
        for (Edge edge : edges) {
            neighbors.get(edge.u).add(edge.v);
        }
    }

    public static class Edge {
        public int u;
        public int v;
        public Edge(int u,int v) {
            this.u = u;
            this.v = v;
        }
    }

    public static class StringAndNumber {
        public int num;
        public String MaxStringPath;
        public StringAndNumber(String MaxStringPath,int num) {
            this.MaxStringPath = MaxStringPath;
            this.num = num;
        }
    }

    @Override
    public int getSize() {
        return vertices.size();
    }

    @Override
    public List<V> getVertices() {
        return vertices;
    }

    @Override
    public V getVertex(int index) {
        return vertices.get(index);
    }

    @Override
    public int getIndex(V v) {
        return vertices.indexOf(v);
    }

    @Override
    public List<Integer> getNeighbors(int index){
        return neighbors.get(index);
    }

    @Override
    public int getDegree(int v) {
        return neighbors.get(v).size();
    }

    @Override
    public int[][] getAdjacencyMatrix() {
        int n = getSize();
        int[][] adjacencyMatrix = new int[n][n];
        for (int i=0 ;i<neighbors.size() ;i++) {
            for(int j=0 ;j<neighbors.get(i).size() ;j++) {
                int v = neighbors.get(i).get(j);
                adjacencyMatrix[i][v] = 1;
            }
        }
        return adjacencyMatrix;
    }

    @Override
    public void printAdjacencyMatrix() {
        int[][] adjacencyMatrix = getAdjacencyMatrix();
        for(int i=0 ; i<adjacencyMatrix.length ;i++){
            for (int j=0 ;j<adjacencyMatrix[i].length;j++) {
                System.out.println(adjacencyMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    @Override
    public void printEdges() {
        for(int u=0 ;u<neighbors.get(u).size() ;u++) {
            System.out.println("Vertex" + u + ":");
            for (int i=0 ;i<neighbors.get(u).size() ;i++) {
                System.out.println("(" + u + "," + neighbors.get(u).get(i) + ")" );
            }
            System.out.println();
        }
    }

    @Override
    public Tree bfs(int v) {
        int verticesSize = vertices.size();
        List<Integer> searchOrders = new ArrayList<Integer>();
        int Node = 1;  //结点数
        int TreeDegree = 1;

        Boolean[] isVisited = new Boolean[verticesSize];

        //父结点初始化，全赋值为 -1;
        int[] parent = new int[verticesSize];
        for(int i=0 ;i<verticesSize ;i++) {
            parent[i] = -1;
            isVisited[i] = false;
        }

        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.offer(v);
        isVisited[v] = true;
        while (!queue.isEmpty()) {
            int u = queue.poll();
            searchOrders.add(u);
            for(int w : neighbors.get(u)) {
                if(!isVisited[w]) {
                    queue.offer(w);
                    parent[w] = u;
                    isVisited[w] = true;
                }
            }
        }
        return new Tree(v,parent,searchOrders);
    }

    @Override
    public Tree dfs(int v) {
        List<Integer> searchOrders = new ArrayList<Integer>();
        int verticesSize = vertices.size();
        int[] parent = new int[verticesSize];
        int parentLength = parent.length;
        boolean[] isVisited = new boolean[verticesSize];

        for(int i=0 ;i<parentLength ;i++) {
            parent[i] = -1;
            isVisited[i] = false;
        }

        dfs(v,parent,searchOrders,isVisited);

        return new Tree(v,parent,searchOrders);
    }

    private void dfs(int v,int []parent,List<Integer> searchOrders,boolean[] isVisited) {
        searchOrders.add(v);
        isVisited[v] = true;
        for (int i : neighbors.get(v)) {
            if(!isVisited[i]) {
                parent[i] = v;
                dfs(i,parent,searchOrders,isVisited);
            }
        }
    }

    public void printBfsTree(Tree tree) {
        System.out.println("正在打印一棵树......");
        System.out.println("root is: " + vertices.get(tree.getRoot()));
        System.out.println("Edges: ");
        int[] parent = tree.getParentArr();
        int parentLength = parent.length;
        for(int i=0 ; i<parentLength ;i++) {
            if (parent[i] != -1) {
                System.out.println("(" + vertices.get(parent[i]) + "." + vertices.get(i) + ")");
            }
            System.out.println();
        }
    }

    public List<List<V>> getAllPath(Tree tree) {
        List<List<V>> returnList = new ArrayList<>();
        List<Integer> endNode = getEndNode(tree);
        ListIterator<Integer> it = endNode.listIterator();
        while (it.hasNext()) {
            int next = it.next();
            returnList.add(getPath(next,tree));
        }
        return returnList;
    }

    public List<V> getPath(int index,Tree tree) {
        ArrayList<V> path = new ArrayList<V>();
        do{
            path.add(vertices.get(index));
            index = tree.getParentArr()[index];
        } while (index!= -1);
        return  path;
    }

    public List<Integer> getEndNode(Tree tree) {
        List<Integer> endNodeList = new ArrayList<Integer>();
        for(int i=0 ;i<tree.getParentArr().length ;i++) {
            if((Arrays.binarySearch(tree.getParentArr(),i) < 0) && tree.getParentArr()[i]!=-1){
                endNodeList.add(i);
            }
        }
        if(endNodeList.size() == 0) {
            endNodeList.add(tree.getRoot());
        }
        return endNodeList;
    }

    public StringAndNumber getMaxPath(Tree tree){
        List<List<V>> lists = getAllPath(tree);
        Collections.sort(lists, new Comparator<List<V>>() {
            @Override
            public int compare(List<V> o1, List<V> o2) {
                return o2.size() - o1.size();
            }
        });
        return new StringAndNumber(ListToString(lists.get(0)),lists.get(0).size());
    }

    private String ListToString(List<V> list) {
        Collections.reverse(list);
        String listString = new String();
        Iterator it =  list.iterator();
        String split = "";
        while (it.hasNext()) {
            V v = (V) it.next();
            listString = listString + split + v.toString() ;
            split = "-";
        }
        return listString;
    }
}
