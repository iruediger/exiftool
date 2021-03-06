/**
 * Copyright 2011 The Buzz Media, LLC
 * Copyright 2015-2019 Mickael Jeanroy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.thebuzzmedia.exiftool.commons.iterables;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Static Collection Utilities.
 */
public final class Collections {

	// Ensure non instantiation.
	private Collections() {
	}

	/**
	 * Check if collection is empty ({@code null} or empty).
	 *
	 * @param values Collection of values.
	 * @param <T> Type of element in collection.
	 * @return {@code true} if collection is empty ({@code null} or empty), {@code false} otherwise.
	 */
	public static <T> boolean isEmpty(Collection<T> values) {
		return values == null || values.isEmpty();
	}

	/**
	 * Check if collection is not empty (not {@code null} and not empty).
	 *
	 * @param values Collection of values.
	 * @param <T> Type of element in collection.
	 * @return {@code true} if collection is not empty (not {@code null} and not empty), {@code false} otherwise.
	 */
	public static <T> boolean isNotEmpty(Collection<T> values) {
		return !isEmpty(values);
	}

	/**
	 * Get size of collection.
	 * If collection is empty (null or empty), zero will be returned.
	 *
	 * @param values Collection of values.
	 * @param <T> Type of element in collection.
	 * @return Size of collection.
	 */
	public static <T> int size(Collection<T> values) {
		return values == null ? 0 : values.size();
	}

	/**
	 * Create in memory collection from given iterable elements.
	 *
	 * <ul>
	 *   <li>If {@code iterables} is null, an empty array list is returned.</li>
	 *   <li>If {@code iterables} is already an instance of {@link Collection}, it is returned.</li>
	 *   <li>Otherwise, iterable structure is iterated and loaded into an {@link ArrayList}.</li>
	 * </ul>
	 *
	 * @param iterables Given iterable.
	 * @param <T> Type of element in iterable structure.
	 * @return The collection result.
	 */
	public static <T> Collection<T> toCollection(Iterable<T> iterables) {
		if (iterables == null) {
			return new ArrayList<>(0);
		}

		if (iterables instanceof Collection) {
			return (Collection<T>) iterables;
		}

		List<T> list = new ArrayList<>();
		for (T value : iterables) {
			list.add(value);
		}

		return list;
	}

	/**
	 * Join elements of collection to a final string.
	 * If collection is empty (null or empty), an empty string is returned.
	 *
	 * @param values Collection of values.
	 * @param separator Separator, use to separate each elements.
	 * @param <T> Type of element in collection.
	 * @return Final string.
	 */
	public static <T> String join(Collection<T> values, String separator) {
		if (isEmpty(values)) {
			return "";
		}

		Iterator<T> it = values.iterator();
		StringBuilder sb = new StringBuilder();
		sb.append(it.next());
		while (it.hasNext()) {
			sb.append(separator).append(it.next());
		}

		return sb.toString();
	}

	/**
	 * Map list of inputs to a new list of outputs.
	 *
	 * @param inputs Input list.
	 * @param mapper Mapper used to transform inputs.
	 * @param <T> Type of input.
	 * @param <U> Type of output.
	 * @return New list of outputs.
	 */
	public static <T, U> List<U> map(Collection<T> inputs, Mapper<T, U> mapper) {
		List<U> outputs = new ArrayList<>(inputs.size());
		for (T input : inputs) {
			U output = mapper.map(input);
			outputs.add(output);
		}

		return outputs;
	}

	/**
	 * Add all elements of given iterable structure to given collection.
	 *
	 * @param collection The collection.
	 * @param iterable The iterable structure.
	 * @param <T> Type of elements.
	 */
	public static <T> void addAll(Collection<T> collection, Iterable<T> iterable) {
		if (iterable == null || collection == null) {
			return;
		}

		for (T value : iterable) {
			collection.add(value);
		}
	}
}
