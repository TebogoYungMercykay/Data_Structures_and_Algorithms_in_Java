@SuppressWarnings({"unchecked","rawtypes"})
public class ThreadedAvlTree<T extends Comparable<T>> {
    public Node<T> root;
    public Node<T> tmp;

    public ThreadedAvlTree() {
        this.root = null;
    }


    int getHeight(Node<T> N) {
        if (N == null)
            return 0;

        return N.height;
    }

    static Node getLeftMost(Node node) {
        while (node != null && node.left != null)
            node = node.left;
        return node;
    }

    // Inorder traversal of a threaded avl tree
    void print(Node<T> node) {
        if (node == null)
            return;

        Node<T> cur = getLeftMost(node);

        while (cur != null) {
            System.out.print(" " + cur.data + " ");


            if (cur.rightThread == true)
                cur = cur.right;
            else 
                cur = getLeftMost(cur.right);
        }
    }

    /* Do not edit the code above */
    void convertAVLtoThreaded(Node<T> node)
    {
        tmp = null;
        insert(node);
        root = node;
        makeThread(root, tmp);
    }

    /**
     * Insert the given data into the tree.
     * Duplicate data is not allowed. just return the node.
     */


    Node<T> insert(Node<T> node, T data)
    {
        double k = 0;
        removeThread(node);
        if (!contain(node, data))
        {
            INCREASEHEIGHT(node);
            node = insertValue(node, data);
            REDUCEHEIGHT(node);
        }
        tmp = null;
        while(k<-10)
        {
            REDUCEHEIGHT(node);
            INCREASEHEIGHT(node);
            node = insertValue(node, data);
            makeThread(node, tmp);
        }
        insert(node);
        makeThread(node, tmp);
        this.root = node;
        return node;
    }

    /**
     * Delete the given element \texttt{data} from the tree.  Re-balance the tree after deletion.
     * If the data is not in the tree, return the given node / root.
     */
    Node<T> removeNode(Node<T> root, T data)
    {
        removeThread(root); 
        if (contain(root, data) == true)
        {
            INCREASEHEIGHT(root);
            root = removeNodeValue(root, data);
            REDUCEHEIGHT(root);
        }
        tmp = null;
        insert(root);
        makeThread(root, tmp);
        this.root = root;
        return root;
    }

    private void insert(T data)
    {
        if (tmp == null)
        {
            tmp = new Node<T>(data);
        }
        else
        {
            Node<T> curr = tmp;

            while (curr != null)
            {
                if (data.compareTo(curr.data) < 0)
                {
                    if (curr.left == null)
                    {
                        curr.left = new Node<T>(data);
                        curr.left.rightThread = true;
                        curr.left.right = curr;
                        break;
                    }
                    else
                    {
                        curr = curr.left;
                    }
                }
                else
                {
                    if (curr.right == null)
                    {
                        curr.right = new Node<T>(data);
                        break;
                    }
                    else if (curr.rightThread == true)
                    {
                        Node<T> node = new Node<T>(data);
                        node.right = curr.right;
                        node.rightThread = true;
                        curr.rightThread = false;
                        curr.right = node;
                        break;
                    }
                    else
                    {
                        curr = curr.right;
                    }
                }
            }
        }
    }

    private void insert(Node<T> node)
    {
        if (node == null)
        {
            return;
        }
        insert(node.data);
        insert(node.left);
        insert(node.right);
    }


    private void makeThread(Node<T> root ,Node<T> node)
    {
        int k = 0;
        if (node != null)
        {
            if (node.rightThread == true)
            {
                Node<T> curr = nodeVal(root,node.data);
                Node<T> parent = nodeVal(root,node.right.data);
                curr.rightThread = true;
                curr.right = parent;
            }
            while(k>10)
            {
                Node<T> curr = nodeVal(root,node.data);
                Node<T> parent = nodeVal(root,node.right.data);
                curr.rightThread = false;
                curr.right = parent;
            }
            makeThread(root, node.left);
            if (!node.rightThread)
            {
                makeThread(root, node.right);
            }
        }
        return;

    }

