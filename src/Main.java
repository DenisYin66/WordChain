import java.util.Iterator;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        lib test = new lib();
        test.dealFile(args);    //处理文件，获取map，保存在test.map1中
        String MaxWordChain = test.getWordChain(test.getMap1());  //操作map1 获取最长单词链，并打印出来
        System.out.println(MaxWordChain);
    }

    public static void out (String fileName,Map<String,String> map){
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry entry = (Map.Entry)it.next();
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
