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

import com.google.common.base.Preconditions;

import java.util.List;
import java.util.Random;

public class RandomListObjectStream<T> implements IObjectStream<T> {

    protected final List<T> objects;
    protected final Random random;

    public RandomListObjectStream(List<T> objects) {
        Preconditions.checkNotNull(objects, "objects cannot be null");
        Preconditions.checkArgument(!objects.isEmpty(), "objects cannot be empty");
        this.objects = objects;
        this.random = new Random(System.currentTimeMillis());
    }

    @Override
    public T next() {
        return objects.get(random.nextInt(objects.size()));
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public void close() {

    }
}
