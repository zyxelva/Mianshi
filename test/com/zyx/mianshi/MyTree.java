package com.zyx.mianshi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import com.sun.jmx.remote.internal.ArrayQueue;

public class MyTree<T>
{
	private TreeNode<?> node;

	private static class TreeNode<T>
	{
		T data;
		private TreeNode<?> left;
		private TreeNode<?> right;

		public TreeNode(T t)
		{
			this.data = t;
			this.left = null;
			this.right = null;
		}
	}

	public void buildBTree(List<T> list)
	{
		node = buildBTree(list, 0);
	}

	public TreeNode<T> buildBTree(List<T> list, int index)
	{
		TreeNode<T> myNode = null;
		if (index < list.size())
		{
			T t = list.get(index);
			myNode = new TreeNode<T>(t);
			myNode.left = buildBTree(list, 2 * index + 1);
			myNode.right = buildBTree(list, 2 * index + 2);
		}
		return myNode;

	}

	public void preOrderPrint(TreeNode<?> tn)
	{
		if (tn == null)
		{
			return;
		}
		System.out.print(tn.data + "\t");
		preOrderPrint(tn.left);
		preOrderPrint(tn.right);
	}

	public void inOrderPrint(TreeNode<?> tn)
	{
		if (tn == null)
		{
			return;
		}

		inOrderPrint(tn.left);
		System.out.print(tn.data + "\t");
		inOrderPrint(tn.right);
	}

	public void postOrderPrint(TreeNode<?> tn)
	{
		if (tn == null)
		{
			return;
		}

		postOrderPrint(tn.left);
		postOrderPrint(tn.right);
		System.out.print(tn.data + "\t");
	}

	public void levelOrderPrint(TreeNode<?> tn)
	{
		if (null == tn)
		{
			return;
		}
		ArrayQueue<TreeNode> q = new ArrayQueue<TreeNode>(15);
		q.add(tn);
		q.add(null);
		while (!q.isEmpty())
		{
			TreeNode t = q.remove(0);
			if (t == null)
			{
				if (q.isEmpty())
				{
					break;
				}
				q.add(null);
				System.out.println();
				continue;
			}
			else
			{
				System.out.print(t.data + "\t");
			}
			if (t.left != null)
			{
				q.add(t.left);
			}
			if (t.right != null)
			{
				q.add(t.right);
			}

		}
	}

	public void preOrderByNonRec(TreeNode tn)
	{
		if (null == tn)
		{
			return;
		}
		Stack<TreeNode> stack = new Stack<>();
		stack.push(tn);
		while (!stack.isEmpty())
		{
			TreeNode t = stack.pop();
			System.out.print(t.data + "\t");
			if (t.right != null)
			{
				stack.push(t.right);
			}
			if (t.left != null)
			{
				stack.push(t.left);
			}
		}
	}

	public void inOrderByNonRec(TreeNode tn)
	{
		if (null == tn)
		{
			return;
		}
		Stack<TreeNode> stack = new Stack<>();
		TreeNode t = tn;
		while (null != t || !stack.isEmpty())
		{
			while (null != t)
			{
				stack.push(t);
				t = t.left;
			}

			if (!stack.isEmpty())
			{
				t = stack.peek();
				System.out.print(t.data + "\t");
				stack.pop();
				t = t.right;
			}
		}

	}

	public void postOrderByNonRec(TreeNode tn)
	{
		if (null == tn)
		{
			return;
		}
		Stack<TreeNode> stack = new Stack<>();
		TreeNode current = null;
		TreeNode pre = null;
		stack.push(tn);

		while (!stack.isEmpty())
		{
			current = stack.peek();
			// 已经是叶子节点，或者左右子树不全为空，且前一个访问节点是当前节点的左或者右节点，则输出，出栈
			if ((current.left == null && current.right == null)
					|| (pre != null && (pre == current.left || pre == current.right)))
			{
				System.out.print(current.data + "\t");
				stack.pop();
				pre = current;
			}
			else
			{
				// 还有左右子节点，则先将右节点入栈，再将左节点入栈，保证出栈顺序满足后序遍历要求
				if (current.right != null)
				{
					stack.push(current.right);
				}
				if (current.left != null)
				{
					stack.push(current.left);
				}
			}
		}
	}

	public int getDepth(TreeNode tn)
	{
		if (tn == null)
		{
			return 0;
		}
		return getMax(getDepth(tn.left), getDepth(tn.right)) + 1;
	}

	public int getMax(int left, int right)
	{
		return left > right ? left : right;
	}

