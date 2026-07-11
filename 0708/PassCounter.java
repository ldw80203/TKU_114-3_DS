public class PassCounter {
    public static void main(String[] args) {
        int count = 0;
        int [] score = {80,55,70};

        for(int i = 0; i < score.length;i++){
            if(score[i]>= 60){
                count++;
            }
        }
        
        System.out.println("Pass count: " + count);
    }
    
}
