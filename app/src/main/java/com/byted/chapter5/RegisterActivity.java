package com.byted.chapter5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        EditText eName = findViewById(R.id.name);
        EditText ePassword = findViewById(R.id.password);
        EditText eRepassword = findViewById(R.id.repassword);
        Button register = findViewById(R.id.register);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://wanandroid.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = eName.getText().toString();
                String password = ePassword.getText().toString();
                String repassword = eRepassword.getText().toString();
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(password) || TextUtils.isEmpty(repassword)) {
                    Toast.makeText(RegisterActivity.this, "参数不合法", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!TextUtils.equals(password, repassword)) {
                    Toast.makeText(RegisterActivity.this, "密码不相等", Toast.LENGTH_SHORT).show();
                    return;
                }
                // todo 做网络请求
                Call<UserResponse> call = apiService.postArticles(name,password,repassword);
                call.enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                        //String mText = response.body().articles.toString();
                        Log.d("回复的数据", response.toString());
                        Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {
                        Log.e("请求失败：",t.toString());
                    }
                });

            }
        });
    }
}
