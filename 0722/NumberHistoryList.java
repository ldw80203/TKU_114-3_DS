public class NumberHistoryList {
    private Node head;
    private int size;
    private static class Node{
        int data;
        Node next;

        Node(int data){
            this.data = data;
            this.next = null;
        }
    } 
    public void addFirst(int value){
        Node newNode = new Node(value);
        newNode.next = head;
        head = newNode;
        size++;
    }   
    public void addLast(int value){
        Node newNode = new Node(value);
        Node current = head;
        if(head == null){
            head = newNode;
            size++;
            return;
        }
        while(current.next != null){
            current = current.next;
        }
        current.next = newNode;
        size ++;
    }
    public boolean contains(int target){
        Node current = head;
        while(current != null){
            if(current.data == target){
                return true;
            }
            current = current.next;
        }
        return false;
    }
    public boolean removeValue(int target){
        if(head == null){
            return false;
        }
        if(head.data == target){
            size--;
            head = head.next;
            return true;
        }
        Node previous = head;
        Node current = previous.next;

        while(current != null){
            if(current.data == target){
                previous.next = current.next;
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;

    }
    public void printList(){
        if(head == null){
            System.out.println("串列是空的");
            return;
        }
        Node current = head;
        while(current != null){
            System.out.print(current.data + "->");
            current = current.next;
        }
        System.out.println("null");
    }
    public int getSize(){
        return size;
    }
    public int getSum(){
        if(head == null){
            return 0;
        }
        int sum = 0;
        Node current = head;
        while(current != null){
            sum += current.data;
            current = current.next;
        }    
        return sum;
    }
    public Integer getMax(){
        if(head == null){
            return null;
        }
        int max = head.data;
        Node current = head.next;
        while(current != null){
            if(current.data > max){
                max = current.data;
            }
            current = current.next;
        }
        return max;
    }
    public Integer getMin(){
        if(head == null){
            return null;
        }
        int min = head.data;
        Node current = head.next;
        while(current != null){
            if(current.data < min){
                min = current.data;
            }
            current = current.next;
        }
        return min;
    }
    public static void main(String[] args){
        NumberHistoryList list = new NumberHistoryList();

        list.addLast(20);
        list.addLast(10);
        list.addFirst(10);
        list.addLast(30);

        System.out.println("是否包含 20：" + list.contains(20));
        System.out.println("刪除 5 是否成功：" + list.removeValue(5));

        list.addFirst(1);
        System.out.println("刪除 30 是否成功：" + list.removeValue(30));

        System.out.print("目前串列：");
        list.printList();
        System.out.println("筆數：" + list.getSize());
        System.out.println("總和：" + list.getSum());
        System.out.println("最大值：" + list.getMax());
        System.out.println("最小值：" + list.getMin());

        NumberHistoryList empty = new NumberHistoryList();
        System.out.println("空串列筆數：" + empty.getSize());
        System.out.println("空串列總和：" + empty.getSum());
        System.out.println("空串列最大值：" + empty.getMax());
        System.out.println("空串列最小值：" + empty.getMin());
    }

}
