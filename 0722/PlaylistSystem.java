public class PlaylistSystem {
    public static void main(String[] args) {
        PlaylistLinkedList playlist = new PlaylistLinkedList();

        System.out.println("加入 S01：" + playlist.addLast("S01", "晴天"));
        System.out.println("加入 S02：" + playlist.addLast("S02", "稻香"));
        System.out.println("加入 S03：" + playlist.addLast("S03", "夜曲"));
        System.out.println("重複加入 S02：" + playlist.addLast("S02", "另一首歌"));
        System.out.println("空白代碼：" + playlist.addLast("", "沒有代碼的歌曲"));

        System.out.println("目前播放順序：");
        playlist.printList();
        System.out.println("搜尋 S02：" + playlist.findByCode("S02"));

        System.out.println("刪除第一首 S01：" + playlist.removeByCode("S01"));
        System.out.println("刪除最後一首 S03：" + playlist.removeByCode("S03"));
        System.out.println("刪除不存在的 S99：" + playlist.removeByCode("S99"));

        System.out.println("刪除後播放順序：");
        playlist.printList();
        System.out.println("歌曲數量：" + playlist.size());
    }
}
