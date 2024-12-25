import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int num = Integer.parseInt(br.readLine());

        Map<Integer, ArrayList<String>> dict = new HashMap<>();
        JUMP: for (int i = 0; i < num; i++) {
            String s = br.readLine();
            Integer length = s.length();
            ArrayList<String> stringList;
            if (dict.containsKey(length)) {
                stringList = dict.get(length);
                Iterator<String> iterator = stringList.iterator();
                while (iterator.hasNext()) {
                    String next = iterator.next();
                    if (next.equals(s)) {
                        continue JUMP;
                    }
                }
            } else {
                stringList = new ArrayList<>();
            }

            stringList.add(s);
            dict.put(s.length(), stringList);
        }
        ArrayList<Integer> keySet = new ArrayList<>(dict.keySet());
        Collections.sort(keySet);

        Iterator<Integer> it = keySet.iterator();
        while (it.hasNext()) {
            ArrayList<String> strings = dict.get(it.next());
            if (strings.size() == 1) {
                bw.write(strings.get(0) + "\n");
                bw.flush();
            } else {
                Collections.sort(strings);
                for (int i = 0; i < strings.size(); i++) {
                    bw.write(strings.get(i) + "\n");
                    bw.flush();
                }
            }

        }

    }
}
