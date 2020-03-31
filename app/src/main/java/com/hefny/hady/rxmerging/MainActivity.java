package com.hefny.hady.rxmerging;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.hefny.hady.rxmerging.models.teams.Teams;
import com.hefny.hady.rxmerging.viewmodels.TeamsViewModel;

public class MainActivity extends AppCompatActivity {

    private TeamsViewModel teamsViewModel;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        teamsViewModel = new ViewModelProvider(this).get(TeamsViewModel.class);
        getTeams();
    }

    public void getTeams() {
        teamsViewModel.getTeamsLiveData().observe(this, new Observer<Teams>() {
            @Override
            public void onChanged(Teams teams) {
                Log.d(TAG, "onChanged: " + teams);
            }
        });
    }
}