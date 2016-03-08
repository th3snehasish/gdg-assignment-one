package com.th3snehasish.gdgassignmentone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.squareup.picasso.Picasso;


public class FamilyAdapter extends ArrayAdapter<String> {


    private final Activity context;
    private final String[] itemname;
    private final String[] itemrelation;
    private final Integer[] imgid;
    public Firebase myFirebaseRef;

    public FamilyAdapter(final Activity context, String[] itemname, Integer[] imgid,String[] itemrelation) {
        super(context, R.layout.list_item_family, itemname);
        this.context = context;
        this.itemname = itemname;
        this.imgid = imgid;
        this.itemrelation = itemrelation;
        Firebase.setAndroidContext(getContext());
        myFirebaseRef = new Firebase("https://radiant-inferno-7570.firebaseio.com");
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        final View rowView = inflater.inflate(R.layout.list_item_family, null, true);

        final TextView txtTitle = (TextView) rowView.findViewById(R.id.textName);
        final TextView txtRelation = (TextView) rowView.findViewById(R.id.textRel);
        final ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);


        txtTitle.setText(itemname[position]);
        txtRelation.setText(itemrelation[position]);
        imageView.setImageResource(imgid[position]);

        myFirebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                txtTitle.setText(String.valueOf(dataSnapshot.child("Family").child(String.valueOf(position)).child("names").getValue()));
                txtRelation.setText(String.valueOf(dataSnapshot.child("Family").child(String.valueOf(position)).child("relation").getValue()));
                Picasso.with(getContext()).load(String.valueOf(dataSnapshot.child("Family").child(String.valueOf(position)).child("imageUrl").getValue())).into(imageView);

                rowView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String lat = String.valueOf(dataSnapshot.child("Family").child(String.valueOf(position)).child("lat").getValue());
                        String lon = String.valueOf(dataSnapshot.child("Family").child(String.valueOf(position)).child("lon").getValue());
                        String name = String.valueOf(dataSnapshot.child("Family").child(String.valueOf(position)).child("names").getValue().toString());

                        if ((lat == "null") || (lon == "null")) {
                            Toast.makeText(getContext(), "Location not Provided by the user", Toast.LENGTH_SHORT).show();
                        } else {

                            Intent intent = new Intent(getContext(), MapsActivity.class);
                            Bundle extras = new Bundle();
                            extras.putString("EXTRA_LAT", lat);
                            extras.putString("EXTRA_LON", lon);
                            extras.putString("EXTRA_NAME", name);
                            intent.putExtras(extras);
                            getContext().startActivity(intent);
                        }
                    }
                });
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        return rowView;
    }
}