	public boolean isCompleteTree(TreeNode tn)
	{
		ArrayQueue<TreeNode> q = new ArrayQueue<TreeNode>(15);
		TreeNode t;

		q.add(tn);
		while ((t = q.remove(0)) != null)
		{
			q.add(t.left);
			q.add(t.right);
		}
		while (!q.isEmpty())
		{
			t = q.remove(0);
			if (null != t)
			{
				return false;
			}
		}
		return true;
	}

	public int getKthNodesNumber(TreeNode tn, int k)
	{
		if (null == tn || k < 0)
		{
			return 0;
		}
		if (k == 1)
		{
			return 1;
		}
		return getKthNodesNumber(tn.left, k - 1) + getKthNodesNumber(tn.right, k - 1);
	}

	public int getLeafNodesNumber(TreeNode tn)
	{
		if (null == tn)
		{
			return 0;
		}
		if (tn.left == null && tn.right == null)
		{
			return 1;
		}
		return getLeafNodesNumber(tn.left) + getLeafNodesNumber(tn.right);
	}

	public boolean isAVLTree(TreeNode tn)
	{
		if (null == tn)
		{
			return true;
		}
		if (!isAVLTree(tn.left) || !isAVLTree(tn.right))
		{
			return false;
		}
		int diff = Math.abs(getDepth(tn.left) - getDepth(tn.right));
		if (diff > 1)
		{
			return false;
		}
		return true;
	}

	public static void main(String[] args)
	{
		String[] shuzu = { "1", "3", "4", "7", "2", "6", "8", "9", "0", "5" };
		List<String> list = new ArrayList<String>();
		list = Arrays.asList(shuzu);

		System.out.println("++++++++输出数组元素++++++++++++++");
		for (String obj : list)
		{
			System.out.print(obj + "\t");
		}

		System.out.println();

		System.out.println("++++++++++++++构建二叉树+++++++++++++");
		MyTree mt = new MyTree();
		mt.buildBTree(list);

		System.out.println();
		System.out.println("+++++++++++++++前序遍历++++++++++++++");
		mt.preOrderPrint(mt.node);

		System.out.println();
		System.out.println("+++++++++++++++ 中序遍历++++++++++++++");
		mt.inOrderPrint(mt.node);

		System.out.println();
		System.out.println("+++++++++++++++  后序遍历++++++++++++++");
		mt.postOrderPrint(mt.node);

		System.out.println();
		System.out.println("+++++++++++++++  分层遍历++++++++++++++");
		mt.levelOrderPrint(mt.node);

		System.out.println();
		System.out.println("+++++++++++++++  前序遍历（非递归）++++++++++++++");
		mt.preOrderByNonRec(mt.node);

		System.out.println();
		System.out.println("+++++++++++++++  中序遍历（非递归）++++++++++++++");
		mt.inOrderByNonRec(mt.node);

		System.out.println();
		System.out.println("+++++++++++++++  后序遍历（非递归）++++++++++++++");
		mt.postOrderByNonRec(mt.node);

		System.out.println();
		System.out.println("+++++++++++++++  二叉树的深度 ++++++++++++++");
		System.out.println("depth=" + mt.getDepth(mt.node));

		System.out.println();
		System.out.println("+++++++++++++++  完全二叉树的判断 ++++++++++++++");
		System.out.println("Complete Binary Tree: " + mt.isCompleteTree(mt.node));

		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		TreeNode<Integer> t1 = new TreeNode<Integer>(10);
		TreeNode<Integer> t2 = new TreeNode<Integer>(6);
		TreeNode<Integer> t3 = new TreeNode<Integer>(14);

		t1.left = t2;
		t2.right = t3;
		mt.levelOrderPrint(t1);
		System.out.println("Complete Binary Tree: " + mt.isCompleteTree(t1));

		System.out.println();
		System.out.println("+++++++++++++++  获取第 K 层的节点数 ++++++++++++++");
		System.out.println("Kth Layer's Nodes: " + mt.getKthNodesNumber(mt.node, 2));

		System.out.println();
		System.out.println("+++++++++++++++  获取叶子节点数 ++++++++++++++");
		System.out.println(" Leaf Nodes: " + mt.getLeafNodesNumber(mt.node));

		System.out.println();
		System.out.println("+++++++++++++++  是否是平衡二叉树 ++++++++++++++");
		System.out.println("  Is a balance tree: " + mt.isAVLTree(mt.node));
		System.out.println("  Is a balance tree: " + mt.isAVLTree(t1));
	}

}
