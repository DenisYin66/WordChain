import java.util.List;

public class UnweightedGraph<V> extends AbstractGraph<V> {

    protected UnweightedGraph(int[][] edges,int numOfVertices) {
        super(edges, numOfVertices);
    }

    public UnweightedGraph(int[][] edges,V[] vertices){
        super(edges, vertices);
    }

    public UnweightedGraph(List<AbstractGraph.Edge> edges,int numOfVertices){
        super(edges, numOfVertices);
    }

    public UnweightedGraph(List<AbstractGraph.Edge> edges,List<V> vertices) {
        super(edges, vertices);
    }

}
