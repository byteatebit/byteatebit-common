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

import com.byteatebit.common.io.IStreamSource;
import org.junit.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class TestConfigurationRepository {

    @Test
    public void testGetConfiguration() throws IOException {
        IConfigurationFactory configurationFactory = mock(IConfigurationFactory.class);
        IConfigurationRepository configurationRepository = ConfigurationRepository.Builder.builder()
                .withConfigurationFactory("mockFactory", configurationFactory)
                .build();
        IStreamSource streamSource = mock(IStreamSource.class);
        configurationRepository.getConfiguration(streamSource, "mockFactory");
        verify(configurationFactory, times(1)).loadConfiguration(streamSource);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUnknownType() throws IOException {
        IConfigurationRepository configurationRepository = ConfigurationRepository.Builder.builder().build();
        configurationRepository.getConfiguration(mock(IStreamSource.class), "unknownType");
    }

}
