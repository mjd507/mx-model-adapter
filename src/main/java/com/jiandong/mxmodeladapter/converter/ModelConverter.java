package com.jiandong.mxmodeladapter.converter;

import com.jiandong.mxmodeladapter.model.IModel;
import com.prowidesoftware.swift.model.mx.AbstractMX;

public interface ModelConverter<S extends AbstractMX, T extends IModel> {

	T convert(S source);

}
