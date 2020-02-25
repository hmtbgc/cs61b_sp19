public class MaxNumber {
    public static int max(int[] m) {
        int maxNumber = m[0];
        for (int i = 0; i < m.length; i++) {
            if (maxNumber < m[i]){
                maxNumber = m[i];
            }
        }
        return maxNumber;
    }

    public static void main(String[] args) {
       int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};  
       System.out.println(max(numbers));    
    }
 }