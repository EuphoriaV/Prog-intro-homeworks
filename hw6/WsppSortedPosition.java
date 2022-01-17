import java.nio.charset.Charset;
import java.io.*;
import java.util.*;

public class WsppSortedPosition {
    public static void main(String[] args) throws IOException {
        Map<String, List<Pair>> map = new LinkedHashMap<>();
        int count = 1, countStr = 1;
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(args[1], Charset.forName("utf-8")));
            Reader reader = new FileReader(args[0], Charset.forName("utf-8"));
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
                                map.get(word).add(new Pair(countStr, count));
                            } else {
                                List<Pair> cur = new ArrayList<>();
                                cur.add(new Pair(countStr, count));
                                map.put(word, cur);
                            }
                            count++;
                        }
                    }
                    countStr++;
                    count = 1;
                }
                List<Map.Entry<String, List<Pair>>> list = new ArrayList<>();
                for (Map.Entry<String, List<Pair>> entry : map.entrySet()) {
                    list.add(entry);
                }
                list.sort(Map.Entry.comparingByKey());
                for (int i = 0; i < list.size(); i++) {
                    out.write(list.get(i).getKey() + " " + list.get(i).getValue().size() + " ");
                    for (int j = 0; j < list.get(i).getValue().size(); j++) {
                        out.write(list.get(i).getValue().get(j) + "");
                        if (j != list.get(i).getValue().size() - 1) {
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

    public static class Pair {
        int str;
        int pos;

        Pair(int str, int pos) {
            this.str = str;
            this.pos = pos;
        }

        @Override
        public String toString() {
            return str + ":" + pos;
        }
    }
}