package com.hefny.hady.rxmerging.api;

import com.hefny.hady.rxmerging.models.players.Players;
import com.hefny.hady.rxmerging.models.teams.Teams;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TeamsApi {
    @GET("competitions/2021/teams")
    Observable<Teams> getTeams();

    @GET("teams/{id}")
    Observable<Players> getPlayers(
            @Path("id") int id
    );
}
