package com.projects.pitjarus_tracking.connections.requests;

/**
 * Created by Sohibun Nawawi on 13/03/2021.
 */

public abstract class DatabaseErrorListener<M> implements Runnable {
    @Override
    public void run() {

    }

    public abstract void onErrorResponseListener();
}
