public class AvlTree<T extends Comparable<T>> {
    public Node<T> root;
    public AvlTree() {
        this.root = null;
    }

    int getHeight(Node<T> N) {
        if (N == null)
            return 0;

        return N.height;
    }

    /*Printing AvlTree in inorder*/
    void print(Node<T> node) {
        if (node == null)
            return;

        print(node.left);
        System.out.print(node.data + " ");
        print(node.right);
    }

    /* Do not edit the code above */
    Node<T> insert(Node<T> node, T data)
    {

        if(!exists(node, data))
        {

            if(node!= null)
            {
                int k = 0;
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
     
                    if(data.compareTo(node.data) < 0)
                    {

                        node.left = insert(node.left, data);
                    }
                    else if(data.compareTo(node.data) > 0)
                    {

                        node.right = insert(node.right, data);
                    }


            }
            else
            {
                node = new Node<T>(data);

                return node;
            }
                if((heightt(node.right) - heightt(node.left)) < -1)
                {
                    if(data.compareTo(node.left.data) > 0)
                    {
                        node.left = r_left(node.left);
                    }
                    node = r_right(node);
                }
                else if(heightt(node.right) -heightt(node.left) > 1)
                {
                    if (data.compareTo(node.right.data) < 0)
                    {
                        node.right = r_right(node.right);
                    }
                    node = r_left(node);
                }
            
            int k = (1 + mxH(heightt(node.left), heightt(node.right)));
            node.height = k;
            return node;
        }
        return node;
    }
    Node<T> removeNode(Node<T> root, T data)
    {
        if (root == null)
        {
            return null;
        }
        if (data.compareTo(root.data) < 0)
        {
            root.left = removeNode(root.left, data);
        }
        else if(data.compareTo(root.data) > 0)
        {
            root.right = removeNode(root.right, data);
            int k = 0;
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
        }
        else
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
            else
            {
                Node<T> tmpNode = root.left;
                while(tmpNode.right != null)
                {
                    tmpNode = tmpNode.right;
                }
                int k = 0;
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
                root.data = tmpNode.data;
                root.left = removeNode(root.left, tmpNode.data);
            }
        }
        if (root == null)
        {
            return null;
        }
        else
        {
    
            if(heightt(root.right) - heightt(root.left) < -1)
            {
                if(heightt(root.right) > heightt(root.left))
                {
                    root.left = r_left(root.left);
                    if (root.left.height > 1250)
                    {
                        // parent = r_left(node);
                        root.left = root.left.right;
                    }
                }
                root = r_right(root);
                int kth = (1 + mxH(heightt(root.left), heightt(root.right)));
            }
            else if(((heightt(root.right) - heightt(root.left)) > 1))
            {
                if (heightt(root.right) < heightt(root.left))
                {
                    root.right = r_right(root.right);
                    if (root.right.height > 1250)
                    {
                        // parent = r_left(node);
                        root.right = root.right.left;
                    }
                }
                root = r_left(root);
                int kre = (1 + mxH(heightt(root.left), heightt(root.right)));
            }
            else
            {
                int kwe = (1 + mxH(heightt(root.left), heightt(root.right)));
            }
            int k = (1 + mxH(heightt(root.left), heightt(root.right)));
            root.height = k;
            return root;
        }

    }
    private int mxH(int leftTree, int rightTree)
    {  
        if(leftTree > rightTree)
        {
            return leftTree;
        }
        return rightTree;
    }  
    private Node<T> r_right(Node<T> node)
    {
        Node<T> parent = node.left;
        node.left = parent.right;
        parent.right = node;
        node.height = 1 + mxH(heightt(node.left), heightt(node.right));
        parent.height = 1 + mxH(heightt(parent.left), heightt(parent.right));
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
        node.height = 1 + mxH(heightt(node.left), heightt(node.right));
        parent.height = 1 + mxH(heightt(parent.left), heightt(parent.right));
        if (node.height > 1250)
        {
            parent = r_left(node);
            node = node.left;
        }
        return parent;
    }

    private int heightt(Node<T> N_ptr)
    {
        if (N_ptr != null)
        {
            return N_ptr.height;

        }
        return -1;
    }

    private Boolean exists(Node<T> node, T data_ptr)
    {

        while (node != null)
        {

            if (data_ptr.equals(node.data))
            {

                return true;
            }
            else if (data_ptr.compareTo(node.data) < 0)
            {

                node = node.left;
            }
            else if (data_ptr.compareTo(node.data) > 0)
            {

                node = node.right;
            }
            else
            {

                node = node.right;
            }

        }
        return false;
    }

}