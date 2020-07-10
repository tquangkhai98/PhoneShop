package com.capstone.phoneshop.ui.userpage;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.capstone.phoneshop.Model.User;

public class UserPageViewModel extends ViewModel {
    //private MutableLiveData<String> mText;
    private MutableLiveData<String> imageURL;
    private MutableLiveData<String> name;
    private MutableLiveData<String> email;




    public UserPageViewModel() {

        imageURL = new MutableLiveData<>();
        name = new MutableLiveData<>();
        email = new MutableLiveData<>();

        imageURL.setValue("https://www.facebook.com/photo.php?fbid=1369044633270180&set=a.105091466332176&type=3&source=11&referrer_profile_id=100004939685707");
        name.setValue("Trần Quang Khải");
        email.setValue("chenguangbankai@gmail.com");
        //mText = new MutableLiveData<>();
        //mText.setValue("This is userpage fragment");
    }

    public LiveData<String> getImageURL() {
        return imageURL;
    }

    public LiveData<String> getname() {
        return name;
    }

    public LiveData<String> getEmail() {
        return email;
    }

    /*public LiveData<String> getText() {
        return mText;
    }*/
}
