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

import java.util.Properties;

public class ShoppingBasket {
    protected static Properties importedProperties = new Properties();

    /**
     * Simply loads the active price database
     */
    public static void loadSystem() {
        importedProperties = UpdateDB.loadSystem();
    }

    /**
     * Search for a product from the database
     * @param val a product name
     * @return the product
     */
    public static Product searchForProduct(String val) {
        Product p = new Product();
        p.name = val;

        p.price = fetchPrice(val);
        return p;
    }

    /**
     * Add item to shopping basket
     * @param val
     */
    public static void addToShoppingBasket(String val) {
    }

    /**
     * The user checks out and pays for the product
     * @param val a product name
     * @return a price
     */
    public static int payForProduct(String val) {
        Product p = new Product();
        p.name = val;

        p.price = fetchPrice(val);
        return p.price;
    }

    /**
     * Fetches the proce for a product
     * @param val
     * @return
     */
    public static int fetchPrice(String val) {
        /*
        if (importedProperties.isEmpty()) {
            System.out.println("System empty. Reloading database.");
            loadSystem();
        }*/

        return (importedProperties.containsKey(val)) ?  Integer.parseInt(importedProperties.getProperty(val)) : 13;
    }
}
