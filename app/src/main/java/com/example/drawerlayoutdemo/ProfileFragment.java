package com.example.drawerlayoutdemo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ProfileFragment extends Fragment
{

    @Override
    public void onViewCreated(
            @NonNull View view, @Nullable Bundle savedInstanceState )
    {
        super.onViewCreated( view, savedInstanceState );

        Button btnDetail = view.findViewById( R.id.btn_detail );
        btnDetail.setOnClickListener( v->{
            Navigation.findNavController(v).navigate( R.id.action_nav_profile_to_profileDetailFragment );
        } );
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState )
    {
        // Inflate the layout for this fragment
        return inflater.inflate( R.layout.fragment_profile, container, false );
    }
}