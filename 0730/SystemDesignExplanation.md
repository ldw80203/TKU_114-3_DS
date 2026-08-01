# 資料結構選擇說明

本說明以 `0730` 的圖書、維修、活動報名與演算法比較程式為範圍。

| 功能 | 檔案與 method | 使用結構或演算法 | 選擇原因 | 未採用方法原因 |
|---|---|---|---|---|
| 保存全部書籍 | `LibraryManagementSystem.main` | `ArrayList<Book>` | 主資料需要可逐筆新增、可依索引排序，也方便交給 Merge Sort 處理。 | Queue 只適合先進先出，Stack 只適合最近一筆操作，不適合保存完整主資料。 |
| 依書籍編號排序 | `BookAlgorithms.mergeSortById` | Merge Sort | 編號排序後可搭配 Binary Search，且 Merge Sort 穩定、時間複雜度為 `O(n log n)`。 | Selection Sort 與 Insertion Sort 在大量資料 worst case 為 `O(n^2)`。 |
| 依書籍編號查詢 | `BookAlgorithms.binarySearchById` | Binary Search | 編號已升冪排序時，每次可排除一半資料，效率高。 | Sequential Search 不需要排序但要從頭找到尾，大量資料較慢。 |
| 依分類查詢全部書籍 | `BookAlgorithms.sequentialSearchByCategory` | Sequential Search | 分類可能有多筆結果，必須掃描全部資料才能收集完整清單。 | Binary Search 通常回傳單一鍵值位置，不適合直接找出全部同分類資料。 |
| 維修等待工作 | `RepairSchedulingSystem.registerTask` | Queue (`ArrayDeque.offer`) | 等待維修依登記順序處理，符合先進先出。 | Stack 會先處理最後登記者，不符合等待隊伍。 |
| 完成維修與復原 | `RepairSchedulingSystem.completeNext`、`undoLastCompletion` | Stack (`ArrayDeque.push` / `poll`) | 復原通常要處理最近完成的工作，Stack 的後進先出最符合。 | Queue 會復原最早完成的工作，不符合最近取消或最近完成的復原需求。 |
| 維修優先排序 | `RepairAlgorithms.mergeSortByPriorityDesc` | Stable Merge Sort | 優先等級相同時先登記者保持在前，穩定排序能保留原本順序。 | 不穩定的 Selection Sort 可能改變相同優先等級的登記順序。 |
| 活動候補 | `EventRegistrationSystem.register`、`promoteFirstWaiting` | Queue | 額滿後候補者應依加入候補的先後順序遞補。 | ArrayList 可做到但需要自行管理刪除第一筆，語意不如 Queue 清楚。 |
| 取消復原 | `EventRegistrationSystem.cancel`、`undoCancel` | Stack | 最近取消的報名最適合先復原。 | Sequential Search 可找到資料，但無法表示復原順序。 |
| 演算法比較 | `AlgorithmComparisonReport.*Comparisons` | Selection、Insertion、Merge Sort | 使用相同資料副本比較資料比較次數，能觀察不同輸入型態的差異。 | 單次毫秒數容易受電腦狀態影響，不能作為唯一判斷。 |
