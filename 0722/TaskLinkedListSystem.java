public class TaskLinkedListSystem {
    public static void main(String[] args) {
        TaskLinkedList tasks = new TaskLinkedList();

        System.out.println("加入一般工作 T01：" + tasks.addLast("T01", "完成報告"));
        System.out.println("加入一般工作 T02：" + tasks.addLast("T02", "整理資料"));
        System.out.println("加入緊急工作 T03：" + tasks.addFirst("T03", "修正錯誤"));
        System.out.println("加入重複代碼 T02：" + tasks.addLast("T02", "重複工作"));
        System.out.println("加入空白工作：" + tasks.addLast("", "沒有代碼"));

        System.out.println("完成 T03：" + tasks.completeTask("T03"));
        System.out.println("再次完成 T03：" + tasks.completeTask("T03"));
        System.out.println("完成不存在的 T99：" + tasks.completeTask("T99"));

        System.out.println("刪除 T02：" + tasks.removeTask("T02"));
        System.out.println("刪除不存在的 T99：" + tasks.removeTask("T99"));

        System.out.println("未完成工作：");
        tasks.printUnfinished();
        System.out.println("工作總數：" + tasks.size());
        System.out.println("未完成數量：" + tasks.countUnfinished());
    }
}
