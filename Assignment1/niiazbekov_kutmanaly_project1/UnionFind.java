public class UnionFind {

    private int[] root;
    private int[] size;
    private int count;

    public UnionFind(int n)
    {
        if (n < 0)
        {
            throw new IllegalArgumentException();
        } 

        count = n;

        root = new int[n];
        for(int i = 0; i < n; i++)
        {
            root[i] = i;
        }

        size = new int[n];
        for(int i = 0; i < n; i++)
        {
            size[i] = 1;
        }
    }

    public int count()
    {
        return count;
    }

    public boolean connected(int p, int q)
    {
        return find(p) == find(q);
    }

    // Follow the link until reaching root (when p = id[p]), return the root
    public int find(int p)
    {
        while(p != root[p])
        {
            p = root[p];
        }
        return p;
    }

    public void union(int p, int q)
    {
        int rootP = find(p);
        int rootQ = find(q);

        if(rootP == rootQ)
        {
            return;
        }

        if(size[rootP] < size[rootQ])
        {
            root[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }
        else
        {
            root[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
        count--;
    }

}
