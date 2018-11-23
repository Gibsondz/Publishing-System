package Interfaces;
public interface Subject {
	public void registerObserver(Observer o);
	public void remove(Observer o);
	public void notifyAllObservers();
}
