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

package com.byteatebit.common.configuration;

import com.byteatebit.common.builder.BaseBuilder;
import com.byteatebit.common.io.IStreamSource;
import com.google.common.base.Preconditions;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConfigurationRepository implements IConfigurationRepository {

    protected final Map<String, IConfigurationFactory> configurationFactoriesByType;

    public ConfigurationRepository(ObjectBuilder builder) {
        this.configurationFactoriesByType = new HashMap<>(builder.configurationFactoriesByType);
    }

    @Override
    public IConfiguration getConfiguration(IStreamSource streamSource, String configurationType) throws IOException {
        Preconditions.checkNotNull(streamSource, "streamSource cannot be null");
        Preconditions.checkNotNull(configurationType, "configurationType cannot be null");
        IConfigurationFactory configurationFactory = configurationFactoriesByType.get(configurationType);
        if (configurationFactory == null)
            throw new IllegalArgumentException("Unknown configuration type: " + configurationType);
        return configurationFactory.loadConfiguration(streamSource);
    }

    public static class Builder extends ObjectBuilder<Builder> {
        @Override
        public Builder self() {
            return this;
        }

        public static Builder builder() {
            return new Builder();
        }
    }

    public static abstract class ObjectBuilder<T extends ObjectBuilder<T>> extends BaseBuilder<T> {

        protected Map<String, IConfigurationFactory> configurationFactoriesByType = new HashMap<>();

        public T withConfigurationFactory(String type, IConfigurationFactory configurationFactory) {
            configurationFactoriesByType.put(type, configurationFactory);
            return self();
        }

        public ConfigurationRepository build() {
            return new ConfigurationRepository(this);
        }
    }

}
