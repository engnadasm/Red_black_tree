package eg.edu.alexu.csd.filestructure.redblacktree;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;

import javax.management.RuntimeErrorException;

import java.util.Set;

public class TreeMap<T extends Comparable<T>, V> implements ITreeMap<T,V>{
	
	private IRedBlackTree<T, V> tree = new RedBlackTree<T, V>();
	private int size = 0;

	@Override
	public Map.Entry<T, V>  ceilingEntry(T key) {
		// TODO Auto-generated method stub
		if (key == null) {
			throw new RuntimeErrorException(null);
		} else {
		INode<T, V> larger = null;
		if(key != null) {
		INode<T, V>	x = tree.getRoot();
		while(!x.isNull()) {
			int cmp = key.compareTo(x.getKey());
			if(cmp == 0) {
				Map.Entry<T, V> e = new AbstractMap.SimpleEntry<T, V>(x.getKey(), x.getValue());
				return e;
			}
			if(cmp < 0) {
				larger = x;
				x = x.getLeftChild();
			}else {
				x = x.getRightChild();
			}
		}
		}
		if(larger == null || larger.isNull())
		return null;
		Map.Entry<T, V> e = new AbstractMap.SimpleEntry<T, V>(larger.getKey(), larger.getValue());
		return e;
		}
	}

	@Override
	public T ceilingKey(T key) {
		// TODO Auto-generated method stub
		Map.Entry<T, V> e = ceilingEntry(key);
		if(e == null)
			return null;
		return e.getKey();
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		tree.clear();
		size = 0;
	}

	@Override
	public boolean containsKey(T key) {
		// TODO Auto-generated method stub
		if (key == null) {
			throw new RuntimeErrorException(null);
		} else {
		return tree.contains(key);
		}
	}

	@Override
	public boolean containsValue(V value) {
		// TODO Auto-generated method stub
		INode<T, V> root = tree.getRoot();
		if (value == null) {
			throw new RuntimeErrorException(null);
		} else if (root.isNull()){
			throw new RuntimeErrorException(null);
		}else {
		return recursiveSearch(root, value);
		}
		}
	

	private boolean recursiveSearch(INode<T, V> root, V value) {
		// TODO Auto-generated method stub
		if(root.isNull())
		return false;
		if(root.getValue().equals(value)) {
			return true;
		}
		if(!root.getLeftChild().isNull()) {
			boolean find = recursiveSearch(root.getLeftChild(),value);
			if(find) return true;
		}
		if(!root.getRightChild().isNull()) {
			boolean find = recursiveSearch(root.getRightChild(),value);
			if(find) return true;
		}
		return false;
	}

	@Override
	public Set<Map.Entry<T, V>> entrySet() {
		// TODO Auto-generated method stub
		Set<Map.Entry<T, V>> s = new LinkedHashSet<Map.Entry<T, V>>();
		INode<T, V> root = tree.getRoot();
		if(root.isNull()) {
			return null;
		}
		s = inOrderTraverseTree(s, root);
		return s;
	}

	private Set<Entry<T, V>> inOrderTraverseTree(Set<Entry<T, V>> s, INode<T, V> root) {
		// TODO Auto-generated method stub
		if(!root.isNull()) {
			inOrderTraverseTree(s, root.getLeftChild());
			Map.Entry<T, V> e = new AbstractMap.SimpleEntry<T, V>(root.getKey(), root.getValue());
			s.add(e);
			inOrderTraverseTree(s, root.getRightChild());
		}
		return s;
	}

	@Override
	public Map.Entry<T, V> firstEntry() {
		// TODO Auto-generated method stub
		if (!tree.isEmpty()) {
			return  minimum(tree.getRoot());
		} else {
			return null;
		}
	}
	
	private Map.Entry<T, V> minimum(INode<T, V> x) {
		// TODO Auto-generated method stub
		while (!x.getLeftChild().isNull()) {
			x = x.getLeftChild();
		}
		Map.Entry<T, V> entry = new AbstractMap.SimpleEntry<T, V>(x.getKey(), x.getValue());
		return entry;
	}

	@Override
	public T firstKey() {
		// TODO Auto-generated method stub
		if (!tree.isEmpty()) {
			return firstEntry().getKey();
		} else {
			return null;
		}
	}
	

	@Override
	public Map.Entry<T, V> floorEntry(T key) {
		// TODO Auto-generated method stub
		INode<T, V> larger = null;
		if(key == null) {
			throw new RuntimeErrorException(null);
		} else {
		INode<T, V>	x = tree.getRoot();
		while(!x.isNull()) {
			int cmp = key.compareTo(x.getKey());
			if(cmp == 0) {
				Map.Entry<T, V> e = new AbstractMap.SimpleEntry<T, V>(x.getKey(), x.getValue());
				return e;
			}
			if(cmp > 0) {
				larger = x;
				x = x.getRightChild();
			}else {
				x = x.getLeftChild();
				
			}
		}
		}
		if(larger == null || larger.isNull())
		return null;
		Map.Entry<T, V> e = new AbstractMap.SimpleEntry<T, V>(larger.getKey(), larger.getValue());
		return e;
	}
	

	@Override
	public T floorKey(T key) {
		// TODO Auto-generated method stub
		Map.Entry<T, V> e = floorEntry(key);
		if(e == null)
			return null;
		return e.getKey();
	}

