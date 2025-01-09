import java.util.*;

class Graph {
    private Map<String, List<String>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    public void addNode(String name) {
        adjacencyList.putIfAbsent(name, new ArrayList<>());
    }

    public void addEdge(String node1, String node2) {
        if (adjacencyList.containsKey(node1) && adjacencyList.containsKey(node2)) {
            adjacencyList.get(node1).add(node2);
            adjacencyList.get(node2).add(node1);
        } else {
            System.out.println("One or both nodes do not exist.");
        }
    }

    public void displayGraph() {
        System.out.println("School Network:");
        for (Map.Entry<String, List<String>> entry : adjacencyList.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

    public boolean hasPath(String start, String end) {
        if (!adjacencyList.containsKey(start) || !adjacencyList.containsKey(end)) {
            return false;
        }

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            if (current.equals(end)) {
                return true;
            }

            visited.add(current);
            for (String neighbor : adjacencyList.get(current)) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                }
            }
        }
        return false;
    }
}

public class SchoolNetworkManager {
    public static void main(String[] args) {
        Graph schoolNetwork = new Graph();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the School Network Manager!");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add Entity (Node)");
            System.out.println("2. Connect Entities (Edge)");
            System.out.println("3. Display Network");
            System.out.println("4. Check Connection Between Entities");
            System.out.println("5. Exit");
            System.out.println();
            System.out.print("Enter: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter entity name: ");
                    String nodeName = scanner.nextLine();
                    schoolNetwork.addNode(nodeName);
                    System.out.println("Entity added successfully!");
                    break;

                case 2:
                    System.out.print("Enter first entity name: ");
                    String entity1 = scanner.nextLine();
                    System.out.print("Enter second entity name: ");
                    String entity2 = scanner.nextLine();
                    schoolNetwork.addEdge(entity1, entity2);
                    System.out.println("Entities connected successfully!");
                    break;

                case 3:
                    schoolNetwork.displayGraph();
                    break;

                case 4:
                    System.out.print("Enter starting entity: ");
                    String start = scanner.nextLine();
                    System.out.print("Enter ending entity: ");
                    String end = scanner.nextLine();
                    if (schoolNetwork.hasPath(start, end)) {
                        System.out.println("A connection exists between " + start + " and " + end + ".");
                    } else {
                        System.out.println("No connection exists between " + start + " and " + end + ".");
                    }
                    break;

                case 5:
                    System.out.println("Exiting School Network Manager. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
