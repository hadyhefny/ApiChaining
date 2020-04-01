package com.hefny.hady.rxmerging.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hefny.hady.rxmerging.api.ServiceGenerator;
import com.hefny.hady.rxmerging.api.TeamsApi;
import com.hefny.hady.rxmerging.models.players.Players;
import com.hefny.hady.rxmerging.models.teams.Teams;
import com.hefny.hady.rxmerging.models.teams.TeamsItem;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class TeamsViewModel extends ViewModel {

    private static final String TAG = "TeamsViewModel";
    private TeamsApi teamsApi;
    private MutableLiveData<Teams> teamsMutableLiveData = new MutableLiveData<>();
    private Teams myTeams = new Teams();
    private TeamsItem myTeamsItem = new TeamsItem();
    private List<TeamsItem> teamsItemList = new ArrayList<>();

    public TeamsViewModel() {
        teamsApi = ServiceGenerator.getRecipeApi();
        getPlayersObservable();
    }

    public LiveData<Teams> getTeamsLiveData() {
        return teamsMutableLiveData;
    }

    private void getPlayersObservable() {
        teamsApi.getTeams()
                .flatMap(new Function<Teams, ObservableSource<TeamsItem>>() {
                    @Override
                    public ObservableSource<TeamsItem> apply(Teams teams) throws Exception {
                        myTeams = teams;
                        return Observable.fromIterable(teams.getTeams());
                    }
                })
                .flatMap(new Function<TeamsItem, ObservableSource<Players>>() {
                    @Override
                    public ObservableSource<Players> apply(TeamsItem teamsItem) throws Exception {
                        myTeamsItem = teamsItem;
                        teamsItemList.add(myTeamsItem);
                        return getPlayers(teamsItem.getId());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Players>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Players players) {
                        myTeamsItem.setPlayers(players);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e);
                    }

                    @Override
                    public void onComplete() {
                        myTeams.setTeams(teamsItemList);
                        teamsMutableLiveData.setValue(myTeams);
                    }
                });
    }

    private Observable<Players> getPlayers(int id) {
        return teamsApi.getPlayers(id);
    }
}