/**
 * JBoss, Home of Professional Open Source.
 * Copyright 2014-2022 Red Hat, Inc., and individual contributors
 * as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.pnc.common.security;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @author <a href="mailto:matejonnet@gmail.com">Matej Lazar</a>
 */
public class Md5Test {

    @Test
    public void calculateMd5() throws IOException, NoSuchAlgorithmException {
        String encoded = Md5.digest("The quick brown fox jumps over the lazy dog.");
        String expected = "e4d909c290d0fb1ca068ffaddf22cbd0";

        Assertions.assertEquals(32, encoded.length(), "Md5 should be 32 chars long.");
        Assertions.assertEquals(expected, encoded);
    }

    @Test
    public void testWithLeadingZeroInTheSum() throws IOException, NoSuchAlgorithmException {
        String encoded = Md5.digest("363");
        String expected = "00411460f7c92d2124a67ea0f4cb5f85";
        Assertions.assertEquals(expected, encoded);

        encoded = Md5.digest("a");
        expected = "0cc175b9c0f1b6a831c399e269772661";
        Assertions.assertEquals(expected, encoded);
    }

    @Test
    public void addingToMd5() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String expected = "e4d909c290d0fb1ca068ffaddf22cbd0";

        Md5 md5 = new Md5();
        md5.add("The quick brown fox ");
        md5.add("jumps over the lazy dog.");
        String encoded = md5.digest();

        Assertions.assertEquals(32, encoded.length(), "Md5 should be 32 chars long.");
        Assertions.assertEquals(expected, encoded);
    }
}
