package com.wisdomleaf.speakingclockapp.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class SpeakingClockServiceTest {
	
	private final SpeakingClockService speakingClockService = new SpeakingClockService();

	@ParameterizedTest
	@MethodSource("provideCurrentTimeValues")
	void testGetCurrentTimeInWords(LocalDateTime localDateTime, String expectedTimeInWords ) {
		// given, when
		String timeInWords = speakingClockService.getCurrentTimeInWords(localDateTime);
		
		// then
		assertThat(timeInWords).isEqualTo(expectedTimeInWords);
		
	}
	
	private static Stream<Arguments> provideCurrentTimeValues(){
		return Stream.of(
					Arguments.of(
								LocalDateTime.of(2023, 2, 15, 23, 0),
								"It's Twenty Three"
							),
					Arguments.of(
								LocalDateTime.of(2023, 2, 16, 0, 0),
								"It's Midnight"
							),
					Arguments.of(
							LocalDateTime.of(2023, 2, 16, 12, 0),
							"It's Midday"	
						),
					Arguments.of(
							LocalDateTime.of(2023, 2, 16, 8, 34),
							"It's Eight Thirty Four"
						),
					Arguments.of(
							LocalDateTime.of(2023, 2, 16, 11, 34),
							"It's Eleven Thirty Four"
						),
					Arguments.of(
							LocalDateTime.of(2023, 2, 16, 13, 34),
							"It's Thirteen Thirty Four"
						),
					Arguments.of(
							LocalDateTime.of(2023, 2, 16, 14, 34),
							"It's Fourteen Thirty Four"
						)
				);
	}

}
