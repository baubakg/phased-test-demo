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

public class Provision {
    public static void main(String args[]) {
        Properties importedProperties = new Properties();

        importedProperties.put("phased-shuffledGroup_3_0", "76");
        importedProperties.put("phased-shuffledGroup_2_1", "52");
        importedProperties.put("phased-shuffledGroup_1_2", "47");
        importedProperties.put("phased-shuffledGroup_0_3", "93");

        File priceFile = new File("products.properties");

        try (FileWriter fw = new FileWriter(priceFile)) {

            importedProperties.store(fw, null);

        } catch (IOException e) {

            throw new PhasedTestException("Error when creating file " + priceFile.getPath() + ".", e);
        }

    }
}
