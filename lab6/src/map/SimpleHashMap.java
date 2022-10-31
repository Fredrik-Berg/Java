package map;

import java.util.Random;

import map.Map.Entry;

public class SimpleHashMap<K, V> implements Map<K, V> {
	private Entry<K, V>[] map;
	private double loadFactor;
	private boolean rehashing;
	private int capacity;
	private int size;

	public SimpleHashMap(int capacity) {
		map = (Entry<K, V>[]) new Entry[capacity];
		rehashing = false;
		loadFactor = 0.75;
		size = 0;
		this.capacity = capacity;
	}

	public SimpleHashMap() {
		this(16);
	}

	public String show() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < map.length; i++) {
			Entry<K, V> e = map[i];
			sb.append(i + " ");
			while (e != null) {
				sb.append(" " + e.toString());
				e = e.next;
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	@Override
	public V get(Object object) {
		K key = (K) object;
		if (find(index(key), key) != null) {
			return find(index(key), key).getValue();
		} else {
			return null;
		}

	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public V put(K key, V value) {
		rehashIfNeeded();
		if (find(index(key), key) == null) {
			size++;
			if (map[index(key)] == null) {
				map[index(key)] = new Entry<K, V>(key, value);
				return null;
			} else {
				Entry<K, V> e = map[index(key)];
				while (e.next != null) {
					e = e.next;
				}
				e.next = new Entry<K, V>(key, value);
				return null;
			}
		} else {
			return find(index(key), key).setValue(value);
		}

	}

	private void rehashIfNeeded() {
		if ((double) size() / capacity > loadFactor) {
			if (!rehashing) {
				rehash();
			}
		}
	}

	private void rehash() {
		System.out.println(size);
		rehashing = true;
		Entry<K, V>[] temp = map;
		capacity *= 2;
		size = 0;
		map = (Entry<K, V>[]) new Entry[capacity];
		for (Entry<K, V> e : temp) {
			while (e != null) {
				put(e.getKey(), e.getValue());
				e = e.next;
			}
		}
		rehashing = false;
	}

	@Override
	public V remove(Object obj) {
		K key = (K) obj;
		// finns inte
		if (find(index(key), key) != null) {
			size--;
			// först
			Entry<K, V> e = map[index(key)];
			if (e.next == null && e.getKey().equals(key)) {
				V temp = e.getValue();
				map[index(key)] = null;
				return temp;
			}
			// mitten
			while (e.next != null) {
				if (e.next.getKey().equals(key)) {
					if (e.next.next != null) {
						V temp = e.getValue();
						e.next = e.next.next;
						return temp;
					} else {
						// sist
						V temp = e.next.getValue();
						e.next = null;
						return temp;
					}
				}
				e = e.next;
			}
		}
		return null;
		
	}

	@Override
	public int size() {
		return size;
	}

	private int index(K key) {
		return Math.abs(key.hashCode() % capacity);
	}

	private Entry<K, V> find(int index, K key) {
		Entry<K, V> e = map[index(key)];
		if (e == null) {
			return null;
		} else if (e.getKey().equals(key)) {
			return e;
		}
		while (e.next != null) {
			e = e.next;
			if (e.getKey().equals(key)) {
				return e;
			}
		}
		return null;
	}

	private static class Entry<K, V> implements Map.Entry<K, V> {
		private K key;
		private V value;
		private Entry<K, V> next;

		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		@Override
		public V setValue(V value) {
			V temp = this.value;
			this.value = value;
			return temp;
		}

		public String toString() {
			return key + " = " + value;
		}
	}
	
	public static void main(String[] args){
		SimpleHashMap<Integer, Integer> map = new SimpleHashMap<Integer, Integer>(10);
		for(int i = 0; i < 100; i++){
			Random rand = new Random();
			int rand1 = rand.nextInt(1000) - 500;
			map.put(rand1, rand1);
		}
		System.out.println(map.show());
	}
}
