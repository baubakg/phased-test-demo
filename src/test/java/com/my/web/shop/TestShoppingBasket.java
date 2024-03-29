/*
 * MIT License
 *
 * © Copyright 2022 Adobe. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.my.web.shop;

import com.adobe.campaign.tests.integro.phased.PhasedTest;
import com.adobe.campaign.tests.integro.phased.PhasedTestManager;
import org.testng.Assert;
import org.testng.annotations.Test;

@PhasedTest
@Test
public class TestShoppingBasket {

    //The user searches for a product
    public void searchForProduct(String val) {
        Product myProduct = ShoppingBasket.searchForProduct(val);

        PhasedTestManager.produce("productPrice",Integer.toString(myProduct.price));

    }

    //The user adds it to the shopping basket
    public void addToShoppingBasket(String val) {
        ShoppingBasket.addToShoppingBasket(val);
    }

    //The user pays for the product
    public void payForProduct(String val) {
        int searchedPrice = Integer.parseInt(PhasedTestManager.consume("productPrice"));
        int paidPrice = ShoppingBasket.payForProduct(val);
        Assert.assertTrue(paidPrice != -1,"Our price should not be the default price");
        Assert.assertEquals(paidPrice,searchedPrice,"We should have the same price as before");
    }

}
