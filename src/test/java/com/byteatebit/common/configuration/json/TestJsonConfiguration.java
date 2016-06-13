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

import com.byteatebit.common.configuration.*;
import com.byteatebit.common.io.StreamSource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class TestJsonConfiguration {

    protected IConfigurationRepository configurationRepository;

    @Before
    public void setUp() throws Exception {
        configurationRepository = ConfigurationRepository.Builder.builder()
                .withConfigurationFactory(ConfigTypes.JSON, new JsonConfigurationFactory())
                .build();
    }

    @Test
    public void testValidConfiguration() throws IOException {
        IConfiguration config = configurationRepository.getConfiguration(
                StreamSource.absClasspathStreamSource("configuration/valid_sample_config.json"),
                ConfigTypes.JSON
        );
        SampleConfig expectedConfig1 = new SampleConfig("sample config 1", "This is a sample configuration");
        SampleConfig expectedConfig2 = new SampleConfig("sample config 2", "This is another sample configuration");
        SampleConfig expectedConfig3 = new SampleConfig(null, null);
        SampleConfig config1 = config.getConfigObject("sample1", SampleConfig.class);
        SampleConfig config2 = config.getConfigObject("sample2", SampleConfig.class);
        SampleConfig config3 = config.getConfigObject("minimal", SampleConfig.class);
        Assert.assertEquals(expectedConfig1, config1);
        Assert.assertEquals(expectedConfig2, config2);
        Assert.assertEquals(expectedConfig3, config3);
    }

    @Test(expected = IOException.class)
    public void testUnparseableConfiguration() throws IOException {
        configurationRepository.getConfiguration(
                StreamSource.absClasspathStreamSource("configuration/unparseable_sample_config.json"),
                ConfigTypes.JSON
        );
    }

    @Test(expected = IOException.class)
    public void testNonExistentFile() throws IOException {
        configurationRepository.getConfiguration(
                StreamSource.absClasspathStreamSource("configuration/this_file_does_not_exist.json"),
                ConfigTypes.JSON
        );
    }

    @Test(expected = ConfigurationException.class)
    public void testMissingKey() throws IOException {
        IConfiguration config = configurationRepository.getConfiguration(
                StreamSource.absClasspathStreamSource("configuration/valid_sample_config.json"),
                ConfigTypes.JSON
        );
        config.getConfigObject("thisKeyDoesNotExist", SampleConfig.class);
    }
}
