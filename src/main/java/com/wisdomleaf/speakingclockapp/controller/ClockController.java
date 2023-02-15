package com.wisdomleaf.speakingclockapp.controller;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wisdomleaf.speakingclockapp.service.SpeakingClockService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ClockController {
	
	private final SpeakingClockService clockService;	
	
	@GetMapping("/currenttime")
	public String getCurrentTime() {
		return clockService.getCurrentTimeInWords(LocalDateTime.now());
	}
}
