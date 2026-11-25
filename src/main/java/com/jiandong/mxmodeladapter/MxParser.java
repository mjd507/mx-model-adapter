package com.jiandong.mxmodeladapter;

import com.jiandong.mxmodeladapter.converter.ModelConverter;
import com.jiandong.mxmodeladapter.model.IModel;
import com.prowidesoftware.swift.model.mx.AbstractMX;

public final class MxParser {

	@SuppressWarnings("unchecked")
	public static IModel parse(String xml) {
		AbstractMX abstractMX = AbstractMX.parse(xml);
		String messageName = abstractMX.getAppHdr().messageName();

		MxTypes mxType = MxTypes.valueOfMessageName(messageName);
		var converter = (ModelConverter<AbstractMX, IModel>) mxType.getConverter();

		return converter.convert(abstractMX);
	}

}
