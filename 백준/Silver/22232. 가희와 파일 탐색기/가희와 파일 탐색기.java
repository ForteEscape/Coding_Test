import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	
	static class FileData {
		String name;
		String extension;
		
		FileData(String name, String extension){
			this.name = name;
			this.extension = extension;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		FileData[] files = new FileData[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), ".");
			files[i] = new FileData(st.nextToken(), st.nextToken());
		}
		
		Set<String> extensionSet = new HashSet<>();
		for(int i = 0; i < M; i++) {
			extensionSet.add(br.readLine());
		}
		
		Arrays.sort(files, (f1, f2) -> {
			if(f1.name.compareTo(f2.name) == 0) {
				if(extensionSet.contains(f1.extension) && !extensionSet.contains(f2.extension)) {
					return -1;
				} else if(extensionSet.contains(f2.extension) && !extensionSet.contains(f1.extension)) {
					return 1;
				} else {
					return f1.extension.compareTo(f2.extension);
				}
			} else {
				return f1.name.compareTo(f2.name);
			}
		});
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) {
			sb.append(files[i].name).append(".").append(files[i].extension).append("\n");
		}
		
		System.out.println(sb);
	}
	
}