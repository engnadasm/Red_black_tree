package eg.edu.alexu.csd.filestructure.redblacktree;

import javax.management.RuntimeErrorException;

public class RedBlackTree<T extends Comparable<T>, V> implements IRedBlackTree<T,V> {
/**
 * root is initialized to a dummy node
 */
	private INode <T,V> root  = new Node<T,V>();	
	
	@Override
	public INode<T, V> getRoot() {
		// TODO Auto-generated method stub
		return root;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return root.isNull();
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		//set the root to a dummy node
		root.setKey(null);
		root.setValue(null);
		root.setLeftChild(null);
		root.setRightChild(null);
		root.setColor(INode.BLACK);
	}

	@Override
	public V search(T key) {
		// TODO Auto-generated method stub
		if(key == null) {
			throw new RuntimeErrorException(null);
		}
		INode<T, V> x = getRoot();
		while(!x.isNull() && key.compareTo(x.getKey()) != 0) {
			if(key.compareTo(x.getKey()) < 0) {
				x = x.getLeftChild();
			} else {
				x = x.getRightChild();
			}
		}
		return x.getValue();
	}

	private INode<T, V> searInser(T key) {
		// TODO Auto-generated method stub
		if(key == null) {
			return null;
		}
		INode<T, V> x = getRoot();
		while(!x.isNull() && key.compareTo(x.getKey()) != 0) {
			if(key.compareTo(x.getKey()) < 0) {
				x = x.getLeftChild();
			} else {
				x = x.getRightChild();
			}
		}
		return x;
	}
	
	@Override
	public boolean contains(T key) {
		// TODO Auto-generated method stub
		if (key == null) {
			throw new RuntimeErrorException(null);
		} else {
		V node = search(key);
		if(node != null)
			return true;
		return false;
		}
	}
//	java -jar RedBlackTreeTester.jar
	@Override
	public void insert(T key, V value) {
		// TODO Auto-generated method stub
		if (value == null) {
			throw new RuntimeErrorException(null);
		}
		if (key != null) {
			INode<T,V> node = searInser(key);
			if (!node.isNull()) {
				node.setValue(value);
			} else {
				INode<T,V> n = new Node<T,V>();
				n.setKey(key);
				n.setValue(value);
				n.setLeftChild(node);
				n.setRightChild(node);
				insertNode(n);
			}
			
		} else {
			throw new RuntimeErrorException(null);
		}
	}
	
	private void insertNode(INode<T,V> z) {
		// TODO Auto-generated method stub
		INode<T,V> y = new Node<T,V>();
		INode<T,V> x = getRoot();
		while(!x.isNull()) {
			y = x;
			//int comp = Integer.compare((Integer) z.getKey(), (Integer) x.getKey());
			if (z.getKey().compareTo(x.getKey()) < 0) {
				x = x.getLeftChild();
			} else {
				x = x.getRightChild();
			}
		}
		z.setParent(y);
		if (y.isNull()) {
			root = z;
		} else if (z.getKey().compareTo(y.getKey()) < 0) {
				y.setLeftChild(z);
		} else {
			y.setRightChild(z);
		}
		z.setColor(true);
		insertFix(z);
	}
	
	private void insertFix(INode<T,V> z) {
		// TODO Auto-generated method stub
		while(z.getParent().getColor()) {
			if (z.getParent().equals(z.getParent().getParent().getLeftChild())) {
				INode<T,V> y = z.getParent().getParent().getRightChild();
				if (y.getColor() && !y.isNull()) {
					z.getParent().setColor(false);
					y.setColor(false);
					z.getParent().getParent().setColor(true);
					z = z.getParent().getParent();
				} else if (z.equals(z.getParent().getRightChild())) {
					z = z.getParent();
					leftRotate(z);
				} else {
					z.getParent().setColor(false);
					z.getParent().getParent().setColor(true);
					rightRotate(z.getParent().getParent());
				}
			} else {
				INode<T,V> y = z.getParent().getParent().getLeftChild();
				if (y.getColor() && !y.isNull()) {
					z.getParent().setColor(false);
					y.setColor(false);
					z.getParent().getParent().setColor(true);
					z = z.getParent().getParent();
				} else if (z.equals(z.getParent().getLeftChild())) {
					z = z.getParent();
					rightRotate(z);
				} else {
					z.getParent().setColor(false);
					z.getParent().getParent().setColor(true);
					leftRotate(z.getParent().getParent());
				}
			}
		}
		root.setColor(false);
	}
	
	private void leftRotate(INode<T,V> x) {
		// TODO Auto-generated method stub
		INode<T,V> y = x.getRightChild();
		x.setRightChild(y.getLeftChild());
		if (!y.getLeftChild().isNull()) {
			y.getLeftChild().setParent(x);
		}
		y.setParent(x.getParent());
		if (x.getParent().isNull()) {
			root = y;
		} else if (x.equals(x.getParent().getLeftChild())) {
			x.getParent().setLeftChild(y);
		} else {
			x.getParent().setRightChild(y);
		}
		y.setLeftChild(x);
		x.setParent(y);
	}

