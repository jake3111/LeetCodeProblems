import Node

"""
# Definition for a Node.
class Node:
    def __init__(self, val=None, next=None):
        self.val = val
        self.next = next
"""


class Solution:
    def insert(self, head: 'Optional[Node]', insertVal: int) -> 'Node':
        if not head:
            head = Node.Node(insertVal)
            head.next = head
            return head
        if head is head.next:
            head.next = Node.Node(insertVal, head.next)
            return head

        first_head = head
        while ((head.val > insertVal or head.next.val < insertVal)
            and (head.next.val > head.val or insertVal <= head.val)):
            head = head.next
        # We've found the replacement case
        head.next = Node.Node(insertVal, head.next)
        return first_head

# Helper function to create a circular linked list from a list
def create_circular_linked_list(lst):
    if not lst:
        return None
    nodes = [Node.Node(val) for val in lst]
    for i in range(len(nodes) - 1):
        nodes[i].next = nodes[i + 1]
    nodes[-1].next = nodes[0]
    return nodes[0]

# Helper function to print circular linked list
def print_circular_linked_list(head, count=10):
    if not head:
        print("[]")
        return
    output = []
    current = head
    for _ in range(count):
        output.append(current.val)
        current = current.next
        if current == head:
            break
    print(output)

# Test cases
sol = Solution()

# Test case 1
head1 = create_circular_linked_list([3, 4, 1])
head1 = sol.insert(head1, 2)
print_circular_linked_list(head1)

# Test case 2
head2 = create_circular_linked_list([])
head2 = sol.insert(head2, 1)
print_circular_linked_list(head2)

# Test case 3
head3 = create_circular_linked_list([1])
head3 = sol.insert(head3, 0)
print_circular_linked_list(head3)