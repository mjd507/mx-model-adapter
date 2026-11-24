package com.jiandong.mxmodeladapter;

import com.jiandong.mxmodeladapter.converter.Pacs00800108Converter;
import com.jiandong.mxmodeladapter.converter.Pacs00800114Converter;

enum MxTypes {

	PACS00800114("pacs.008.001.14", Pacs00800114Converter.class),
	PACS008("pacs.008", Pacs00800108Converter.class) // default for pacs.008.*.*
	;

	private final String messageName;

	private final Class<?> converterClass;

	MxTypes(String messageName, Class<?> converterClass) {
		this.messageName = messageName;
		this.converterClass = converterClass;
	}

	public Class<?> getConverterClass() {
		return converterClass;
	}

	static MxTypes valueOfMessageName(String messageName) {
		String messageNameLowerCase = messageName.toLowerCase();
		for (MxTypes mxType : MxTypes.values()) {
			if (mxType.messageName.equals(messageNameLowerCase)) {
				return mxType;
			}
		}
		String[] split = messageNameLowerCase.split("\\.");
		String simplifiedMessageName = split[0] + "." + split[1];
		for (MxTypes mxType : MxTypes.values()) {
			if (mxType.messageName.equals(simplifiedMessageName)) {
				return mxType;
			}
		}
		throw new IllegalArgumentException("Undefined message Name: " + messageName);
	}
}
