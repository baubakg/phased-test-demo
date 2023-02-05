package com.my.web.shop;

import com.adobe.campaign.tests.integro.phased.NonInterruptiveEvent;

import java.util.Properties;

public class Gremlins extends NonInterruptiveEvent {
    @Override
    public boolean startEvent() {
        System.out.println("Let's delete everyting!!!!!!");
        ShoppingBasket.importedProperties = new Properties();

        return true;
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public boolean waitTillFinished() {
        return true;
    }
}
