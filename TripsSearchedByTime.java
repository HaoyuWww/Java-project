import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        BufferedReader br = null;
        HashMap<String,TreeMap<String,String>> infoMap = new HashMap<>();
        try{
            String line;
            br = new BufferedReader(new FileReader("stop_times.txt"));
            br.readLine();
            while((line = br.readLine()) != null){
                String[] items = line.split(",");
                String id = items[0];
                String arrivalTime = items[1].trim();
                if(isValidTime(arrivalTime)){
                    TreeMap<String, String> detail;
                    if(infoMap.containsKey(arrivalTime)){
                        detail = infoMap.get(arrivalTime);
                    }else{
                        detail = new TreeMap<>();
                    }
                    detail.put(id,line);
                    infoMap.remove(arrivalTime);
                    infoMap.put(arrivalTime,detail);
                }
            }
            //enter the arrival time
            Scanner scanner = new Scanner(System.in);
            while(scanner.hasNextLine()) {
                String oneArrivalTime = scanner.nextLine();
                if (isValidTime(oneArrivalTime)) {
                    if (!infoMap.containsKey(oneArrivalTime)) {
                        System.out.println("Zero item matches this time");
                            return;
                    }
                    TreeMap<String, String> treeMap = infoMap.get(oneArrivalTime);
                    Iterator<Map.Entry<String, String>> iterator = treeMap.entrySet().iterator();
                    while (iterator.hasNext()) {
                        System.out.println(iterator.next().getValue());
                    }
                } else {
                    System.out.println("The time format is wrong");
                }
            }
        }catch (FileNotFoundException e){
            System.out.println("File not found!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Determine whether the arrival time is reasonable
    private static boolean isValidTime(String arrivalTime) {
        String[] time = arrivalTime.trim().split(":");
        int[] timeNum = new int[3];
        for(int i = 0;i<3;i++){
            timeNum[i] = Integer.parseInt(time[i]);
        }
        if(timeNum[0] < 0 || timeNum[0] > 23) return false;
        if(timeNum[1] < 0 || timeNum[1] > 59) return false;
        if(timeNum[2] < 0 || timeNum[2] > 59) return false;

        return true;
    }
}
