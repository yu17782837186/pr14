import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class HeapSee {
    class TreeNode {
        char val;
        TreeNode left;
        TreeNode reight;

        public TreeNode(char val) {
            this.val = val;
            this.left = null;
            this.reight = null;
        }
    }
    //根据字符串创建二叉树  递归  先序递归
    public int i;
    TreeNode createTestTree(String s) {
        TreeNode root = null;
        //1. 如果对应字符不是#，那么创建一个根节点
        if (s.charAt(i)!='#') {
            root = new TreeNode(s.charAt(i));
            i++;
            root.left = createTestTree(s);
            root.reight = createTestTree(s);
        }else {  //2. 如果是#，那么直接下标++
            i++;
        }
        return root;
    }
    // 节点个数
    int getSize(TreeNode root) {
        if(root == null) {
            return 0;
        }
        return getSize(root.left) + getSize(root.reight)+1;
    }
    //叶子节点个数
    int getLeafSize(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null &&root.reight == null) {
            return 1;
        }
        return getLeafSize(root.left)+getLeafSize(root.reight);
    }
    //第k层节点的个数
    int getKleafSize(TreeNode root,int k) {
        if (root == null) {
            return 0;
        }
        if (k == 1) {
            return 1;
        }
        return getKleafSize(root.left,k-1)
                +getKleafSize(root.reight,k-1);
    }

    //查找，依次在二叉树的根，
    TreeNode find(TreeNode root,int value) {
        if (root == null ) {
            return null;
        }
        if (root.val == value) {
            return root;
        }
        TreeNode r = find(root.left,value);
        if (r != null) {
            return r;
        }
        r = find(root.reight,value);
        if (r != null) {
            return r;
        }
        return null;
    }
    //二叉树的高度
    int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = height(root.left);
        int reightHeight = height(root.reight);
        return leftHeight > reightHeight ?
                leftHeight+1 : reightHeight+1;
    }

    //二叉树的前序遍历非递归
    void binaryTreePrevOrderNonR(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode top = null;
        while(cur !=  null || !stack.empty()){
            while(cur != null) {
                System.out.println(cur.val+" ");
                stack.push(cur);
                cur = cur.left;
            }
            top = stack.pop();
            cur= top.reight;
        }
    }
    //二叉树的中序遍历非递归
    void binaryTreeInOrderNonR(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode top = null;
        while(cur != null || !stack.empty()) {
            while(cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            top = stack.pop();
            System.out.println(top.val+" ");
            cur = top.reight;
        }
    }
    //二叉树的后序遍历非递归
    void binaryTreePostOrderNonR(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode top = null;
        TreeNode pre = null;
        while(cur !=  null || !stack.empty()){
            while(cur != null) {
                System.out.println(cur.val+" ");
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.peek();
            if (cur.reight == null || cur.reight == pre) {
                System.out.println(cur.val+" ");
                stack.pop();
                pre = cur; //cur已经打印
                cur = null;
            }else {
                cur = cur.reight;
            }
        }
    }
    //二叉树的前序遍历递归
    void binaryTreePrevOrder(TreeNode  root){
        if (root!= null) {
            return;
        }
        System.out.println(root.val+" ");
        binaryTreePrevOrder(root.left);
        binaryTreePrevOrder(root.reight);
}
    //二叉树的中序遍历递归
    void binaryTreeInOrder(TreeNode root){
        if (root == null) {
            return;
        }
        binaryTreePrevOrder(root.left);
        System.out.println(root.val+" ");
        binaryTreeInOrder(root.reight);
    }
    //二叉树的后序遍历递归
    void binaryTreePostOrder(TreeNode root){
        if (root == null) {
            return;
        }
        binaryTreePrevOrder(root.left);
        binaryTreeInOrder(root.reight);
        System.out.println(root.val+" ");
    }
    //二叉树的层序遍历
    void binaryTreeLevelOder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        if(root != null) {
            queue.offer(root); //add  offer的区别
        }

        while(!queue.isEmpty()) {
            TreeNode cur = queue.peek();
            System.out.println(cur.val+"");
            queue.poll();
            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.reight != null) {
                queue.offer(cur.reight);
            }
        }
        System.out.println();
    }
    //判断一棵树是否是完全二叉树 返回0代表是完全二叉树
    int binaryTreeComplete(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.offer(root);
        }
        while(!queue.isEmpty()) {
            TreeNode cur = queue.peek();
            queue.poll();
            if (cur != null) {
                queue.offer(cur.left);
                queue.offer(cur.reight);
            }else {
                break;
            }
        }
        // 出队只要不是空 就说明不是完全二叉树l
        while(!queue.isEmpty()) {
            TreeNode cur = queue.peek();
            if (cur != null) {
                return -1; //不是完全二叉树
            }else {
                queue.poll();
            }
        }
        return 0;
    }
    // 检查两个树是否相同  oj
    public boolean isSameTree(TreeNode p,TreeNode q) {
        if (p == null && q != null) {
            return false;
        }
        if (p!= null && q == null) {
            return false;
        }
        if (p == null && q == null) {
            return true;
        }
        if (p.val != q.val) {
            return false;
        }
        return isSameTree(p.left,q.left)
                && isSameTree(p.reight,q.reight);
    }
    // 判断是否一个树是另一个树的子树  oj
    public boolean isSubTree(TreeNode m,TreeNode n) {
        if (m == null && n == null) {
            return false;
        }
        if (isSameTree(m,n)) {
            return true;
        }
        if (isSubTree(m.left,n)) {
            return  true;
        }
        if (isSubTree(m.reight,n)) {
            return true;
        }
        return false;
    }
    //判断一个树是否是镜像二叉树  oj
    public boolean isSymmetriChild(TreeNode leftChild,
                                   TreeNode reightChild) {
        if (leftChild == null && reightChild != null) {
            return false;
        }
        if (leftChild != null && reightChild == null) {
            return false;
        }
        if(leftChild == null && reightChild == null) {
            return true;
        }
        return leftChild.val == reightChild.val &&
                isSymmetriChild(leftChild.left,reightChild.reight)
                && isSymmetriChild(leftChild.reight,reightChild.left);
    }
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetriChild(root.left,root.reight);
    }

    //根据二叉树创建字符串
    //二叉树的最近公共祖先
    public TreeNode lowestCommonAncestor(TreeNode root,
                                         TreeNode p,TreeNode q) {
        if (root == null) {
            return  null;
        }
        if (root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode reight = lowestCommonAncestor(root.reight,p,q);
        if (left != null && reight != null) {
            return  root;
        }
        if (left == null && reight != null) {
            return reight;
        }
        if (left!= null && reight == null) {
            return left;
        }
        return null;
         t
    }

}
