package com.github.gbaso.examples.utils;

import java.util.stream.Collector;
import java.util.stream.Collectors;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CollectorUtils {

	public static <T> Collector<T, ?, T> toSingleElement() {
		return Collectors.collectingAndThen(Collectors.toSet(), collection -> {
			if (collection.isEmpty()) {
				throw new IllegalArgumentException("No elements found");
			} else if (collection.size() > 1) {
				throw new IllegalArgumentException("Too many elements: " + collection.size());
			}
			return collection.iterator().next();
		});
	}

}
