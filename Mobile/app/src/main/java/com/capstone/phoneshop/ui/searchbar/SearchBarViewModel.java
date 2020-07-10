package com.capstone.phoneshop.ui.searchbar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SearchBarViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public SearchBarViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is searchbar fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
