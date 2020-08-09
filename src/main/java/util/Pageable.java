package util;

public final class Pageable {
    private int index;
    private int size;

    public final int getIndex(){
        return index;
    }
    public final void setIndex(int index){
        this.index = index;
    }
    public final int getSize(){
        return size;
    }
    public final void setSize(int size){
        this.size = size;
    }
}
