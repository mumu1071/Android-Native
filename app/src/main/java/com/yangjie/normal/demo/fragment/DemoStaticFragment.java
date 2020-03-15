package com.yangjie.normal.demo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yangjie.normal.R;

/**
 * @author yangjie
 * @description:
 * @date :2020/3/15 11:47 AM
 */
public class DemoStaticFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.demo_fragment_static, container, false);
        return view;
    }


}