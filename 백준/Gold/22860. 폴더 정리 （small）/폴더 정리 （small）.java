import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	
	static class Folder {
		List<String> files;
		List<Folder> folders;
		
		Folder(){
			files = new ArrayList<>();
			folders = new ArrayList<>();
		}
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static Map<String, Folder> directory = new HashMap<>();
	static Map<String, Integer> result = new HashMap<>();
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// main이 먼저 들어오지 않을 수 있음을 유의 => 부모 자식 관계가 뒤섞여 나올 가능성 존재.
		// contains 연산을 통해 존재하는지 확인하고 존재하는경우 넣고 없는 경우 새로 넣어주기
		for(int i = 0; i < N + M; i++) {
			st = new StringTokenizer(br.readLine());
			
			String parent = st.nextToken();
			String child = st.nextToken();
			int isFolder = Integer.parseInt(st.nextToken());
			
			if(!directory.containsKey(parent)) {
				directory.put(parent, new Folder());
			}
			
			if(isFolder == 1) { // this is folder
				if(!directory.containsKey(child)) {
					directory.put(child, new Folder());
				}
				directory.get(parent).folders.add(directory.get(child));
				
			} else { // this is file
				directory.get(parent).files.add(child);
			}
		}
		
		int query = Integer.parseInt(br.readLine());
		for(int i = 0; i < query; i++) {
			result.clear();
			String[] path = br.readLine().split("/");
			String destination = path[path.length - 1];
			
			dfs(directory.get(destination));
			
			int fileTypeCnt = result.keySet().size();
			int fileCnt = 0;
			
			for(String fileName : result.keySet()) {
				fileCnt += result.get(fileName);
			}
			
			System.out.println(fileTypeCnt + " " + fileCnt);
		}
	}
	
	public static void dfs(Folder startFolder) {
		for(String file : startFolder.files) {
			result.put(file, result.getOrDefault(file, 0) + 1);
		}
		
		for(Folder folder : startFolder.folders) {
			dfs(folder);
		}
	}
}