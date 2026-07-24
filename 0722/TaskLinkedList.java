public class TaskLinkedList {
    private TaskNode head;
    private int size;

    public boolean addFirst(String code, String description) {
        return addFirst(new TaskNode(code, description));
    }

    public boolean addFirst(TaskNode newNode) {
        if (!isValid(newNode) || findByCode(newNode.getCode()) != null) {
            return false;
        }

        newNode.setNext(head);
        head = newNode;
        size++;
        return true;
    }

    public boolean addLast(String code, String description) {
        return addLast(new TaskNode(code, description));
    }

    public boolean addLast(TaskNode newNode) {
        if (!isValid(newNode) || findByCode(newNode.getCode()) != null) {
            return false;
        }

        newNode.setNext(null);
        if (head == null) {
            head = newNode;
            size++;
            return true;
        }

        TaskNode current = head;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        current.setNext(newNode);
        size++;
        return true;
    }

    public TaskNode findByCode(String code) {
        if (isBlank(code)) {
            return null;
        }

        TaskNode current = head;
        while (current != null) {
            if (current.getCode().equalsIgnoreCase(code.trim())) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }

    public boolean completeTask(String code) {
        TaskNode task = findByCode(code);
        if (task == null || task.isCompleted()) {
            return false;
        }
        task.complete();
        return true;
    }

    public boolean removeTask(String code) {
        if (head == null || isBlank(code)) {
            return false;
        }

        if (head.getCode().equalsIgnoreCase(code.trim())) {
            head = head.getNext();
            size--;
            return true;
        }

        TaskNode previous = head;
        TaskNode current = head.getNext();
        while (current != null) {
            if (current.getCode().equalsIgnoreCase(code.trim())) {
                previous.setNext(current.getNext());
                size--;
                return true;
            }
            previous = current;
            current = current.getNext();
        }
        return false;
    }

    public int size() {
        return size;
    }

    public int countUnfinished() {
        int count = 0;
        TaskNode current = head;
        while (current != null) {
            if (!current.isCompleted()) {
                count++;
            }
            current = current.getNext();
        }
        return count;
    }

    public void printUnfinished() {
        TaskNode current = head;
        boolean found = false;
        while (current != null) {
            if (!current.isCompleted()) {
                System.out.println(current);
                found = true;
            }
            current = current.getNext();
        }

        if (!found) {
            System.out.println("沒有未完成工作");
        }
    }

    private boolean isValid(TaskNode node) {
        return node != null && !isBlank(node.getCode()) && !isBlank(node.getDescription());
    }

    private boolean isBlank(String text) {
        return text == null || text.trim().isEmpty();
    }
}