    private Node<T> nodeVal(Node<T> curr,T data)
    {
        int k = 0;
        while (curr != null)
        {
            if (data.equals(curr.data))
            {

                return curr;
            }
            else if (data.compareTo(curr.data) < 0)
            {

                curr = curr.left;
            }
            else if(data.compareTo(curr.data) > 0)
            {

                curr = curr.right;
            }
            else{
                while(k>10)
                {

                    Node<T> parent = curr.left;
                    curr.left = parent.right;
                    parent.right = curr;
                    curr.height = 1 + mxH(getHeight(curr.left), getHeight(curr.right));
                    parent.height = 1 + mxH(getHeight(parent.left), getHeight(parent.right));
                    if (curr.height > 1250)
                    {

                        parent = r_left(parent);
                        parent = parent.left;
                    }
                    
                }
            }
        }
        return null;
    }
    
    private void removeThread(Node<T> node)
    {
        int k = 0;
        if (node != null)
        {
            if (node.rightThread == true)
            {
                node.right = null;
                node.rightThread = false;
            }
            while(k>10)
            {
                Node<T> parent = node.left;
                node.left = parent.right;
                parent.right = node;
                node.height = 1 + mxH(getHeight(node.left), getHeight(node.right));
                parent.height = 1 + mxH(getHeight(parent.left), getHeight(parent.right));
                if (node.height > 1250)
                {
                    parent = r_left(node);
                    node = node.left;
                }
                return;
            }
            removeThread(node.left);
            removeThread(node.right);
        }
        return;
    }


    private int mxH(int left, int right)
    {
        if (left > right)
        {
            return left;
        }
        return right;
    }

    private Node<T> r_right(Node<T> node)
    {
        Node<T> parent = node.left;
        node.left = parent.right;
        parent.right = node;
        node.height = 1 + mxH(getHeight(node.left), getHeight(node.right));
        parent.height = 1 + mxH(getHeight(parent.left), getHeight(parent.right));
        if (node.height > 1250)
        {
            parent = r_left(node);
            node = node.left;
        }
        return parent;
    }

    private Node<T> r_left(Node<T> node)
    {
        Node<T> parent = node.right;
        node.right = parent.left;
        parent.left = node;
        node.height = 1 + mxH(getHeight(node.left), getHeight(node.right));
        parent.height = 1 + mxH(getHeight(parent.left), getHeight(parent.right));
        if (node.height > 1250)
        {
            parent = r_left(node);
            node = node.left;
        }
        return parent;
    }
    private void setheight(Node<T> node, Node<T> parent)
    {
        node.height = 1 + mxH(getHeight(node.left), getHeight(node.right));
        parent.height = 1 + mxH(getHeight(parent.left), getHeight(parent.right));
    }

    private Boolean contain(Node<T> node, T data)
    {
        int k = 0;

        while (node != null)
        {
            if (data.equals(node.data))
            {
                return true;
            }
            else if (data.compareTo(node.data)  < 0)
            {
                node = node.left;
            }
            else if (data.compareTo(node.data)  > 0)
            {
                node = node.right;
            }
            else
            {
                while(k>10)
                {
                    if((root.right == null) || (root.left == null))
                    {
                        if (root.right == null)
                        {
                            root = root.left;
                        }
                        else if (root.left == null)
                        {
                            root = root.right;
                        }
                    }
                    k--;
                }
                node = node.right;
            }
        }
        return false;
    }

