/*
 * Copyright 2022 Adobe
 * All Rights Reserved.
 *
 * NOTICE: Adobe permits you to use, modify, and distribute this file in
 * accordance with the terms of the Adobe license agreement accompanying
 * it.
 */
package com.my.web.shop;

import com.adobe.campaign.tests.integro.phased.Mutational;
import com.adobe.campaign.tests.integro.phased.PhasedTestManager;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class ShoppingCartDemo extends Mutational {
    public void loginToSite(String val) {
        PhasedTestManager.produce("authToken","123456");
        System.out.println("ShoppingCartDemo.loginToSite "+val);
    }

    public void searchProduct(String val) {
        Product myProduct = ShoppingBasket.searchForProduct(val);

        PhasedTestManager.produce("product",val);
        PhasedTestManager.produce("productPrice",Integer.toString(myProduct.price));
        System.out.println("ShoppingCartDemo.searchProduct "+val+ "  price is "+myProduct.price);

    }

    public void addProductToCart(String val) {
        String productId = PhasedTestManager.consume("product");
        System.out.println("ShoppingCartDemo.addProductToCart "+val);

        ShoppingBasket.addToShoppingBasket(productId);
        PhasedTestManager.produce("cart","cart"+val);
    }

    public void checkout(String val) {
        PhasedTestManager.consume("authToken");
        PhasedTestManager.consume("cart");
        String productId = PhasedTestManager.consume("product");
        System.out.println("ShoppingCartDemo.checkout "+val);

        int searchedPrice = Integer.parseInt(PhasedTestManager.consume("productPrice"));
        int paidPrice = ShoppingBasket.payForProduct(productId);

        Assert.assertTrue(paidPrice != -1,"Our price should not be the default price");
        Assert.assertEquals(paidPrice,searchedPrice,"We should have the same price as before");
    }
}
