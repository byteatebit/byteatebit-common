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

import com.google.common.base.Preconditions;

import java.io.IOException;
import java.io.InputStream;

public class AbsoluteClasspathStreamSource implements IStreamSource {

    protected final String classpathResource;

    public AbsoluteClasspathStreamSource(String classpathResource) {
        this.classpathResource = Preconditions.checkNotNull(classpathResource, "classpathResource must not be null");
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return getClass().getClassLoader().getResourceAsStream(classpathResource);
    }
}
