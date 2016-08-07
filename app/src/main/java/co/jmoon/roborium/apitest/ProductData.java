package co.jmoon.roborium.apitest;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class ProductData {

    private JSONObject mProduct = new JSONObject();
    private String mQuantity;

    public JSONObject getProduct() {
        return mProduct;
    }







    public void setProduct(final String p_id) throws JSONException, UnsupportedEncodingException, InterruptedException {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final BufferedReader[] reader = {null};
                    final String data = URLEncoder.encode("id", "UTF-8")
                            + "=" + URLEncoder.encode(p_id, "UTF-8");

                    // Defined URL  where to send data
                    URL url = new URL("http://delhimaker.space/index.php?route=api/order/single");

                    // Send POST data request

                    URLConnection conn = url.openConnection();
                    conn.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                    wr.write(data);
                    wr.flush();

                    // Get the server response

                    reader[0] = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder sb = new StringBuilder();

                    // Read Server Response

                    String line;
                    while ((line = reader[0].readLine()) != null) {
                        // Append server response in string
                        sb.append(line).append("\n");
                    }


                    String text = sb.toString();



                    mProduct = new JSONObject(text);
                    Log.d("JSON DATA",text + "\n"+ mProduct.getString("name"));


                    getName();
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                    Log.d("Error:product data : ", e.toString());
                }
            }
        });
        thread.start();
        Log.d("JSON DATA", mProduct.getString("name"));
    }


    public String getName(){
        try {
            Log.d("JSON DATA", mProduct.getString("name"));
            return mProduct.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getImage() {

        try {
            return mProduct.getString("image");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getPrice() {
        try {
            return mProduct.getString("price");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getManufacturer() {
        try {
            return mProduct.getString("manufacturer");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getDescription() {
        try {
            return mProduct.getString("description");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getAvailability() {
        try {
            return mProduct.getString("stock_status");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setQuantity(String quantity) {
        mQuantity = quantity;
    }

    public String getQuantity() {
        return mQuantity;
    }
}