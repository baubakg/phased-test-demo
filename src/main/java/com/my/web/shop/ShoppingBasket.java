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

import com.adobe.campaign.tests.integro.phased.PhasedTestException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ShoppingBasket {
    private static Properties importedProperties = new Properties();

    /**
     * Simply loads the active price database
     */
    public static void loadSystem() {
        importedProperties = UpdateDB.loadSystem();
    }
    public static Product searchForProduct(String val) {
        Product p = new Product();
        p.name = val;
        if (importedProperties.containsKey(val)) {
            p.price = Integer.parseInt(importedProperties.getProperty(val));
        } else {
            p.price = 13;
        }

        return p;
    }

    public static void addToShoppingBasket(String val) {
    }

    public static int payForProduct(String val) {
        Product p = new Product();
        p.name = val;
        if (importedProperties.containsKey(val)) {
            p.price = Integer.parseInt(importedProperties.getProperty(val));
        } else {
            p.price = 13;
        }

        return p.price;
    }
}
