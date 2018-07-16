import java.io.*;
import java.util.*;

public class lib {
    private Map<String, String> map1;

    public Map<String, String> getMap1() {
        return map1;
    }

    private List<Map.Entry<String,String>> orderList1;

    public lib() {
        super();
        map1 = new HashMap<String, String>();
    }

    /**
     * @description 读取文件，生成特殊Map，Key:单词，Value:单词最后一个字母
     * @method  dealFile
     * @param args 文件名数组
     * @CreateDate:  2018/7/15 17:37
     * @author: yinzc
     */
    public void dealFile(String[] args) {
        try {
            FileReader fileReader = new FileReader(args[0]);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                dealLine(line);
                //System.out.println(line);
                line = bufferedReader.readLine();
            }

            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("FileNotFoundException");
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException");
            System.exit(0);
        }
    }

    public void dealLine(String line) {
        int length = line.length();
        String Reg = "[a-zA-Z]{2,}[a-zA-Z0-9]*";
        if (length > 0) {
            if (!(isSplit(line.charAt(length - 1)))) {
                line = line.concat(".");
            }
            for (int i = 0; i < length; i++) {
                int first = i;
                int end = i;
                char temp1 = line.charAt(i);

                if (isZ(temp1)) {
                    i++;
                    while (!(isSplit(line.charAt(i)))) {
                        end = i;
                        i++;
                    }

                    String subString = line.substring(first, end + 1);
                    String endString = line.substring(end,end+1);
                    boolean idWord = subString.matches(Reg);
                    if (idWord) {
                        subString.toLowerCase();
                        // System.out.println(subString);
                        if (!map1.containsKey(subString)) {
                            map1.put(subString, endString);
                        }
                    }
                }
            }
        }
    }

    public Boolean isZ(char temp){
        if (((temp <= 'Z') && (temp >= 'A')) || ((temp >= 'a') && temp <= 'z')) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean isNumber (char temp){
    if ((temp <= '9') && (temp >= '0')) {
        return true;
    } else {
        return false;
    }
}

    public Boolean isSplit(char temp) {
        if(isZ(temp) || isNumber(temp)){
            return false;
        } else {
            return true;
        }
    }

    /**
     * @description 通过特殊Map获取最长单词链
     * @method  getWordChain
     * @param map （key:单词 value: 最后一个字母）（key:apple value:e）
     * @CreateDate:  2018/7/15 17:30
     * @author: yinzc
     */
    public String getWordChain(Map<String,String> map) {
        int mapSize = map.size();
        int mapEntryIndex = 0;
        List<String> vertices = new ArrayList<>();
        List<AbstractGraph.Edge> edges = new ArrayList<AbstractGraph.Edge>();
        String[][] a = new String[mapSize][2];
//  通过map获取 图的两个参数 结点数组vertices 边数组edges
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String ,String> entry = (Map.Entry<String ,String>) it.next();
            vertices.add(entry.getKey());
            a[mapEntryIndex][0] = entry.getKey();
            a[mapEntryIndex][1] = entry.getValue();
            mapEntryIndex++;
        }
        edges = getGraphEdges(a);
//构造图
        Graph<String> graph = new UnweightedGraph<String>(edges,vertices);
//循环每个结点，以结点为root，深度优先生成树，获取每棵树的最大单词链，存入allPath中。
        List<AbstractGraph.StringAndNumber> allPath = new ArrayList<>();
        int graphSize = graph.getSize();
        for(int i=0 ; i<graphSize ;i++ ) {
            Tree testTree = graph.dfs(i);
            AbstractGraph.StringAndNumber ac = ((UnweightedGraph<String>) graph).getMaxPath(testTree);
            allPath.add(ac);
        }
//对整个allpath排序，获取整个森林的最长单词链
        Collections.sort(allPath, new Comparator<AbstractGraph.StringAndNumber>() {
            @Override
            public int compare(AbstractGraph.StringAndNumber o1, AbstractGraph.StringAndNumber o2) {
                return o2.num - o1.num;
            }
        });
        return allPath.get(0).MaxStringPath;
    }

    /**
     * @description 二元数组构造图的边列表，后续构造图所需参数。
     * @method  getGraphEdges
     * @param a 二元数组
     * @CreateDate:  2018/7/15 17:35
     * @author: yinzc
     */
    public List<AbstractGraph.Edge> getGraphEdges(String[][] a) {
        List<AbstractGraph.Edge> edges = new ArrayList<AbstractGraph.Edge>();
        int length = a.length;
        for(int i=0 ; i<length ;i++) {
            for(int j=0 ; j <length ;j++) {
                String fisrtCharString = String.valueOf(a[i][0].toLowerCase().charAt(0));
                if(fisrtCharString.equals(a[j][1])  && i!=j) {
                    AbstractGraph.Edge edge = new AbstractGraph.Edge(j,i);
                    edges.add(edge);
                }
            }
        }
        return edges;
    }
}