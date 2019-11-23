package sample.myClass;

public class Worker {
    private String fio;
    private String password;

    public Worker(String fio, String password) {
        this.fio = fio;
        this.password = password;

    }

    public Worker() {

    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
