package com.example.thriftandthrive.userAccount.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.thriftandthrive.R;
import com.example.thriftandthrive.api.ApiClient;
import com.example.thriftandthrive.api.response.LoginResponse;
import com.example.thriftandthrive.home.MainActivity;
import com.example.thriftandthrive.utils.SharedPrefUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment implements View.OnClickListener {
    EditText emailEt, passwordET;
    LinearLayout loginBut;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }


    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        emailEt = view.findViewById(R.id.emailET);
        passwordET = view.findViewById(R.id.passwordET);
        loginBut = view.findViewById(R.id.loginLL);
        loginBut.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if (validate()) {
            Call<LoginResponse> loginCall = ApiClient.getClient().login(emailEt.getText().toString(), passwordET.getText().toString());
            loginCall.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    LoginResponse loginResponse = response.body();
                    if (response.isSuccessful()) {
                        if(!response.body().getError()){
                            Toast.makeText(getActivity(), "You'" + "re successfully logged in", Toast.LENGTH_SHORT).show();
                            SharedPrefUtils.setString(getActivity(),getString(R.string.api_key),loginResponse.getApiKey());
                           // Toast.makeText(getActivity(),SharedPrefUtils.getString(getActivity(),getString(R.string.api_key)),Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }

//
                    }
                }


                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }

            });
        }

    }
    private boolean validate() {
        boolean validate = true;
        if (emailEt.getText().toString().isEmpty()
                || passwordET.getText().toString().isEmpty()
               ) {
            Toast.makeText(getActivity(), "None of the above fields can be empty", Toast.LENGTH_SHORT).show();
            validate = false;
        } else{

        }

        return validate;
    }


}



