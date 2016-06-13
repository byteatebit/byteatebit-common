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

import com.byteatebit.common.configuration.ConfigurationException;
import com.byteatebit.common.configuration.IConfiguration;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonConfiguration implements IConfiguration {

    protected final JsonNode jsonConfig;
    protected final ObjectMapper objectMapper;

    public JsonConfiguration(JsonNode jsonConfig, ObjectMapper objectMapper) {
        this.jsonConfig = jsonConfig;
        this.objectMapper = objectMapper;
    }

    @Override
    public <T> T getConfigObject(String key, Class<T> configClass) {
        JsonNode node = jsonConfig.get(key);
        if (node == null)
            throw new ConfigurationException("Cannot find configuration object with key '" + key + "'");
        try {
            return objectMapper.readerFor(configClass).readValue(node);
        } catch (IOException e) {
            throw new ConfigurationException(e);
        }
    }
}
