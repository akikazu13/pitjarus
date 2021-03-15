package com.projects.pitjarus_tracking.connections.requests;

/**
 * Created by Sohibun Nawawi on 13/03/2021.
 */

public abstract class DatabaseSuccessListener<M> implements Runnable{
    private M result;

    public M getResult() {
        return result;
    }

    public void setResult(M result) {
        this.result = result;
    }

    @Override
    public void run() {
        onSuccessResponse(getResult());
    }

    public abstract void onSuccessResponse(M results);
}
