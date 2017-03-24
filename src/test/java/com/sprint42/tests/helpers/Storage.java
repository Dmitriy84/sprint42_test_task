package com.sprint42.tests.helpers;

import java.util.ArrayList;
import java.util.List;

public final class Storage {
	private static List<Object> _data = new ArrayList<Object>();

	public static void add(Object item) {
		_data.add(item);
	}

	public static Object getRandom() {
		Object res = _data.get(Utils.random.nextInt(_data.size()));
		return res;
	}
}