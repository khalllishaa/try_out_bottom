package com.example.try_out_bottom.Team;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.try_out_bottom.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class arsenal extends Fragment {

    private RecyclerView recyclerView;
    private TeamAdapter adapter;
    private List<Team> teamList;
    private static final String BASE_URL = "https://www.thesportsdb.com/api/v1/json/3/";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_arsenal, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        teamList = new ArrayList<>();
        adapter = new TeamAdapter(getContext(), teamList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        fetchDataFromAPI();

        return view;
    }

    private void fetchDataFromAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TeamService service = retrofit.create(TeamService.class);
        Call<TeamResponse> call = service.getTeams();
        call.enqueue(new Callback<TeamResponse>() {
            @Override
            public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {
                if (response.isSuccessful()) {
                    TeamResponse teamResponse = response.body();
                    if (teamResponse != null && teamResponse.getTeams() != null) {
                        teamList.addAll(teamResponse.getTeams());
                        adapter.notifyDataSetChanged();
                    }
                } else {
                    // Handle unsuccessful response
                }
            }

            @Override
            public void onFailure(Call<TeamResponse> call, Throwable t) {
                // Handle network failures
            }
        });
    }
}
