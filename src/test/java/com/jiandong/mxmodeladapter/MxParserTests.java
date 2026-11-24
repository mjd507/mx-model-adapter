package com.jiandong.mxmodeladapter;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

import com.jiandong.mxmodeladapter.model.IModel;
import com.jiandong.mxmodeladapter.model.MxHeader;
import com.jiandong.mxmodeladapter.model.MxPacs008;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MxParserTests {

	@Test
	void testParsePacs00800108() throws IOException {
		String xml;
		try (InputStream input = getClass().getResourceAsStream("/pacs008/pacs00800108.xml")) {
			assertThat(input).as("Test XML resource should exist").isNotNull();
			xml = new String(input.readAllBytes(), StandardCharsets.UTF_8);
		}

		IModel model = MxParser.parse(xml);

		assertThat(model).isInstanceOf(MxPacs008.class);

		MxPacs008 pacs008 = (MxPacs008) model;

		MxHeader mxHeader = pacs008.mxHeader();

		assertThat(mxHeader).extracting(MxHeader::sender, MxHeader::receiver,
						MxHeader::reference, MxHeader::creationDate,
						MxHeader::duplicate)
				.containsExactly("BOTKJPJTXXX", "SMBCJPJTXXX",
						"BIZ202611240001", OffsetDateTime.parse("2026-11-24T17:14:00+09:00"),
						false);

		assertThat(pacs008).extracting(MxPacs008::messageId, MxPacs008::creationDateTime,
						MxPacs008::settlementMethod)
				.containsExactly("MSG202611240001", OffsetDateTime.parse("2026-11-24T17:14:00+09:00"),
						"INDA");

		assertThat(pacs008.creditTransferTransactions())
				.hasSize(1)
				.first()
				.satisfies(tx -> {
					Assertions.assertThat(tx).extracting(
							MxPacs008.CreditTransferTransaction::instructionId,
							MxPacs008.CreditTransferTransaction::endToEndId,
							MxPacs008.CreditTransferTransaction::uetr,
							MxPacs008.CreditTransferTransaction::currency,
							MxPacs008.CreditTransferTransaction::settlementAmount,
							MxPacs008.CreditTransferTransaction::settlementDate,
							MxPacs008.CreditTransferTransaction::chargeBearerCode,
							MxPacs008.CreditTransferTransaction::instructingAgentBIC,
							MxPacs008.CreditTransferTransaction::instructedAgentBIC,
							MxPacs008.CreditTransferTransaction::debtorDetails,
							MxPacs008.CreditTransferTransaction::creditorDetails
					).containsExactly(
							"INSTR202611240001",
							"E2E202611240001",
							"9b0c2b4a-5e3d-4f1a-8c7b-1234567890ab",
							"JPY",
							BigDecimal.valueOf(150000),
							LocalDate.parse("2026-11-24"),
							"SHAR",
							"BOTKJPJTXXX",
							"SMBCJPJTXXX",
							new MxPacs008.Customer(
									"1234567",
									"BOTKJPJTXXX",
									"TARO YAMADA",
									"CHIYODA-KU, TOKYO",
									"JP",
									List.of()),
							new MxPacs008.Customer(
									"7654321",
									"SMBCJPJTXXX",
									"HANAKO SUZUKI",
									"SHIBUYA-KU, TOKYO",
									"JP",
									List.of())
					);
				});
	}

}
