/*
 *
 *  * Copyright (C) 2014 Antonio Leiva Gordillo.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.lalitp.myapplication.UserInterface.ProductList;


import com.lalitp.myapplication.Pojo.ProductList.ProductList;
import com.lalitp.myapplication.Utils.Common_Utils;

import java.util.List;

public class ProductListPresenterImpl implements ProductListPresenter, ProductListInteractor.ViewChangeListener {

    private ProductListView productListView;
    private ProductListInteractor productListInteractor;

    public ProductListPresenterImpl(ProductListView loginView) {
        this.productListView = loginView;
        this.productListInteractor = new ProductListInteractorImpl();
    }

    @Override
    public void getProductData() {

        if(productListView!=null)
            productListView.showProgress();

        if (Common_Utils.isNetworkAvailable()) {
            productListInteractor.getProductList(this);
        } else {
            if (productListView != null)
                productListView.showInternetError();
        }

    }

    @Override
    public void getProductList(List<ProductList> productLists) {
        if (productListView != null) {
            productListView.getProductList(productLists);
        }
    }

    @Override
    public void onError(String errMsg) {
        if (productListView != null) {
            productListView.showError(errMsg);
        }
    }

}
