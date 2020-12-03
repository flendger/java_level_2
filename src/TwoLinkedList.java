public class TwoLinkedList {
    private Node head;
    private Node tail;
    private int size;

    public void add(String val) {
        if (head == null) {
            head = new Node(val);
            tail = head;
            size++;
            return;
        }

        Node newNode = new Node(val);
        newNode.setPrev(tail);
        tail.setNext(newNode);
        tail = newNode;
        size++;
    }

    public void add(int index, String val) {
        if (index > size) {
            throw new IndexExceedsSizeLengthException(String.format("Index %s cannot be more than real size %s", index, size));
        }

        if (index == 0) {
            if (head == null) {
                add(val);
            } else {
                head.setValue(val);
            }
            return;
        }

        int currentIndex = 0;
        Node current = head;
        do {
            current = current.getNext();
            currentIndex++;
        } while (currentIndex != index);

        if (current == null) {
            add(val);
        } else {
            current.setValue(val);
        }
    }

    public int size() {
        return size;
    }

    public Iterator iterator() {
        return new Iterator(head);
    }

    @Override
    public String toString() {
        Node current = head;
        StringBuilder stringBuilder = new StringBuilder();
        while (current != null) {
            stringBuilder.append(current);
            current = current.next;
            if (current != null) {
                stringBuilder.append("; ");
            }
        }
        return "TwoDirectionalList{" +
                "head=" + stringBuilder.toString() +
                '}';
    }

    private static class Node {
        private String value;
        private Node next;
        private Node prev;

        public Node(String value) {
            this(value, null, null);
        }

        public Node(String value, Node next, Node prev) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value='" + value + '\'' +
                    ", next=" + ((next == null)? "null":next.getValue()) +
                    ", prev=" + ((prev == null)? "null":prev.getValue()) +
                    '}';
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }
    }

    public static class Iterator {
        private Node head;
        private Node current;

        private Iterator() {
        }

        private Iterator(Node current) {
            this.head = current;
        }

        public boolean hasNext() {
            if (current == null) {
                return head != null;
            }
            return current.getNext() != null;
        }

        public String next() {
            if (current == null) {
                current = head;
            } else {
                current = current.getNext();
            }
            return current.getValue();
        }
    }
}
