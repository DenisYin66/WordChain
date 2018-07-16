import java.util.ArrayList;
import java.util.List;

public class Tree {
    private int root;
    private int[] parent;
    private List<Integer> searchOrders;

    public Tree(int root ,int[] parent,List<Integer> searchOrders) {
        this.root = root;
        this.parent = parent;
        this.searchOrders = searchOrders;
    }

    public Tree(int root ,int[] parent) {
        this.root = root;
        this.parent = parent;
    }

    public int getRoot() {
        return this.root;
    }

    public int getParent(int v) {
        return parent[v];
    }

    public int[] getParentArr() {
        return parent;
    }

    public List<Integer> getSearchOrders() {
        return this.searchOrders;
    }

    public int getNumberOfVerticesFound() {
        return searchOrders.size();
    }

    public int getTreeDegree() {
        int TreeDegree = 1;
        int parentLength = parent.length;
        List<Integer> rootParent = new ArrayList<Integer>();
        List<Integer> Temp = new ArrayList<Integer>();
        Temp.add(root);
        Boolean[] isVisit = new Boolean[parentLength];
        for (int i=0 ;i<parentLength ;i++) {
            isVisit[i] = false;
        }

        rootParent.add(root);
        while (Temp.size() != 0 ){
            Temp.clear();
            for (int i = 0; i < parentLength; i++) {
                if(!isVisit[i]) {
                    if (rootParent.contains(parent[i])) {
                        Temp.add(i);
                        isVisit[i] = true;
                    }
                }
            }
            rootParent.addAll(Temp);
            TreeDegree++;
        }
        TreeDegree--;
        return TreeDegree;
    }

}
