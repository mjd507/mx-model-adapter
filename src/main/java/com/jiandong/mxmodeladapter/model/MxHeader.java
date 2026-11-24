package com.jiandong.mxmodeladapter.model;

import java.time.OffsetDateTime;

public record MxHeader(String sender,
					   String receiver,
					   String reference, //  "BizMsgIdr"
					   OffsetDateTime creationDate,  // "CreDt"
					   boolean duplicate  // "PssblDplct"
) {

}
