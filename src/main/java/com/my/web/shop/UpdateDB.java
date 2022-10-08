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

import java.io.*;
import java.util.Properties;

public class UpdateDB {
    public static void main(String args[]) {
        Properties importedProperties =  loadSystem();

        importedProperties.put("phased-shuffledGroup_2_1","56");

        updateSystem(importedProperties);

    }

    protected static Properties loadSystem() {
        Properties importedProperties;
        File priceFile = new File("products.properties");

        if (!priceFile.exists()) {
            System.out.println("Price file NOT FOUND!!!!");
        }

        try (InputStream input = new FileInputStream(priceFile)) {
            importedProperties = new Properties();
            // load a properties file
            importedProperties.load(input);
        } catch (IOException e) {

            throw new PhasedTestException("Error when loading file " + priceFile.getPath() + ".", e);
        }
        return importedProperties;
    }

    protected static void updateSystem(Properties importedProperties) {
        File priceFile = new File("products.properties");

        try (FileWriter fw = new FileWriter(priceFile)) {

            importedProperties.store(fw, null);

        } catch (IOException e) {

            throw new PhasedTestException("Error when creating file " + priceFile.getPath() + ".", e);
        }
    }
}
