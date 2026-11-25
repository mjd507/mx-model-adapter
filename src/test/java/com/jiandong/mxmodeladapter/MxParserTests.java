package com.jiandong.mxmodeladapter;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import com.jiandong.mxmodeladapter.model.IModel;
import com.jiandong.mxmodeladapter.model.MxPacs008;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

class MxParserTests {

	static Object[][] xmlResourceProvider() {
		return new Object[][] {
				{"/pacs008/pacs00800108.xml", MxPacs008.class},
				{"/pacs008/pacs00800114.xml", MxPacs008.class}
		};
	}

	@ParameterizedTest
	@MethodSource(value = "xmlResourceProvider")
	void testParse(String xmlResource, Class<? extends IModel> expectedModelClass) throws IOException {

		try (InputStream input = getClass().getResourceAsStream(xmlResource)) {
			assertThat(input).as("Test XML resource should exist").isNotNull();
			String xml = new String(input.readAllBytes(), StandardCharsets.UTF_8);
			IModel model = MxParser.parse(xml);

			assertThat(model).isInstanceOf(expectedModelClass);
		}

	}

}
