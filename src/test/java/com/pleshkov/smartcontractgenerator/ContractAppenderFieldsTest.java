package com.pleshkov.smartcontractgenerator;

import com.pleshkov.smartcontractgenerator.model.ContractParams;
import com.pleshkov.smartcontractgenerator.model.Whitelist;
import com.pleshkov.smartcontractgenerator.service.ContractAppender;
import com.pleshkov.smartcontractgenerator.repo.LibraryLoader;
import com.pleshkov.smartcontractgenerator.service.util.CodeAppender;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class ContractAppenderFieldsTest {

	private final LibraryLoader libraryLoader = new LibraryLoader();

	private final CodeAppender codeAppender = new CodeAppender();

	private ContractAppender appender = new ContractAppender(libraryLoader, codeAppender);

	@Test
	void testMerkleRootField_WhenWhitelistMerkleEnabled(){
		ContractParams params = Mockito.mock(ContractParams.class);
		when(params.getWhitelistType()).thenReturn(Whitelist.MERKLE);
		when(params.getSetWhitelist()).thenReturn(true);

		String result = appender.createFields(params);

		assertTrue(result.contains("merkleRoot"));
		assertFalse(result.contains("whitelist"));
	}

	@Test
	void testWhitelistField_WhenWhitelistMerkleEnabled(){
		ContractParams params = Mockito.mock(ContractParams.class);
		when(params.getWhitelistType()).thenReturn(Whitelist.ARRAY);
		when(params.getSetWhitelist()).thenReturn(true);

		String result = appender.createFields(params);

		assertTrue(result.contains("whitelist"));
		assertFalse(result.contains("merkleRoot"));
	}

	@Test
	void testWhitelistAndMerkleRootField_WhenWhitelistDisabled(){
		ContractParams params = Mockito.mock(ContractParams.class);
		when(params.getSetWhitelist()).thenReturn(false);

		String result = appender.createFields(params);

		assertFalse(result.contains("whitelist"));
		assertFalse(result.contains("merkleRoot"));
	}

	@Test
	void testPremintField_WhenWhitelistEnabled(){
		ContractParams params = Mockito.mock(ContractParams.class);
		when(params.getSetWhitelist()).thenReturn(true);

		String result = appender.createFields(params);

		assertTrue(result.contains("isPremintActive"));
	}


	@Test
	void testPremintField_WhenRevealDisabled(){
		ContractParams params = Mockito.mock(ContractParams.class);
		when(params.getSetReveal()).thenReturn(false);

		String result = appender.createFields(params);

		assertFalse(result.contains("isPremintActive"));
	}

	@Test
	void testNewLineAtTheEnd(){
		ContractParams params = Mockito.mock(ContractParams.class);

		String result = appender.createFields(params);

		assertTrue(result.endsWith("\n"));
	}


	@Test
	void testRequiredFields(){
		ContractParams params = Mockito.mock(ContractParams.class);

		String result = appender.createFields(params);

		assertTrue(result.contains("uri"));
		assertTrue(result.contains("isMintActive"));
		assertTrue(result.contains("mockUri"));
		assertTrue(result.contains("cost"));
		assertTrue(result.contains("supply"));
		assertTrue(result.contains("ownerAddress"));
		assertTrue(result.contains("maxMint"));
	}
}
