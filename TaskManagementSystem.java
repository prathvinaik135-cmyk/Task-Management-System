
import java.util.LinkedList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Comparator;

class Task {
    int id;
    String title;
    String description;
    String priority;
    boolean completed;

    Task(int id, String title, String description, String priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.completed = false;
    }

    void display() {
        System.out.println(
            "ID: " + id
            + " | Title: " + title
            + " | Description: " + description
            + " | Priority: " + priority
            + " | Status: " + (completed ? "Completed" : "Pending")
        );
    }
}

public class TaskManagementSystem {

    static LinkedList<Task> tasks = new LinkedList<>();
    static HashMap<Integer, Task> taskMap = new HashMap<>();
    static Scanner sc = new Scanner(System.in);

    static int nextId = 1;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== Task Management System =====");
            System.out.println("1. Add Task");
            System.out.println("2. View All Tasks");
            System.out.println("3. Search Task");
            System.out.println("4. Update Task");
            System.out.println("5. Delete Task");
            System.out.println("6. Mark Task as Completed");
            System.out.println("7. View Tasks by Priority");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int choice = readInt();

            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    viewTasks();
                    break;
                case 3:
                    searchTask();
                    break;
                case 4:
                    updateTask();
                    break;
                case 5:
                    deleteTask();
                    break;
                case 6:
                    completeTask();
                    break;
                case 7:
                    viewByPriority();
                    break;
                case 8:
                    System.out.println("Goodbye!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    static void addTask() {
        System.out.print("Enter task title: ");
        String title = sc.nextLine();

        System.out.print("Enter description: ");
        String description = sc.nextLine();

        String priority = readPriority();

        Task task = new Task(nextId++, title, description, priority);

        tasks.add(task);
        taskMap.put(task.id, task);

        System.out.println("Task added successfully!");
    }

    static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }

        for (Task task : tasks) {
            task.display();
        }
    }

    static void searchTask() {
        System.out.print("Enter task ID: ");
        int id = readInt();

        Task task = taskMap.get(id);

        if (task == null) {
            System.out.println("Task not found.");
        } else {
            task.display();
        }
    }

    static void updateTask() {
        System.out.print("Enter task ID to update: ");
        int id = readInt();

        Task task = taskMap.get(id);

        if (task == null) {
            System.out.println("Task not found.");
            return;
        }

        System.out.print("Enter new title: ");
        task.title = sc.nextLine();

        System.out.print("Enter new description: ");
        task.description = sc.nextLine();

        task.priority = readPriority();

        System.out.println("Task updated successfully!");
    }

    static void deleteTask() {
        System.out.print("Enter task ID to delete: ");
        int id = readInt();

        Task task = taskMap.remove(id);

        if (task == null) {
            System.out.println("Task not found.");
        } else {
            tasks.remove(task);
            System.out.println("Task deleted successfully!");
        }
    }

    static void completeTask() {
        System.out.print("Enter task ID to complete: ");
        int id = readInt();

        Task task = taskMap.get(id);

        if (task == null) {
            System.out.println("Task not found.");
        } else {
            task.completed = true;
            System.out.println("Task marked as completed!");
        }
    }

    static void viewByPriority() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }

        PriorityQueue<Task> queue = new PriorityQueue<>(
            Comparator.comparingInt(
                task -> priorityValue(task.priority)
            )
        );

        queue.addAll(tasks);

        System.out.println("\nTasks by Priority:");

        while (!queue.isEmpty()) {
            queue.poll().display();
        }
    }

    static int priorityValue(String priority) {
        switch (priority) {
            case "High":
                return 1;
            case "Medium":
                return 2;
            default:
                return 3;
        }
    }

    static String readPriority() {
        while (true) {
            System.out.print("Enter priority (High/Medium/Low): ");
            String priority = sc.nextLine().trim();

            if (priority.equalsIgnoreCase("High")) {
                return "High";
            } else if (priority.equalsIgnoreCase("Medium")) {
                return "Medium";
            } else if (priority.equalsIgnoreCase("Low")) {
                return "Low";
            }

            System.out.println("Invalid priority. Please try again.");
        }
    }

    static int readInt() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Enter a valid whole number: ");
            }
        }
    }
}
