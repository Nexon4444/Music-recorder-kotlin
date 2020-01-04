//package com.android.musicrecorder;
//
//import androidx.annotation.NonNull;
//
//import com.android.musicrecorder.DBObjects.User;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class FirebaseDataHelper {
//    private FirebaseDatabase firebaseDatabase;
//    private DatabaseReference databaseReference;
//    private List<User> users = new ArrayList<>();
//
//
//    public FirebaseDataHelper() {
//        firebaseDatabase = FirebaseDatabase.getInstance();
//        firebaseDatabase.getReference("users");
//    }
//
//    public interface DataStatus{
//        void dataIsLoaded(List<User> users, List<String> keys);
//        void dataIsInserted();
//        void dataIsModified();
//
//    }
//
//    public void readUsers(final DataStatus dataStatus){
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                users.clear();
//                List<String> keys = new ArrayList<>();
//                for(DataSnapshot keyNode : dataSnapshot.getChildren()){
//                    keys.add(keyNode.getKey());
//                    User user = keyNode.getValue(User.class);
//                    users.add(user);
//                }
//                dataStatus.dataIsLoaded(users, keys);
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        })
//    }
//}
