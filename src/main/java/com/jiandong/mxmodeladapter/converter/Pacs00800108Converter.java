package com.jiandong.mxmodeladapter.converter;

import java.util.List;
import java.util.Objects;

import com.jiandong.mxmodeladapter.model.MxHeader;
import com.jiandong.mxmodeladapter.model.MxPacs008;
import com.prowidesoftware.swift.model.mx.AppHdr;
import com.prowidesoftware.swift.model.mx.MxPacs00800108;
import com.prowidesoftware.swift.model.mx.dic.ActiveCurrencyAndAmount;
import com.prowidesoftware.swift.model.mx.dic.CashAccount38;
import com.prowidesoftware.swift.model.mx.dic.CreditTransferTransaction39;
import com.prowidesoftware.swift.model.mx.dic.FIToFICustomerCreditTransferV08;
import com.prowidesoftware.swift.model.mx.dic.GroupHeader93;
import com.prowidesoftware.swift.model.mx.dic.PartyIdentification135;
import com.prowidesoftware.swift.model.mx.dic.PaymentIdentification7;

public class Pacs00800108Converter implements ModelConverter<MxPacs00800108, MxPacs008> {

	@Override
	public MxPacs008 convert(MxPacs00800108 source) {
		AppHdr appHdr = source.getAppHdr();
		MxHeader mxHeader = new MxHeader(
				appHdr.from(),
				appHdr.to(),
				appHdr.reference(),
				appHdr.creationDate(),
				appHdr.duplicate()
		);
		FIToFICustomerCreditTransferV08 customerCreditTransferV08 = source.getFIToFICstmrCdtTrf();
		GroupHeader93 grpHdr = customerCreditTransferV08.getGrpHdr();
		List<CreditTransferTransaction39> cdtTrfTxInf = customerCreditTransferV08.getCdtTrfTxInf();
		List<MxPacs008.CreditTransferTransaction> creditTransferTransactions = cdtTrfTxInf.stream()
				.map(tx -> {
					PaymentIdentification7 pmtId = tx.getPmtId();
					ActiveCurrencyAndAmount intrBkSttlmAmt = tx.getIntrBkSttlmAmt();
					PartyIdentification135 dbtr = tx.getDbtr();
					CashAccount38 dbtrAcct = tx.getDbtrAcct();
					PartyIdentification135 cdtr = tx.getCdtr();
					CashAccount38 cdtrAcct = tx.getCdtrAcct();
					return new MxPacs008.CreditTransferTransaction(
							pmtId.getInstrId(),
							pmtId.getEndToEndId(),
							pmtId.getUETR(),
							intrBkSttlmAmt.getCcy(),
							intrBkSttlmAmt.getValue(),
							tx.getIntrBkSttlmDt(),
							tx.getChrgBr().value(),
							tx.getChrgsInf().stream().map(chrgInf -> new MxPacs008.ChargeInformation(
									chrgInf.getAmt().getCcy(),
									chrgInf.getAmt().getValue()
							)).toList(),
							tx.getInstgAgt().getFinInstnId().getBICFI(),
							tx.getInstdAgt().getFinInstnId().getBICFI(),
							new MxPacs008.Customer(
									Objects.requireNonNullElse(dbtrAcct.getId().getIBAN(), dbtrAcct.getId().getOthr().getId()),
									tx.getDbtrAgt().getFinInstnId().getBICFI(),
									dbtr.getNm(),
									dbtr.getPstlAdr().getTwnNm(),
									dbtr.getPstlAdr().getCtry(),
									dbtr.getPstlAdr().getAdrLine()
							),
							new MxPacs008.Customer(
									Objects.requireNonNullElse(cdtrAcct.getId().getIBAN(), cdtrAcct.getId().getOthr().getId()),
									tx.getCdtrAgt().getFinInstnId().getBICFI(),
									cdtr.getNm(),
									cdtr.getPstlAdr().getTwnNm(),
									cdtr.getPstlAdr().getCtry(),
									cdtr.getPstlAdr().getAdrLine()
							)
					);
				})
				.toList();
		return new MxPacs008(mxHeader, grpHdr.getMsgId(), grpHdr.getCreDtTm(), grpHdr.getSttlmInf().getSttlmMtd().value(),
				creditTransferTransactions);
	}

}
