package com.example.try_out_bottom.country;

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

public class countries extends Fragment {

    private RecyclerView recyclerView;
    private CoAdapter adapter;
    private List<countriess> teamList;
    private static final String BASE_URL = "https://www.thesportsdb.com/api/v1/json/3/";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_countries, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        teamList = new ArrayList<>();
        adapter = new CoAdapter(getContext(), teamList);
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

        CoService service = retrofit.create(CoService.class);
        Call<CoResponse> call = service.getCountries();
        call.enqueue(new Callback<CoResponse>() {
            @Override
            public void onResponse(Call<CoResponse> call, Response<CoResponse> response) {
                if (response.isSuccessful()) {
                    CoResponse coResponse = response.body();
                    if (coResponse != null && coResponse.getCountries() != null) {
                        teamList.addAll(coResponse.getCountries());
                        adapter.notifyDataSetChanged();
                        System.out.println("API call successful");
                    }
                } else {
                    // Handle unsuccessful response
                    System.out.println("API call failed with response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<CoResponse> call, Throwable t) {
                // Handle network failures
                System.out.println("API call failed with error: " + t.getMessage());
            }
        });
    }
}
