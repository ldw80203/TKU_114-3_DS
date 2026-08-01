# 完整測試紀錄

| 編號 | 輸入 | 操作 | 預期結果 | 實際結果 | 狀態 |
|---|---|---|---|---|---|
| 1 | 空書籍清單 | `BookAlgorithms.binarySearchById(books, "B001")` | 回傳 `null` | 回傳 `null` | 通過 |
| 2 | 書籍 `B003,B001,B004,B002` | `BookAlgorithms.mergeSortById` | 編號升冪 `B001,B002,B003,B004` | 符合 | 通過 |
| 3 | 書籍借閱數 `15,28,8,36` | `BookAlgorithms.mergeSortByBorrowCountDesc` | 借閱次數降冪 | 符合 | 通過 |
| 4 | 重複書籍編號 `B002` | `LibraryManagementSystem.addBook` | 拒絕新增 | 顯示 `Reject duplicate id` | 通過 |
| 5 | 已排序書籍 | `BookAlgorithms.binarySearchById(books, "B003")` | 找到 `B003 Java Basics` | 符合 | 通過 |
| 6 | 已排序書籍 | `BookAlgorithms.binarySearchById(books, "B999")` | 找不到，回傳 `null` | 符合 | 通過 |
| 7 | 分類 `Programming` | `BookAlgorithms.sequentialSearchByCategory` | 找出全部 Programming 書籍 | 符合 | 通過 |
| 8 | 維修 `R001,R002,R003,R004` | `RepairSchedulingSystem.completeNext` | 第一筆等待工作變 completed | 符合 | 通過 |
| 9 | 已完成兩筆維修 | `RepairSchedulingSystem.undoLastCompletion` | 最近完成工作回到等待 Queue | 符合 | 通過 |
| 10 | 維修優先級 `2,5,5,3` | `RepairAlgorithms.mergeSortByPriorityDesc` | 優先級降冪，兩筆 5 保持登記順序 | 符合 | 通過 |
| 11 | 維修設備 `Printer` | `RepairAlgorithms.searchByEquipmentName` | 找到兩筆 Printer | 符合 | 通過 |
| 12 | 16、128、1024 筆資料 | `AlgorithmComparisonReport.main` | 輸出三種演算法比較表 | 符合 | 通過 |
| 13 | 活動容量 3、第四筆報名 | `EventRegistrationSystem.register` | 第四筆進候補 Queue | 符合 | 通過 |
| 14 | 重複報名 `A004` | `EventRegistrationSystem.register` | 拒絕重複編號 | 顯示 `Reject duplicate registration id` | 通過 |
| 15 | 取消不存在 `A999` | `EventRegistrationSystem.cancel` | 顯示不能取消 | 符合 | 通過 |
| 16 | 取消後有候補 | `EventRegistrationSystem.cancel` | 第一位候補遞補為 registered | 符合 | 通過 |
| 17 | 最近取消紀錄 | `EventRegistrationSystem.undoCancel` | 最近取消者復原，若額滿則回候補 | 符合 | 通過 |
| 18 | 空取消 Stack | `EventRegistrationSystem.undoCancel` | 顯示沒有可復原取消 | 手動檢查 method 分支符合 | 通過 |

目前沒有未通過項目，因此無修正與重測紀錄。
