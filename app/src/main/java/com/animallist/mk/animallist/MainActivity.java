package com.animallist.mk.animallist;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_IMAGE = 100;

    private RecyclerView mRecyclerView;
    private EditText mAnimalText;
    private Button mAddButton;
    private Button mImageButton;

    private AnimalAdapter mAnimalAdapter;
    private ArrayList<Animal> mAnimalList;
    private Uri mImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAnimalText = findViewById(R.id.animalinput);
        mAddButton = findViewById(R.id.add_element);
        mImageButton = findViewById(R.id.choose_image);
        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        mAnimalList = new ArrayList<>();
        mAnimalAdapter = new AnimalAdapter(mAnimalList);

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String animalName = mAnimalText.getText().toString();
                if (!animalName.equals("")) {
                    Animal animal = new Animal(animalName, mImageUri);
                    mAnimalList.add(animal);
                    mAnimalAdapter.setAnimals(mAnimalList);
                    mRecyclerView.setAdapter(mAnimalAdapter);
                    mAnimalText.setText("");
                } else
                    Toast.makeText(MainActivity.this, "Insert An Animal Name!", Toast.LENGTH_LONG).show();
            }
        });

        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chooseIntent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(chooseIntent, REQUEST_CODE_IMAGE);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_IMAGE:
                    handleImageRequestResult(data);
                    break;
                default:
                    Log.d("AnimalImageResult", "Unknown request code.");
            }
        } else {
            Log.e("AnimalImageResult", String.format("Unexpected Result code %s", resultCode));
        }
    }

    private void handleImageRequestResult(Intent data) {
        mImageUri = null;
        if (data.getClipData() != null) {
            mImageUri = data.getClipData().getItemAt(0).getUri();
        } else if (data.getData() != null) {
            mImageUri = data.getData();
        }
        if (mImageUri == null) {
            return;
        }
    }
}
