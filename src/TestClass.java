import java.io.*;
import java.util.LinkedList;

public class TestClass {
    public static void main(String[] args) throws IOException {
        IniFilesWriter writer = new IniFilesWriter(new FileWriter("text.txt"));
        LinkedList<PairUnifiedGenericsContainer<String, LinkedList<PairUnifiedGenericsContainer<String, String>>>> list = new LinkedList<>();
        LinkedList<PairUnifiedGenericsContainer<String,String>> father = new LinkedList<>();
        father.add(new PairUnifiedGenericsContainer<>("papa=","ma\"ma"));
        father.add(new PairUnifiedGenericsContainer<>("sestea", "vn\\yk"));
        list.add(new PairUnifiedGenericsContainer<>("Father", father));
        writer.writeIniFile(list);
        IniFilesReader reader = new IniFilesReader(new FileReader("text.txt"));
        list = reader.readIniFile();
        System.out.println(list.get(0).getValue1() + " " + list.get(0).getValue2().get(0).getValue2());
    }
}
