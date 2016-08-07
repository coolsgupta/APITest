package co.jmoon.roborium.apitest;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    private ProductData mProductData ;
    private int mP_id ;
    private ImageView mProductImage;
    private TextView mProductName;
    private TextView mProductPrice;
    private TextView mProductManufacturer;
    private TextView mProductDescription;
    private EditText mProductQuantity;
    private TextView mProductAvailability;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mP_id = 258;
        mProductData = new ProductData();


        mProductImage = (ImageView) findViewById(R.id.productImage);
        mProductName = (TextView) findViewById(R.id.productName);
        mProductPrice = (TextView) findViewById(R.id.productPrice);
        mProductManufacturer = (TextView) findViewById(R.id.productManufacturer);
        mProductDescription = (TextView) findViewById(R.id.productDescription);
        mProductAvailability = (TextView) findViewById(R.id.productAvailability);
        mProductQuantity = (EditText) findViewById(R.id.productQuantity);
        try {
            mProductData.setProduct(""+mP_id);
        } catch (JSONException | NullPointerException | InterruptedException | UnsupportedEncodingException e) {
            e.printStackTrace();
            Log.d("Error:product page : ",e.toString());
        }

        setProductPage();


    }

    public void order(View view){
        try {
            mProductData.setProduct(""+mP_id++);
        } catch (JSONException | InterruptedException | UnsupportedEncodingException e) {
            e.printStackTrace();
            Log.d("Error:product page : ",e.toString());
        }

        setProductPage();

    }

    public void setProductPage(){
        //mProductImage.setImageBitmap(BitmapFactory.decodeFile(mProductData.getImage()));
        mProductName.setText(mProductData.getName());
        mProductPrice.setText(mProductData.getPrice());
        mProductManufacturer.setText(mProductData.getManufacturer());
        mProductDescription.setText(mProductData.getDescription());

        if(mProductData.getAvailability().equals("out_of_stock")) {
            mProductAvailability.setText(mProductData.getAvailability());
            mProductAvailability.setVisibility(View.VISIBLE);
        }

        mProductData.setQuantity(String.valueOf(mProductQuantity.getText()));
    }
}

