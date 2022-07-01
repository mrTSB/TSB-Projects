public interface Collideable<T>
  {
    boolean didCollideLeft(T obj);  
    boolean didCollideRight(T obj);  
    boolean didCollideTop(T obj);  
    boolean didCollideBottom(T obj);  
  }
