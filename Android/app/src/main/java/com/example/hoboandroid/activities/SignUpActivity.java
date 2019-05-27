package com.example.hoboandroid.activities;

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
import com.example.hoboandroid.models.Data;
import com.example.hoboandroid.services.UserService;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignUpActivity extends AppCompatActivity {


    EditText emailId, name, phoneNumber, gender,dateOfBirth,
            address1, address2, city, state, pincode,password, confPass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Button signUpButton = findViewById(R.id.signup_button);


        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getUserData();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if(!(name.getText().toString().length()==0
                        || gender.getText().toString().length()==0
                        || dateOfBirth.getText().toString().length()==0
                        || address1.getText().toString().length()==0
                        || address2.getText().toString().length()==0
                        || city.getText().toString().length()==0
                        || state.getText().toString().length()==0
                        || pincode.getText().toString().length()==0)) {


                    if (emailId.getText().toString().trim().matches(emailPattern)) {

                        if (password.getText().toString().length() >= 6) {

                            if(password.getText().toString().equals(confPass.getText().toString())) {


                                init();

                            }
                            else{
                                Toast.makeText(getApplicationContext(), "Passwords don't match",
                                        Toast.LENGTH_SHORT).show();
                            }

                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Password size should be >8",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Invalid email address",
                                Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(getApplicationContext(), "Cannot leave fields empty",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    private void getUserData(){

        emailId = findViewById(R.id.signup_userEmail);
        name = findViewById(R.id.signup_userName);
        password = findViewById(R.id.signup_passWord);
        confPass = findViewById(R.id.signup_confirmPass);
        phoneNumber  =findViewById(R.id.signup_phone);
        dateOfBirth = findViewById(R.id.signup_dob);
        gender = findViewById(R.id.signup_gender);
        city = findViewById(R.id.signup_city);
        address1 = findViewById(R.id.signup_address1);
        address2 = findViewById(R.id.signup_address2);
        state = findViewById(R.id.signup_state);
        pincode = findViewById(R.id.signup_pincode);


    }

    private void init(){


        Retrofit retrofit = RetrofitClient.getRetrofitClient();
        UserService api = retrofit.create(UserService.class);

        Data user = new Data();
        user.setAddress1(address1.getText().toString());
        user.setAddress2(address2.getText().toString());
        user.setCity(city.getText().toString());
        //user.setDateOfBirth(Date.valueOf(dateOfBirth.getText().toString()));
        user.setEmailId(emailId.getText().toString());
        user.setGender(gender.getText().toString());
        user.setName(name.getText().toString());
        user.setPassword(password.getText().toString());
        user.setPhoneNumber(Long.parseLong(phoneNumber.getText().toString()));
        user.setState(state.getText().toString());
        user.setPincode(Integer.parseInt(pincode.getText().toString()));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = (Date) sdf.parse(dateOfBirth.getText().toString());
            user.setDateOfBirth(date);
        } catch (ParseException e) {
            user.setDateOfBirth(null);
            e.printStackTrace();
        }

        Log.d("SignUp",user.toString());

        api.signUp(user).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

                Log.d("SignUp", "CartItem : " + response.toString());
                if(response.body()==null){
                    Log.e("SignUp","Body is null");
                    Toast.makeText(getApplicationContext(),"Please fill details correctly", Toast.LENGTH_SHORT).show();

                }
                else {
                    if(response.body().getData().equals("adding successful")) {
                        Log.d("SignUp", "CartItem Body : " + response.body().toString());
                        Toast.makeText(getApplicationContext(), "Please login now to continue", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                    }


                    //TODO
                    // GO to the profile screen or landing page
                    // and take response.body().getData().getEmailId()
                }
                finish();
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

                Log.d("SignUp", "Failure Occurred ");
            }
        });

    }
}
