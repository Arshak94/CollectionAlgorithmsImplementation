import javax.swing.tree.TreeNode;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        MyHashMap<String, String> myHashMap = new MyHashMap<>();
        myHashMap.put("Barev", "Arshak");
        String ars=myHashMap.put("Barev", "Arshak1");
        myHashMap.put("Hajox", "Arshak");
        System.out.println(myHashMap.toString());
        System.out.println("barev");
    }
}
