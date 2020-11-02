package ai.bisa.webinarbatch3.Login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import ai.bisa.webinarbatch3.Home.HomeActivity;
import ai.bisa.webinarbatch3.R;
import ai.bisa.webinarbatch3.Server.AppController;

public class LoginActivity extends AppCompatActivity {
    EditText email,password;
    CardView login,register;
    ProgressDialog pDialog;

    int success;
    ConnectivityManager conMgr;

    private static final String TAG = "Login";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    public final static String TAG_PASSWORD = "password";
    public final static String TAG_TOKEN= "token";
    public final static String TAG_NAMA = "nama";
    public final static String TAG_ID = "user_id";
    public final static String TAG_PHONE = "phone";
    public final static String TAG_PHOTO_PROFILE = "photo_profile";
    public final static String TAG_EMAIL = "email";
    public final static String TAG_ALAMAT = "alamat";
    String tag_json_obj = "json_obj_req";

    SharedPreferences sharedpreferences;
    Boolean session = false;
    String id, nama,phone,emailstr,alamat,passwordstr,photo_profile,tokenstr ;
    public static final String my_shared_preferences = "my_shared_preferences";
    public static final String session_status = "session_status";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("Login");
        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        {
            if (conMgr.getActiveNetworkInfo() != null
                    && conMgr.getActiveNetworkInfo().isAvailable()
                    && conMgr.getActiveNetworkInfo().isConnected()) {
            } else {
                Toast.makeText(getApplicationContext(), "No Internet Connection",
                        Toast.LENGTH_LONG).show();
            }
        }
        email = findViewById(R.id.TFemail);
        password = findViewById(R.id.TFpassword);
        passwordstr = password.getText().toString();
        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        session = sharedpreferences.getBoolean(session_status, false);
        id = sharedpreferences.getString(TAG_ID, null);
        nama = sharedpreferences.getString(TAG_NAMA, null);
        emailstr = sharedpreferences.getString(TAG_EMAIL, null);
        alamat = sharedpreferences.getString(TAG_ALAMAT, null);
        photo_profile = sharedpreferences.getString(TAG_PHOTO_PROFILE,null);
        phone = sharedpreferences.getString(TAG_PHONE, null);
        passwordstr = sharedpreferences.getString(TAG_PASSWORD,null);
        tokenstr = sharedpreferences.getString(TAG_TOKEN,null);
        if (session) {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra(TAG_ID, id);
            intent.putExtra(TAG_NAMA, nama);
            intent.putExtra(TAG_EMAIL, emailstr);
            intent.putExtra(TAG_ALAMAT, alamat);
            intent.putExtra(TAG_PHOTO_PROFILE,photo_profile);
            intent.putExtra(TAG_PHONE, phone);
            intent.putExtra(TAG_PASSWORD,passwordstr);
            intent.putExtra(TAG_TOKEN,tokenstr);
            finish();
            startActivity(intent);
        }

        login = findViewById(R.id.Blogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (conMgr.getActiveNetworkInfo() != null
                        && conMgr.getActiveNetworkInfo().isAvailable()
                        && conMgr.getActiveNetworkInfo().isConnected()) {
                    checkLogin(email.getText().toString(),password.getText().toString());
                } else {
                    Toast.makeText(getApplicationContext(), "No Internet Connection",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
//        register = findViewById(R.id.Bregister);
//        register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getApplicationContext(),Register.class);
//                startActivity(i);
//            }
//        });
    }
    private void checkLogin(final String email, final String passwordstr) {
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Logging in ...");
        showDialog();
        String url = "https://tokoelektronikokta.000webhostapp.com/android/user.php?apicall=md5(login)";
        StringRequest strReq = new StringRequest(Request.Method.POST, url, response -> {
            Log.e(TAG, "Login Response: " + response);
            hideDialog();

            try {
                JSONObject jObj = new JSONObject(response);
                success = jObj.getInt(TAG_SUCCESS);

                // Check for error node in json
                if (success == 1) {
                    String nama = jObj.getString(TAG_NAMA);
                    String id = jObj.getString(TAG_ID);
                    String phone = jObj.getString(TAG_PHONE);
                    String photoprofile = jObj.getString(TAG_PHOTO_PROFILE);
                    String email1 = jObj.getString(TAG_EMAIL);
                    String alamat = jObj.getString(TAG_ALAMAT);
                    String token = jObj.getString(TAG_TOKEN);
                    Log.e("Successfully Login!", jObj.toString());
                    String passwordstr1 = password.getText().toString();
                    Toast.makeText(getApplicationContext(), jObj.getString(TAG_MESSAGE),
                            Toast.LENGTH_SHORT).show();
                    // menyimpan login ke session
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putBoolean(session_status, true);
                    editor.putString(TAG_PASSWORD, passwordstr1);
                    editor.putString(TAG_NAMA,nama);
                    editor.putString(TAG_PHOTO_PROFILE,photoprofile);
                    editor.putString(TAG_ID, id);
                    editor.putString(TAG_PHONE, phone);
                    editor.putString(TAG_EMAIL, email1);
                    editor.putString(TAG_ALAMAT, alamat);
                    editor.putString(TAG_TOKEN,token);
                    editor.apply();

                    // Memanggil main activity
                    Intent intent = new Intent(this, HomeActivity.class);
                    intent.putExtra(TAG_ID, id);
                    intent.putExtra(TAG_PASSWORD, passwordstr1);
                    intent.putExtra(TAG_NAMA, nama);
                    intent.putExtra (TAG_PHONE, phone);
                    intent.putExtra(TAG_PHOTO_PROFILE,photoprofile);
                    intent.putExtra(TAG_EMAIL, email1);
                    intent.putExtra(TAG_ALAMAT, alamat);
                    intent.putExtra(TAG_TOKEN, token);
                    finish();
                    startActivity(intent);
                } else {
                   Toast.makeText(getApplicationContext(),
                            jObj.getString(TAG_MESSAGE), Toast.LENGTH_SHORT).show();

                }
            } catch (JSONException e) {
                // JSON error
                e.printStackTrace();
            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();

                hideDialog();
            }


        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email );
                params.put("password", passwordstr);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);

    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}