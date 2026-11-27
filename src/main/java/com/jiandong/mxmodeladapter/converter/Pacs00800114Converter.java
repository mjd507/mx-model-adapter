package com.jiandong.mxmodeladapter.converter;

import java.util.Optional;

import com.jiandong.mxmodeladapter.model.MxHeader;
import com.jiandong.mxmodeladapter.model.MxPacs008;
import com.prowidesoftware.swift.model.mx.AppHdr;
import com.prowidesoftware.swift.model.mx.MxPacs00800114;
import com.prowidesoftware.swift.model.mx.dic.BranchAndFinancialInstitutionIdentification8;
import com.prowidesoftware.swift.model.mx.dic.CashAccount40;
import com.prowidesoftware.swift.model.mx.dic.Charges16;
import com.prowidesoftware.swift.model.mx.dic.CreditTransferTransaction73;
import com.prowidesoftware.swift.model.mx.dic.GenericAccountIdentification1;
import com.prowidesoftware.swift.model.mx.dic.PartyIdentification272;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface Pacs00800114Converter extends ModelConverter<MxPacs00800114, MxPacs008> {

	@Override
	@Mapping(target = "mxHeader", source = "appHdr")
	@Mapping(target = "messageId", source = "FIToFICstmrCdtTrf.grpHdr.msgId")
	@Mapping(target = "creationDateTime", source = "FIToFICstmrCdtTrf.grpHdr.creDtTm")
	@Mapping(target = "settlementMethod", source = "FIToFICstmrCdtTrf.grpHdr.sttlmInf.sttlmMtd")
	@Mapping(target = "creditTransferTransactions", source = "FIToFICstmrCdtTrf.cdtTrfTxInf")
	MxPacs008 convert(MxPacs00800114 source);

	@Mapping(target = "sender", expression = "java(source.from())")
	@Mapping(target = "receiver", expression = "java(source.to())")
	@Mapping(target = "reference", expression = "java(source.reference())")
	@Mapping(target = "creationDate", expression = "java(source.creationDate())")
	@Mapping(target = "duplicate", expression = "java(source.duplicate())")
	MxHeader mapHeader(AppHdr source);

	@Mapping(target = "instructionId", source = "pmtId.instrId")
	@Mapping(target = "endToEndId", source = "pmtId.endToEndId")
	@Mapping(target = "uetr", source = "pmtId.UETR")
	@Mapping(target = "currency", source = "intrBkSttlmAmt.ccy")
	@Mapping(target = "settlementAmount", source = "intrBkSttlmAmt.value")
	@Mapping(target = "settlementDate", source = "intrBkSttlmDt")
	@Mapping(target = "chargeBearerCode", source = "chrgBr")
	@Mapping(target = "chargeInformation", source = "chrgsInf")
	@Mapping(target = "instructingAgentBIC", source = "instgAgt.finInstnId.BICFI")
	@Mapping(target = "instructedAgentBIC", source = "instdAgt.finInstnId.BICFI")
	@Mapping(target = "debtorDetails", expression = "java(mapCustomer(source.getDbtr(), source.getDbtrAcct(), source.getDbtrAgt()))")
	@Mapping(target = "creditorDetails", expression = "java(mapCustomer(source.getCdtr(), source.getCdtrAcct(), source.getCdtrAgt()))")
	MxPacs008.CreditTransferTransaction mapTransaction(CreditTransferTransaction73 source);

	@Mapping(target = "chargeCurrency", source = "amt.ccy")
	@Mapping(target = "chargeAmount", source = "amt.value")
	MxPacs008.ChargeInformation mapCharge(Charges16 source);

	@Mapping(target = "accountId", source = "cashAccount")
	@Mapping(target = "agentBic", source = "customerAgent.finInstnId.BICFI")
	@Mapping(target = "name", source = "customer.nm")
	@Mapping(target = "townName", source = "customer.pstlAdr.twnNm")
	@Mapping(target = "country", source = "customer.pstlAdr.ctry")
	@Mapping(target = "addressLine", source = "customer.pstlAdr.adrLine")
	MxPacs008.Customer mapCustomer(PartyIdentification272 customer, CashAccount40 cashAccount,
			BranchAndFinancialInstitutionIdentification8 customerAgent);

	default String mapAccountId(CashAccount40 cashAccount) {
		return Optional.ofNullable(cashAccount)
				.map(CashAccount40::getId)
				.map(id -> Optional
						.ofNullable(id.getIBAN())
						.orElse(Optional.ofNullable(id.getOthr())
								.map(GenericAccountIdentification1::getId)
								.orElse(null)))
				.orElse(null);
	}

}
