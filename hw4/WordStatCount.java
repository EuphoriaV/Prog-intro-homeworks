import java.io.*;
import java.util.*;

public class WordStatCount {
    public static void main(String[] args) throws IOException {
        Map<String, Integer> map = new LinkedHashMap<>();
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(args[1]));
            MyScanner scanner = new MyScanner(new FileReader(args[0]));
            try {
                String str;
                while (scanner.hasNextLine()) {
                    str = scanner.nextLine();
                    str = str.toLowerCase();
                    MyScanner sc = new MyScanner(new StringReader(str));
                    while (sc.hasNextWord()) {
                        String word = sc.nextWord();
                        if (word.length() > 0) {
                            if (map.containsKey(word)) {
                                map.put(word, map.get(word) + 1);
                            } else {
                                map.put(word, 1);
                            }
                        }
                    }
                }
                ArrayList<Map.Entry<String, Integer>> list = new ArrayList<>();
                for (Map.Entry<String, Integer> entry : map.entrySet()) {
                    list.add(entry);
                }
                list.sort(Map.Entry.comparingByValue());
                for (int i = 0; i < list.size(); i++) {
                    out.write(list.get(i).getKey() + " " + list.get(i).getValue());
                    out.newLine();
                }
            } finally {
                scanner.close();
                out.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found: " + e.getMessage());
        }
    }
}