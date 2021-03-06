package com.example.dogsapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dogsapp.R;
import com.example.dogsapp.model.DogBreed;
import com.example.dogsapp.util.Util;
import com.example.dogsapp.view.ListFragmentDirections;

import java.util.List;

public class DogsListAdapter extends RecyclerView.Adapter<DogsListAdapter.DogViewHolder> {

    private List<DogBreed> mDogsList;

    public DogsListAdapter(List<DogBreed> dogsList) {
        this.mDogsList = dogsList;
    }

    public void updateDogsList(List<DogBreed> newDogsList) {
        mDogsList.clear();
        mDogsList.addAll(newDogsList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dog, parent, false);
        return new DogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DogViewHolder holder, int position) {
        holder.name.setText(mDogsList.get(position).dogBreed);
        holder.lifespan.setText(mDogsList.get(position).lifeSpan);
        Util.loadImages(holder.image, mDogsList.get(position).imageUrl, Util.getProgressDrawable(holder.image.getContext()));

        holder.mLayout.setOnClickListener(view -> {
            ListFragmentDirections.ActionDetail action = ListFragmentDirections.actionDetail();
            action.setDogUuid(mDogsList.get(position).uuid);
            Navigation.findNavController(view).navigate(action);
        });

    }

    @Override
    public int getItemCount() {
        if (mDogsList != null) {
            return mDogsList.size();
        }
        return 0;
    }


    public class DogViewHolder extends RecyclerView.ViewHolder {
        LinearLayout mLayout;
        View itemView;
        ImageView image;
        TextView name, lifespan;

        public DogViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            image = itemView.findViewById(R.id.imageView);
            name = itemView.findViewById(R.id.name);
            lifespan = itemView.findViewById(R.id.lifeSpan);
            mLayout = itemView.findViewById(R.id.dogLayout);
        }
    }
}
