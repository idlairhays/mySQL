package belajar.java.mysql;

// import bumbu-bumbu yang diperlukan
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.io.BufferedReader;
import java.io.InputStreamReader;

// bisa juga diimpor sekaligus seperti ini:
// import java.sql.*


public class Aplikasi2 {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
static final String DB_URL = "jdbc:mysql://localhost/universitas";
static final String USER = "root";
static final String PASS = "";

static Connection conn;
static Statement stmt;
static ResultSet rs;

static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
static BufferedReader input = new BufferedReader(inputStreamReader);
    // Menyiapkan paramter JDBC untuk koneksi ke datbase

    // Menyiapkan objek yang diperlukan untuk mengelola database
public static void main(String[] args) {

    try {
        // register driver
        Class.forName(JDBC_DRIVER);

        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        stmt = conn.createStatement();

        while (!conn.isClosed()) {
            showMenu();
        
        }

        stmt.close();
        conn.close();

    } catch (Exception e) {
        e.printStackTrace();
    }
}
    static void showMenu() {
    System.out.println("\n========= MENU UTAMA =========");
    System.out.println("1. Insert Data");
    System.out.println("2. Show Data");
    System.out.println("3. Edit Data");
    System.out.println("4. Delete Data");
    System.out.println("0. Keluar");
    System.out.println("");
    System.out.print("PILIHAN> ");

    try {
        int pilihan = Integer.parseInt(input.readLine());

        switch (pilihan) {
            case 0:
                System.exit(0);
                break;
            case 1:
                insertMahasiswa();
                break;
            case 2:
                showData();
                break;
            case 3:
                updateMahasiswa();
                break;
            case 4:
                deleteMahasiswa();
                break;
            default:
                System.out.println("Pilihan salah!");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    static void showData() {
    String sql = "SELECT * FROM mahasiswa";
    try {
        rs = stmt.executeQuery(sql);
        
        System.out.println("+--------------------------------+");
        System.out.println("|    DATA MAHASISWA DI UNIVERSITAS   |");
        System.out.println("+--------------------------------+");
        while (rs.next()) {
            
            int idMhs = rs.getInt("id_mahasiswa");
            String nama = rs.getString("nama");
            String alamat = rs.getString("alamat");
            String nama_orangtua = rs.getString("nama_orangtua");
            String jk = rs.getString("jenis_kelamin");
            
            System.out.println(String.format("%d. %s -- (%s),(%s),(%s)", idMhs, nama, alamat, nama_orangtua, jk));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    static void insertMahasiswa() {
    try {
        // ambil input dari user
        System.out.print("Nama: ");
        String nama = input.readLine().trim();
        System.out.print("Alamat: ");
        String alamat = input.readLine().trim();
        System.out.print("Nama Orangtua: ");
        String nama_ortu = input.readLine().trim();
        System.out.print("Jenis Kelamin: ");
        String jk = input.readLine().trim();
        
        
        
        
        // query simpan
        String sql = "INSERT INTO mahasiswa (nama, alamat, nama_ortu, jk) VALUE('%s', '%s')";
        sql = String.format(sql, nama, alamat, nama_ortu, jk);
        
        // simpan mahasiswa
        stmt.execute(sql);
        
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    static void updateMahasiswa() {
    try {  
        // ambil input dari user
        System.out.print("ID yang mau diedit: ");
        int idMhs = Integer.parseInt(input.readLine());
        System.out.print("Nama: ");
        String nama = input.readLine().trim();
        System.out.print("Alamat: ");
        String alamat = input.readLine().trim();
        System.out.print("Nama Orangtua: ");
        String nama_ortu = input.readLine().trim();
        System.out.print("Jenis Kelamin: ");
        String jk = input.readLine().trim();
        // query update
        String sql = "UPDATE mahasiswa SET nama='%s', alamat='%s', nama_ortu='%s', jk='%s' WHERE idMhs=%d";
        sql = String.format(sql, nama, alamat, nama_ortu, jk, idMhs);

        // update data mahasiswa
        stmt.execute(sql);
        
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    static void deleteMahasiswa() {
    try {
        
        // ambil input dari user
        System.out.print("ID yang mau dihapus: ");
        int idMhs = Integer.parseInt(input.readLine());
        
        // buat query hapus
        String sql = String.format("DELETE FROM mahasiswa WHERE idMhs=%d", idMhs);
        // hapus data
        stmt.execute(sql);
        
        System.out.println("Data telah terhapus...");
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    
}