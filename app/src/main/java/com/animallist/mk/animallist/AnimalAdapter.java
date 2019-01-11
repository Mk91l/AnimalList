package com.animallist.mk.animallist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalHolder> {

    ArrayList<Animal> mAnimals;


    public AnimalAdapter(ArrayList<Animal> animals) {
        mAnimals = animals;
    }

    @NonNull
    @Override
    public AnimalHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item2, parent, false);
        return new AnimalHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalHolder animalHolder, int position) {
        Animal animal = mAnimals.get(position);
        animalHolder.bind(animal);
    }

    @Override
    public int getItemCount() {
        return mAnimals.size();
    }

    public void setAnimals(ArrayList<Animal> animals) {
        mAnimals = animals;
    }
}

class AnimalHolder extends RecyclerView.ViewHolder {

    TextView mAnimalName;
    ImageView mAnimalView;

    public AnimalHolder(@NonNull View itemView) {
        super(itemView);
        mAnimalName = itemView.findViewById(R.id.textView);
        mAnimalView = itemView.findViewById(R.id.imageView);
    }

    public void bind(Animal animal) {
        mAnimalName.setText(animal.getName());
        mAnimalView.setImageURI(animal.getImage());
    }
}