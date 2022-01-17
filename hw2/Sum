public class Sum {
    public static void main(String[] args) {
        int sum = 0;
        for (int i = 0; i < args.length; i++) {
            sum += getSum(args[i]);
        }
        System.out.println(sum);
    }

    public static int getSum(String s) {
        int sum = 0;
        int index = 0;
        s += " /";
        s = s.replace("+", "");
        while (index < s.length()) {
            s = s.strip();
            if (Character.isWhitespace(s.charAt(index))) {
                sum += Integer.parseInt(s.substring(0, index));
                s = s.replaceFirst(s.substring(0, index), "");
                index = 0;
            } else index++;
        }
        return sum;
    }
} 
