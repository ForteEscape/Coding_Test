import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Node {
        String element;
        Set<String> parent;
        Set<String> child;

        Node(String element) {
            this.element = element;
            parent = new HashSet<>();
            child = new HashSet<>();
        }
    }

    static Map<String, Node> tree;
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        tree = new HashMap<>();
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());

            String child = st.nextToken();
            String parent = st.nextToken();

            if (!tree.containsKey(parent)) {
                tree.put(parent, new Node(parent));
            }

            if (!tree.containsKey(child)) {
                tree.put(child, new Node(child));
            }

            tree.get(parent).child.add(child);
            tree.get(child).parent.add(parent);
        }

        st = new StringTokenizer(br.readLine());
        String target1 = st.nextToken();
        String target2 = st.nextToken();

        boolean flag1 = bfs(target1, target2);
        boolean flag2 = bfs(target2, target1);

        if (flag1 || flag2) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    public static boolean bfs(String start, String key) {
        Deque<Node> queue = new ArrayDeque<>();
        queue.addLast(tree.get(start));

        while(!queue.isEmpty()) {
            Node cur = queue.pollFirst();

            if (cur.element.equals(key)) {
                return true;
            }

            for (String element: cur.child) {
                queue.addLast(tree.get(element));
            }
        }

        return false;
    }
}