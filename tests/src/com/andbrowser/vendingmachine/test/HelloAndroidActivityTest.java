
package com.andbrowser.vendingmachine.test;

import android.test.*;
import android.widget.*;

import com.andbrowser.vendingmachine.*;

public class HelloAndroidActivityTest extends
        ActivityInstrumentationTestCase2<HelloAndroidActivity> {

    private HelloAndroidActivity mActivity;

    private TextView mView;

    private String mStringResource;

    public HelloAndroidActivityTest() {
        super(HelloAndroidActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mActivity = getActivity();
        assertNotNull(mActivity);
        mView = (TextView) mActivity.findViewById(com.andbrowser.vendingmachine.R.id.textView);
        assertNotNull(mView);
        mStringResource = mActivity.getString(com.andbrowser.vendingmachine.R.string.hello);
        assertNotNull(mStringResource);
    }

    public void testText() throws Exception {
        assertEquals(mStringResource, mView.getText().toString());
    }

}
