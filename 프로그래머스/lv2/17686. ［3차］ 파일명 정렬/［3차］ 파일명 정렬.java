import java.util.*;

class FileData implements Comparable<FileData>{
    String header;
    String number;
    String tail;
    
    FileData(String header, String number, String tail){
        this.header = header;
        this.number = number;
        this.tail = tail;
    }
    
    @Override
    public int compareTo(FileData f){
        if(this.header.compareToIgnoreCase(f.header) > 0){
            return 1;
        } else if(this.header.compareToIgnoreCase(f.header) == 0){
            int thisDataNum = Integer.parseInt(this.number);
            int paramDataNum = Integer.parseInt(f.number);
            
            if(thisDataNum < paramDataNum){
                return -1;
            } else if(thisDataNum > paramDataNum){
                return 1;
            } else{
                return 0;
            }
        } else{
            return -1;
        }
    }
}


class Solution {
    public String[] solution(String[] files) {
        FileData[] fileData = new FileData[files.length];
        int fileDataIdx = 0;
        
        for(String file: files){
            int idx = 0;
            StringBuilder header = new StringBuilder();
            
            //header
            while(!Character.isDigit(file.charAt(idx))){
                header.append(file.charAt(idx));
                idx++;
            }
            
            //number;
            StringBuilder number = new StringBuilder();
            while(idx < file.length() && Character.isDigit(file.charAt(idx))){
                number.append(file.charAt(idx));
                idx++;
            }
            
            StringBuilder tail = new StringBuilder();
            while(idx < file.length()){
                tail.append(file.charAt(idx));
                idx++;
            }
            
            
            if(tail.length() == 0){
                fileData[fileDataIdx++] = new FileData(header.toString(), number.toString(), "");
            }else{
                fileData[fileDataIdx++] = new FileData(header.toString(), number.toString(), tail.toString());
            }
        }
        
        for(int i = 0; i < files.length; i++){
            System.out.println(fileData[i].header + " " + fileData[i].number + " " + fileData[i].tail);
        }
        
        Arrays.sort(fileData);
        
        String[] answer = new String[files.length];
        for(int i = 0; i < files.length; i++){
            answer[i] = fileData[i].header + fileData[i].number + fileData[i].tail;
        }
        
        return answer;
    }
}