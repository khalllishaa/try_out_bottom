package com.example.try_out_bottom.Team;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TeamService {
    // Endpoint untuk Arsenal
    @GET("search_all_teams.php?l=English%20Premier%20League")
    Call<TeamResponse> getTeams();

}
