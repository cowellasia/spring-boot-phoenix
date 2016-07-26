package coole.co.data.model;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class PagedList<E> implements List<E>  {

  private List<E> list;
  
  private long totalRecords;

  public PagedList(List<E> list) {
    super();
    this.list = list;
  }

  public PagedList(List<E> list, long totalRecords) {
    super();
    this.list = list;
    this.totalRecords = totalRecords;
  }

  @Override
  public boolean add(E element) {
    return list.add(element);
  }

  @Override
  public void add(int index, E element) {
    list.add(index, element);
  }

  @Override
  public boolean addAll(Collection<? extends E> elements) {
    return list.addAll(elements);
  }

  @Override
  public boolean addAll(int index, Collection<? extends E> elements) {
    return list.addAll(index, elements);
  }

  @Override
  public void clear() {
    list.clear();
  }

  @Override
  public boolean contains(Object element) {
    return list.contains(element);
  }

  @Override
  public boolean containsAll(Collection<?> elements) {
    return list.containsAll(elements);
  }

  @Override
  public E get(int index) {
    return list.get(index);
  }

  @Override
  public int indexOf(Object element) {
    return list.indexOf(element);
  }

  @Override
  public boolean isEmpty() {
    return list.isEmpty();
  }

  @Override
  public Iterator<E> iterator() {
    return list.iterator();
  }

  @Override
  public int lastIndexOf(Object element) {
    return list.lastIndexOf(element);
  }

  @Override
  public ListIterator<E> listIterator() {
    return list.listIterator();
  }

  @Override
  public ListIterator<E> listIterator(int index) {
    return list.listIterator(index);
  }

  @Override
  public boolean remove(Object element) {
    return list.remove(element);
  }

  @Override
  public E remove(int index) {
    return list.remove(index);
  }

  @Override
  public boolean removeAll(Collection<?> elements) {
    return list.removeAll(elements);
  }

  @Override
  public boolean retainAll(Collection<?> elements) {
    return list.retainAll(elements);
  }

  @Override
  public E set(int index, E element) {
    return list.set(index, element);
  }

  @Override
  public int size() {
    return list.size();
  }

  @Override
  public List<E> subList(int fromIndex, int toIndex) {
    return list.subList(fromIndex, toIndex);
  }

  @Override
  public Object[] toArray() {
    return list.toArray();
  }

  @SuppressWarnings("unchecked")
  @Override
  public Object[] toArray(Object[] a) {
    return list.toArray(a);
  }

  public long getTotalRecords() {
    return totalRecords;
  }

  public void setTotalRecords(long totalRecords) {
    this.totalRecords = totalRecords;
  }

  public List<E> getList() {
    return list;
  }

  public void setList(List<E> list) {
    this.list = list;
  }

}
