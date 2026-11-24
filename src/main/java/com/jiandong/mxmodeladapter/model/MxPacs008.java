package com.jiandong.mxmodeladapter.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

import com.prowidesoftware.swift.model.mx.dic.ChargeBearerType1Code;
import com.prowidesoftware.swift.model.mx.dic.SettlementMethod1Code;

public class MxPacs008 extends MxHeader {

	private String messageId; // "MsgId"

	private OffsetDateTime creationDateTime; // "CreDtTm"

	private SettlementMethod1Code settlementMethod; // "SttlmMtd"

	private List<CreditTransferTransaction> creditTransferTransactions; // "CdtTrfTxInf"

	public static class CreditTransferTransaction {

		private String instructionId; // "InstrId"

		private String endToEndId; // "EndToEndId"

		private String uetr; // "UETR"

		private String currency; // "intrBkSttlmAmt/Ccy"

		private BigDecimal settlementAmount; // "intrBkSttlmAmt/Amt"

		private LocalDate settlementDate; // "IntrBkSttlmDt"

		private ChargeBearerType1Code chargeBearerCode; // "ChrgBr"

		private String chargeCurrency; // "ChrgsInf/Amt"

		private BigDecimal chargeAmount; // "ChrgsInf/Amt"

		private String instructingAgentBIC; // "InstgAgt/FinInstnId/BICFI"

		private String instructedAgentBIC; // "InstdAgt/FinInstnId/BICFI"

		private Customer debtorDetails; // "Dbtr"

		private String DebtorAccountId; // "DbtrAcct/Id/"

		private String DebtorAgentBIC; // "DbtrAgt/FinInstnId/BICFI"

		private Customer creditorDetails; // "Cdtr"

		private String creditorAccountId; // "CdtrAcct/Id/"

		private String CreditorAgentBIC; // "CdtrAgt/FinInstnId/BICFI"

	}

	public class Customer {

		private String name; // "Nm"

		private String townName; // "PstlAdr/TwnNm"

		private String country; // "PstlAdr/Ctry"

		private List<String> addressLine; // "PstlAdr/AdrLine"

	}

}
