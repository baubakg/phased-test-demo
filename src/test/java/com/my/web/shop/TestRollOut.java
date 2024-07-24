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

import com.adobe.campaign.tests.integro.phased.BeforePhase;
import com.adobe.campaign.tests.integro.phased.Phases;
import com.adobe.campaign.tests.integro.phased.utils.ConfigValueHandler;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class TestRollOut {
    /**
     * We load the prices onto the system
     */
    @BeforeTest
    @BeforePhase(appliesToPhases = { Phases.ASYNCHRONOUS, Phases.CONSUMER, Phases.PRODUCER})
    public void loadDB() {
        ConfigValueHandler.PHASED_TEST_DETECT_ORDER.activate("true");
        ShoppingBasket.loadPriceDB();
    }

    /**
     * Reset the system for normal tests
     */
    @BeforeSuite
    @BeforePhase(appliesToPhases = { Phases.NON_PHASED})
    public void resetDB() {
        System.out.println("Provision system");
        ConfigValueHandler.PHASED_TEST_DETECT_ORDER.activate("true");

        Provision.resetSystem();
        ShoppingBasket.loadPriceDB();
    }

}
