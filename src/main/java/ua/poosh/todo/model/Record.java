package ua.poosh.todo.model;

public class Record {

    private String id;

    private String toDo;

    private boolean done;


    public Record() {
    }

    /*public Record(String toDo, boolean done) {
        this.toDo = toDo;
    }*/

    public Record(String toDo, boolean done) {
        this.toDo = toDo;
        this.done = done;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToDo() {
        return toDo;
    }

    public void setToDo(String toDo) {
        this.toDo = toDo;
    }

    public boolean getDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }


    // Object override methods:


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Record record = (Record) o;

        return  toDo != null ? toDo.equals(record.toDo) : record.toDo == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
