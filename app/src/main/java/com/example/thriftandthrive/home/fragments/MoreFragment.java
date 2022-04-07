package com.example.thriftandthrive.home.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.thriftandthrive.R;
import com.example.thriftandthrive.more.AboutUsActivity;
import com.example.thriftandthrive.more.ContactusActivity;
import com.example.thriftandthrive.more.ProfileActivity;
import com.example.thriftandthrive.more.TermsandConditionActivity;
import com.example.thriftandthrive.userAccount.UserAccountActivity;
import com.example.thriftandthrive.utils.SharedPrefUtils;

public class MoreFragment extends Fragment {
    TextView logoutTV;
    TextView profileTV;
    TextView adminAreaTV;
    TextView policiesTV;
    TextView aboutusTV, contactusTV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_more, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        logoutTV = view.findViewById(R.id.logoutTV);
        profileTV = view.findViewById(R.id.profileTV);
        adminAreaTV = view.findViewById(R.id.adminAreaTV);
        policiesTV = view.findViewById(R.id.policiesTV);
        aboutusTV = view.findViewById(R.id.aboutusTV);
        contactusTV = view.findViewById(R.id.contactusTV);
        contactusOnClick();
        checkAdmin();
        setClickListeners();
        ProfileOnClick();
        PoliciesOnClick();

    }

    private void PoliciesOnClick() {
                policiesTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(), TermsandConditionActivity.class);
                startActivity(intent);
            }
        });
        aboutusTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AboutUsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void contactusOnClick() {

        contactusTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ContactusActivity.class);
                startActivity(intent);
            }
        });
    }


//    private void PoliciesOnClick() {
//        policiesTV.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent= new Intent(getActivity(), TermsAndConditionActivity.class);
//                startActivity(intent);
//            }
//        });
//        aboutusTV.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), AboutUsActivity.class);
//                startActivity(intent);
//            }
//        });
//    }


    private void checkAdmin() {
        boolean is_staff = SharedPrefUtils.getBool(getActivity(), getString(R.string.staff_key), false);
        if (is_staff)
            adminAreaTV.setVisibility(View.VISIBLE);

    }

    private void ProfileOnClick() {
        profileTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(), ProfileActivity.class);
                startActivity(intent);

            }
        });
    }
    private void setClickListeners() {
        logoutTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPrefUtils.clear(getContext());
                Intent userAccount = new Intent(getContext(), UserAccountActivity.class);
                startActivity(userAccount);
                getActivity().finish();
            }
        });
//        adminAreaTV.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), AdminActivity.class);
//                startActivity(intent);
//            }
//        });


    }
}

//
//public class MoreFragment extends Fragment {
//    TextView profileTV, adminAreaTV, AboutUsTV, TermsTV, contactusTV;
//    Button logoutTV;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_more, container, false);
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        profileTV = view.findViewById(R.id.profileTV);
//        logoutTV = view.findViewById(R.id.logoutTV);
//        adminAreaTV = view.findViewById(R.id.adminAreaTV);
//        AboutUsTV = view.findViewById(R.id.aboutusbackIV);
////        TermsTV = view.findViewById(R.id.TermsTV);
//        contactusTV = view.findViewById(R.id.contactusTV);
//        setClickListeners();
//        OnClick();
//        checkAdmin();
//
//    }
//
//    private void setClickListeners() {
//        logoutTV.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SharedPrefUtils.clear(getContext());
//                Intent userAccount = new Intent(getContext(), UserAccountActivity.class);
//                startActivity(userAccount);
//                getActivity().finish();
//            }
//        });
////        logoutTV.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////
////                SharedPrefUtils.clear(getContext());
////                Intent userAccount = new Intent(getContext(), UserAccountActivity.class);
////                startActivity(userAccount);
////                getActivity().finish();
////            }
////        });
//    }
//
//
//
//    private void checkAdmin() {
//        boolean is_staff = SharedPrefUtils.getBool(getActivity(),"sfk",false );
//        if (is_staff)
//            adminAreaTV.setVisibility(View.VISIBLE);
//    }
//
//
//    private void OnClick() {
//        profileTV.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), ProfileActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        contactusTV.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), ContactusActivity.class);
//                startActivity(intent);
//            }
//        });
//
//
//
//        AboutUsTV.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent  = new Intent(getActivity(), AboutUsActivity.class);
//                startActivity(intent);
//            }
//        });
//
//
//    }
//
//}