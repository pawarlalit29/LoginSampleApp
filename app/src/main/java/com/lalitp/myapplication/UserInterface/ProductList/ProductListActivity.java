package com.lalitp.myapplication.UserInterface.ProductList;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.lalitp.myapplication.Pojo.ProductList.ProductList;
import com.lalitp.myapplication.R;
import com.lalitp.myapplication.UserInterface.Adaptor.ProductListAdapter;
import com.lalitp.myapplication.Utils.AppConstant;
import com.lalitp.myapplication.Utils.Common_Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductListActivity extends AppCompatActivity implements ProductListView {

    @BindView(R.id.recycleview)
    RecyclerView recycleview;
    private Context context;
    private List<ProductList> productList;
    private ProductListAdapter productListAdapter;
    private ProductListPresenter productListPresenter;

    private HashMap<String,String> updateProductList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        context = this;
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recycleview.setLayoutManager(layoutManager);

        updateProductList = new HashMap<>();
        productList = new ArrayList<>();
        productListAdapter = new ProductListAdapter(productList);
        recycleview.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));
        recycleview.setItemAnimator(new DefaultItemAnimator());
        recycleview.setAdapter(productListAdapter);

        productListAdapter.setOnCheckedChangeListener(new ProductListAdapter.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(int position, boolean isChecked) {
                ProductList product = productList.get(position);
                product.setPIsnew(isChecked);

                Gson gson = new Gson();
                updateProductList.put(product.getPid(),gson.toJson(product));

            }
        });

        productListPresenter = new ProductListPresenterImpl(this);
        productListPresenter.getProductData();
    }

    @Override
    public void showProgress() {
        Common_Utils.showProgress(context);
    }

    @Override
    public void getProductList(List<ProductList> productLists) {
        Common_Utils.hideProgress();
        if (productList != null && !productList.isEmpty()) {
            productList.clear();
        }

        productList.addAll(productLists);
        productListAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String errMsg) {
        Common_Utils.hideProgress();
    }

    @Override
    public void showInternetError() {
        Common_Utils.hideProgress();
        Common_Utils.showToast(AppConstant.no_internet_connection);
    }

    @OnClick(R.id.btn_save)
    public void updateProductData(){

        //our api is not supported to update the data but we show updated json in toast view

        if(!updateProductList.isEmpty()) {
            JSONObject obj = new JSONObject(updateProductList);
            try {
                JSONArray array=new JSONArray("["+obj.toString()+"]");

                Common_Utils.showToast(array.toString());

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else
            Common_Utils.showToast("You don't have any changes to save.");



    }
}
