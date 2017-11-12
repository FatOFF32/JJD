package Lessons7andAbove;

public class Item {
    Item next;
    Object value;

    public Item(Object value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        int result = 1;
        if (value != null) result = value.hashCode();
        result = 31 * result;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item that = (Item) o;
        // проверим на заполненность значений, а дальше применим equals самих объектов
        if (this.value == null && that.value == null) return true;
        if (this.value == null || that.value == null) return false;

        return this.value.equals(((Item) o).value);
    }
}