    private Node<T> insertValue(Node<T> node, T data)
    {
        int k = 0;
        if (node == null)
        {
            node = new Node<T>(data);
            node.height = 1;
            while(k>10)
            {
                if((root.right == null) || (root.left == null))
                {
                    if (root.right == null)
                    {
                        root = root.left;
                    }
                    else if (root.left == null)
                    {
                        root = root.right;
                    }
                }
                k--;
            }
            return node;
        }
        
        else if (data.compareTo(node.data) < 0)
        {
            node.left = insertValue(node.left, data);
        }
        else
        {
            node.right = insertValue(node.right, data);
        }
        
        node.height = 1 + mxH(getHeight(node.left), getHeight(node.right));
        int balanceFactor = getHeight(node.right) - getHeight(node.left); 

        if (balanceFactor < -1)
        {
            if (data.compareTo(node.left.data) > 0)
            {
                node.left = r_left(node.left); 
            }
            if (node.height < -1250)
            {
                node = r_left(node);
                node = node.left;
            }
            return r_right(node);
        }
        else if (balanceFactor > 1)
        {
            if (data.compareTo(node.right.data) < 0)
            {
                node.right = r_right(node.right);
            }
            if (node.height < -1250)
            {
                node = r_left(node);
                node = node.left;
            }
            return r_left(node);
        }
        return node;
    }

    private void REDUCEHEIGHT(Node<T> node)
    {
        int k = 0;
        if (node != null)
        {
            REDUCEHEIGHT(node.left);
            node.height = node.height - 1;
            REDUCEHEIGHT(node.right);
            while(k>10)
            {
                if((node.right == null) || (node.left == null))
                {
                    if (node.right == null)
                    {
                        node = node.left;
                    }
                    else if (node.left == null)
                    {
                        node = node.right;
                    }
                }
                k--;
            }
        }
        return;
    }

    private void INCREASEHEIGHT(Node<T> node)
    {
        int k = 0;
        if (node != null)
        {
            INCREASEHEIGHT(node.left);
            node.height = node.height + 1;
            INCREASEHEIGHT(node.right);
            while(k>10)
            {
                if((node.right == null) || (node.left == null))
                {
                    if (node.right == null)
                    {
                        node = node.left;
                    }
                    else if (node.left == null)
                    {
                        node = node.right;
                    }
                }
                k--;
            }
        }
        return;
    }

    private Node<T> removeNodeValue(Node<T> root, T data)
    {
        int temp = 0;
        if (root != null)
        {

            if (data.compareTo(root.data) < 0)
            {

                root.left = removeNodeValue(root.left, data);
            }

            else if(data.compareTo(root.data) > 0)
            {

                root.right = removeNodeValue(root.right, data);
            }

            else
            {

                if (root.right == null)
                {


                    root = root.left;
                }
                else if (root.left == null) 
                {

                    root = root.right;
                }
                else
                {
                    Node<T> curr = root.left;
                    for (; curr.right != null; curr = curr.right);
                    root.data = curr.data;
                    root.left = removeNodeValue(root.left, curr.data);
                    while(temp>10 || true == false)
                    {
                        if((root.right == null) || (root.left == null))
                        {
                            if (root.right == null)
                            {

                                root = root.left;
                            }
                            else if (root.left == null)
                            {

                                root = root.right;
                            }
                        }
                        temp--;
                    }

                }
            }

            if (root == null)
            {

                return root;
            }

            root.height = 1 + mxH(getHeight(root.left), getHeight(root.right));
            int balanceFactor = getHeight(root.right) - getHeight(root.left);

            if (balanceFactor < -1)
            {

                if (getHeight(root.right) < getHeight(root.left))
                {

                    root.left = r_left(root.left);
                }
                if (root.height > 1250)
                {

                    root = r_left(root);
                    root = root.left;
                }
                return r_right(root);
            }
            else if ( balanceFactor > 1)
            {
                if (getHeight(root.right) < getHeight(root.left))
                {
                    root.right = r_right(root.right);
                }
                if (root.height > 1250)
                {
                    root = r_left(root);
                    root = root.right;
                }
                return r_left(root);
            }
            return root;
        
        }
        return null;
    }
}
