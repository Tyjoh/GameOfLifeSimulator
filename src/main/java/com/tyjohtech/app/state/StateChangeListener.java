package com.tyjohtech.app.state;

public interface StateChangeListener<S extends State> {
    void onChanged(S state);
}
