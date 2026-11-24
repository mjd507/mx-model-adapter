package com.jiandong.mxmodeladapter.converter;

import com.jiandong.mxmodeladapter.model.BaseModel;
import com.prowidesoftware.swift.model.mx.AbstractMX;

public interface ModelConverter<S extends AbstractMX, T extends BaseModel> {

	T convert(S source);

}
