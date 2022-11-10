package com.github.gbaso.examples.utils;

import java.util.function.BinaryOperator;

import lombok.experimental.UtilityClass;

@UtilityClass
public class FunctionUtils {

	public static <T> BinaryOperator<T> conflict() {
		return (t1, t2) -> {
			throw new IllegalArgumentException("Conflict: " + t1 + ", " + t2);
		};
	}

}
