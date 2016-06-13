/*
 * Copyright (c) 2016 byteatebit
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.byteatebit.common.stream;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

public class TestListObjectStream {

    @Test
    public void testMultivaluedList() {
        IObjectStream<String> stream = new ListObjectStream<String>(Arrays.asList("item1", "item2", "item3"));
        Assert.assertEquals("item1", stream.next());
        Assert.assertEquals("item2", stream.next());
        Assert.assertEquals("item3", stream.next());
        Assert.assertNull(stream.next());
    }

    @Test
    public void testSingletonList() {
        IObjectStream<String> stream = new ListObjectStream<String>(Arrays.asList("item1"));
        Assert.assertEquals("item1", stream.next());
        Assert.assertNull(stream.next());
    }

    @Test
    public void testEmptyList() {
        IObjectStream<String> stream = new ListObjectStream<String>(Collections.emptyList());
        Assert.assertNull(stream.next());
    }
}
