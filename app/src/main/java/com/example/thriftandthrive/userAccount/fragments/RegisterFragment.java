package com.example.thriftandthrive.userAccount.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.thriftandthrive.R;
import com.example.thriftandthrive.api.ApiClient;
import com.example.thriftandthrive.api.response.RegisterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterFragment extends Fragment {
    EditText emailET, passwordET, confirmPassET, nameET;
    LinearLayout registerLL;
    ProgressBar circularProgress;


    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        emailET = view.findViewById(R.id.emailET);
        nameET = view.findViewById(R.id.nameET);
        passwordET = view.findViewById(R.id.passwordET);
        confirmPassET = view.findViewById(R.id.confirmPassET);
        circularProgress = view.findViewById(R.id.circularProgress);
        registerLL = view.findViewById(R.id.registerLL);
        registerLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    toggleLoading(true);
                    Call<RegisterResponse> registerCall = ApiClient.getClient().register(nameET.getText().toString(), emailET.getText().toString(), passwordET.getText().toString());
                    registerCall.enqueue(new Callback<RegisterResponse>() {
                        @Override
                        public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                            RegisterResponse registerResponse = response.body();
                            toggleLoading(false);
                            if (response.isSuccessful()) {
                                Toast.makeText(getActivity(), registerResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                if (!registerResponse.getError()) {
                                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.formContainerFL, new LoginFragment()).commit();

                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<RegisterResponse> call, Throwable t) {
                            toggleLoading(false);
                            Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });

                }
            }
        });

    }

    void toggleLoading(Boolean toogle) {
        if (toogle)
            circularProgress.setVisibility(View.VISIBLE);
        else
            circularProgress.setVisibility(View.GONE);
    }

    public boolean validate() {
        boolean validate = true;
        if (emailET.getText().toString().isEmpty()
                || passwordET.getText().toString().isEmpty()
                || confirmPassET.getText().toString().isEmpty()
                || nameET.getText().toString().isEmpty()) {
            Toast.makeText(getActivity(), "None of the above fields can be empty", Toast.LENGTH_SHORT).show();
            validate = false;
        } else if (!passwordET.getText().toString().equals(confirmPassET.getText().toString())) {
            confirmPassET.setError("Password doesnot match please check!!");
            validate = false;

        }

        return validate;
    }

}
//public class RegisterFragment extends Fragment {
//
//    EditText emailET, passwordET, confirmPassET, nameET;
//    LinearLayout registerLL;
//    ProgressBar circularProgress;
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_register, container, false);
//    }
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        emailET = view.findViewById(R.id.emailET);
//        nameET = view.findViewById(R.id.nameET);
//        passwordET = view.findViewById(R.id.passwordET);
//        confirmPassET = view.findViewById(R.id.confirmPassET);
//        circularProgress = view.findViewById(R.id.circularProgress);
//        registerLL = view.findViewById(R.id.registerLL);
//        registerLL.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                toggleLoading(true);
//                if (validate()) {
//                    Call<RegisterResponse> registerCall = ApiClient.getClient().register(nameET.getText().toString(), emailET.getText().toString(), passwordET.getText().toString());
//                    registerCall.enqueue(new Callback<RegisterResponse>() {
//                        @Override
//                        public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
//                            RegisterResponse registerResponse = response.body();
//                            if (response.isSuccessful()) {
//                                Toast.makeText(getActivity(), registerResponse.getMessage(), Toast.LENGTH_SHORT).show();
//                                if (!registerResponse.getError()) {
//                                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.formContainerFL, new LoginFragment()).commit();
//
//                                }
//                            }
//                        }
//
//
//                        @Override
//                        public void onFailure(Call<RegisterResponse> call, Throwable t) {
//                            toggleLoading(false);
//                            Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//                        }
//
//                    });
//                }
//
//            }
//        });
//    }
//    void toggleLoading(Boolean toogle) {
//        if (toogle)
//            circularProgress.setVisibility(View.VISIBLE);
//        else
//            circularProgress.setVisibility(View.GONE);
//    }
//
//    private boolean validate() {
//        boolean validate = true;
//        if (emailET.getText().toString().isEmpty()
//                || passwordET.getText().toString().isEmpty()
//                || confirmPassET.getText().toString().isEmpty()
//                || nameET.getText().toString().isEmpty()) {
//            Toast.makeText(getActivity(), "None of the above fields can be empty", Toast.LENGTH_SHORT).show();
//            validate = false;
//        } else if (!passwordET.getText().toString().equals(confirmPassET.getText().toString())) {
//            confirmPassET.setError("Password does not match please check!!");
//            validate = false;
//
//        }
//
//        return validate;
//    }
//
//}
