package com.jiandong.mxmodeladapter;

import java.util.function.Supplier;

import com.jiandong.mxmodeladapter.converter.ModelConverter;
import com.jiandong.mxmodeladapter.converter.Pacs00800108Converter;
import com.jiandong.mxmodeladapter.converter.Pacs00800114Converter;
import org.mapstruct.factory.Mappers;

enum MxTypes {

	PACS00800108("pacs.008.001.08", () -> Mappers.getMapper(Pacs00800108Converter.class)),
	PACS00800114("pacs.008.001.14", () -> Mappers.getMapper(Pacs00800114Converter.class)),
	;

	private final String messageName;

	private final Supplier<? extends ModelConverter<?, ?>> converterSupplier;

	MxTypes(String messageName, Supplier<? extends ModelConverter<?, ?>> converterSupplier) {
		this.messageName = messageName;
		this.converterSupplier = converterSupplier;
	}

	public ModelConverter<?, ?> getConverter() {
		return converterSupplier.get();
	}

	static MxTypes valueOfMessageName(String messageName) {
		String messageNameLowerCase = messageName.toLowerCase();
		for (MxTypes mxType : MxTypes.values()) {
			if (mxType.messageName.equals(messageNameLowerCase)) {
				return mxType;
			}
		}
		throw new IllegalArgumentException("Undefined message Name: " + messageName);
	}
}
