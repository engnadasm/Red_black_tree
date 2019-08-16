/*
 * Decompiled with CFR 0.139.
 */
package eg.edu.alexu.csd.filestructure.redblacktree;

import eg.edu.alexu.csd.filestructure.redblacktree.INode;

public interface IRedBlackTree<T extends Comparable<T>, V> {
    public INode<T, V> getRoot();

    public boolean isEmpty();

    public void clear();

    public V search(T var1);

    public boolean contains(T var1);

    public void insert(T var1, V var2);

    public boolean delete(T var1);
}

