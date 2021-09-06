package Carlos.Meneses.leal_app;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Data {

    public static HttpURLConnection getConnection(String url) {
        System.out.println("URL: "+ url);
        HttpURLConnection con = null;
        try {
            con=(HttpURLConnection)new URL(url).openConnection();
            con.setReadTimeout(60000);
            con.setRequestProperty("User-Agent", "Carlos");
        } catch (MalformedURLException e) {
            Log.e("GetConnection ()", "Invalid URL: " + e.toString());
        } catch (IOException e) {
            Log.e("GetConnection ()", "Could not connect: " + e.toString());
        }
        return con;
    }

    public static String readContents (String url) {
        HttpURLConnection con = getConnection(url);
        if (con == null) return null;
        try {
            StringBuilder sb = new StringBuilder(8192);
            String tp;
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            con.getInputStream()
                    )
            );
            while ((tp = br.readLine())!= null)
                sb.append(tp).append("\n");
            br.close();
            return sb.toString();
        } catch (IOException e) {
            Log.d("Read Failed!", e.toString());
            return null;
        }
    }
}
