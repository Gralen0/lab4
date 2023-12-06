public interface Observer {
     void update();
     void moveIt(String carName, int x, int y);
     void notifyCarAdd();
     void notifyCarRemove();

}
