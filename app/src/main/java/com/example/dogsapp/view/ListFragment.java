package com.example.dogsapp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.dogsapp.R;
import com.example.dogsapp.adapters.DogsListAdapter;
import com.example.dogsapp.viewmodel.ListViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListFragment extends Fragment {

    // UI
    @BindView(R.id.dogsList)
    RecyclerView dogsList;
    @BindView(R.id.listError)
    TextView listError;
    @BindView(R.id.loadingView)
    ProgressBar loadingView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    // function
    private View v;
    private ListViewModel mViewModel;
    private DogsListAdapter mDogsListAdapter = new DogsListAdapter(new ArrayList<>());


    public ListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        onGoToDetails();

        mViewModel = ViewModelProviders.of(this).get(ListViewModel.class);
        mViewModel.refresh();

        dogsList.setLayoutManager(new LinearLayoutManager(getContext()));
        dogsList.setHasFixedSize(true);
        dogsList.setItemViewCacheSize(20);
        dogsList.setAdapter(mDogsListAdapter);

        observeViewModel();


    }

    private void observeViewModel() {
        mViewModel.dogs.observe(this, dogs -> {
            if (dogs != null && dogs instanceof List) {
                dogsList.setVisibility(View.VISIBLE);
                mDogsListAdapter.updateDogsList(dogs);
            }
        });

        mViewModel.dogLoadError.observe(this, isError -> {
            if (isError != null && isError instanceof Boolean) {
                listError.setVisibility(isError ? View.VISIBLE : View.GONE);
            }
        });

        mViewModel.loading.observe(this, isLoading -> {
            if (isLoading != null && isLoading instanceof Boolean) {
                loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                if (isLoading) {
                    listError.setVisibility(View.GONE);
                    dogsList.setVisibility(View.GONE);
                }
            }
        });
    }

    private void onGoToDetails() {
        ListFragmentDirections.ActionDetail action = ListFragmentDirections.actionDetail();
        Navigation.findNavController(v).navigate(action);
    }

}