import java.util.Iterator;

public class CircularArray<T> implements Iterable<T> {

	T[] objects;
	int head = 0;

	@SuppressWarnings("unchecked")
	CircularArray(int size) {
		objects = (T[]) new Object[size];
	}

	int convert(int index) {
		if (index < 0) {
			index += objects.length;
		}
		return (head + index) % objects.length;
	}

	void rotate(int shiftRight) {
		head = convert(shiftRight);
	}

	T get(int i) {
		if (i < 0 || i >= objects.length) {
			throw new java.lang.IndexOutOfBoundsException("check your index");
		}
		return objects[convert(i)];
	}

	void set(int i, T object) {
		objects[convert(i)] = object;
	}

	public Iterator<T> iterator() {
		return new CircularArrayIterator<T>(this);
	}

	class CircularArrayIterator<TI> implements Iterator<TI> {
		private int current = -1;
		private TI[] objectsTI;

		public CircularArrayIterator(CircularArray<TI> circularArray) {
			objectsTI = circularArray.objects;
		}

		@Override
		public boolean hasNext() {
			return current < objects.length - 1;
		}

		@Override
		public TI next() {
			current++;
			TI objectTI = (TI) objectsTI[convert(current)];
			return objectTI;
		}
	}

	public static void main(String[] args) {
		CircularArray<String> circularArray = new CircularArray<String>(5);

		for (int i = 0; i < 5; i++) {
			circularArray.set(i, String.valueOf(i + 1));
		}

		for (String s : circularArray) {
			System.out.println(s);
		}
	}
}