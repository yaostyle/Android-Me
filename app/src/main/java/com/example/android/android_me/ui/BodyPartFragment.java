package com.example.android.android_me.ui;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

import java.util.ArrayList;
import java.util.List;

public class BodyPartFragment extends Fragment {

    public static final String IMAGE_ID_LIST = "image_ids";
    public static final String LIST_IDNEX = "list_index";

    private static final String TAG = "BodyPartFragment";
    
    // Vars
    private List<Integer> mImageIds;
    private int mListIndex;

    // Constructor (empty)
    public BodyPartFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        // Load the saved state if there's any image list and index
        if (savedInstanceState != null) {
            mImageIds = savedInstanceState.getIntegerArrayList(IMAGE_ID_LIST);
            mListIndex = savedInstanceState.getInt(LIST_IDNEX);
        }


        // Inflate the AndroidMe fragment layout
        final View rootView = inflater.inflate(R.layout.fragment_body_part, container, false);

        // Get a reference to the imageView in the fragment layout
        final ImageView imageView = (ImageView) rootView.findViewById(R.id.body_part_image_view);

        // If a list of image ids exists, set the image resource to the correct item
        // Otherwise create a log statment that indicates it wasn't found
        if(mImageIds != null) {
            imageView.setImageResource(mImageIds.get(mListIndex));

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mListIndex < mImageIds.size()-1) {
                        mListIndex++;
                    } else {
                        mListIndex = 0;
                    }

                    imageView.setImageResource(mImageIds.get(mListIndex));
                }
            });
        } else {
            Log.d(TAG, "this fragment has a null list of image id");
        }

        return rootView;
    }

    public void setmImageIds(List<Integer> mImageIds) {
        this.mImageIds = mImageIds;
    }

    public void setmListIndex(int index) {
        this.mListIndex = mListIndex;
    }

    // Override onSaveInstanceState to maintain both image list & list index
    @Override
    public void onSaveInstanceState(Bundle currsentState) {
        currsentState.putIntegerArrayList(IMAGE_ID_LIST, (ArrayList<Integer>) mImageIds);
        currsentState.putInt(LIST_IDNEX, mListIndex);
    }
}
