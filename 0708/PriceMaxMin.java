public class PriceMaxMin {
    public static void main(String[] args){
        int[] pricevalue = {80,55,60};
        
        int max = pricevalue[0];
        int min = pricevalue[0];
        
        for (int i = 1;i<pricevalue.length;i++){
            if(pricevalue[i]>max){
                max = pricevalue[i];
            }
            if(pricevalue[i]<min){
                min = pricevalue[i];
            }
        }
        System.out.println("Highest: "+max);
        System.out.println("lowerst: "+min);


    }
}
