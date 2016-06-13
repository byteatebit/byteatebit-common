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

package com.byteatebit.common.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class TestCollectionsUtil {

    @Test
    public void testCdrEmptyList() {
        Assert.assertTrue(CollectionsUtil.cdr(new ArrayList<>()).isEmpty());
    }

    @Test
    public void testCdrSingletonList() {
        Assert.assertTrue(CollectionsUtil.cdr(Arrays.asList("item1")).isEmpty());
}

    @Test
    public void testMultiValuedList() {
        Assert.assertEquals(Arrays.asList("item2"), CollectionsUtil.cdr(Arrays.asList("item1", "item2")));
    }
}
