package com.jiandong.mxmodeladapter.model;

import java.time.OffsetDateTime;

public class MxHeader extends BaseModel {

	private String sender;

	private String receiver;

	private String reference; //  "BizMsgIdr"

	private OffsetDateTime creationDate; // "CreDt"

	private boolean duplicate; // "PssblDplct"

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public OffsetDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(OffsetDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public boolean isDuplicate() {
		return duplicate;
	}

	public void setDuplicate(boolean duplicate) {
		this.duplicate = duplicate;
	}

}
