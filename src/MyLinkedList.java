import java.util.Iterator;

/**
 * Date 3/23/18
 * Developer: Arshak Tovmasyan
 */
public class MyLinkedList<E> extends MyAbstractList<E> {

    private Node<E> head;

    private Node<E> tail;

    public MyLinkedList(){

    }

    public MyLinkedList(E[] objects){
        super(objects);
    }

    /**
     *Add element to the begginning of the list
     * @param e
     */
    public void addFirst(E e){
        Node<E> newNode = new Node<>(e);
        newNode.next = this.head;
        this.head=newNode;
        this.size++;
        if (this.tail==null){
            this.tail=this.head;
        }
    }

    /**
     *Add element to the end of the list
     * @param e
     */
    public void addLast(E e){
        Node<E> newNode = new Node<>(e);
        if (tail==null){
            head=tail=newNode;
        } else {
            tail.next=newNode;
            tail=tail.next;
        }
        size++;
    }

    /**
     *Get the first element in the list
     * @return E
     */
    public E getFirst(){
        if (size==0){
            return null;
        } else
        return head.element;
    }

    /**
     *Get the last element in the list
     * @return E
     */
    public E getLast(){
        if (size==0){
            return null;
        } else
        return tail.element;
    }

    /**
     *Remove the head node from the list and return the object that contain remove node
     * @return E
     */
    public E removeFirst(){
        if (size==0){
            return null;
        } else {
            Node<E> temp = head;
            head=head.next;
            size--;
            if (head==null){
                tail=null;
            }
            return temp.element;
        }
    }

    /**
     *Remove the last node from list and return the object that contain remove node
     * @return E
     */
    public E removeLast(){
        if (size==0){
            return null;
        } else if (size==1){
            Node<E> temp = tail;
            head = tail=null;
            size--;
            return temp.element;
        } else {
            Node<E> current = head;
            for(int i = 0; i < size-2; i++){
                current=current.next;
            }
            Node<E> temp = tail;
            tail = current;
            tail.next = null;
            size--;
            return temp.element;
        }
    }

    /**
     *Inserts element in to the list at the specified index
     * @param index
     * @param e
     */
    @Override
    public void add(int index, E e) {
        if (index == 0){
            addFirst(e);
        } else if (index>=size){
            addLast(e);
        } else {
            Node<E> current = head;
            for (int i = 0; i<index; i++){
                current=current.next;
            }
            Node<E> temp = current.next;
            current.next = new Node<>(e);
            (current.next).next=temp;
            size++;
        }
    }

    /**
     *Clear the list
     */
    @Override
    public void clear() {
        size=0;
        head=tail=null;
    }

    /**
     *Check if this list contains the element e return true
     * @param e
     * @return boolean
     */
    @Override
    public boolean contains(E e) {
        Node<E> current = head;
        for(int i = 0; i<size; i++){
            current = current.next;
            if (current.element.equals(e)){
                return true;
            }
        }
        return false;
    }

    /**
     *Get the element of the specified index
     * @param index
     * @return E
     */
    @Override
    public E get(int index) {
        if (index<0 || index>=size){
            return null;
        } else if (index==0){
            return getFirst();
        } else if (index == size-1){
            return getLast();
        } else {
            Node<E> previouse = head;
            for (int i = 0; i<size-2; i++){
                previouse = previouse.next;
            }
            return previouse.element;
        }
    }

    /**
     *Check if this list contains element e return index if this list does not exist element e return -1
     * @param e
     * @return int
     */
    @Override
    public int indexOf(E e) {
        Node<E> current = head;
        for (int i = 0; i< size; i++){
            if (current.element.equals(e)){
                return i;
            }
            current=current.next;
        }
        return -1;
    }

    /**
     *Check if this list contains element e return last index of e
     * @param e
     * @return int
     */
    @Override
    public int lastIndexOf(E e) {
        int index = 0;
        Node node = head;
        for (int i = 0; i < size; i++){
            if (node.element.equals(e)){
                index=i;
            }
            node=node.next;
        }
        return index;
    }

    /**
     *Remove the element at the specified position in this list and return the element that was removed from list
     * @param index
     * @return
     */
    @Override
    public E remove(int index) {
        if (index<0 || index>=size){
            return null;
        } else if (index==0){
            removeFirst();
        } else if (index == size-1){
            removeLast();
        } else {
            Node<E> previous = head;
            for(int i = 0; i<index; i++){
                previous=previous.next;
            }
            Node<E> current = previous.next;
            previous.next=current.next;
            size--;
            return current.element;
        }
        return null;
    }

    /**
     *Replace the element the specified position in this list with the specified element
     * @param index
     * @param e
     * @return
     */
    @Override
    public Object set(int index, E e) {
        if (index<0 || index>=size){
            return null;
        } else if (index==0){
            head.element=e;
        } else if (index==size-1){
            tail.element=e;
        } else {
            Node<E> current = head;
            for (int i = 0; i < index; i++){
                current = current.next;
                if (i == index-1){
                    current.element=e;
                }
            }
            return current.element;
        }
        return null;
    }

    public String toString(){
        StringBuilder result = new StringBuilder("[");
        Node<E> current = head;
        for (int i = 0; i<size; i++){
            result.append(current.element);
            current=current.next;
            if (current!=null){
                result.append(",");
            }
        }
        result.append("]");
        return result.toString();
    }

    /**
     *
     * @return
     */
    @Override
    public Iterator<E> iterator() {
        return new MyLinkedListIterator<>();
    }

    /**
     *
     * @param <E>
     */
    private static class Node<E>{
        private E element;
        private  Node<E> next;

        public Node(E element) { this.element = element;}
    }

    private class MyLinkedListIterator<E> implements Iterator<E>{
        // TODO why intellij impose explicit cast, Current and Head have the same type
        private Node<E> current = (Node<E>) MyLinkedList.this.head;

        @Override
        public boolean hasNext() {
            return (current!=null);
        }

        @Override
        public E next() {
            E e = current.element;
            current=current.next;
            return e;
        }
        //TODO chem karum anem harc WILLIAMIN
        @Override
        public void remove() {
            E e = current.element;

        }
    }
}
