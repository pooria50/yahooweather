package com.example.pooria.yahooweatherapi.Mvp_NameFamily;

/**
 * Created by pooria on 5/9/18.
 */

public class MvpPresenter implements Contract.Presenter {
    Contract.Model model;

    public MvpPresenter(Contract.Model model) {
        model = new MvpModel();
    }
}
