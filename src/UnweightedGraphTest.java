import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class UnweightedGraphTest {

    String[] vertices = {"BeiJing","WuHan","ShangHai","GuangZhou","ShenZhen","HangZhou",
            "ChengDu","ChongQing","ChangSha","NanJing","ChenZhou","ZiXing"};
    int[][] edges = {
            {0,1},{0,3},{0,5},
            {1,0},{1,2},{1,3},
            {2,1},{2,3},{2,4},{2,10},
            {3,0},{3,1},{3,2},{3,4},{3,5},
            {4,2},{4,3},{4,5},{4,7},{4,8},{4,10},
            {5,0},{5,3},{5,4},{5,6},{5,7},
            {6,5},{6,7},
            {7,4},{7,5},{7,6},{7,8},
            {8,4},{8,7},{8,9},{8,10},{8,11},
            {9,8},{9,11},
            {10,2},{10,4},{10,8},{10,11},
            {11,8},{11,9},{11,10}
    };
    Graph<String> graph1 = new UnweightedGraph<String>(edges,vertices);

    @Test
    public void getSize() {
        Assert.assertEquals(12,graph1.getSize());
    }

    @Test
    public void getVertices() {
        List<String> verticesList = new ArrayList<String>();
        for(int i=0 ;i<vertices.length ;i++) {
            verticesList.add(vertices[i]);
        }
        Assert.assertEquals(verticesList,graph1.getVertices());
    }

    @Test
    public void getVertex() {
        for (int i=0 ;i<vertices.length ; i++) {
            Assert.assertEquals(vertices[i],graph1.getVertex(i));
        }

    }

    @Test
    public void getIndex() {
        for (int i=0 ;i<vertices.length ;i++)
            Assert.assertEquals(i,graph1.getIndex(vertices[i]));
    }

    //暂未实现
    @Test
    public void getNeighbors() {

    }

    @Test
    public void getDegree() {
        Assert.assertEquals(3,graph1.getDegree(0));
        Assert.assertEquals(3,graph1.getDegree(1));
        Assert.assertEquals(4,graph1.getDegree(2));
        Assert.assertEquals(5,graph1.getDegree(3));
        Assert.assertEquals(6,graph1.getDegree(4));
        Assert.assertEquals(5,graph1.getDegree(5));

    }

    //暂未实现
    @Test
    public void getAdjacencyMatrix() {
    }
}