	@Override
	public V get(T key) {
		// TODO Auto-generated method stub
		return tree.search(key);
	}

	@Override
	public ArrayList<Map.Entry<T, V>>headMap(T toKey) {
		// TODO Auto-generated method stub
		if(toKey == null) {
			return null;
		}
		ArrayList<Map.Entry<T, V>> a = new ArrayList<Map.Entry<T, V>>();
		INode<T, V> root = tree.getRoot();
		if(root.isNull()) {
			return null;
		}
		a = inOrderTraversekeys(a, root, toKey);
		return a;
	}

	private ArrayList<Map.Entry<T, V>> inOrderTraversekeys(ArrayList<Map.Entry<T, V>> s, INode<T, V> root, T toKey) {
		// TODO Auto-generated method stub
		if(!root.isNull() && root.getKey().compareTo(toKey) < 0) {
			inOrderTraversekeys(s, root.getLeftChild(), toKey);
			Map.Entry<T, V> e = new AbstractMap.SimpleEntry<T, V>(root.getKey(), root.getValue());
			s.add(e);
			inOrderTraversekeys(s, root.getRightChild(), toKey);
		}
		return s;
	}
	@Override
	public ArrayList<Map.Entry<T, V>> headMap(T toKey, boolean inclusive) {
		// TODO Auto-generated method stub
		if(inclusive) {
			if(toKey == null) {
				return null;
			}
			ArrayList<Map.Entry<T, V>> a = new ArrayList<Map.Entry<T, V>>();
			INode<T, V> root = tree.getRoot();
			if(root.isNull()) {
				return null;
			}
			
			return inOrderTraversekeysInclusive(a, root, toKey);
			} else return headMap(toKey);
	}
	private ArrayList<Entry<T, V>> inOrderTraversekeysInclusive(ArrayList<Entry<T, V>> s, INode<T, V> root, T toKey) {
		// TODO Auto-generated method stub
		if(!root.isNull() && root.getKey().compareTo(toKey) <= 0) {
			inOrderTraversekeys(s, root.getLeftChild(), toKey);
			Map.Entry<T, V> e = new AbstractMap.SimpleEntry<T, V>(root.getKey(), root.getValue());
			s.add(e);
			inOrderTraversekeys(s, root.getRightChild(), toKey);
		}
		return s;
	}
	@Override
	public Set<T> keySet() {
		// TODO Auto-generated method stub
		Set<T> s = new LinkedHashSet<T>();
		INode<T, V> root = tree.getRoot();
		if(root.isNull()) {
			return null;
		}
		s = inOrderTraversekeys(s, root);
		return s;
	}

	private Set<T> inOrderTraversekeys(Set<T> s, INode<T, V> root) {
		// TODO Auto-generated method stub
		if(!root.isNull()) {
			inOrderTraversekeys(s, root.getLeftChild());
			s.add(root.getKey());
			inOrderTraversekeys(s, root.getRightChild());
		}
		return s;
	}

	@Override
	public Entry<T,V> lastEntry() {
		// TODO Auto-generated method stub
		if (!tree.isEmpty()) {
			return  maximum(tree.getRoot());
		} else {
			return null;
		}
	}

	private Entry<T,V> maximum(INode<T,V> x) {
		// TODO Auto-generated method stub
		while (!x.getRightChild().isNull()) {
			x = x.getRightChild();
		}
		Map.Entry<T,V> entry = new AbstractMap.SimpleEntry<T,V>(x.getKey(), x.getValue());
		return entry;
	}
	
	@Override
	public T lastKey() {
		// TODO Auto-generated method stub
		if (!tree.isEmpty()) {
			return lastEntry().getKey();
		} else {
			return null;
		}
	}

	@Override
	public Entry<T,V> pollFirstEntry() {
		// TODO Auto-generated method stub
		Entry<T,V> x = firstEntry();
		if (tree.isEmpty()) {
			return null;
		} else {
			if (tree.delete(firstEntry().getKey())) {
				size--;
			}
			return x;
		}
	}

	@Override
	public Entry<T,V> pollLastEntry() {
		// TODO Auto-generated method stub
		Entry<T,V> x = lastEntry();
		if (tree.isEmpty()) {
			return null;
		} else {
			if (tree.delete(lastEntry().getKey())) {
				size--;
			}
			return x;
		}
	}

	@Override
	public void put(T key, V value) {
		// TODO Auto-generated method stub
		if(key != null && !tree.contains(key)) {
	    	size++;
	    }
		tree.insert(key, value);
	}

	
	@Override
	public void putAll(Map<T,V> map) {
		// TODO Auto-generated method stub
		if (map == null) {
			throw new RuntimeErrorException(null);
		} else {
			map.forEach((k,v) -> put((T) k, v));
		}
	}

	@Override
	public boolean remove(T key) {
		// TODO Auto-generated method stub
		if(key != null && !tree.contains(key)) {
	    	size--;
	    }
		return tree.delete(key);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub
		 Collection<V> result = new ArrayList<V>();
		 values(tree.getRoot(), result);
	     return result;
	 }

	    private void values(INode<T,V> x, Collection<V> result) {
	        if (x.isNull())
	            return;
	        values(x.getLeftChild(), result);
	        result.add(x.getValue());
	        values(x.getRightChild(), result);
	    }
	}

