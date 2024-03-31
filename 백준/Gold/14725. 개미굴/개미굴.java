import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Trie {
        Map<String, Trie> childNode;

        Trie() {
            childNode = new TreeMap<>();
        }

        public void insert(String str) {
            Trie trie = this;
            StringTokenizer st = new StringTokenizer(str);

            int len = Integer.parseInt(st.nextToken());

            for(int i = 0; i < len; i++) {
                String element = st.nextToken();
                trie.childNode.putIfAbsent(element, new Trie());
                trie = trie.childNode.get(element);
            }
        }

        public void print(Trie cur, int depth) {
            Trie current = cur;

            if (current.childNode != null) {
                List<String> list = new ArrayList<>(current.childNode.keySet());

                for (String element: list) {
                    for (int i = 0; i< depth; i++) {
                        System.out.print("--");
                    }
                    System.out.println(element);
                    print(current.childNode.get(element), depth + 1);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        Trie trie = new Trie();

        for (int i = 0; i < N; i++) {
            trie.insert(br.readLine());
        }

        trie.print(trie, 0);
    }
}