package com.example.app_control_domotico.data.model.Metodos;

import android.content.Context;
import android.provider.DocumentsContract;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.JsonElement;
import com.google.protobuf.Empty;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Map;

public class FnFirebase {

    public static Map<String, Object> GetLeds(final Context context){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("Proyecto_Movil").document("Leds");
        final Map<String, Object>[] Datos = new Map[]{null};
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Toast.makeText(context, "DocumentSnapshot data: " + document.getData(),Toast.LENGTH_SHORT).show();
                        Datos[0] = document.getData();
                    } else {
                        Toast.makeText(context, "No such document",Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "get failed with "+ task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
        return Datos[0];
    }

}
