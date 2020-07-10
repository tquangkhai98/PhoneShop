package com.capstone.phoneshop.ui.searchbar;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.capstone.phoneshop.Adapter.ProductAdapter;
import com.capstone.phoneshop.Data.DownloaderSearchProduct;
import com.capstone.phoneshop.Model.Product;
import com.capstone.phoneshop.R;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

public class SearchBarFragment extends Fragment {
    private SearchBarViewModel searchBarViewModel;

    private List<String> lastSearches;
    private MaterialSearchBar searchBar;
    private RecyclerView recyclerView;
    //private DownloaderSearchProduct downloader;
    public static List<Product> list;
    public static ProductAdapter productAdapter;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        searchBarViewModel =
                ViewModelProviders.of(this).get(SearchBarViewModel.class);
        View root = inflater.inflate(R.layout.fragment_search_bar, container, false);
        /*final TextView textView = root.findViewById(R.id.text_search);
        searchBarViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        list = new ArrayList<>();
        recyclerView = root.findViewById(R.id.viewProductInSearchBar);

        recyclerView.setHasFixedSize(true);
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // In landscape
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, LinearLayout.VERTICAL));
        } else {
            // In portrait
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayout.VERTICAL));
        }

        productAdapter = new ProductAdapter(getActivity(), list);
        recyclerView.setAdapter(productAdapter);
        productAdapter.notifyDataSetChanged();






        searchBar = root.findViewById(R.id.searchBar);
        searchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                //do notthing  host chùa request chi nhiều
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                Toast.makeText(getActivity(),"Loading",Toast.LENGTH_SHORT).show();
                new DownloaderSearchProduct().execute(new String[]{text.toString()});
            }

            @Override
            public void onButtonClicked(int buttonCode) {
                if(buttonCode== KeyEvent.KEYCODE_ENTER){
                    Toast.makeText(getActivity(),searchBar.getText().toString(),Toast.LENGTH_SHORT).show();

                }
            }
        });






        return root;
    }
}
