package chapter2.Interface.Subject;

import chapter2.Interface.Observer.Observer;

public interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}
