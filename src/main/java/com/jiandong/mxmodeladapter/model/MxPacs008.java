package com.jiandong.mxmodeladapter.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

public record MxPacs008(
		MxHeader mxHeader,
		String messageId, // "MsgId"
		OffsetDateTime creationDateTime, // "CreDtTm"
		String settlementMethod, // "SttlmMtd"
		List<CreditTransferTransaction> creditTransferTransactions // "CdtTrfTxInf"
) implements IModel {

	public record CreditTransferTransaction(
			String instructionId, // "InstrId"
			String endToEndId, // "EndToEndId"
			String uetr, // "UETR"
			String currency, // "intrBkSttlmAmt/Ccy"
			BigDecimal settlementAmount, // "intrBkSttlmAmt/Amt"
			LocalDate settlementDate, // "IntrBkSttlmDt"
			String chargeBearerCode, // "ChrgBr"
			List<ChargeInformation> chargeInformation, // "ChrgsInf"
			String instructingAgentBIC, // "InstgAgt/FinInstnId/BICFI"
			String instructedAgentBIC, // "InstdAgt/FinInstnId/BICFI"
			Customer debtorDetails, // "Dbtr"
			Customer creditorDetails // "Cdtr"
	) {

	}

	public record ChargeInformation(
			String chargeCurrency, // "Amt"
			BigDecimal chargeAmount // "Amt"
	) {

	}

	public record Customer(
			String accountId,
			String agentBic,
			String name, // "Nm"
			String townName, // "PstlAdr/TwnNm"
			String country, // "PstlAdr/Ctry"
			List<String> addressLine // "PstlAdr/AdrLine"
	) {

	}

}
