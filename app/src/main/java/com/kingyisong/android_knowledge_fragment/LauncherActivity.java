package com.kingyisong.android_knowledge_fragment;

import android.app.Activity;
import android.os.Bundle;

import com.kingyisong.android_knowledge_fragment.ui.meta.CanvasBaseTransform;

public class LauncherActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new CanvasBaseTransform(this));
    }

}
