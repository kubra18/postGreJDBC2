import java.sql.*;

public class PreparedStatement01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //bu bir interfacedir.
        /*
        Birden cok kez calistirilabilen onceden derlenmis  bir SQL kodunu temsil eder.
        Parameterelendirilmis SQL sorgulari(Query) ile calisir. ( parametrize
        Bu sorguyu sifir veya daha fazla parametre ile kullanabiliriz.
         */

        //1. drivera kaydol
        Class.forName("org.postgresql.Driver");
        //2 database baglan
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed", "postgres", "18101989ksm");
        //3. statement olusturma
        Statement st = con.createStatement();// buna gerek yok ama tekrar okumak icin lazim olabilir.


        //1. Örnek: Prepared statement kullanarak company adı IBM olan number_of_employees değerini 9999 olarak güncelleyin.
        // 1. Adim PreparedStatement query'sini olustur.
        // 9999 yerine ve IBM yerine ? koyarak buralari parametrize etmis olacagiz istedgimizi koyacagiz
        String sql1 = " update companies set  number_of_employees = ?  where company =?;";

        // 2. ADim prepared statement objesini olustur.
        PreparedStatement pst1 = con.prepareStatement(sql1);

        // 3. ADim  soru isaretleri yerine deger atayacagiz.  set kullaniyoruz  setInt - setString - data tipine gore. ? leri set edecegiz.
        pst1.setInt(1, 9999);// 1. parametremi ? lerin birincisi
        pst1.setString(2, "IBM");

        // 4. Adim query i calistir.
        int guncellenenSatirSayisi = pst1.executeUpdate();
        System.out.println("guncellenenSatirSayisi = " + guncellenenSatirSayisi);

        String sql2 = " select * from companies";
        ResultSet rs = st.executeQuery(sql2);

        while (rs.next()) {
            System.out.println(rs.getInt(1) + "-------->" +
                    rs.getString(2) + "--------" +
                    rs.getInt(3));
        }


//2. Örnek: Prepared statement kullanarak company adı GOOGLE olan number_of_employees değerini 5555 olarak güncelleyin.

        String sql3 = " update companies set number_of_employees = ? wherecompany = ?";
        pst1.setInt(1, 5555);
        pst1.setString(2, "GOOGLE");

        int guncellenenSatirSayisi2 = pst1.executeUpdate();
        System.out.println("guncellenenSatirSayisi = " + guncellenenSatirSayisi2);
        st.close();
        con.close();


    }
}