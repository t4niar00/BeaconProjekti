package com.T4Niar00StudentsOamkFi.IbeaconprojectJwv.estimote;

public interface BeaconContentFactory {

    void getContent(BeaconID beaconID, Callback callback);

    interface Callback {
        void onContentReady(Object content);
    }
}
