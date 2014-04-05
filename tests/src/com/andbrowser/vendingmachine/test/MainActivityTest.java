
package com.andbrowser.vendingmachine.test;

import android.app.*;
import android.test.*;
import android.widget.*;

import com.andbrowser.vendingmachine.*;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private Activity mActivity;

    private TextView mTextViewLog;

    private TextView mTextViewTotal;

    private TextView mTextViewStock;

    private Button mPurchangeCoke;

    private Button mInsertCoke;

    private Button mInsert10;

    private Button mInsert50;

    private Button mInsert100;

    private Button mInsert500;

    private Button mInsert1000;

    private CheckBox mCheckBoxAdminMode;

    private Button mBtnRefund;

    private Button mRegund;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mActivity = getActivity();
        assertNotNull(mActivity);
        mTextViewLog = (TextView) mActivity.findViewById(com.andbrowser.vendingmachine.R.id.tv_log);
        assertNotNull(mTextViewLog);
        mTextViewTotal = (TextView) mActivity
                .findViewById(com.andbrowser.vendingmachine.R.id.tv_total);
        assertNotNull(mTextViewTotal);
        mTextViewStock = (TextView) mActivity
                .findViewById(com.andbrowser.vendingmachine.R.id.tv_stock);
        assertNotNull(mTextViewStock);
        mPurchangeCoke = (Button) mActivity
                .findViewById(com.andbrowser.vendingmachine.R.id.btn_purchase_coke);
        assertNotNull(mPurchangeCoke);
        mRegund = (Button) mActivity.findViewById(com.andbrowser.vendingmachine.R.id.btn_refund);
        assertNotNull(mRegund);
        mInsertCoke = (Button) mActivity
                .findViewById(com.andbrowser.vendingmachine.R.id.btn_insert_coke);
        assertNotNull(mInsertCoke);
        mInsert10 = (Button) mActivity
                .findViewById(com.andbrowser.vendingmachine.R.id.btn_insert_10);
        assertNotNull(mInsert10);
        mInsert50 = (Button) mActivity
                .findViewById(com.andbrowser.vendingmachine.R.id.btn_insert_50);
        assertNotNull(mInsert50);
        mInsert100 = (Button) mActivity
                .findViewById(com.andbrowser.vendingmachine.R.id.btn_insert_100);
        assertNotNull(mInsert100);
        mInsert500 = (Button) mActivity
                .findViewById(com.andbrowser.vendingmachine.R.id.btn_insert_500);
        assertNotNull(mInsert500);
        mInsert1000 = (Button) mActivity
                .findViewById(com.andbrowser.vendingmachine.R.id.btn_insert_1000);
        assertNotNull(mInsert1000);
        mCheckBoxAdminMode = (CheckBox) mActivity
                .findViewById(com.andbrowser.vendingmachine.R.id.checkBoxAdminMode);
        assertNotNull(mCheckBoxAdminMode);
        mBtnRefund = (Button) mActivity.findViewById(com.andbrowser.vendingmachine.R.id.btn_refund);
    }

    public void testInitialTotalZero() throws Exception {
        assertEquals(Integer.valueOf(0), Integer.valueOf(mTextViewTotal.getText().toString()));
    }

    public void testInitialButtonEnable() throws Exception {
        assertTrue(mPurchangeCoke.isEnabled());
        assertTrue(mRegund.isEnabled());
        assertTrue(mInsert10.isEnabled());
        assertTrue(mInsert50.isEnabled());
        assertTrue(mInsert100.isEnabled());
        assertTrue(mInsert500.isEnabled());
        assertTrue(mInsert1000.isEnabled());
        assertFalse(mInsertCoke.isEnabled());
    }

    public void testAdminModeButtonEnable() throws Exception {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mCheckBoxAdminMode.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
        assertFalse(mPurchangeCoke.isEnabled());
        assertFalse(mRegund.isEnabled());
        assertFalse(mInsert10.isEnabled());
        assertFalse(mInsert50.isEnabled());
        assertFalse(mInsert100.isEnabled());
        assertFalse(mInsert500.isEnabled());
        assertFalse(mInsert1000.isEnabled());
        assertTrue(mInsertCoke.isEnabled());
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mCheckBoxAdminMode.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
        assertTrue(mPurchangeCoke.isEnabled());
        assertTrue(mInsert10.isEnabled());
        assertTrue(mInsert50.isEnabled());
        assertTrue(mInsert100.isEnabled());
        assertTrue(mInsert500.isEnabled());
        assertTrue(mInsert1000.isEnabled());
        assertFalse(mInsertCoke.isEnabled());
    }

    public void testInsertOneCoke() throws Exception {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mCheckBoxAdminMode.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mInsertCoke.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
        final String log = mTextViewLog.getText().toString();
        final String exptectedRegex = ".*Add 1 Coke.*";
        MoreAsserts.assertContainsRegex(exptectedRegex, log);
    }

    public void testInsert10() throws Exception {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mInsert10.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
        final String log = mTextViewLog.getText().toString();
        final String exptectedRegex = ".*?Success?.*Add 10\n.*";
        MoreAsserts.assertContainsRegex(exptectedRegex, log);
        final String notExptectedRegex = ".*F1ail*";
        MoreAsserts.assertNotContainsRegex(notExptectedRegex, log);
    }

    public void testInsert50() throws Exception {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mInsert50.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
        final String log = mTextViewLog.getText().toString();
        final String exptectedRegex = ".*?Success?.*Add 50\n.*";
        MoreAsserts.assertContainsRegex(exptectedRegex, log);
        final String notExptectedRegex = ".*F1ail*";
        MoreAsserts.assertNotContainsRegex(notExptectedRegex, log);
    }

    public void testInsert100() throws Exception {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mInsert100.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
        final String log = mTextViewLog.getText().toString();
        final String exptectedRegex = ".*?Success?.*Add 100\n.*";
        MoreAsserts.assertContainsRegex(exptectedRegex, log);
        final String notExptectedRegex = ".*F1ail*";
        MoreAsserts.assertNotContainsRegex(notExptectedRegex, log);
    }

    public void testInsert500() throws Exception {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mInsert500.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
        final String log = mTextViewLog.getText().toString();
        final String exptectedRegex = ".*?Success?.*Add 500\n.*";
        MoreAsserts.assertContainsRegex(exptectedRegex, log);
        final String notExptectedRegex = ".*F1ail*";
        MoreAsserts.assertNotContainsRegex(notExptectedRegex, log);
    }

    public void testInsert1000() throws Exception {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mInsert1000.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
        final String log = mTextViewLog.getText().toString();
        final String exptectedRegex = ".*?Success?.*Add 1000\n.*";
        MoreAsserts.assertContainsRegex(exptectedRegex, log);
        final String notExptectedRegex = ".*F1ail*";
        MoreAsserts.assertNotContainsRegex(notExptectedRegex, log);
    }

    public void testPurchaseOneCoke() {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mCheckBoxAdminMode.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mInsertCoke.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mCheckBoxAdminMode.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mInsert1000.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mPurchangeCoke.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
        assertEquals(Integer.valueOf(880), Integer.valueOf(mTextViewTotal.getText().toString()));
    }

    public void testPurchaneOneCokeButNoStock() {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mInsert1000.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mPurchangeCoke.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
    }

    public void testPurchaneOneCokeButNoMoney() {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mCheckBoxAdminMode.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mInsertCoke.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mPurchangeCoke.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
    }

    public void testStateDestroy() {
        assertEquals(Integer.valueOf(0), Integer.valueOf(mTextViewTotal.getText().toString()));
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mInsert100.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
        assertEquals(Integer.valueOf(100), Integer.valueOf(mTextViewTotal.getText().toString()));
        mActivity.finish();
        mActivity = this.getActivity();
        assertEquals(Integer.valueOf(100), Integer.valueOf(mTextViewTotal.getText().toString()));
    }

    public void testPurchaseMax() {
        final int JUCE_MAX = 10;
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mCheckBoxAdminMode.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
        for (int i = 0; i < JUCE_MAX; i++) {
            mActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mInsertCoke.performClick();
                }
            });
            getInstrumentation().waitForIdleSync();
        }
        for (int i = 0; i < JUCE_MAX; i++) {
            mActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mInsert10.performClick();
                }
            });
            getInstrumentation().waitForIdleSync();
            mActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mInsert50.performClick();
                }
            });
            getInstrumentation().waitForIdleSync();
            mActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mInsert100.performClick();
                }
            });
            getInstrumentation().waitForIdleSync();
            mActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mInsert500.performClick();
                }
            });
            getInstrumentation().waitForIdleSync();
            mActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mInsert1000.performClick();
                }
            });
            getInstrumentation().waitForIdleSync();
        }
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mCheckBoxAdminMode.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
        final Juice coke = new Coke();
        for (int i = 0; i < JUCE_MAX; i++) {
            final Integer total = ((10 + 50 + 100 + 500 + 1000) * (JUCE_MAX))
                    - (i * coke.getPrice());
            assertEquals(total, Integer.valueOf(mTextViewTotal.getText().toString()));
            mActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mPurchangeCoke.performClick();
                }
            });
            getInstrumentation().waitForIdleSync();
        }
    }

    public void testInitialRefundButtonPush() {
        assertEquals(Integer.valueOf(0), Integer.valueOf(mTextViewTotal.getText().toString()));
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mBtnRefund.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
        assertEquals(Integer.valueOf(0), Integer.valueOf(mTextViewTotal.getText().toString()));
    }

    public void testUserInputCoin10andUserRefund() {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mInsert10.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
        assertEquals(Integer.valueOf(10), Integer.valueOf(mTextViewTotal.getText().toString()));
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mBtnRefund.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
        assertEquals(Integer.valueOf(0), Integer.valueOf(mTextViewTotal.getText().toString()));
    }

    public void testUserInputSomeCoinandUserRefund() {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mInsert1000.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mInsert500.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mInsert100.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mInsert50.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mInsert10.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
        assertEquals(Integer.valueOf(1660), Integer.valueOf(mTextViewTotal.getText().toString()));
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mBtnRefund.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
        assertEquals(Integer.valueOf(0), Integer.valueOf(mTextViewTotal.getText().toString()));
    }

    public void testInitialRefund() throws Exception {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mBtnRefund.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
        assertEquals(Integer.valueOf(0), Integer.valueOf(mTextViewTotal.getText().toString()));
    }

    public void test十円をいれてからのRefundで合計が0円になること() throws Exception {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mInsert10.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mBtnRefund.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
        assertEquals("0", mTextViewTotal.getText());
    }

    public void test十円五十円百円五百円千円をいれてからのRefundで合計が0円になること() throws Exception {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mInsert10.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mInsert50.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mInsert100.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mInsert500.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mInsert1000.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mBtnRefund.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
        assertEquals(Integer.valueOf(0), Integer.valueOf(mTextViewTotal.getText().toString()));
    }

    public void test十円五十円百円五百円千円を仕様の最大回数いれてからのRefundで合計が0円になること() throws Exception {
        final int INSERT_MAX = 10;
        for (int i = 0; i < INSERT_MAX; i++) {
            mActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mInsert10.performClick();
                }
            });
            getInstrumentation().waitForIdleSync();
            mActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mInsert50.performClick();
                }
            });
            getInstrumentation().waitForIdleSync();
            mActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mInsert100.performClick();
                }
            });
            getInstrumentation().waitForIdleSync();
            mActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mInsert500.performClick();
                }
            });
            getInstrumentation().waitForIdleSync();
            mActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mInsert1000.performClick();
                }
            });
            getInstrumentation().waitForIdleSync();
            assertEquals(Integer.valueOf((10 + 50 + 100 + 500 + 1000) * (i + 1)),
                    Integer.valueOf(mTextViewTotal.getText().toString()));
        }
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mBtnRefund.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
        assertEquals(Integer.valueOf(0), Integer.valueOf(mTextViewTotal.getText().toString()));
    }
}
