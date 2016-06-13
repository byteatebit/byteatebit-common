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

package com.byteatebit.common.configuration.json;

import com.byteatebit.common.configuration.IConfiguration;
import com.byteatebit.common.configuration.IConfigurationFactory;
import com.byteatebit.common.io.IStreamSource;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class JsonConfigurationFactory implements IConfigurationFactory {

    protected final ObjectMapper objectMapper;

    public JsonConfigurationFactory() {
        objectMapper = new ObjectMapper();
    }

    @Override
    public IConfiguration loadConfiguration(IStreamSource streamSource) throws IOException {
        try (InputStream in = streamSource.getInputStream()) {
            return new JsonConfiguration(objectMapper.readerFor(JsonNode.class).readTree(in), objectMapper);
        }
    }
}
