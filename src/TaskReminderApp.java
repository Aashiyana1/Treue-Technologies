import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Task {
    private String task_title;
    private String task_deuDate;
    private boolean task_completed;

    public Task(String task_title, String task_deuDate) {
        this.task_title = task_title;
        this.task_deuDate = task_deuDate;
        this.task_completed = false;
    }

    public String getTaskTitle() {
        return task_title;
    }

    public String getdueDate() {
        return task_deuDate;
    }

    public boolean isTaskCompleted() {
        return task_completed;
    }

    public void markTaskCompleted() {
        task_completed = true;
    }

    @Override
    public String toString() {
        String status = task_completed ? "Completed" : "Pending";
        return "Task Information:" +
                "\nTask Title: " + task_title +
                "\nDue Date: " + task_deuDate +
                "\nTask Status: " + status;
    }
}

class TaskReminderApp {
    private static final int AddTask = 1;
    private static final int MarkAsCompleted = 2;
    private static final int ViewTask = 3;
    private static final int Exit = 4;

    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("------ Task Reminder App -----");
            System.out.println("1. Add A New Task");
            System.out.println("2. Mark The Task as Completed");
            System.out.println("3. View All Tasks");
            System.out.println("4. Exit");
            System.out.print("Please Enter The Choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Please Enter The Title of Task: " + "\n");
                    String Title = scanner.nextLine();
                    System.out.print("Enter Due Date of Task: ");
                    String DueDate = scanner.nextLine();
                    Task task = new Task(Title , DueDate);
                    tasks.add(task);
                    System.out.println("Task Has Been Successfully Added.");
                    break;
                case 2:
                    System.out.print("Please Enter Index of Task You Want to Be Marked as Completed: " + "\n");
                    int taskIndex = scanner.nextInt();
                    if (taskIndex >= 0 && taskIndex < tasks.size()) {
                        Task selectedTask = tasks.get(taskIndex);
                        selectedTask.markTaskCompleted();
                        System.out.println("Great Work! The Task Has Been Successfully Marked as Completed.");
                    } else {
                        System.out.println("Error! The Index is Invalid.");
                    }
                    break;
                case 3:
                    System.out.println("Tasks:" + "\n");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println("Task " + i + ":");
                        System.out.println(tasks.get(i));
                    }
                    break;
                case 4:
                    System.out.println("Thank You For Using Our Application. Visit Again" + "\n");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Error! Choose Valid Option.Try Again");
            }
        }
    }
}
