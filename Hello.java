import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hello {
    public static void main(String[] args) throws Throwable{
        Problem1192 problem = new Problem1192();

//        List<List<Integer>> connections = new ArrayList<>();
//        connections.add(new ArrayList<>(Arrays.asList(0, 1)));
//        connections.add(new ArrayList<>(Arrays.asList(1, 2)));
//        connections.add(new ArrayList<>(Arrays.asList(2, 0)));
//        connections.add(new ArrayList<>(Arrays.asList(1, 3)));
//        List<List<Integer>>  paths = problem.criticalConnections(4, connections);

        List<List<Integer>> connections = problem.generateConnectionsForFile("Problem1192.data");
        List<List<Integer>> paths = problem.criticalConnections(1000, connections);

        for(List<Integer> path: paths){
            System.out.println(String.format("%d - %d", path.get(0), path.get(1)));
        }
    }


}