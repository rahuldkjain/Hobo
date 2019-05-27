package com.example.hoboandroid.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hoboandroid.R;
import com.example.hoboandroid.RetrofitClient;
import com.example.hoboandroid.models.ApiResponse;
import com.example.hoboandroid.models.LoginData;
import com.example.hoboandroid.services.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {


    private EditText email;
    private EditText password;
    private Button login,signup;
    private LoginData loginData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {

        email = findViewById(R.id.login_username);
        password = findViewById(R.id.login_password);
        login = findViewById(R.id.login_login_button);

        signup = findViewById(R.id.login_sign_up_button);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SignUpActivity.class));

                finish();
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


                loginData = new LoginData();
                loginData.setEmailId(email.getText().toString());
                loginData.setPassword(password.getText().toString());

                if (email.getText().toString().trim().matches(emailPattern))
                {
                        userVerify();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }




    private void userVerify() {

        Retrofit retrofit = RetrofitClient.getRetrofitClient();
        UserService api = retrofit.create(UserService.class);

        Log.d("ABCD", "getName api built");

        api.loginByEmailAndPassword(loginData)
                .enqueue(new Callback<ApiResponse>() {

                    @Override
                    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

                        Log.d("ABCD", "On response");

                        if(response.body()!=null) {

                            ApiResponse apiResponse = response.body();

                            Toast.makeText(getApplicationContext(), apiResponse.getMessage(),
                                    Toast.LENGTH_SHORT).show();
                            if(response.body().getMessage().equals("verification successful")){

                                SharedPreferences sharedPreferences = getSharedPreferences("Users",MODE_PRIVATE);

                                (sharedPreferences.edit()).putString("UserId",email.getText().toString()).commit();
                            }

                            finish();
                            //TODO
                            // GO to the profile screen or landing page
                            // and take response.body().getData().getEmailId()
                        }
                        else {
                            Log.e("ABCD","Response body is null");
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse> call, Throwable t) {
                        Log.e("ABCD", t.getMessage());
                        Log.e("ABCD", "Failure Occurred");

                    }
                });


    }
}

