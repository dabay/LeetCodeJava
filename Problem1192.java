import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Great explanation https://www.youtube.com/watch?v=aZXi1unBdJA
 */
public class Problem1192 {
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> critialPaths = new ArrayList<>();

        Map<Integer, Set<Integer>> connectionMap = new HashMap<>();
        for (List<Integer> c : connections) {
            int node1 = c.get(0);
            int node2 = c.get(1);
            connectionMap.putIfAbsent(node1, new HashSet<>());
            connectionMap.get(node1).add(node2);
            connectionMap.putIfAbsent(node2, new HashSet<>());
            connectionMap.get(node2).add(node1);
        }

        int[] reachSteps = new int[n];
        Arrays.fill(reachSteps, Integer.MAX_VALUE);

        dfs(0, -1, 0, reachSteps, connectionMap, critialPaths);

        return critialPaths;
    }

    private int dfs(int currentNode, int parentNode, int currentStep, int[] reachSteps, Map<Integer, Set<Integer>> connectionMap, List<List<Integer>> critialPaths) {
        reachSteps[currentNode] = currentStep;
        int smallestStep = currentStep;
        for (int nextNode : connectionMap.get(currentNode)) {
            if (nextNode == parentNode) continue;
            if (reachSteps[nextNode] == Integer.MAX_VALUE) {
                smallestStep = Math.min(
                        smallestStep,
                        dfs(nextNode, currentNode, currentStep + 1, reachSteps, connectionMap, critialPaths)
                );
            } else {
                smallestStep = Math.min(
                        smallestStep,
                        reachSteps[nextNode]
                );
            }
        }
        if (parentNode != -1 && smallestStep == currentStep) {
            critialPaths.add(new ArrayList<>(Arrays.asList(parentNode, currentNode)));
        }
        reachSteps[currentNode] = smallestStep;
        return smallestStep;
    }

    public List<List<Integer>> generateConnectionsForFile(String dataPath) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(dataPath)));
        br.readLine();
        String rawInput = br.readLine();
        List<List<Integer>> conenctions = new ArrayList<>();
        String r = "\\[(\\d+),(\\d+)]";
        Pattern p = Pattern.compile(r);
        Matcher m = p.matcher(rawInput);
        while (m.find()) {
            int node1 = Integer.parseInt(m.group(1));
            int node2 = Integer.parseInt(m.group(2));
            conenctions.add(new ArrayList<>(Arrays.asList(node1, node2)));
        }
        return conenctions;
    }

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