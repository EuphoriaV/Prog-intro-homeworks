import java.nio.charset.Charset;
import java.io.*;
import java.util.*;

public class Wspp {
    public static void main(String[] args) throws IOException {
        Map<String, ArrayList<Integer>> map = new LinkedHashMap<>();
        int count = 1;
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(args[1], Charset.forName("utf-8")));
            Reader reader = new FileReader(args[0],Charset.forName("utf-8"));
            MyScanner scanner = new MyScanner(reader);
            try {
                String str;
                while (scanner.hasNextLine()) {
                    str = scanner.nextLine();
                    str = str.toLowerCase();
                    MyScanner scanner1 = new MyScanner(new StringReader(str));
                    while (scanner1.hasNextWord()){
                        String word = scanner1.nextWord();
                        if (word.length() > 0) {
                            if (map.containsKey(word)) {
                                map.get(word).add(count);
                            } else {
                                ArrayList<Integer> cur = new ArrayList<>();
                                cur.add(count);
                                map.put(word, cur);
                            }
                            count++;
                        }
                    }
                }
                for (String s : map.keySet()) {
                    out.write(s + " " + map.get(s).size() + " ");
                    for (int i = 0; i < map.get(s).size(); i++) {
                        out.write(map.get(s).get(i).intValue() + "");
                        if (i != map.get(s).size() - 1) {
                            out.write(" ");
                        }
                    }
                    out.newLine();
                }
            } finally {
                reader.close();
                scanner.close();
                out.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found: " + e.getMessage());
        }
    }
}