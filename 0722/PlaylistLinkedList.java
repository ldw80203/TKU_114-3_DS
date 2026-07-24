public class PlaylistLinkedList {
    private PlaylistNode head;
    private int size;

    public boolean addLast(String code, String name) {
        return addLast(new PlaylistNode(code, name));
    }

    public boolean addLast(PlaylistNode newNode) {
        if (newNode == null || isBlank(newNode.getCode()) || isBlank(newNode.getName())
                || findByCode(newNode.getCode()) != null) {
            return false;
        }

        newNode.setNext(null);
        if (head == null) {
            head = newNode;
            size++;
            return true;
        }

        PlaylistNode current = head;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        current.setNext(newNode);
        size++;
        return true;
    }

    public PlaylistNode findByCode(String code) {
        if (isBlank(code)) {
            return null;
        }

        PlaylistNode current = head;
        while (current != null) {
            if (current.getCode().equalsIgnoreCase(code.trim())) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }

    public boolean removeByCode(String code) {
        if (head == null || isBlank(code)) {
            return false;
        }

        if (head.getCode().equalsIgnoreCase(code.trim())) {
            head = head.getNext();
            size--;
            return true;
        }

        PlaylistNode previous = head;
        PlaylistNode current = head.getNext();
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

    public boolean isEmpty() {
        return head == null;
    }

    public void printList() {
        if (head == null) {
            System.out.println("播放清單是空的");
            return;
        }

        PlaylistNode current = head;
        while (current != null) {
            System.out.print(current + " -> ");
            current = current.getNext();
        }
        System.out.println("null");
    }

    private boolean isBlank(String text) {
        return text == null || text.trim().isEmpty();
    }
}
