package com.trianz.campuscanteen;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by Niveditha.Kabbur on 4/25/2017.
 */

public class FirebaseObservable {

    private FirebaseDatabase mRootRef;

    public Observable<DataSnapshot> createFirebaseDataObservable() {

        Observable<DataSnapshot> firebaseDataObservable = Observable.create(new ObservableOnSubscribe<DataSnapshot>() {

            @Override
            public void subscribe(final ObservableEmitter<DataSnapshot> emitter) throws Exception {

                mRootRef = FirebaseDatabase.getInstance();
                DatabaseReference ref = mRootRef.getReference("appdata");

                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        emitter.onNext(dataSnapshot);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });

        return firebaseDataObservable;
    }

}
