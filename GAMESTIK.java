import java.util.Scanner; // membaca input pengguna
import java.util.Random; // menghasilkan angka acak

public class GAMESTIK {

    // Jumlah stick awal random (10-30)
    public static int generateInitialSticks() {
        Random stick = new Random();
        // Random : kelas; stick : nama var; new Random : membuat objek baru
        return stick.nextInt(21) + 10; 
        // stick.nextInt(21) : ambil angka acak dari 0 - (n-1); makannya + 10
    }
    
    // stick 1–3, dan tidak boleh lebih dari stick yang tersisa
    public static boolean userInput(int input, int sisaStik) {
        if (input >= 1 && input <= 3 && input <= sisaStik) {
            return true;
        }
        return false;
    }
    
    // menentukan langkah komputer
    public static int computerMove(int sisaStik) {
        // memastikan komputer menang
        // komputer selalu membuat sisa stick jadi kelipatan 4 untuk lawan
        int strategiComp = (sisaStik - 1) % 4;
        
        if (strategiComp == 0) {
            // Jika tidak ada langkah optimal, ambil 1
            return 1;
        } else {
            return strategiComp;
        }
    }
    
    // mengecek apakah permainan berakhir
    public static boolean isGameOver(int sisaStik, boolean isUserTurn) {
        if (sisaStik == 0) {
            if (isUserTurn) {
                System.out.println("\nAnda mengambil stick terakhir. Anda kalah!");
            } else {
                System.out.println("\nKomputer mengambil stick terakhir. Komputer kalah!");
            }
            return true;
        }
        return false;//sisaStik != 0
    }
    
    // Fungsi utama permainan
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalSticks = generateInitialSticks();
        //memanggil fungsi generateInitialSticks(), yang hasilnya disimpan di totalSticks
        boolean userTurn = true; // menentukan giliran— user mulai duluan.
        
        System.out.println("==================== STICK GAME =====================");
        System.out.println("Aturan Main:");
        System.out.println("- Jumlah stick awal: " + totalSticks);
        System.out.println("- Anda dan komputer bergantian mengambil stick");
        System.out.println("- Setiap pemain bisa mengambil 1-3 stick per giliran");
        System.out.println("- Pemain yang mengambil stick terakhir KALAH");
        System.out.println("=====================================================");
        
        while (totalSticks > 0) {
            System.out.println("\nStick tersisa: " + totalSticks);
            
            if (userTurn) {
                // Giliran user
                System.out.print("Ambil stick (1-3): ");
                int userTake = scanner.nextInt();
                
                // Validasi input
                while (!userInput(userTake, totalSticks)) {
                    System.out.print("Input tidak valid. Ambil stick (1-3): ");
                    userTake = scanner.nextInt();
                }
                
                totalSticks -= userTake;
                //Jumlah stik dikurangi sesuai pilihan user.
                System.out.println("Anda mengambil " + 
                userTake + " stick.");
                
                // Cek jika game berakhir
                if (isGameOver(totalSticks, userTurn)) {
                    break;
                }
            } else {
                // Giliran komputer
                int computerTake = computerMove(totalSticks);
                totalSticks -= computerTake;
                //Jumlah stik dikurangi sesuai pilihan komputer.
                System.out.println("Komputer mengambil " + 
                computerTake + " stick.");
                
                // Cek jika game berakhir
                if (isGameOver(totalSticks, userTurn)) {
                    break;
                }
            }
            
            userTurn = !userTurn; // Ganti giliran
        }
        
        scanner.close();
    }
}