import java.util.ArrayDeque;
import java.util.ArrayList;

public class EventRegistrationSystem {
    private static final int CAPACITY = 3;

    public static void main(String[] args) {
        ArrayList<Registration> allRegistrations =
            new ArrayList<Registration>();
        ArrayDeque<Registration> waitingQueue =
            new ArrayDeque<Registration>();
        ArrayDeque<Registration> cancelStack =
            new ArrayDeque<Registration>();

        register(allRegistrations, waitingQueue, new Registration("A003", "Amy"));
        register(allRegistrations, waitingQueue, new Registration("A001", "Ben"));
        register(allRegistrations, waitingQueue, new Registration("A002", "Cara"));
        register(allRegistrations, waitingQueue, new Registration("A004", "Dora"));
        register(allRegistrations, waitingQueue, new Registration("A004", "Duplicate"));

        cancel(allRegistrations, waitingQueue, cancelStack, "A002");
        cancel(allRegistrations, waitingQueue, cancelStack, "A999");
        undoCancel(allRegistrations, waitingQueue, cancelStack);

        ArrayList<Registration> sorted =
            new ArrayList<Registration>(allRegistrations);
        RegistrationAlgorithms.mergeSortById(sorted);

        System.out.println();
        System.out.println("Sorted by id:");
        printRegistrations(sorted);
        System.out.println("Binary search A003: "
            + RegistrationAlgorithms.binarySearchById(sorted, "A003"));
        System.out.println("Sequential search name Amy:");
        printRegistrations(
            RegistrationAlgorithms.sequentialSearchByName(allRegistrations, "Amy")
        );
        System.out.println("Waiting queue size: " + waitingQueue.size());
    }

    public static void register(
        ArrayList<Registration> allRegistrations,
        ArrayDeque<Registration> waitingQueue,
        Registration registration
    ) {
        if (RegistrationAlgorithms.sequentialSearchById(
                allRegistrations,
                registration.getId()
            ) != null) {
            System.out.println("Reject duplicate registration id: "
                + registration.getId());
            return;
        }

        if (activeCount(allRegistrations) >= CAPACITY) {
            registration.setStatus("waiting");
            waitingQueue.offer(registration);
            System.out.println("Event full, add to waiting queue: "
                + registration);
        } else {
            registration.setStatus("registered");
            System.out.println("Registered: " + registration);
        }
        allRegistrations.add(registration);
    }

    public static void cancel(
        ArrayList<Registration> allRegistrations,
        ArrayDeque<Registration> waitingQueue,
        ArrayDeque<Registration> cancelStack,
        String id
    ) {
        Registration registration =
            RegistrationAlgorithms.sequentialSearchById(allRegistrations, id);
        if (registration == null || "cancelled".equals(registration.getStatus())) {
            System.out.println("Cannot cancel missing registration: " + id);
            return;
        }

        registration.setStatus("cancelled");
        cancelStack.push(registration);
        System.out.println("Cancelled: " + registration);
        promoteFirstWaiting(waitingQueue);
    }

    public static void undoCancel(
        ArrayList<Registration> allRegistrations,
        ArrayDeque<Registration> waitingQueue,
        ArrayDeque<Registration> cancelStack
    ) {
        Registration registration = cancelStack.poll();
        if (registration == null) {
            System.out.println("No cancellation to undo.");
            return;
        }
        if (activeCount(allRegistrations) >= CAPACITY) {
            registration.setStatus("waiting");
            waitingQueue.offerFirst(registration);
        } else {
            registration.setStatus("registered");
        }
        System.out.println("Undo cancellation: " + registration);
    }

    private static void promoteFirstWaiting(
        ArrayDeque<Registration> waitingQueue
    ) {
        Registration next = waitingQueue.poll();
        if (next == null) {
            System.out.println("Waiting queue is empty.");
            return;
        }
        next.setStatus("registered");
        System.out.println("Promote from waiting queue: " + next);
    }

    private static int activeCount(ArrayList<Registration> registrations) {
        int count = 0;
        for (Registration registration : registrations) {
            if ("registered".equals(registration.getStatus())) {
                count++;
            }
        }
        return count;
    }

    private static void printRegistrations(
        ArrayList<Registration> registrations
    ) {
        if (registrations.isEmpty()) {
            System.out.println("(empty)");
            return;
        }
        for (Registration registration : registrations) {
            System.out.println(registration);
        }
    }
}
