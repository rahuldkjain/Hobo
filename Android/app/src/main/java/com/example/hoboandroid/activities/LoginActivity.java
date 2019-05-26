package com.example.hoboandroid.activities;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hoboandroid.Api;
import com.example.hoboandroid.R;
import com.example.hoboandroid.models.ApiResponse;
import com.example.hoboandroid.models.User;
import com.example.hoboandroid.models.category.Category;
import com.example.hoboandroid.services.ProductService;
import com.example.hoboandroid.services.UserService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText username,password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.login_username);
        password = findViewById(R.id.login_password);
        login = findViewById(R.id.login);



        login.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        //TODO user login preferences
/*        if(username.getText().toString().equals("")|| password.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"Enter valid login details",Toast.LENGTH_LONG).show();
        }
        else{

            if(isLoggedIn()){
                Retrofit retrofit = Api.getclient(getResources().getString(R.string.user_host_address),"product/listcategory/");

                UserService service = retrofit.create(UserService.class);


                service.loginByEmailAndPassword(username.getText().toString(),password.getText().toString())
                        .enqueue(new Callback<ApiResponse<User>>() {
                    @Override
                    public void onResponse(Call<ApiResponse<User>> call, Response<ApiResponse<User>> response) {

                        //TODO email and password entered aren't valid
                        if (response.body() != null) {
                            response.body().getMessage() != "verification fail,successful"
                            //sharedPreferences.edit().putString("userId",response.body().getData().getUserId());
                            //SharedPreferences.Editor sharedPrefEditor = sharedPreferences.edit();

                        }

                        SharedPreferences cartPreferences = getSharedPreferences("Cart",MODE_PRIVATE);

                        //TODO if the cart items are present and should be added to his cart, else check every time going into cart for the user presence or show no items

                    }
                    @Override
                    public void onFailure(Call<ApiResponse<User>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Check your connection", Toast.LENGTH_LONG).show();
                        Log.d("Login Activity", t.getMessage() + " failure");
                    }// happens when api is not able to be connect or getting any response(even a failure response is called a response)
                });
            }
            else{
                Log.d("LoginActivity","User already logged in");
                Toast.makeText(getApplicationContext(),"Already Logged in",Toast.LENGTH_LONG).show();
            }


        }
*/

    }

}
