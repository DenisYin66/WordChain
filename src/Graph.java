import java.util.List;

public interface Graph<V> {
    public int getSize();    //返回这个图的结点个数
    public List<V> getVertices(); //返回图的结点
    public V getVertex(int index); //返回下标index对应的结点
    public int getIndex(V v);  //返回结点v对应的下标
    public List<Integer> getNeighbors(int index);  //返回下标index对应的结点的相邻结点
    public int getDegree(int v); //返回结点v的度
    public int[][] getAdjacencyMatrix(); //返回邻接矩阵
    public void printAdjacencyMatrix(); //打印邻接矩阵
    public void printEdges();  //打印所有的邻接表
    public Tree bfs(int v);     //返回一个广度优先搜索树
    public Tree dfs(int v);     //返回一个深度优先搜索树
    public List<List<V>> getAllPath(Tree tree);  //返回这个树从根节点到叶子节点所有的路径(倒序)
    public List<V> getPath(int index,Tree tree);    //以某个节点为起点，返回该节点到根节点的路径
    public List<Integer> getEndNode(Tree tree);       //获取这颗树的所有叶子结点！
}
