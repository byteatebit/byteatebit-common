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

package com.byteatebit.common.io;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class TestAbsoluteClasspathStreamSource {

    @Test
    public void testGetInputStream() throws IOException {
        IStreamSource streamSource = new AbsoluteClasspathStreamSource("io/classpath_resource.txt");
        try (InputStream in = streamSource.getInputStream()) {
            String message = IOUtils.toString(in, StandardCharsets.UTF_8);
            Assert.assertEquals("Sample classpath resource", message);
        }
    }
}
