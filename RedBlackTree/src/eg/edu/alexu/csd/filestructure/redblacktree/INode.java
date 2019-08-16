/*
 * Decompiled with CFR 0.139.
 */
package eg.edu.alexu.csd.filestructure.redblacktree;

public interface INode<T extends Comparable<T>, V> {
    public static final boolean RED = true;
    public static final boolean BLACK = false;

    public void setParent(INode<T, V> var1);

    public INode<T, V> getParent();

    public void setLeftChild(INode<T, V> var1);

    public INode<T, V> getLeftChild();

    public void setRightChild(INode<T, V> var1);

    public INode<T, V> getRightChild();

    public T getKey();

    public void setKey(T var1);

    public V getValue();

    public void setValue(V var1);

    public boolean getColor();

    public void setColor(boolean var1);

    public boolean isNull();
}

