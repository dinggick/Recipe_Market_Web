package com.recipe.pair;

/**
 * One of the team members did not have javafx.util.Pair, so made it
 * @param <T1>
 * @param <T2>
 * @author yonghwan
 */
public class Pair<T1, T2> {
	private final T1 key;
	private final T2 value;
	
	public Pair(T1 key, T2 value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * 
	 * @return T1
	 */
	public T1 getKey() {
		return key;
	}

	/**
	 * 
	 * @return T2
	 */
	public T2 getValue() {
		return value;
	}
}
