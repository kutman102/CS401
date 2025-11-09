package Assignment1;

public class UnionFind {

    private int[] ID;
    private int[] size;
    private int count;

    public UnionFind(int N)
    {
        count = N;
        ID = new int[N];
        for(int i = 0; i < N; i++)
        {
            ID[i] = i;
        }
        size = new int[N];
        for(int i = 0; i < N; i++)
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

    public int find(int p)
    {
        while(p != ID[p])
        {
            p = ID[p];
        }
        return p;
    }

    public void union(int p, int q)
    {
        int i = find(p);
        int j = find(q);

        if(i == j)
        {
            return;
        }

        if(size[i] < size[j])
        {
            ID[i] = j;
            size[j] += size[i];
        }
        else
        {
            ID[j] = i;
            size[i] += size[j];
        }
        count--;
    }

}
