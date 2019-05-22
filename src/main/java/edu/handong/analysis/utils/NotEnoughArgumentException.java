package edu.handong.analysis.utils;

@SuppressWarnings("serial")
public class NotEnoughArgumentException extends Exception {

	public NotEnoughArgumentException(String message) {
		super("The file path does not exist. Please check your CLI argument!");
	}

	public NotEnoughArgumentException() {
		super("No CLI argument Exception! Please put a file path.");
	}

}
