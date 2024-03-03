package tdd.filtered;

@FunctionalInterface
public interface Filter {

    boolean applyFilter(Integer element);
}
