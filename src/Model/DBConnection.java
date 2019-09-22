/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


import com.firebase.client.Firebase;
import com.firebase.client.FirebaseApp;
import com.firebase.client.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;



/**
 *
 * @author Lenovo
 */
public class DBConnection {
    
   // private Firebase firebase;
    
    public static void main(String[] args ) throws FileNotFoundException, IOException{
        
        FileInputStream serviceAccount =
      new FileInputStream("D://CSED//SellerDesktopApp/ServiceAccountKey.json");
  
       Firebase rootRef = new Firebase("https://clothes-24705.firebaseio.com");
   /* FirebaseOptions options = new FirebaseOptions.Builder()
      .setCredentials(GoogleCredentials.fromStream(serviceAccount))
      .setDatabaseUrl("https://clothes-24705.firebaseio.com")
      .build();


        FirebaseApp.initializeApp(options);
    Firestore db  = FirestoreClient.getFirestore();
    DocumentReference docRef = db.collection("product").document();
    Map<String, Object> data = new HashMap<>();
    data.put("first", "Ada");
    data.put("last", "Lovelace");
    data.put("born", 1815);
    
    ApiFuture<WriteResult> result =docRef.set(data);*/
    }
    
    
    
}
