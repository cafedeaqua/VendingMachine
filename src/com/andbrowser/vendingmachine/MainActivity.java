
package com.andbrowser.vendingmachine;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;

import java.util.*;

public class MainActivity extends Activity {
    private VendingMachine mVm;

    private TextView mTextViewLog;

    private TextView mTextViewTotal;

    private Button mPurchangeCoke;

    private Button mInsertCoke;

    private Button mInsert10;

    private Button mInsert50;

    private Button mInsert100;

    private Button mInsert500;

    private Button mInsert1000;

    private TextView mTextViewStock;

    private Button mRefund;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextViewLog = (TextView) findViewById(R.id.tv_log);
        mTextViewTotal = (TextView) findViewById(R.id.tv_total);
        mTextViewStock = (TextView) findViewById(R.id.tv_stock);
        mPurchangeCoke = (Button) findViewById(R.id.btn_purchase_coke);
        mRefund = (Button) findViewById(R.id.btn_refund);
        mInsertCoke = (Button) findViewById(R.id.btn_insert_coke);
        mInsert10 = (Button) findViewById(R.id.btn_insert_10);
        mInsert50 = (Button) findViewById(R.id.btn_insert_50);
        mInsert100 = (Button) findViewById(R.id.btn_insert_100);
        mInsert500 = (Button) findViewById(R.id.btn_insert_500);
        mInsert1000 = (Button) findViewById(R.id.btn_insert_1000);
        mVm = new VendingMachine();
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        // Inflate the menu; this adds it/ems to the action bar if it is
        // present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void onClickInsertCoke(final View v) {
        final Juice coke = new Coke();
        mVm.addStock(coke);
        addLog("[Success]\tAdd 1 Coke.");
        updateVendingMachineDisplay();
    }

    public void onClickRefund(final View v) {
        Toast.makeText(this, "onClickRefund", Toast.LENGTH_SHORT).show();
        final ArrayList<Integer> refundList = mVm.refund();
        addLog("[refund]" + refundList.toString());
        updateVendingMachineDisplay();
    }

    private void updateVendingMachineDisplay() {
        mTextViewTotal.setText(mVm.getTotal().toString());
        mTextViewStock.setText(mVm.getStock().toString());
    }

    private void addLog(final String message_one_line) {
        final StringBuffer buffer = new StringBuffer(mTextViewLog.getText().toString());
        buffer.append(message_one_line);
        buffer.append("\n");
        mTextViewLog.setText(buffer.toString());
    }

    public void onClickMoney(final View v) {
        switch (v.getId()) {
        case R.id.btn_insert_10:
            Toast.makeText(this, "10", Toast.LENGTH_SHORT).show();
            if (mVm.insert(Money.COIN_10)) {
                addLog("[Success]\tAdd 10");
            }
            break;
        case R.id.btn_insert_50:
            Toast.makeText(this, "50", Toast.LENGTH_SHORT).show();
            if (mVm.insert(Money.COIN_50)) {
                addLog("[Success]\tAdd 50");
            }
            break;
        case R.id.btn_insert_100:
            Toast.makeText(this, "100", Toast.LENGTH_SHORT).show();
            if (mVm.insert(Money.COIN_100)) {
                addLog("[Success]\tAdd 100");
            }
            break;
        case R.id.btn_insert_500:
            Toast.makeText(this, "500", Toast.LENGTH_SHORT).show();
            if (mVm.insert(Money.COIN_500)) {
                addLog("[Success]\tAdd 500");
            }
            break;
        case R.id.btn_insert_1000:
            Toast.makeText(this, "1000", Toast.LENGTH_SHORT).show();
            if (mVm.insert(Money.BILL_1000)) {
                addLog("[Success]\tAdd 1000");
            }
            break;
        default:
            Toast.makeText(this, "No acceptable money.", Toast.LENGTH_LONG).show();
            break;
        }
        updateVendingMachineDisplay();
    }

    public void onClickPurchaseCoke(final View v) {
        final Juice coke = new Coke();
        if (mVm.isPurchase(coke)) {
            mVm.purchase(coke);
            addLog(String.format("[Success]\tPurchase %s(%s)", coke.getName(), coke.getPrice()));
        } else {
            if (mVm.getTotal() < coke.getPrice()) {
                Toast.makeText(this, "No Money", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "No Stock", Toast.LENGTH_SHORT).show();
            }
        }
        updateVendingMachineDisplay();
    }

    public void onClickAdminMode(final View v) {
        final CheckBox checkBox = (CheckBox) v;
        final boolean status = checkBox.isChecked();
        if (status) {
            mInsertCoke.setEnabled(true);
            mPurchangeCoke.setEnabled(false);
            mRefund.setEnabled(false);
            mInsert10.setEnabled(false);
            mInsert50.setEnabled(false);
            mInsert100.setEnabled(false);
            mInsert500.setEnabled(false);
            mInsert1000.setEnabled(false);
        } else {
            mInsertCoke.setEnabled(false);
            mPurchangeCoke.setEnabled(true);
            mInsert10.setEnabled(true);
            mInsert50.setEnabled(true);
            mInsert100.setEnabled(true);
            mInsert500.setEnabled(true);
            mInsert1000.setEnabled(true);
        }
    }
}
