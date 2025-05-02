public class ativi6 {
     public static void main(String[] args) {
            int a = 0;
            int b = 1;
    
            System.out.println("Série de Fibonacci até passar de 100:");
    
            while (a <= 100) {
                System.out.print(a + " ");
                int proximo = a + b;
                a = b;
                b = proximo;
            }
    } 
}
