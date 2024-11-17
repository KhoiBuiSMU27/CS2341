Name: Khoi Bui
ID: 49298944

			Prove that the time complexity of search and insert in a Red-Black Tree is O(log n)

1. Height of a Red-Black Tree
	- Black-Height Property: 
		+ Every path from the root to a leaf has the same number of black nodes. Let this number be bh.

	- No Consecutive Red Nodes:
		+ Since no two red nodes can be adjacent, the maximum height of the tree is at most 2 * bh, as red nodes can alternate with                black nodes.

	- Node Count and Black Height:
		+ The minimum number of nodes in a Red-Black Tree with a black height (bh) is given by: n >= 2^(bh) - 1
		+ Therefore, bh <= log2(n + 1)

	- Total Tree Height:
		+ Since the total height of the tree is at most 2 * bh → h <= 2 * log2(n + 1)
		+ Hence, the height of the tree is O(log n).

2. Time Complexity of Search (get)
	- The get method follows a single path from the root to a node. Given that the height of the tree is O(log n), the search operation        traverses at most h nodes.
	- Therefore, search complexity = O(h) = O(log n)

3. Time Complexity of Insert (put)
  * Insertion in a Red-Black Tree consists of two parts:
	- Standard Binary Search Tree Insertion:
		+ Involves traversing a path from the root to the appropriate leaf location, which takes O(log n) due to the logarithmic height.

	- Rebalancing:
		+ Rebalancing involves recoloring and at most 2 rotations (left or right).
		+ Each rotation is a constant-time operation, as it only involves a few pointer adjustments.
		+ Rebalancing only occurs along the path from the inserted node to the root, which is at most O(log n).
		+ Thus, the overall complexity for insertion = O(log n)


						How the tree remains balanced after multiple operations

1. How Balancing Works
	- After insertion or deletion, violations of Red-Black properties (e.g., two consecutive red nodes) are corrected through recoloring       and rotations (left or right).
	- These adjustments ensure the tree remains approximately balanced, with a height of O(log n).

2. Impact on Performance
	- Efficiency: Both search and insertion operate in O(log n) due to the logarithmic height.
	- Stability: The tree avoids becoming skewed, unlike unbalanced binary search trees, ensuring consistent performance even under                       worst-case scenarios.









