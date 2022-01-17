package md2html;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class Md2Html {
    public static void main(String[] args) throws IOException {
        try {
            Map<String, String> map = Map.of(
                    "`", "code",
                    "''", "q",
                    "**", "strong",
                    "__", "strong",
                    "*", "em",
                    "_", "em",
                    "--", "s"
            );

            Map<String, Integer> index = Map.of(
                    "`", 0,
                    "''", 1,
                    "**", 2,
                    "__", 3,
                    "*", 4,
                    "_", 5,
                    "--", 6
            );
            Scanner sc = new Scanner(new File(args[0]), Charset.forName("utf-8"));
            BufferedWriter writer = new BufferedWriter(new FileWriter(args[1], Charset.forName("utf-8")));
            try {
                while (sc.hasNextLine()) {
                    StringBuilder cur = new StringBuilder();
                    while (sc.hasNextLine()) {
                        String str = sc.nextLine();
                        if (str.isEmpty()) {
                            break;
                        }
                        cur.append(str + System.lineSeparator());
                    }
                    String s = cur.toString();
                    s = s.replace("&", "&amp;");
                    s = s.replace("<", "&lt;");
                    s = s.replace(">", "&gt;");
                    cur = new StringBuilder(s);
                    int[] first = new int[7];
                    int[] second = new int[7];
                    for (int i = 0; i < 7; i++) {
                        first[i] = -1;
                        second[i] = -1;
                    }
                    int k;
                    for (int i = 0; i < cur.length(); i += k) {
                        k = 1;
                        int j = -1;
                        String a = "";
                        if (i + 2 < cur.length()) {
                            a = cur.substring(i, i + 2);
                            if (map.containsKey(a)) {
                                k = 2;
                                j = index.get(a);
                            }
                        }
                        if (i + 1 < cur.length() && j == -1) {
                            a = String.valueOf(cur.charAt(i));
                            if (map.containsKey(a)) {
                                j = index.get(a);
                            }
                        }
                        if (i + a.length() < cur.length() && j != -1) {
                            if (first[j] == -1) {
                                first[j] = i;
                            } else if (second[j] == -1) {
                                second[j] = i;
                            }
                            boolean containsSlash = false;
                            if (second[j] > -1 && first[j] > -1) {
                                if (second[j] > 0 && first[j] > 0) {
                                    if (cur.charAt(first[j] - 1) == '\\' || cur.charAt(second[j] - 1) == '\\') {
                                        containsSlash = true;
                                    }
                                }
                                if (!containsSlash) {
                                    cur.replace(first[j], first[j] + a.length(), "<" + map.get(a) + ">");
                                    second[j] += map.get(a).length() + 2 - a.length();
                                    cur.replace(second[j], second[j] + a.length(), "</" + map.get(a) + ">");
                                }
                                first[j] = -1;
                                second[j] = -1;
                            }
                        }
                    }
                    cur = new StringBuilder(cur.toString().replace("\\", ""));
                    if (!cur.isEmpty()) {
                        cur = new StringBuilder(cur.substring(0, cur.length() - System.lineSeparator().length()));
                        writer.write(hOrP(cur).toString());
                        writer.newLine();
                    }
                }
            } finally {
                sc.close();
                writer.close();
            }
        } catch (IOException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    public static StringBuilder hOrP(StringBuilder str) {
        int indexOfHead = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != '#') {
                if (str.charAt(i) == ' ') {
                    indexOfHead = i;
                }
                break;
            }
        }
        if (indexOfHead < 1) {
            str.insert(0, "<p>");
            str.append("</p>");
        } else {
            str = new StringBuilder(str.substring(indexOfHead + 1));
            str.insert(0, "<h" + indexOfHead + ">");
            str.append("</h" + indexOfHead + ">");
        }
        return str;
    }
}