
package com.andbrowser.vendingmachine.test;

import android.test.*;

import com.andbrowser.vendingmachine.*;

import java.util.*;

public class VendingMachineTest extends AndroidTestCase {

    private VendingMachine mVm;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mVm = new VendingMachine();
        final Juice coke = new Coke();
        mVm.addStock(coke);
        mVm.addStock(coke);
        mVm.addStock(coke);
        mVm.addStock(coke);
        mVm.addStock(coke);
    }

    public void test初期状態が0円であること() {
        assertEquals(Integer.valueOf(0), mVm.getTotal());
    }

    public void test十円玉を入れて合計が十円であること() {
        assertTrue(mVm.insert(Money.COIN_10));
        assertEquals(Integer.valueOf(10), mVm.getTotal());
    }

    public void test一円玉を入れても受領されずに合計が０円であること() {
        assertFalse(mVm.insert(Integer.valueOf(1)));
        assertEquals(Integer.valueOf(0), mVm.getTotal());
    }

    public void test五円玉を入れても受領されずに合計が０円であること() {
        assertFalse(mVm.insert(Integer.valueOf(5)));
        assertEquals(Integer.valueOf(0), mVm.getTotal());
    }

    public void test百円玉を入れて合計が百円であること() {
        assertTrue(mVm.insert(Money.COIN_100));
        assertEquals(Integer.valueOf(100), mVm.getTotal());
    }

    public void test五百円玉を入れて合計が五百円であること() {
        assertTrue(mVm.insert(Money.COIN_500));
        assertEquals(Integer.valueOf(500), mVm.getTotal());
    }

    public void test千円札を入れて合計が千円であること() {
        assertTrue(mVm.insert(Money.BILL_1000));
        assertEquals(Integer.valueOf(1000), mVm.getTotal());
    }

    public void test十円玉と五十円玉と百円玉と五百円玉と千円札を入れて合計１６６０円であること() {
        assertTrue(mVm.insert(Money.COIN_10));
        assertTrue(mVm.insert(Money.COIN_50));
        assertTrue(mVm.insert(Money.COIN_100));
        assertTrue(mVm.insert(Money.COIN_500));
        assertTrue(mVm.insert(Money.BILL_1000));
        assertEquals(Integer.valueOf(1660), mVm.getTotal());
    }

    public void test初期状態で払戻すると何も戻されないこと() {
        final ArrayList<Integer> moneyList = mVm.refund();
        assertTrue(moneyList.isEmpty());
    }

    public void test十円玉を入力して十円の払い戻しができること() {
        assertTrue(mVm.insert(Money.COIN_10));
        final ArrayList<Integer> moneyList = mVm.refund();
        assertEquals(Integer.valueOf(10), moneyList.get(0));
    }

    public void test十円玉と五十円玉と百円玉と五百円玉と千円札を入力しての払い戻しができること() {
        assertTrue(mVm.insert(Money.COIN_10));
        assertTrue(mVm.insert(Money.COIN_50));
        assertTrue(mVm.insert(Money.COIN_100));
        assertTrue(mVm.insert(Money.COIN_500));
        assertTrue(mVm.insert(Money.BILL_1000));
        final ArrayList<Integer> moneyList = mVm.refund();
        assertEquals(Integer.valueOf(10), moneyList.get(0));
        assertEquals(Integer.valueOf(50), moneyList.get(1));
        assertEquals(Integer.valueOf(100), moneyList.get(2));
        assertEquals(Integer.valueOf(500), moneyList.get(3));
        assertEquals(Integer.valueOf(1000), moneyList.get(4));
    }

    public void testコーラ１２０円を作成できること() {
        final Juice coke = new Coke();
        assertEquals("Coke", coke.getName());
        assertEquals(Integer.valueOf(120), coke.getPrice());
    }

    public void test自動販売機に5本のコーラが格納されていること() {
        assertEquals(5, mVm.getStock().size());
    }

    public void test自動販売機に格納されているジュースの情報を取得できること() {
        final ArrayList<Juice> stock = mVm.getStock();
        final ArrayList<JuiceStock> stockInfomation = VendingMachineUtil.calculateStock(stock);
        assertEquals("Coke", stockInfomation.get(0).getJuice().getName());
        assertEquals(Integer.valueOf(120), stockInfomation.get(0).getJuice().getPrice());
        assertEquals(Integer.valueOf(5), stockInfomation.get(0).getStockSize());
    }

    public void testコーラが自動販売機が空の状態で購入不可状態を取得できること() {
        final VendingMachine vm = new VendingMachine();
        final Juice coke = new Coke();
        assertFalse(vm.isPurchase(coke));
    }

    public void testお金が投入されておらずコーラ5本が格納されているときに購入不可状態を取得できること() {
        final Juice coke = new Coke();
        assertFalse(mVm.isPurchase(coke));
    }

    public void test１１０円という足りないお金が投入されておらずコーラ5本が格納されているときに購入不可状態を取得できること() {
        final Juice coke = new Coke();
        mVm.insert(Money.COIN_10);
        mVm.insert(Money.COIN_100);
        assertFalse(mVm.isPurchase(coke));
    }

    public void test１２０円お金が投入されていてコーラ5本が格納されているときに購入状態を取得できること() {
        final Juice coke = new Coke();
        mVm.insert(Money.COIN_10);
        mVm.insert(Money.COIN_10);
        mVm.insert(Money.COIN_100);
        assertTrue(mVm.isPurchase(coke));
    }

    public void testお金が投入されていてコーラ5本が格納されているときに購入状態を取得できること２() {
        final Juice coke = new Coke();
        mVm.insert(Money.COIN_10);
        mVm.insert(Money.COIN_10);
        mVm.insert(Money.COIN_10);
        mVm.insert(Money.COIN_100);
        assertTrue(mVm.isPurchase(coke));
    }

    public void test在庫がない場合に購入操作を行っても何もしないこと() {
        ArrayList<Juice> stock = mVm.getStock();
        ArrayList<JuiceStock> stockInfomation = VendingMachineUtil.calculateStock(stock);
        final Juice coke = new Coke();
        assertEquals("Coke", stockInfomation.get(0).getJuice().getName());
        assertEquals(Integer.valueOf(120), stockInfomation.get(0).getJuice().getPrice());
        assertEquals(Integer.valueOf(5), stockInfomation.get(0).getStockSize());
        assertEquals(Integer.valueOf(0), mVm.getTotal());
        assertFalse(mVm.purchase(coke));
        stock = mVm.getStock();
        stockInfomation = VendingMachineUtil.calculateStock(stock);
        assertEquals(Integer.valueOf(0), mVm.getTotal());
        assertEquals("Coke", stockInfomation.get(0).getJuice().getName());
        assertEquals(Integer.valueOf(120), stockInfomation.get(0).getJuice().getPrice());
        assertEquals(Integer.valueOf(5), stockInfomation.get(0).getStockSize());
    }

    public void test初期状態で売上金額が０円が取得できること() {
        assertEquals(Integer.valueOf(0), mVm.getSaleAmount());
    }

    public void testジュース以上のお金が投入されていて購入操作したときに1本分売上金額が増えること() {
        mVm.insert(Money.COIN_10);
        mVm.insert(Money.COIN_10);
        mVm.insert(Money.COIN_100);
        final Juice coke = new Coke();
        assertTrue(mVm.purchase(coke));
        assertEquals(Integer.valueOf(120), mVm.getSaleAmount());
        assertEquals(Integer.valueOf(0), mVm.getTotal());
    }

    public void testジュース以上のお金が投入されていて購入操作したときに5本分売上金額が増えること() {
        mVm.insert(Money.BILL_1000);
        mVm.insert(Money.BILL_1000);
        mVm.insert(Money.BILL_1000);
        final Juice coke = new Coke();
        assertTrue(mVm.purchase(coke));
        assertTrue(mVm.purchase(coke));
        assertTrue(mVm.purchase(coke));
        assertTrue(mVm.purchase(coke));
        assertTrue(mVm.purchase(coke));
        assertEquals(Integer.valueOf(600), mVm.getSaleAmount());
        assertEquals(Integer.valueOf(2400), mVm.getTotal());
    }

    public void test5個在庫がある状態から１つ購入すると在庫が4つになること() {
        ArrayList<Juice> stock = mVm.getStock();
        ArrayList<JuiceStock> stockInfomation = VendingMachineUtil.calculateStock(stock);
        final Juice coke = new Coke();
        assertEquals(Integer.valueOf(5), stockInfomation.get(0).getStockSize());
        mVm.insert(Money.COIN_500);
        mVm.purchase(coke);
        stock = mVm.getStock();
        stockInfomation = VendingMachineUtil.calculateStock(stock);
        assertEquals(Integer.valueOf(4), stockInfomation.get(0).getStockSize());
    }

    public void test五百円入れて１２０円コーラを購入すると投入金額が３８０円になること() {
        final Juice coke = new Coke();
        mVm.insert(Money.COIN_500);
        mVm.purchase(coke);
        assertEquals(Integer.valueOf(380), mVm.getTotal());
        assertEquals(Integer.valueOf(120), mVm.getSaleAmount());
    }

    public void test千円入れて１２０円コーラを購入すると投入金額が８８０円になること() {
        final Juice coke = new Coke();
        mVm.insert(Money.BILL_1000);
        mVm.insert(Money.BILL_1000);
        mVm.purchase(coke);
        assertEquals(Integer.valueOf(1880), mVm.getTotal());
        assertEquals(Integer.valueOf(120), mVm.getSaleAmount());
        final ArrayList<Integer> moneyList = mVm.refund();
        assertEquals(Integer.valueOf(1000), moneyList.get(0));
        assertEquals(Integer.valueOf(500), moneyList.get(1));
        assertEquals(Integer.valueOf(100), moneyList.get(2));
        assertEquals(Integer.valueOf(100), moneyList.get(3));
        assertEquals(Integer.valueOf(100), moneyList.get(4));
        assertEquals(Integer.valueOf(50), moneyList.get(5));
        assertEquals(Integer.valueOf(10), moneyList.get(6));
        assertEquals(Integer.valueOf(10), moneyList.get(7));
        assertEquals(Integer.valueOf(10), moneyList.get(8));
    }
}
