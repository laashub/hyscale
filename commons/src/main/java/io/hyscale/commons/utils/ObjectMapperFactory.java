/**
 * Copyright 2019 Pramati Prism, Inc.
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
package io.hyscale.commons.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class ObjectMapperFactory {

	private static ObjectMapper jsonObjectMapper;

	private static ObjectMapper yamlObjectMapper;

	public static ObjectMapper jsonMapper() {
		jsonObjectMapper = new ObjectMapper();
		return defaultConfig(jsonObjectMapper);
	}

	public static ObjectMapper yamlMapper() {
		yamlObjectMapper = new ObjectMapper(new YAMLFactory());
		return defaultConfig(yamlObjectMapper);
	}

	private static ObjectMapper defaultConfig(ObjectMapper objectMapper) {
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
		return objectMapper;
	}
}