	private void rightRotate(INode<T,V> x) {
		// TODO Auto-generated method stub
		INode<T,V> y = x.getLeftChild();
		x.setLeftChild(y.getRightChild());
		if (!y.getRightChild().isNull()) {
			y.getRightChild().setParent(x);
		}
		y.setParent(x.getParent());
		if (x.getParent().isNull()) {
			root = y;
		} else if (x.equals(x.getParent().getLeftChild())) {
			x.getParent().setLeftChild(y);
		} else {
			x.getParent().setRightChild(y);
		}
		y.setRightChild(x);
		x.setParent(y);
	}
	
	@Override
	public boolean delete(T key) {
		// TODO Auto-generated method stub
		if (key == null) {
			throw new RuntimeErrorException(null);
		} else {
		INode<T,V> n = searInser(key);
		if (!n.isNull()) {
			deleteNode(n);
			return true;
		} else {
			return false;
		}
		}
	}

	private void deleteNode(INode<T,V> z) {
		// TODO Auto-generated method stub
		INode<T,V> x = new Node<T,V>();
		INode<T,V> y = z;
		boolean yOrigColor = y.getColor();
		if(z.getLeftChild().isNull()) {
			x = z.getRightChild();
			transplant(z, z.getRightChild());
		} else if (z.getRightChild().isNull()) {
			x = z.getLeftChild();
			transplant(z, z.getLeftChild());
		} else {
			y = minimum(z.getRightChild());
			yOrigColor = y.getColor();
			x = y.getRightChild();
			if (y.getParent().equals(z)) {
				x.setParent(y);
			} else {
				transplant(y, y.getRightChild());
				y.setRightChild(z.getRightChild());
				y.getRightChild().setParent(y);
			}
			transplant(z, y);
			y.setLeftChild(z.getLeftChild());
			y.getLeftChild().setParent(y);
			y.setColor(z.getColor());
		}
		if (!yOrigColor) {
			deleteFix(x);
		}

	}

	private void deleteFix(INode<T, V> x) {
		// TODO Auto-generated method stub
		while (!x.equals(root) && !x.getColor()) {
			if (x.equals(x.getParent().getLeftChild())) {
				INode<T,V> w = x.getParent().getRightChild();
				if (w.getColor()) {
					w.setColor(false);
					x.getParent().setColor(true);
					leftRotate(x.getParent());
					w = x.getParent().getRightChild();
				}
				if (!w.getLeftChild().getColor() && !w.getRightChild().getColor()) {
					w.setColor(true);
					x = x.getParent();
					continue;
				} else if (!w.getRightChild().getColor() && w.getLeftChild().getColor()) {
					w.getLeftChild().setColor(false);
					w.setColor(true);
					rightRotate(w);
					w = x.getParent().getRightChild();
				}
				if (w.getRightChild().getColor()) {
					w.setColor(x.getParent().getColor());
					x.getParent().setColor(false);
					w.getRightChild().setColor(false);
					leftRotate(x.getParent());
					x = getRoot();
				}
				
			} else {
				INode<T,V> w = x.getParent().getLeftChild();
				if (w.getColor()) {
					w.setColor(false);
					x.getParent().setColor(true);
					rightRotate(x.getParent());
					w = x.getParent().getLeftChild();
				}
				if (!w.getRightChild().getColor() && !w.getLeftChild().getColor()) {
					w.setColor(true);
					x = x.getParent();
					continue;
				} else if (!w.getLeftChild().getColor() && w.getRightChild().getColor()) {
					w.getRightChild().setColor(false);
					w.setColor(true);
					leftRotate(w);
					w = x.getParent().getLeftChild();
				}
				if (w.getLeftChild().getColor()) {
					w.setColor(x.getParent().getColor());
					x.getParent().setColor(false);
					w.getLeftChild().setColor(false);
					rightRotate(x.getParent());
					x = getRoot();
				}
				
			}
		}
			x.setColor(false);
	}

	private INode<T, V> minimum(INode<T, V> x) {
		// TODO Auto-generated method stub
		while (!x.getLeftChild().isNull()) {
			x = x.getLeftChild();
		}
		return x;
	}

	private void transplant(INode<T, V> u, INode<T, V> v) {
		// TODO Auto-generated method stub
		if (u.getParent().isNull()) {
			root = v;
		} else if (u.equals(u.getParent().getLeftChild())) {
			u.getParent().setLeftChild(v);
		} else {
			u.getParent().setRightChild(v);
		}
		v.setParent(u.getParent());
	}
	
	public INode<T, V> treeSuccessor(INode<T, V> x){

		// if x.left is not nil, call treeMinimum(x.right) and
		// return it's value
		if (!x.getLeftChild().isNull())
			return minimum(x.getRightChild());

		INode<T, V> y = x.getParent();

		// while x is it's parent's right child...
		while (!y.isNull() && x.equals(y.getRightChild())){
			// Keep moving up in the tree
			x = y;
			y = y.getParent();
		}
		// Return successor
		return y;
	}
}
