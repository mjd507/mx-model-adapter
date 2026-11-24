package com.jiandong.mxmodeladapter;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.jiandong.mxmodeladapter.converter.ModelConverter;
import com.jiandong.mxmodeladapter.model.IModel;
import com.prowidesoftware.swift.model.mx.AbstractMX;

public final class MxParser {

	private static final Logger log = Logger.getLogger(MxParser.class.getName());

	@SuppressWarnings("unchecked")
	public static IModel parse(String xml) {
		AbstractMX abstractMX = AbstractMX.parse(xml);
		String messageName = abstractMX.getAppHdr().messageName(); // pacs.008.001.14

		MxTypes mxType = MxTypes.valueOfMessageName(messageName);

		Class<?> converterClass = mxType.getConverterClass();

		ModelConverter<AbstractMX, IModel> converter;
		try {
			converter = (ModelConverter<AbstractMX, IModel>) converterClass.getDeclaredConstructor().newInstance();
		}
		catch (Exception e) {
			log.log(Level.SEVERE, "Failed to instantiate converter for message type: " + messageName, e);
			throw new RuntimeException(e);
		}

		return converter.convert(abstractMX);
	}

}
