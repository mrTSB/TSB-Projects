public interface Collideable<T>{
  double dist(T other);
  boolean didCollide(T other);
}