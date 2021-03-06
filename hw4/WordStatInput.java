import java.nio.charset.Charset;
import java.io.*;
import java.util.*;

public class WordStatInput {
    public static void main(String[] args) throws IOException {
        Map<String, Integer> map = new LinkedHashMap<>();
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(args[1], Charset.forName("utf-8")));
            Scanner scanner = new Scanner(new File(args[0]), Charset.forName("utf-8"));
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
                for (String s : map.keySet()) {
                    out.write(s + " " + map.get(s));
                    out.newLine();
                }
            } finally {
                scanner.close();
                out.close();
            }
        } catch (FileNotFoundException e){
            System.out.println("file not found: " + e.getMessage());
        }
    }
}