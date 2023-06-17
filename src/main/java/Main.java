package main.java;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
/*
// Perform rate limiting logic for provided customer ID. Return true if the
2
// request is allowed, and false if it is not.
3
boolean rateLimit(int customerId)
Each customer can make X requests per Y seconds
 */

    public static void main(String[] args) {

        List<FileObject> fileList = new ArrayList<>();
        Main m = new Main();
        System.out.println(m.generateTotalSize(fileList, 2));
    }

    public  Result generateTotalSize(List<FileObject> fileList, int n) {
            int totalSize = 0;

            List<String> collectionName = new ArrayList<>();
            Result result = new Result();
            Map<FileObject, Integer> fileSizeMap = new HashMap<>();
            for(FileObject file: fileList){
              if(null != file.collectionName && !"".equals(file.collectionName)) {

                  if(fileSizeMap.containsKey(file)){

                      int size = fileSizeMap.get(file);
                      fileSizeMap.put(file, size + file.size);
                  }else{

                      fileSizeMap.put(file, file.size);
                  }

              }
              totalSize+= file.size;
            }

            Set<FileObject> files = fileSizeMap.keySet();
            Set<FileObject> x = files.stream()
                                .sorted(Comparator.comparingInt(fileSizeMap::get).reversed())
                                .collect(Collectors.toCollection(LinkedHashSet::new));

            for(FileObject y: x){
                if(n> 0){
                    collectionName.add(y.collectionName);
                    n--;

                }else
                    break;
            }
            result.totalSize = totalSize;
            result.collectionName = collectionName;
            return result;
    }
}



