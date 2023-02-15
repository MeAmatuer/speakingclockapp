package com.wisdomleaf.speakingclockapp.service;

import static com.wisdomleaf.speakingclockapp.constants.AppConstants.BLANK;
import static com.wisdomleaf.speakingclockapp.constants.AppConstants.MIDDAY;
import static com.wisdomleaf.speakingclockapp.constants.AppConstants.MIDNIGHT;
import static com.wisdomleaf.speakingclockapp.constants.AppConstants.PAST;
import static com.wisdomleaf.speakingclockapp.constants.AppConstants.TIME_PREFIX;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import static com.wisdomleaf.speakingclockapp.constants.AppConstants.*;

@Service
public class SpeakingClockService {

	private final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");

	public String getCurrentTimeInWords(LocalDateTime dateTime) {
		String formattedTime = dateTime.format(TIME_FORMAT);
		return getTimeInWords(formattedTime);
	}

	private String getTimeInWords(String formattedTime) {
		Integer minute = Integer.parseInt(formattedTime.split(":")[1]);
		Integer hour = Integer.parseInt(formattedTime.split(":")[0]);
		StringBuilder time = new StringBuilder(TIME_PREFIX);

		if (hour == 0) {
			if (minute == 0)
				time.append(MIDNIGHT);
			else
				time.append(getMinuteInWords(minute)).append(PAST).append(MIDNIGHT);
		} else if (hour == 12 && minute == 0) {
			time.append(MIDDAY);
		}
		return time.append(getHourInWords(hour)).append(BLANK).append(getMinuteInWords(minute)).toString().trim();
	}

	private String getMinuteInWords(Integer minute) {
		return getNumberInWords(minute, true);
	}

	private String getHourInWords(Integer hour) {
		return getNumberInWords(hour, false);
	}

	private String getNumberInWords(Integer number, Boolean isMinute) {
		if (number == 0) {
			return EMPTY;
		} else if (number < 10) {
			if (isMinute)
				return "o " + NUMS_IN_WORDS[number - 1];
			else
				return NUMS_IN_WORDS[number - 1];
		} else if (number > 9 && number < 14) {
			return NUMS_IN_WORDS[number - 1];
		} else if (number == 15) {
			return FIFTEEN;
		} else if (number > 15 && number < 20 || number == 14) {
			return NUMS_IN_WORDS[number % 10 - 1] + "teen";
		} else if (number > 19 && number < 61) {
			return TENS_NUMS_IN_WORDS[number / 10 - 2] + BLANK + getNumberInWords(number % 10, false);
		} else {
			return EMPTY;
		}
	}
}
