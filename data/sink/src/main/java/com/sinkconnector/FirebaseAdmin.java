package com.sinkconnector;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FirebaseAdmin {

  private DatabaseReference databaseReference;
  private final String firebaseUrl;
  private final String firebaseConfig;

  public FirebaseAdmin(String firebaseUrl, String firebaseConfig) {
    this.firebaseUrl = firebaseUrl;
    this.firebaseConfig = firebaseConfig;
  }

  public void initializeFirebase() throws IOException {
    ByteArrayInputStream serviceAccount = new ByteArrayInputStream(firebaseConfig.getBytes(StandardCharsets.UTF_8));
    FirebaseOptions options = new FirebaseOptions.Builder()
        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
        .setDatabaseUrl(firebaseUrl)
        .build();

    if (FirebaseApp.getApps().isEmpty()) {
      FirebaseApp.initializeApp(options);
    }

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    this.databaseReference = database.getReference();
  }

  public void saveData(String path, String id, Object object) {
    DatabaseReference childRef = this.databaseReference.child(path);
    childRef.child(id).setValueAsync(object);
  }
}
