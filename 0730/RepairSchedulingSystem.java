import java.util.ArrayDeque;
import java.util.ArrayList;

public class RepairSchedulingSystem {
    public static void main(String[] args) {
        ArrayList<RepairTask> allTasks = new ArrayList<RepairTask>();
        ArrayDeque<RepairTask> waitingQueue = new ArrayDeque<RepairTask>();
        ArrayDeque<RepairTask> completedStack = new ArrayDeque<RepairTask>();

        registerTask(allTasks, waitingQueue, new RepairTask("R001", "Printer", 2, 1));
        registerTask(allTasks, waitingQueue, new RepairTask("R002", "Router", 5, 2));
        registerTask(allTasks, waitingQueue, new RepairTask("R003", "Printer", 5, 3));
        registerTask(allTasks, waitingQueue, new RepairTask("R004", "Projector", 3, 4));

        completeNext(waitingQueue, completedStack);
        completeNext(waitingQueue, completedStack);
        undoLastCompletion(waitingQueue, completedStack);

        System.out.println("Search id R003: "
            + RepairAlgorithms.searchById(allTasks, "R003"));
        System.out.println("Search equipment Printer:");
        printTasks(RepairAlgorithms.searchByEquipmentName(allTasks, "Printer"));

        ArrayList<RepairTask> sorted = new ArrayList<RepairTask>(allTasks);
        RepairAlgorithms.mergeSortByPriorityDesc(sorted);
        System.out.println();
        System.out.println("All tasks sorted by priority descending:");
        printTasks(sorted);

        System.out.println();
        printStatistics(waitingQueue, completedStack, allTasks);
    }

    private static void registerTask(
        ArrayList<RepairTask> allTasks,
        ArrayDeque<RepairTask> waitingQueue,
        RepairTask task
    ) {
        allTasks.add(task);
        waitingQueue.offer(task);
    }

    private static void completeNext(
        ArrayDeque<RepairTask> waitingQueue,
        ArrayDeque<RepairTask> completedStack
    ) {
        RepairTask task = waitingQueue.poll();
        if (task == null) {
            System.out.println("No waiting repair task.");
            return;
        }
        task.setStatus("completed");
        completedStack.push(task);
    }

    private static void undoLastCompletion(
        ArrayDeque<RepairTask> waitingQueue,
        ArrayDeque<RepairTask> completedStack
    ) {
        RepairTask task = completedStack.poll();
        if (task == null) {
            System.out.println("No completed repair task to undo.");
            return;
        }
        task.setStatus("waiting");
        waitingQueue.offerFirst(task);
    }

    private static void printStatistics(
        ArrayDeque<RepairTask> waitingQueue,
        ArrayDeque<RepairTask> completedStack,
        ArrayList<RepairTask> allTasks
    ) {
        System.out.println("Waiting count: " + waitingQueue.size());
        System.out.println("Completed count: " + completedStack.size());
        System.out.println("All count: " + allTasks.size());
    }

    private static void printTasks(ArrayList<RepairTask> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("(empty)");
            return;
        }
        for (RepairTask task : tasks) {
            System.out.println(task);
        }
    }
}
